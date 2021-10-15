package nl.simpliphi.customerservice.customer;

import io.github.alikelleci.easysourcing.messages.commands.annotations.HandleCommand;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import nl.simpliphi.shopdomain.customer.Customer;
import nl.simpliphi.shopdomain.customer.CustomerCommand;
import nl.simpliphi.shopdomain.customer.CustomerEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ValidationException;

@Slf4j
@Component
public class CustomerCommandHandler {

    private final MapperFacade mapper;

    @Autowired
    public CustomerCommandHandler(MapperFacade mapper) {
        this.mapper = mapper;
    }

    @HandleCommand
    public CustomerEvent handle(CustomerCommand.CreateCustomer command, Customer currentState) {
        if (currentState != null) {
            throw new ValidationException("Customer already exists!");  // only visible in debug logging
        }

        return mapper.map(command, CustomerEvent.CustomerCreated.class);

//        return CustomerEvent.CustomerCreated.builder()
//                .customerId(command.getCustomerId())
//                .firstName(command.getFirstName())
//                .lastName(command.getLastName())
//                .build();
    }

    @HandleCommand
    public CustomerEvent handle(CustomerCommand.ChangeFirstName command, Customer currentState) {
        if (currentState == null) {
            throw new ValidationException("Customer does not exist!");
        }
        return CustomerEvent.FirstNameChanged.builder()
                .customerId(command.getCustomerId())
                .firstName(command.getFirstName())
                .build();
    }
}
