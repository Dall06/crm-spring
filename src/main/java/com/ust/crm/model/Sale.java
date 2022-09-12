package com.ust.crm.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class Sale {
    @NotNull(message = "sale id cannot be null")
    @PositiveOrZero(message = "invalid id")
    private long id;

    @NotNull(message = "sale qty cannot be null")
    @DecimalMin(value = "1.00", inclusive = true, message = "sale must be at least 1.00")
    private float qty;

    @NotEmpty(message = "sale must contain a product")
    @NotNull(message = "sale products cannot be null")
    private List<Product> products;

    @NotNull(message = "sale must contain a client")
    private Client client;

    @NotNull(message = "sale creation date cannot be null")
    @PastOrPresent(message = "sale creation date cannot be in the future")
    private LocalDate createdAt;
}
