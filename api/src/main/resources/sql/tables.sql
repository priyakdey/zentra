create table account
(
    id         serial       not null,
    username   varchar(255) not null,
    password   varchar(255) not null,
    email      varchar(255) not null,
    is_active  boolean      not null default false,
    created_at timestamp             default current_timestamp,
    primary key (id)
);

create table account_activation_token
(
    user_id    int          not null,
    token      varchar(255) not null,
    created_at timestamp default current_timestamp,
    foreign key (user_id) references account (id) on delete cascade
);
