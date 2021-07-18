package de.jayasinghe.samples.notification;

import de.jayasinghe.samples.notification.dispatch.statemachine.DeliveredAction;
import de.jayasinghe.samples.notification.dispatch.statemachine.OrderEvents;
import de.jayasinghe.samples.notification.dispatch.statemachine.OrderStates;
import de.jayasinghe.samples.notification.dispatch.statemachine.PlacedAction;
import de.jayasinghe.samples.notification.dispatch.statemachine.ShippedAction;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.service.StateMachineService;
import org.springframework.statemachine.test.StateMachineTestPlan;
import org.springframework.statemachine.test.StateMachineTestPlanBuilder;

import java.util.UUID;

@SpringBootTest
class BasicStateMachineTest {

    @Autowired StateMachineService<OrderStates, OrderEvents> service;
    @Autowired StateMachineFactory<OrderStates, OrderEvents> factory;

    @MockBean PlacedAction placedAction;
    @MockBean ShippedAction shippedAction;
    @MockBean DeliveredAction deliveredAction;

    @Test
    void testMachineCreation() {
        StateMachine<OrderStates, OrderEvents> stateMachine = factory.getStateMachine();
        stateMachine.startReactively().block();
        Assertions.assertThat(stateMachine).isNotNull();
    }

    @Test
    void testStandardWorkflow() throws Exception {
        UUID stateMachineId = UUID.randomUUID();
        //StateMachine<OrderStates, OrderEvents> stateMachine = service.acquireStateMachine(stateMachineId.toString());
        StateMachine<OrderStates, OrderEvents> stateMachine = factory.getStateMachine(stateMachineId);
        stateMachine.startReactively().block();

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
