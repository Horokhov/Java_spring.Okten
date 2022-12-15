package ua.com.owu.lessonsspring.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.owu.lessonsspring.Models.Customer;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MainController {

    ArrayList<Customer> customerList=new ArrayList<>();

    public MainController() {
        customerList.add(new Customer(5,"Max"));
        customerList.add(new Customer(6,"Oleg"));
        customerList.add(new Customer(7,"Boris"));

    }

    @GetMapping("/getList")
    //@ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<ArrayList<Customer>> getCustomerList() {
        return new ResponseEntity<>(this.customerList, HttpStatus.OK);
    }

    @PostMapping("/addCustomer")
    public ResponseEntity<ArrayList<Customer>> addCustomer(@RequestBody Customer customer) {
        System.out.println(customer);
        this.customerList.add(customer);
        return new ResponseEntity<>(this.customerList,HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteCustomer/{id}")
    public ResponseEntity<List<Customer>> deleteCustomer(@PathVariable int id) {
        this.customerList.remove(id - 1);
        return new ResponseEntity<>(this.customerList, HttpStatus.valueOf(200));

    }
}
