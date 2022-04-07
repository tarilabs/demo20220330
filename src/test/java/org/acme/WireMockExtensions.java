package org.acme;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

import java.util.Collections;
import java.util.Map;

import com.github.tomakehurst.wiremock.WireMockServer;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

public class WireMockExtensions implements QuarkusTestResourceLifecycleManager {  

    private WireMockServer wireMockServer;

    @Override
    public Map<String, String> start() {
        wireMockServer = new WireMockServer(wireMockConfig().dynamicPort());
        wireMockServer.start(); 
        configureFor("localhost", wireMockServer.port()); // this is needed since WireMock is wired to a random port, to use the stubFor to the correct WireMock server

        stubFor(post(urlEqualTo("/predictions"))   
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(
                            "{\r\n    \"result\": {\r\n        \"predictions\": \"true\",\r\n        \"scores\": [\r\n            0.12923076923076923,\r\n            0.8707692307692307\r\n        ],\r\n        \"names\": [\r\n            \"false\",\r\n            \"true\"\r\n        ]\r\n    }\r\n}"
                        )));

        return Collections.singletonMap("org.acme.demo20220330.OPSClient/mp-rest/url", wireMockServer.baseUrl()); 
    }

    @Override
    public void stop() {
        if (null != wireMockServer) {
            wireMockServer.stop();  
        }
    }
}