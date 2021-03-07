package com.thoughtmechanicx.licensingservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class LicenseService {

    private void randomlyRunLong() {
        Random rand = new Random();
        int randomNum = rand.nextInt((3 - 1) + 1) + 1;
        if (randomNum == 3) sleep();
    }

    private void sleep() {
        try {
            Thread.sleep(11000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Below way u can set timeout prop , fallback in Hystrix command-> if this method take more than 5s then fallback method executed
    @HystrixCommand(fallbackMethod = "handleFallback"
            , commandProperties = {@HystrixProperty(
            name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")})
    public String testHystrix() {
        randomlyRunLong();
        return "Going good!";
    }

    public String handleFallback() {
        return "fallback occurred!";
    }
}
