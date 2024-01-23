package org.acme.data;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDateTime;

public class Order extends PanacheEntity {

    public long accountId;

    public String symbol;

    public double share;

    public LocalDateTime createdTime;

    @Enumerated(EnumType.STRING)
    public OrderType orderType;

    @Enumerated(EnumType.STRING)
    public OrderStatus orderStatus;
}
