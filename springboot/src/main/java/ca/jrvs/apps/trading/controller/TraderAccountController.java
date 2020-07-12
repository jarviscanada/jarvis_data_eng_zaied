package ca.jrvs.apps.trading.controller;

import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Trader;
import ca.jrvs.apps.trading.model.domain.TraderAccountView;
import ca.jrvs.apps.trading.service.TraderAccountService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;

@Controller
@RequestMapping("/trader")
public class TraderAccountController {

    private TraderAccountService traderAccountService;

    @Autowired
    public TraderAccountController(TraderAccountService traderAccountService)
    {
        this.traderAccountService = traderAccountService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @PostMapping(path="/firstname/{firstname}/lastname/{lastname}/dob/{dob}/country/{country}/email/{email}",
            produces = {MediaType.APPLICATION_JSON_VALUE} )
    public TraderAccountView createTrader(@PathVariable String firstname, @PathVariable String lastname,
                                          @PathVariable @DateTimeFormat(pattern = "yyyy-mm-dd") String dob,
                                          @PathVariable String country, @PathVariable String email){
        try{

            Trader trader = new Trader();
            trader.setFirstName(firstname);
            trader.setLastName(lastname);
            trader.setDob(Date.valueOf(dob));
            trader.setCountry(country);
            trader.setEmail(email);
            return traderAccountService.createTraderAndAccount(trader);
        }catch (Exception ex)
        {
            throw ResponseExceptionUtil.getResponseException(ex);
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @PostMapping(path="/",produces={MediaType.APPLICATION_JSON_UTF8_VALUE})
    public TraderAccountView createTrader(@RequestBody Trader trader){
        try{
            return traderAccountService.createTraderAndAccount(trader);
        }catch (Exception ex)
        {
            throw ResponseExceptionUtil.getResponseException(ex);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @DeleteMapping(path = "/traderId/{traderId}")
    public void deleteTrader(@PathVariable Trader trader){
        try{
            traderAccountService.deleteTraderById(trader.getId());
        } catch (Exception ex)
        {
            throw ResponseExceptionUtil.getResponseException(ex);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @PutMapping(path = "/deposit/traderId/{traderId}/amount/{amount}")
    public Account depositFund(@PathVariable Integer traderId, @PathVariable Double amount){
        try {
            return traderAccountService.deposit(traderId,amount);
        }catch (Exception ex)
        {
            throw ResponseExceptionUtil.getResponseException(ex);
        }

    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @PutMapping(path = "/withdraw/traderId/{traderId}/amount/{amount}")
    public Account withdrawFund(@PathVariable Integer traderId, @PathVariable Double amount){
        try {
            return traderAccountService.withdraw(traderId,amount);
        }catch (Exception ex)
        {
            throw ResponseExceptionUtil.getResponseException(ex);
        }

    }


}
