package com.microservices.config;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LimitConfiguration {

    private int maximum;
    private int minimum;

}
