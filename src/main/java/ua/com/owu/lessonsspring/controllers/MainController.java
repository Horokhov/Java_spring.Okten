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

    @PutMapping("/putCustomer/{id}")
    public ResponseEntity<ArrayList<Customer>> putCustomer(@PathVariable int id,@RequestBody Customer customer) {
       Customer customer2 = customerList.stream().filter(customer1 -> customer1.getId() == id).
                limit(1).
                findFirst().
                get();
        System.out.println(customer2);
        int indexOf = customerList.indexOf(customer2);
        customerList.set(indexOf,customer);
        return new ResponseEntity<>(HttpStatus.valueOf(201));
    }

    @PatchMapping("/patchCustomer/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void patchCustomer(@PathVariable int id, @RequestBody Customer customer) {
       Customer customer1 = customerList.stream().filter(c->c.getId()==id).findFirst().get();
       customer1.setId(customer.getId());
       customer1.setName(customer.getName());
       int index = customerList.indexOf(customer1);
       customerList.set(index,customer);
       return; new ResponseEntity<>(HttpStatus.valueOf(201));
    }
}
