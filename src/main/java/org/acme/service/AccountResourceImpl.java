package org.acme.service;

import io.quarkus.security.identity.SecurityIdentity;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import org.acme.data.Account;
import org.acme.data.Order;
import org.acme.data.OrderType;
import org.acme.model.OrderRequest;
import org.acme.model.OrderOut;
import org.acme.resources.AccountResource;
import org.acme.resources.StockService;
import org.acme.security.model.User;

public class AccountResourceImpl implements AccountResource {

    @Inject
    StockService stockService;

    @Inject
    SecurityIdentity identity;

    @Inject

    @Override
    public Account getAccount() {
        String name = identity.getPrincipal().getName();
        User user = User.findByUsername(name);
        if (user == null) {
            throw new NotFoundException("user does not exist");
        }
        return Account.findByUser(user);
    }

    public void createAccount() {
        String name = identity.getPrincipal().getName();
        User user = User.findByUsername(name);
        if (user == null) {
            throw new NotFoundException("user does not exist");
        }
        Account.createAccount(user);
    }

    @Override
    /**
     * CreateOrder saves received order
     * The order are validate prior to saving them
     * * For MARKET_BUY value the current price is fetch and used (validation and order)
     *   * account balance should be sufficient
     *
     * The account balances is not modified until order has been processed
     * The account should be locked during balance modification to prevent concurrent orders issues
     */
    public OrderOut createOrder(OrderRequest in) {
        Account account = getAccount();

        if (OrderType.MARKET_BUY.equals(in.orderType)) {
            Order order = new Order();
            // set up order;
            order.accountId = account.id;
            validateSufficientFund(order);

        }

        return null;
    }

    private void validateSufficientFund(Order order) {
        double currentPrice = stockService.getStockPriceBySymbol(order.symbol);
        double transactionFee = 1;
        double fundRequired = order.share * currentPrice + transactionFee;
        // transaction fee to be determined

        Account account = getAccount();
        if (account.balance < fundRequired) {

            throw new BadRequestException("Not sufficient fund");
        }
    }
}
