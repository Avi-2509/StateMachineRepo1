package com.springframework1.msscssm.services;

import com.springframework1.msscssm.domain.Payment;
import com.springframework1.msscssm.domain.PaymentEvent;
import com.springframework1.msscssm.domain.PaymentState;
import org.springframework.statemachine.StateMachine;


public interface PaymentService {
    Payment newPayment(Payment payment);
    StateMachine<PaymentState, PaymentEvent> preAuth(Long paymentId);
    StateMachine<PaymentState, PaymentEvent> authorizePayment(Long paymentId);
    StateMachine<PaymentState, PaymentEvent> declineAuth(Long paymentId);




}
