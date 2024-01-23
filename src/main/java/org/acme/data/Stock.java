package org.acme.data;


import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "stock")
public class Stock extends PanacheEntity {
    public String name;

    public String symbol;
}
