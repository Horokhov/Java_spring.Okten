package ua.com.owu.lessonsspring.services;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ua.com.owu.lessonsspring.dao.CustomerDAO;
import ua.com.owu.lessonsspring.models.ActivationToken;
import ua.com.owu.lessonsspring.models.Customer;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {

    private CustomerDAO customerDAO;
    private MailService mailService;

    public void save(Customer customer) {
        customer.setActivationToken(new ActivationToken());
        customerDAO.save(customer);
        mailService.send(customer);
    }

    public ResponseEntity<List<Customer>> customerListByName(String name) {
        if (name != null && !name.isBlank()) {
            List<Customer> customerByName = customerDAO.findCustomerByName(name);
            System.out.println(customerByName);
            return new ResponseEntity<>(customerByName, HttpStatusCode.valueOf(200));
        } else {
            throw new RuntimeException();
        }

    }

    public Customer getCustomerById(int id) {
       return customerDAO.findById(id).get();
    }

    public void updateCustomer(Customer customer) {
       customerDAO.save(customer);
    }


    public Customer byToken(String token) {
        customerDAO.byToken(token);
    }


    public void activate(Customer customer) {
        if (customer.getActivationToken().getExpire().isAfter(LocalDateTime.now())) {
            customer.setActivated(true);
            updateCustomer(customer);
        }


    }

}