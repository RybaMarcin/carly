package org.carly.shared.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public interface TimeService {
    LocalDate getLocalDate();

    LocalDateTime getLocalDateTime();
}
