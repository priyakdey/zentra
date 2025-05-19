-- Setup database
CREATE DATABASE zentra;
CREATE USER zentra_user WITH PASSWORD 'zentra_password';
GRANT ALL PRIVILEGES ON DATABASE zentra TO zentra_user;

\c zentra;

GRANT USAGE, CREATE ON SCHEMA public TO zentra_user;
ALTER DEFAULT PRIVILEGES IN SCHEMA public
    GRANT SELECT, INSERT, UPDATE, DELETE ON TABLES TO zentra_user;

-- Cleanup
DROP INDEX IF EXISTS idx_account_email;
DROP TABLE IF EXISTS task;
DROP TABLE IF EXISTS account;
DROP SEQUENCE IF EXISTS seq_account_id;
DROP SEQUENCE IF EXISTS seq_task_id;

-- Sequences
CREATE SEQUENCE seq_account_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE seq_task_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

GRANT USAGE, SELECT, UPDATE ON SEQUENCE seq_account_id TO zentra_user;
GRANT USAGE, SELECT, UPDATE ON SEQUENCE seq_task_id TO zentra_user;

-- Tables
CREATE TABLE account
(
    id            INT          NOT NULL DEFAULT nextval('seq_account_id'),
    email         VARCHAR(254) NOT NULL,
    password_hash TEXT         NOT NULL,
    created_at    timestamptz  NOT NULL DEFAULT now(),
    PRIMARY KEY (id)
);

CREATE UNIQUE INDEX idx_account_email ON account (email);

CREATE TABLE task
(
    id                        INT         NOT NULL DEFAULT nextval('seq_task_id'),
    title                     TEXT        NOT NULL,
    is_completed              BOOLEAN     NOT NULL DEFAULT false,
    tentative_completion_date timestamptz,
    created_at                timestamptz NOT NULL DEFAULT (now() at time zone 'utc'),
    completed_at              timestamptz,
    account_id                INT         NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (account_id) REFERENCES account (id)
);

ALTER TABLE account OWNER TO zentra_user;
ALTER TABLE task OWNER TO zentra_user;

ALTER SEQUENCE seq_account_id OWNER TO zentra_user;
ALTER SEQUENCE seq_task_id OWNER TO zentra_user;

ALTER INDEX idx_account_email OWNER TO zentra_user;
