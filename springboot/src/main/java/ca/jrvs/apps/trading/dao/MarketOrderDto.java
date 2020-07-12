package ca.jrvs.apps.trading.dao;

public class MarketOrderDto {

    private Integer accountId;
    private String size;
    private String ticker;

    public MarketOrderDto(){

    }

    public MarketOrderDto(Integer accountId, String size, String ticker){
        this.accountId = accountId;
        this.size = size;
        this.ticker = ticker;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }
}
