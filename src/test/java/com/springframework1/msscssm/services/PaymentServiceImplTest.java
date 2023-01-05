package com.springframework1.msscssm.services;

import com.springframework1.msscssm.domain.Payment;
import com.springframework1.msscssm.domain.PaymentEvent;
import com.springframework1.msscssm.domain.PaymentState;
import com.springframework1.msscssm.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;

import java.awt.event.PaintEvent;
import java.math.BigDecimal;


@SpringBootTest
class PaymentServiceImplTest {
    @Autowired
    PaymentService paymentService;
    @Autowired
    PaymentRepository paymentRepository;
    Payment payment;
    @BeforeEach
    void setUp() {

        payment = Payment.builder().amount(new BigDecimal("12.89")).build();
    }

    void preAuth() {
        Payment savedPayment = paymentService.newPayment(payment);
        System.out.println("Should be New");
        System.out.println(savedPayment.getState());
        paymentService.preAuth(savedPayment.getId());
        StateMachine<PaymentState, PaymentEvent> sm = paymentService.preAuth(savedPayment.getId());
        Payment preAuthPayment = paymentRepository.getOne(savedPayment.getId());
        System.out.println("Should be Pre_Auth or PreAuth error");
        System.out.println(sm.getState().getId());
        System.out.println(preAuthPayment);

    }


    @RepeatedTest(5)
    void testAuth(){
        Payment savedPayment = paymentService.newPayment(payment);
        StateMachine<PaymentState,PaymentEvent> preAuthsm = paymentService.preAuth(savedPayment.getId());
        if(preAuthsm.getState().getId() == PaymentState.PRE_AUTH){
            System.out.println("PRE AUTHORIZATION DONE");
            StateMachine<PaymentState, PaymentEvent> authsm = paymentService.authorizePayment(savedPayment.getId());
            System.out.println("result of sm "+authsm.getState().getId());

        }
        else{
            System.out.println("pre authorization not done");
        }

    }
}