package com.ust.crm.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class Product {
    @NotNull(message = "product id cannot be null")
    @PositiveOrZero(message = "invalid id")
    private long id;

    @NotNull(message = "product name cannot be null")
    @NotEmpty(message = "product name cannot be empty")
    @Size(min = 3, max = 30, message = "product name invalid length... min 3, max 30")
    private String name;

    @Size(min = 3, max = 30, message = "product category invalid length... min 3, max 30")
    private String category;

    @DecimalMin(value = "1.00", inclusive = true, message = "product price must be at least 1.00")
    private float price;

    @NotNull(message = "product registry number cannot be null")
    @NotEmpty(message = "product registry number cannot be empty")
    @Pattern(regexp = "^(\\d{3}-?){2}\\d{4}$")
    private String registryNumber;

    @NotNull(message = "product creation date cannot be null")
    @PastOrPresent(message = "product creation date cannot be in the future")
    private LocalDate createdAt;
}
