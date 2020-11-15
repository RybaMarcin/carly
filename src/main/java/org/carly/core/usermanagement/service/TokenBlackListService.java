package org.carly.core.usermanagement.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.annotation.ApplicationScope;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@ApplicationScope
public class TokenBlackListService {

    private final Map<String, LocalDateTime> blacklistMap = new ConcurrentHashMap<>();

    public void addTokenToMap(String requestToken) {
        String token = requestToken.substring(7);
        LocalDateTime now = LocalDateTime.now();
        blacklistMap.putIfAbsent(token, now);
    }

    public void clearBlackList(){
        LocalDateTime minusMinutes = LocalDateTime.now().minusMinutes(15);
        for (Map.Entry<String, LocalDateTime> token : blacklistMap.entrySet()) {
            if (token.getValue().isBefore(minusMinutes)) {
                blacklistMap.remove(token.getKey());
            }
        }
    }

    public Map<String, LocalDateTime> getBlacklistMap() {
        return blacklistMap;
    }
}