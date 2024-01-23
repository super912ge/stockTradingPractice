package org.acme.data;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.ws.rs.BadRequestException;
import org.acme.security.model.User;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "account")
public class Account extends PanacheEntity {
    public double balance;

    @CreationTimestamp
    public LocalDateTime created;

    public boolean active;

    // might be multiple accounts per user
    @OneToOne
    @JoinColumn(name = "user_id")
    public User user;

    @OneToOne(cascade = CascadeType.ALL)
    public Portfolio portfolio;

    public static Account findByUser(User user) {
        return find("user", user).firstResult();
    }
    public static Account createAccount(User user) {
        // one account per person for now.
        if (findByUser(user) != null) {
            throw new BadRequestException("Account already exist");
        }
        Account account = new Account();
        account.user = user;
        account.portfolio = new Portfolio();
        persist(account);
        return account;
    }
}
