package ca.jrvs.apps.trading.model.domain;

public class TraderAccountView {

    private Account account;
    private Trader trader;

    public Account getAccount() {
        return account;
    }

    public Trader getTrader() {
        return trader;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setTrader(Trader trader) {
        this.trader = trader;
    }
}
