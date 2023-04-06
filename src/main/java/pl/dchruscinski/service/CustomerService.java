package pl.dchruscinski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import pl.dchruscinski.entity.Customer;
import pl.dchruscinski.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public List<Customer> getCustomersByProductId(Integer productId) {
        return customerRepository.getCustomersByProductId(productId);
    }

    public boolean existsById(Integer id) {
        return customerRepository.existsById(id);
    }

    public Customer getCustomer(Integer id) {
        Optional<Customer> result = customerRepository.findById(id);

        Customer customer = null;
        if (result.isPresent()) {
            customer = result.get();
        }

        return customer;
    }

    public Customer addCustomer(@RequestBody Customer customer) {
        // In case of someone using API will pass ID in JSON - set it to "0" to save new item instead of updating existing.
        customer.setId(0);

        return customerRepository.save(customer);
    }

    public Customer updateCustomer(@RequestBody Customer customer, @PathVariable Integer customerId) {
        customer.setId(customerId);
        return customerRepository.save(customer);
    }

    public void deleteCustomerById(Integer id) {
        customerRepository.deleteById(id);
    }

    public Integer countCustomers() {
        return customerRepository.countCustomers();
    }

    public Integer countCustomersByProductId(Integer productId) {
        return customerRepository.countCustomersByProductId(productId);
    }
}