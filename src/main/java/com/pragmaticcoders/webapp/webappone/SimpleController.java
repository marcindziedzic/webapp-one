package com.pragmaticcoders.webapp.webappone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SimpleController {

    // TODO implement own IPing
    // TODO implement own IRule
    // TODO implement ILoadBalancer -> DynamicServerListLoadBalancer

    /*
    public DynamicServerListLoadBalancer(IClientConfig clientConfig, IRule rule, IPing ping,
                                         ServerList<T> serverList, ServerListFilter<T> filter,
                                         ServerListUpdater serverListUpdater) {


                                         git@github.com:Netflix/ribbon.git

     */

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(path = "/api/call")
    public String call() {
        return this.restTemplate.getForObject("http://say-hello/api/inner/call", String.class);
    }

    @GetMapping(path = "/api/inner/call")
    public String innerCall() {
        return "InnerOk";
    }
}
