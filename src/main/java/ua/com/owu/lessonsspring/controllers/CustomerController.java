package ua.com.owu.lessonsspring.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.owu.lessonsspring.dao.CustomerDAO;
import ua.com.owu.lessonsspring.models.Customer;
import ua.com.owu.lessonsspring.models.dto.CustomerDTO;
import ua.com.owu.lessonsspring.models.views.Views;
import ua.com.owu.lessonsspring.services.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/customers")
@AllArgsConstructor
public class CustomerController {


    private CustomerDAO customerDAO;

    private CustomerService customerService;


    @GetMapping("")
    @JsonView(Views.Client.class)
    public ResponseEntity<List<Customer>> getCustomers() {
        List<Customer> all = customerDAO.findAll();
        return new ResponseEntity<>(all, HttpStatusCode.valueOf(200));

    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCustomer(@RequestBody Customer customer) {

        customerService.save(customer);


    }

    @GetMapping("/{id}")
    @JsonView({Views.Admin.class})
    public ResponseEntity<Customer> getOneCustomer(@PathVariable int id) {
        Customer customer = customerDAO.findById(id).get();
        return new ResponseEntity<>(customer, HttpStatusCode.valueOf(200));

    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable int id) {
        customerDAO.deleteById(id);
    }

    @PatchMapping("/{id}")
    public void updateCustomer(@PathVariable int id, @RequestBody CustomerDTO customerDTO) {
        Customer customer = customerDAO.findById(id).get();
        customer.setName(customerDTO.getUsername());
        customerDAO.save(customer);

    }

    @GetMapping("/name/{name}") // /customers/name/vasya
    public ResponseEntity<List<Customer>> getCustomersByname(@PathVariable String name) {
        return customerService.customerListByName(name);
    }


    @GetMapping("/activate/{token}")
    public void activateCustomer(@PathVariable String token) {
        Customer customer = customerService.byToken(token);
        customerService.activate(customer);


    }


}