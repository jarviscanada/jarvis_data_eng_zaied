package ca.jrvs.apps.trading.controller;

import ca.jrvs.apps.trading.model.view.PortfolioView;
import ca.jrvs.apps.trading.model.view.TraderAccountView;
import ca.jrvs.apps.trading.service.DashBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    private DashBoardService dashboardService;

    @Autowired
    public DashboardController(DashBoardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/profile/traderId/{traderId}", produces = {
            MediaType.APPLICATION_JSON_UTF8_VALUE})
    public TraderAccountView getAccount(@PathVariable Integer traderId) {
        try {
            return dashboardService.getTraderAccount(traderId);
        } catch (Exception e) {
            throw ResponseExceptionUtil.getResponseException(e);
        }
    }


    @GetMapping(path = "/portfolio/traderId/{traderId}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public PortfolioView getPortfolioView(@PathVariable Integer traderId) {
        try {
            return dashboardService.getProfileViewByTraderId(traderId);
        } catch (Exception e) {
            throw ResponseExceptionUtil.getResponseException(e);
        }
    }
}
