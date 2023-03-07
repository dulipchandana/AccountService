package com.igreendata.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.hateoas.config.EnableHypermediaSupport;

import static org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType.HAL;
import static org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType.HAL_FORMS;

@SpringBootApplication
@EnableJpaAuditing
@ComponentScan(basePackages = {"com.igreendata.account.controller","com.igreendata.account.service","com.igreendata.account.config",
		"com.igreendata.account.advisor","com.igreendata.account.aspect"})
@EnableHypermediaSupport(type = { HAL, HAL_FORMS })
public class AccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountServiceApplication.class, args);
	}

}
