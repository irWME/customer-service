package nl.simpliphi.customerservice.customer;

import io.github.alikelleci.easysourcing.messages.eventsourcing.annotations.ApplyEvent;
import nl.simpliphi.shopdomain.customer.Customer;
import nl.simpliphi.shopdomain.customer.CustomerEvent;
import org.springframework.stereotype.Component;

@Component
public class CustomerAggregator {

  @ApplyEvent
  public Customer apply(CustomerEvent.CustomerCreated event, Customer currentState) {
    return Customer.builder()
        .id(event.getCustomerId())
        .firstName(event.getFirstName())
        .lastName(event.getLastName())
        .build();
  }

  @ApplyEvent
  public Customer apply(CustomerEvent.FirstNameChanged event, Customer currentState) {
    return currentState.toBuilder()
        .firstName(event.getFirstName())
        .build();
  }
}
