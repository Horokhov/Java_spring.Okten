package ua.com.owu.lessonsspring.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.owu.lessonsspring.dao.CustomerDAO;
import ua.com.owu.lessonsspring.models.Customer;

@RestController
@AllArgsConstructor
public class MainController {
    private CustomerDAO customerDAO;
    @GetMapping("/")
    public String open() {
        return "open";
    }

    @PostMapping("/save")

    public void save(Customer customer) {
        customerDAO.save(customer);
    }

}
