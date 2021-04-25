package de.jayasinghe.samples.notification.statemachine;

import static de.jayasinghe.samples.notification.statemachine.OrderEvents.DELIVERED;
import static de.jayasinghe.samples.notification.statemachine.OrderEvents.PLACED;
import static de.jayasinghe.samples.notification.statemachine.OrderEvents.SHIPPED;
import static de.jayasinghe.samples.notification.statemachine.OrderStates.COMPLETE;
import static de.jayasinghe.samples.notification.statemachine.OrderStates.INITIAL;
import static de.jayasinghe.samples.notification.statemachine.OrderStates.IN_PROCESS;
import static de.jayasinghe.samples.notification.statemachine.OrderStates.ON_THE_ROAD;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.stereotype.Component;

import java.util.Set;

@Configuration
@EnableStateMachineFactory
@Component
public class OrderStateMachineConfig extends EnumStateMachineConfigurerAdapter<OrderStates, OrderEvents> {

    @Override
    public void configure(StateMachineStateConfigurer<OrderStates, OrderEvents> states) throws Exception {
        states.withStates()
                .initial(INITIAL)
                .states(Set.of(IN_PROCESS, ON_THE_ROAD))
                .end(COMPLETE);
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<OrderStates, OrderEvents> transitions) throws Exception {
        transitions
                .withExternal()
                .source(INITIAL).target(IN_PROCESS)
                .event(PLACED)
                .and()
                .withExternal()
                .source(IN_PROCESS)
                .target(ON_THE_ROAD)
                .event(SHIPPED)
                .and()
                .withExternal()
                .source(ON_THE_ROAD)
                .target(COMPLETE)
                .event(DELIVERED);
    }
}
