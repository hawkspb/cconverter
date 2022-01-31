package com.example.cconverter.service;

import com.example.cconverter.exception.ConvertException;
import com.example.cconverter.model.RatesResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class ConvertService {
    @Value("${exchangeratesapi.url}")
    private String exchangeRatesApiUrl;

    private Map<String, Double> rates = new HashMap<>();

    public double convert(double amount, String source, String target) throws ConvertException {
        Double sourceRate = rates.get(source);
        if (sourceRate == null) throw new ConvertException(source);

        Double targetRate = rates.get(target);
        if (targetRate == null) throw new ConvertException(target);

        return (amount / sourceRate) * targetRate;
    }

    @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.HOURS)
    private void reloadRates() {
        RatesResponse response = WebClient.create(exchangeRatesApiUrl)
                .get()
                .retrieve()
                .bodyToMono(RatesResponse.class)
                .block();

        if (response != null && response.success) {
            rates = response.rates;
        }
    }

}
