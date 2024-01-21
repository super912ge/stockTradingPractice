package org.acme.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import org.acme.security.model.User;

import java.time.LocalDateTime;

@Entity
@Table(name = "account")
public class Account extends PanacheEntity {
    public double balance;

    public LocalDateTime created;

    public boolean active;

    // might be multiple accounts per user
    @OneToOne
    @JoinColumn(name = "user_id")
    public User user;

}
