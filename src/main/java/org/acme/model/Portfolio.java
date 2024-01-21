package org.acme.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Portfolio extends PanacheEntity {

    @OneToOne
    @JoinColumn(name = "account_id")
    public Account account;

    @OneToMany
    public List<StockHolding> stockHoldings =  new ArrayList<>();

}
