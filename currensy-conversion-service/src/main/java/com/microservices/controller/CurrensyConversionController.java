package com.microservices.controller;


import com.microservices.CurrensyExchangeServiceProxy;
import com.microservices.bean.CurrensyConversionBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CurrensyConversionController {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CurrensyExchangeServiceProxy proxy;

    @GetMapping("/currensy-converter/from/{from}/to/{to}/quantity/{quantity}")
    private CurrensyConversionBean convertCurrensy(@PathVariable String from,
                                                   @PathVariable String to,
                                                   @PathVariable BigDecimal quantity){
        //Feign - problem 1
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);

        ResponseEntity<CurrensyConversionBean> responseEntity = new RestTemplate().getForEntity(
                "http://localhost:8000/currensy-exchange/from/{from}/to/{to}",
                CurrensyConversionBean.class, uriVariables);

        CurrensyConversionBean response = responseEntity.getBody();

        return new CurrensyConversionBean(response.getId(),from,to,response.getConversionMultiple(),quantity,quantity.multiply(response.getConversionMultiple()),response.getPort());
    }

    @GetMapping("/currensy-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
    private CurrensyConversionBean convertCurrensyFeign(@PathVariable String from,
                                                   @PathVariable String to,
                                                   @PathVariable BigDecimal quantity){


        CurrensyConversionBean response = proxy.retrieveExchangeValue(from,to);

        logger.info("{}", response);

        return new CurrensyConversionBean(response.getId(),from,to,response.getConversionMultiple(),quantity,quantity.multiply(response.getConversionMultiple()),response.getPort());
    }

}
