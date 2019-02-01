package io.github.makbn.datatransmission.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

public class DTService {

    @Autowired
    private LoadBalancerClient loadBalancer;

    public String getCustomerName() {
        RestTemplate restTemplate = new RestTemplate();
        ServiceInstance instance = loadBalancer.choose("eureka-client-2");
        URI uri = instance.getUri();
        return restTemplate.getForObject(uri, String.class);
    }

    public String getFallbackCustomerName() {
        System.out.println("coming inside fallback method");
        return "Resillient Customer";
    }
}
