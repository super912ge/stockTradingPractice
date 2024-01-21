package org.acme.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class StockHolding extends PanacheEntity {
    @ManyToOne
    @JoinColumn(name = "stock_id")
    public Stock stock;

    public double currentShare;

    @ManyToOne
    @JoinColumn(name = "portfolio_id")
    public Portfolio portfolio;

}
