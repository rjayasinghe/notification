package de.jayasinghe.samples.notification;

import de.jayasinghe.samples.notification.statemachine.OrderEvents;
import de.jayasinghe.samples.notification.statemachine.OrderStates;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.test.StateMachineTestPlan;
import org.springframework.statemachine.test.StateMachineTestPlanBuilder;

@SpringBootTest
class BasicStateMachineTest {

    @Autowired
    StateMachineFactory<OrderStates, OrderEvents> factory;

    @Test
    void testMachineCreation() {
        StateMachine<OrderStates, OrderEvents> stateMachine = factory.getStateMachine();
        stateMachine.start();
        Assertions.assertThat(stateMachine).isNotNull();
    }

    @Test
    void testStandardWorkflow() throws Exception {
        StateMachine<OrderStates, OrderEvents> stateMachine = factory.getStateMachine();
        stateMachine.start();

        StateMachineTestPlan<OrderStates, OrderEvents> plan = StateMachineTestPlanBuilder.<OrderStates, OrderEvents>builder()
                .defaultAwaitTime(2)
                .stateMachine(stateMachine)
                .step()
                .expectStates(OrderStates.INITIAL)
                .and()
                .step()
                .sendEvent(OrderEvents.PLACED)
                .expectStateChanged(1)
                .expectStates(OrderStates.IN_PROCESS)
                .and()
                .step()
                .sendEvent(OrderEvents.SHIPPED)
                .expectStateChanged(1)
                .expectStates(OrderStates.ON_THE_ROAD)
                .and()
                .step()
                .sendEvent(OrderEvents.DELIVERED)
                .expectStateChanged(1)
                .expectStates(OrderStates.COMPLETE)
                .and()
                .build();

        plan.test();

    }


}
