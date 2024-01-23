package org.acme.service;

import org.acme.data.Order;

public interface TransactionService {
    void processOrder(Order order);
}
