package com.microservices;

import com.microservices.bean.CurrensyConversionBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "currensy-exchange-service")netflix-zuul-api-gateway-server
@FeignClient(name = "netflix-zuul-api-gateway-server")
@RibbonClient(name = "currensy-exchange-service")
public interface CurrensyExchangeServiceProxy {

    @GetMapping("/currensy-exchange-service/currensy-exchange/from/{from}/to/{to}")
    public CurrensyConversionBean retrieveExchangeValue(@PathVariable("from") String from,
                                                        @PathVariable("to") String to);

}
