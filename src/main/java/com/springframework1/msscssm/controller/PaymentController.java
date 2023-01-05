package com.springframework1.msscssm.controller;

import com.springframework1.msscssm.domain.PaymentEvent;
import com.springframework1.msscssm.domain.PaymentState;
import com.springframework1.msscssm.services.PaymentService;
import com.springframework1.msscssm.services.PaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.statemachine.StateMachine;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.event.PaintEvent;
@RestController
@RequestMapping("abc")
public class PaymentController {
    @Autowired
    PaymentServiceImpl paymentServiceImpl;
    @GetMapping(value = "/status", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getPaymentStatus(){
        StateMachine<PaymentState, PaymentEvent> sm = paymentServiceImpl.preAuth(252L);
        return null;
    }
}
