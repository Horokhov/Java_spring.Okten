package ua.com.owu.lessonsspring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.owu.lessonsspring.models.ActivationToken;

public interface ActivationTokenDAO extends JpaRepository<ActivationToken, Integer> {
}