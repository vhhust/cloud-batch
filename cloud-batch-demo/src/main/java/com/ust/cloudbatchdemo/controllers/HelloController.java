package com.ust.cloudbatchdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.discovery.EurekaClient;

@RefreshScope
@RestController
public class HelloController {

	@Value("${external.property}") String property;
	
	@Autowired
	private EurekaClient ec;

	//any change in the properties file will require to make a POST to
	//curl -x post localhost:micro_port/micro_name/actuator/refresh
    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok(property + " from ID -> " + ec.getApplicationInfoManager().getInfo().getInstanceId());
    }
}
