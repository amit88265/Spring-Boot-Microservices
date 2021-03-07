package com.thoughtmechanix.organizationservice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrganisationController {


    @GetMapping("/org/{orgId}")
    ResponseEntity<String> getOrg(@PathVariable("orgId") String orgId) {
        return ResponseEntity.ok("My Org with Id " + orgId);
    }

    @GetMapping("/hello")
    ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello World!");
    }
}
