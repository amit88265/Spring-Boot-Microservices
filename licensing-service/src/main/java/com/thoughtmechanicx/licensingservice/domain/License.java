package com.thoughtmechanicx.licensingservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(setterPrefix = "with", builderClassName = "Builder")
public class License {
    private String orgId;
    private String licenseId;
    private String productName;
    private String LicenseType;
    private String myValue;
}
