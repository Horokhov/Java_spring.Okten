package ua.com.owu.lessonsspring.services;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ua.com.owu.lessonsspring.dao.ActivationTokenDAO;
import ua.com.owu.lessonsspring.models.ActivationToken;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ActivationTokenService {
    private ActivationTokenDAO activationTokenDAO;

}