package org.acme.model;

import org.acme.data.OrderType;

import java.time.LocalDateTime;

public class OrderRequest {

    public String symbol;

    public double share;

    public LocalDateTime createdTime;

    public OrderType orderType;
}
