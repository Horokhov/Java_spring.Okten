package ua.com.owu.lessonsspring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.com.owu.lessonsspring.models.Customer;

import java.util.List;

public interface CustomerDAO extends JpaRepository<Customer, Integer> {


    @Query("select c from Customer  c where  c.name=:name")
    List<Customer> getByName(@Param("name") String name);

    List<Customer> findCustomerByName(String name);

    @Query("select c from Customer c where c.activationToken.token=:t")
    Customer byToken(String t);
}