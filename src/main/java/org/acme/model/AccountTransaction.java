package org.acme.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
public class AccountTransaction extends PanacheEntity {

    public LocalDateTime created;
    @NotNull
    @Min(0)
    public double price;

    @NotNull
    @Min(0)
    public double share;

    @ManyToOne
    @JoinColumn(name = "account_id")
    public Account account;


    @OneToOne
    @JoinColumn(name = "stock_id")
    public Stock stock;

    @NotNull
    @Min(0)
    public double beforeBalance;

    @NotNull
    @Min(0)
    public double afterBalance;

}
