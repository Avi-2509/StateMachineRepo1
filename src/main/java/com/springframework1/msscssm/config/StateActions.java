package com.springframework1.msscssm.config;

import com.springframework1.msscssm.domain.PaymentEvent;
import com.springframework1.msscssm.domain.PaymentState;
import com.springframework1.msscssm.services.PaymentServiceImpl;
import lombok.Builder;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;


import java.util.Random;
@Component
public class StateActions {
    @Bean
    public Action<PaymentState, PaymentEvent> preAuthAction(){
        return context->{
            System.out.println("Pre Auth called !!");
            if(new Random().nextInt(10)<8){
                System.out.println("Pre Auth Approved");
                context.getStateMachine().sendEvent(MessageBuilder.withPayload(PaymentEvent.PRE_AUTH_APPROVED)
                        .setHeader(PaymentServiceImpl.PAYMENT_ID_HEADER,context.getMessageHeader(PaymentServiceImpl.PAYMENT_ID_HEADER))
                        .build());
            }
            else{
                System.out.println("Pre Authorization declined");
                context.getStateMachine().sendEvent(MessageBuilder.withPayload(PaymentEvent.PRE_AUTH_DECLINED)
                        .setHeader(PaymentServiceImpl.PAYMENT_ID_HEADER,context.getMessageHeader(PaymentServiceImpl.PAYMENT_ID_HEADER))
                        .build());
            }
        };
    }
    @Bean
    public Action<PaymentState, PaymentEvent> authPayment(){
        return context->{
            System.out.println("Authorization was called !!!");
            if(new Random().nextInt(10)<8){
                System.out.println("Authorization Approved");
                context.getStateMachine().sendEvent(MessageBuilder.withPayload(PaymentEvent.AUTH_APPROVED)
                        .setHeader(PaymentServiceImpl.PAYMENT_ID_HEADER,context.getMessageHeader(PaymentServiceImpl.PAYMENT_ID_HEADER))
                        .build());
            }
            else{
                System.out.println("Authorization declined");
                context.getStateMachine().sendEvent(MessageBuilder.withPayload(PaymentEvent.AUTH_DECLINED)
                        .setHeader(PaymentServiceImpl.PAYMENT_ID_HEADER,context.getMessageHeader(PaymentServiceImpl.PAYMENT_ID_HEADER))
                        .build());
            }
        };
    }

}
