package ca.jrvs.apps.trading.controller;


import ca.jrvs.apps.trading.dao.MarketOrderDto;
import ca.jrvs.apps.trading.model.domain.SecurityOrder;
import ca.jrvs.apps.trading.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/order")
public class orderController {

    private OrderService orderService;

    @Autowired
    public orderController(OrderService orderService)
    {
        this.orderService = orderService;
    }

    @PostMapping(path = "/marketOrder")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public SecurityOrder postMarketOrder(@RequestBody MarketOrderDto orderDto)
    {
        try{
            return orderService.executeMarketOrder(orderDto);
        }catch (Exception ex)
        {
            throw ResponseExceptionUtil.getResponseException(ex);
        }
    }

}
