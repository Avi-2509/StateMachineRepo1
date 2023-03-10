package com.springframework1.msscssm.services;

import com.springframework1.msscssm.domain.Payment;
import com.springframework1.msscssm.domain.PaymentEvent;
import com.springframework1.msscssm.domain.PaymentState;
import com.springframework1.msscssm.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class PaymentStateChangeInterceptor extends StateMachineInterceptorAdapter<PaymentState, PaymentEvent> {
    @Autowired
    private  PaymentRepository paymentRepository;

    @Override
    public void preStateChange(State<PaymentState, PaymentEvent> state, Message<PaymentEvent> message, Transition<PaymentState, PaymentEvent> transition, StateMachine<PaymentState, PaymentEvent> stateMachine, StateMachine<PaymentState, PaymentEvent> rootStateMachine) {
        Optional.ofNullable(message).ifPresent(msg->{
                Optional.ofNullable(Long.class.cast(msg.getHeaders().getOrDefault("payment_id",-1L)))
                        .ifPresent(paymentId->{
                            Payment payment = paymentRepository.getOne(paymentId);
                            payment.setState(state.getId());
                            paymentRepository.save(payment);
                        });
    });
    }
}
