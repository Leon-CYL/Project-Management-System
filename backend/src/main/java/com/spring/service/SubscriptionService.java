package com.spring.service;

import com.spring.model.PlanType;
import com.spring.model.Subscription;
import com.spring.model.User;

public interface SubscriptionService {
    Subscription createSubscription(User user) throws Exception;

    Subscription getUserSubscription(Long userId) throws Exception;

    Subscription upgradeSubscription(Long userId, PlanType planType) throws Exception;

    boolean isValid(Subscription subscription) throws Exception;
}
