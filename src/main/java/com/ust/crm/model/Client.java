package com.ust.crm.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@Builder
@AllArgsConstructor
public class Client {
    @NotNull(message = "client id cannot be null")
    @PositiveOrZero(message = "invalid id")
    private long id;

    @NotNull(message = "client name cannot be null")
    @NotEmpty(message = "client name cannot be empty")
    @Size(min = 3, max = 50, message = "client name invalid length... min 3, max 50")
    private String name;

    @Email
    @NotNull(message = "client email cannot be null")
    @NotEmpty(message = "client email cannot be empty")
    private String email;

    @Min(value = 10, message = "client must have more than 10 employees")
    @NotNull(message = "client employees quantity cannot be null")
    private int employeesQty;

    @NotNull(message = "client address quantity cannot be null")
    @NotBlank(message = "client address cannot be empty")
    @Size(min = 10, message = "client address is invalid")
    private String address;
}
