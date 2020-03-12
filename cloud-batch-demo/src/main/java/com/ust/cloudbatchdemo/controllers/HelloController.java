package com.ust.cloudbatchdemo.controllers;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.discovery.EurekaClient;

@RefreshScope
@RestController
public class HelloController {

	@Value("${external.property}") String property;
	
	@Autowired
	private EurekaClient ec;
	
	@Autowired
	private JobLauncher jl;
	
	@Autowired
	private Job job;

	//any change in the properties file will require to make a POST to
	//curl -x post localhost:micro_port/micro_name/actuator/refresh
    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok(property + " from ID -> " + ec.getApplicationInfoManager().getInfo().getInstanceId()
        		+ "<br>Port -> " + ec.getApplicationInfoManager().getInfo().getPort());
    }
    
    @RequestMapping("/peliculasJob")
    public String insertPeliculas() throws Exception {
    	jl.run(job, new JobParameters());
    	return "Películas introducidas correctamente";
    }
}
