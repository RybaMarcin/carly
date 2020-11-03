package org.carly.core.usermanagement.service;

import org.springframework.web.context.annotation.ApplicationScope;

import java.util.ArrayList;
import java.util.List;

@ApplicationScope
public class TokenBlackListService {

    private final List<String> blackList = new ArrayList<>();

    public void addTokenToBlackList(String token) {
        String t = blackList.stream().filter(bl -> bl.equals(token)).findFirst().orElse(null);
        if (t == null) {
            blackList.add(token);
        }
    }

    public void clearBlackList() {
        blackList.clear();
    }
}
