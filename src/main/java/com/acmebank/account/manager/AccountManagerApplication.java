package com.acmebank.account.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class AccountManagerApplication {

  public static void main(String[] args) {
    SpringApplication.run(AccountManagerApplication.class, args);
  }

}
