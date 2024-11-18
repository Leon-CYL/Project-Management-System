package com.spring.controller;

import com.spring.model.PlanType;
import com.spring.model.Subscription;
import com.spring.model.User;
import com.spring.service.SubscriptionService;
import com.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private UserService userService;


    @GetMapping("/user")
    public ResponseEntity<Subscription> getUserSubscription(
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        return new ResponseEntity<>(subscriptionService.getUserSubscription(user.getId()), HttpStatus.OK);
    }


    @PatchMapping("/upgrade")
    public ResponseEntity<Subscription> upgradeSubscription(
            @RequestHeader("Authorization") String jwt,
            @RequestParam PlanType planType
    ) throws Exception{

        User user = userService.findUserProfileByJwt(jwt);
        return new ResponseEntity<>(subscriptionService.upgradeSubscription(user.getId(), planType), HttpStatus.OK);
    }
}
