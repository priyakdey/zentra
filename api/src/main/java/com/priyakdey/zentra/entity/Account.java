package com.priyakdey.zentra.entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

/**
 * @author Priyak Dey
 */
@Entity
@Table(name = "account")
public class Account implements Serializable {

    @Serial
    private static final long serialVersionUID = -3123772232678437527L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_id_seq_gen")
    @SequenceGenerator(initialValue = 1, allocationSize = 1, name = "account_id_seq_gen",
            sequenceName = "account_id_seq")
    private Integer id;

    @Column
    private String username;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String name;

    @Column
    private boolean isEnabled;

    // TODO: might need a transformer for this
    @Column
    private ZonedDateTime createdAt;

    public Account() {
    }

    public Account(String username, String email, String password, String name) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        // TODO: integrate email service for activating accounts
        this.isEnabled = true;
        this.createdAt = ZonedDateTime.now(Clock.systemUTC());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
