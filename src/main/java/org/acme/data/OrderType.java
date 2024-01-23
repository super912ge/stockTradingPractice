package org.acme.data;

public enum OrderType {
    MARKET_BUY("Market Buy"),
    MARKET_SELL("Market Sell");

    private final String displayName;

    OrderType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
