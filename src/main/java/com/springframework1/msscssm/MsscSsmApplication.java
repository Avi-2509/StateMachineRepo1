package com.springframework1.msscssm;

import com.springframework1.msscssm.domain.Payment;
import com.springframework1.msscssm.domain.PaymentEvent;
import com.springframework1.msscssm.domain.PaymentState;
import com.springframework1.msscssm.repository.PaymentRepository;
import com.springframework1.msscssm.services.PaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@SpringBootApplication(scanBasePackages =  "com.springframework1" )
@ConfigurationPropertiesScan( "com.springframework1" )
public class MsscSsmApplication {

	public static void main(String[] args) {

		//SpringApplication.run(MsscSsmApplication.class, args);
		ApplicationContext context = SpringApplication.run(MsscSsmApplication.class, args);
		PaymentRepository paymentRepository = context.getBean(PaymentRepository.class);
//		Payment payment = new Payment();
//		payment.setState(PaymentState.NEW);
//		payment.setAmount(new BigDecimal(23.67));
//		Payment payment1 = paymentRepository.save(payment);
//		System.out.println(payment1);


	}

}
