package org.carly.shared.utils.time.service;

import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
//@ConditionalOnMissingBean(TimeController.class)
public class RealTimeService implements TimeService {

    @Override
    public LocalDate getLocalDate() {
        return LocalDate.now(Clock.systemUTC());
    }

    @Override
    public LocalDateTime getLocalDateTime() {
        return LocalDateTime.now(Clock.systemUTC());
    }

}
