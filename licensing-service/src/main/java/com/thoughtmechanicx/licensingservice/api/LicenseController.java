package com.thoughtmechanicx.licensingservice.api;

import com.thoughtmechanicx.licensingservice.client.OrganisationClient;
import com.thoughtmechanicx.licensingservice.domain.License;
import com.thoughtmechanicx.licensingservice.service.LicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope  // pur @RefreshScope on that class u want spring props chnage at runtime
public class LicenseController {

    @Value("${my.value}")
    private String myValue;

    @Autowired
    private OrganisationClient organisationClient;
    @Autowired
    private LicenseService licenseService;


    @GetMapping("/v1/organizations/{organizationId}/licenses/{licenseId}")
    ResponseEntity<License> getLicense(@PathVariable("organizationId") String orgId,
                                       @PathVariable("licenseId") String licenseId) {
        ResponseEntity<String> response = organisationClient.getOrg(orgId);
        String body = response.getBody();
        License license = License.builder()
                .withLicenseId(licenseId)
                .withOrgId(body)
                .withLicenseType("Seat")
                .withProductName("Teleco")
                .withMyValue(myValue)
                .build();
        return ResponseEntity.ok(license);

    }

    @GetMapping("/hello")
    ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello World!");
    }

    @GetMapping("/test-hystrix")
    String testHystrix() {
        return licenseService.testHystrix();
    }

    @DeleteMapping("/delete")
    Boolean delete() {
        return true;
    }
}
