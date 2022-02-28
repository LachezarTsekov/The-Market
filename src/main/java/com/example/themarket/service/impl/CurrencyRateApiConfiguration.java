package com.example.themarket.service.impl;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class CurrencyRateApiConfiguration {


    private final RestTemplate restTemplate;

    public CurrencyRateApiConfiguration(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public BigDecimal currencyRate(String first, String second) {

        String pathRequest = "https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/{first}/{second}.json";
        ResponseEntity<Map<String, String>> response = restTemplate
                .exchange(pathRequest,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<Map<String, String>>() {
                        },
                        first, second);

        String currency = response.getBody().get(second);


        return BigDecimal.valueOf(Double.parseDouble(currency));
    }

}
