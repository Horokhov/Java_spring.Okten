package ua.com.owu.lessonsspring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.owu.lessonsspring.models.Customer;

public interface CustomerDAO extends JpaRepository<Customer,Integer> {
    Customer findCustomerByLogin(String login);
}
