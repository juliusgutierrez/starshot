package com.starshot.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.jmx.support.RegistrationPolicy;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableMBeanExport(registration= RegistrationPolicy.IGNORE_EXISTING)
public class DemoApplication extends SpringBootServletInitializer {

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

}
