package com.springframework1.msscssm.config;

import com.springframework1.msscssm.domain.PaymentEvent;
import com.springframework1.msscssm.domain.PaymentState;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineConfig;
import org.springframework.statemachine.config.StateMachineFactory;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class StateMachineConfigTest {
    @Autowired
    StateMachineFactory<PaymentState, PaymentEvent> factory;
    @Test
    public void testNewStateMachine(){
        StateMachine<PaymentState,PaymentEvent> machine = factory.getStateMachine(UUID.randomUUID());
        machine.start();
        System.out.println(machine.getState().toString());
        machine.sendEvent(PaymentEvent.PRE_AUTHORIZE);
        System.out.println(machine.getState().toString());
        machine.sendEvent(PaymentEvent.PRE_AUTH_APPROVED);
        machine.sendEvent(PaymentEvent.PRE_AUTH_DECLINED);
        System.out.println(machine.getState().toString());



    }


}