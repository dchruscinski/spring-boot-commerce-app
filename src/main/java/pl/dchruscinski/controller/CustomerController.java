package pl.dchruscinski.controller;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dchruscinski.config.exception.ResourceNotFoundException;
import pl.dchruscinski.entity.Customer;
import pl.dchruscinski.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    public static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getCustomers() {
        logger.debug("getCustomers(): returning customers list.");

        if (customerService.countCustomers() == 0) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(customerService.getCustomers());
    }

    @GetMapping("/{customerId}")
    // public Customer getCustomer(@PathVariable("customerId") Integer id) {
    public ResponseEntity<Customer> getCustomer(@PathVariable Integer customerId) {
        if (!customerService.existsById(customerId)) {
            throw new ResourceNotFoundException("getCustomer(): cannot find customer with ID:" + customerId);
        }

        logger.debug("getCustomer(): returning customer with ID: {}.", customerId);
        return ResponseEntity.ok(customerService.getCustomer(customerId));
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> countCustomers() {
        logger.debug("getCustomerCount(): returning customers count.");
        return ResponseEntity.ok(customerService.countCustomers());
    }

    @Transactional
    @PostMapping
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        logger.debug("addCustomer(): adding new customer: {}.", customer.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.addCustomer(customer));
    }

    @Transactional
    @PutMapping("/{customerId}")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer, @PathVariable Integer customerId) {
        if (!customerService.existsById(customerId)) {
            throw new ResourceNotFoundException("updateCustomer(): cannot find customer with ID: {}:" + customerId);
        }

        logger.debug("updateCustomer(): updating customer with ID: {} with values: {}.", customerId, customer.toString());
        return ResponseEntity.ok(customerService.updateCustomer(customer, customerId));
    }

    @Transactional
    @DeleteMapping("/{customerId}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable Integer customerId) {
        if (!customerService.existsById(customerId)) {
            throw new ResourceNotFoundException("deleteCustomer(): cannot find customer with ID: {}:" + customerId);
        }

        logger.debug("deleteCustomer(): deleting customer with ID: {}.", customerId);
        customerService.deleteCustomerById(customerId);
        return ResponseEntity.noContent().build();
    }
}