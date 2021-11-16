package nl.simpliphi.customerservice.customer;

import com.github.easysourcing.messages.aggregates.annotations.ApplyEvent;
import nl.simpliphi.shopdomain.customer.Customer;
import nl.simpliphi.shopdomain.customer.CustomerEvent;
import org.springframework.stereotype.Component;

@Component
public class CustomerAggregator {

  @ApplyEvent
  public Customer apply(Customer currentState, CustomerEvent.CustomerCreated event) {
    return Customer.builder()
        .id(event.getCustomerId())
        .firstName(event.getFirstName())
        .lastName(event.getLastName())
        .build();
  }

  @ApplyEvent
  public Customer apply(Customer currentState, CustomerEvent.FirstNameChanged event) {
    return currentState.toBuilder()
        .firstName(event.getFirstName())
        .build();
  }
}
