package ua.com.owu.lessonsspring.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
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

    @GetMapping("/getlist")
    //@ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<ArrayList<Customer>> getCustomerList() {
        return new ResponseEntity<>(this.customerList, HttpStatus.OK);
    }
}
