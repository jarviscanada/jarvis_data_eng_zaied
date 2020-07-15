package ca.jrvs.apps.trading.model.view;

import ca.jrvs.apps.trading.model.domain.SecurityRows;

import java.util.List;

public class PortfolioView {

    private List<SecurityRows> secRows;

    public List<SecurityRows> getSecRows() {
        return secRows;
    }

    public void setSecRows(List<SecurityRows> secRows) {
        this.secRows = secRows;
    }
}
