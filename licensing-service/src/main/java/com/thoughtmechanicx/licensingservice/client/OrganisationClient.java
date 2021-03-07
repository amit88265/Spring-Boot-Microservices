package com.thoughtmechanicx.licensingservice.client;

import com.thoughtmechanicx.licensingservice.utils.UserContextInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "organizationservice", configuration = {UserContextInterceptor.class})
public interface OrganisationClient {

    @GetMapping("/org/{orgId}")
    ResponseEntity<String> getOrg(@PathVariable("orgId") String orgId);
}
