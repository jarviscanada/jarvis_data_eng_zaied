package ca.jrvs.apps.trading.controller;

import ca.jrvs.apps.trading.model.domain.IexQuote;
import ca.jrvs.apps.trading.model.domain.Quote;
import ca.jrvs.apps.trading.service.QuoteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "/quote", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RequestMapping("/quote")
@Controller
public class QuoteController {

    private final QuoteService quoteService;

    @Autowired
    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @ApiOperation(value = "Show iexQuote", notes = "Show iexQuote for a given ticker/symbol")
    @ApiResponses(value = {@ApiResponse(code = 404, message = "ticker is not found")})
    @GetMapping(path = "/iex/ticker/{ticker}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public IexQuote getQuote(@PathVariable String ticker) {
        try {
            return quoteService.findIexQuoteByTicker(ticker);
        } catch (Exception ex) {
            throw ResponseExceptionUtil.getResponseException(ex);
        }
    }

    @ApiOperation(value = "Update quote table using iex data", notes = "Update all quotes in the quote table.Use IEX" +
            "Market data as the source")
    @PutMapping(path = "/iexMarketData")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Quote> updateMarketData() {
        try {
            return quoteService.updateMarketData();
        } catch (Exception ex) {
            throw ResponseExceptionUtil.getResponseException(ex);
        }
    }

    @ApiOperation(value = "Update a given quote in the quote table", notes = "manually update a a given quote " +
            "using market data as the source")
    @PutMapping(path = "/")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Quote putQuote(@RequestBody Quote quote) {
        try {
            return quoteService.saveQuote(quote);
        } catch (Exception ex) {
            throw ResponseExceptionUtil.getResponseException(ex);
        }
    }

    @ApiOperation(value = "add a new ticker to the dailylist (quote table)",
            notes = "add a new ticker/symbol to the quote table so that trader can trade this security")
    @ApiResponses(value = {@ApiResponse(code = 404, message = "ticker is not found in IEX system")})
    @PostMapping(path = "/tickerId/{tickerId}")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Quote createQuote(@PathVariable String tickerId) {

        try {
            return quoteService.saveQuote(tickerId);
        } catch (Exception ex) {
            throw ResponseExceptionUtil.getResponseException(ex);
        }
    }

    @ApiOperation(value = "show the dailylist",
            notes = "show dailylist for this trading system")
    @GetMapping(path = "/dailyList")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Quote> getDailyList() {
        try {
            return quoteService.findAllQuotes();
        } catch (Exception ex) {
            throw ResponseExceptionUtil.getResponseException(ex);
        }
    }


}
