package com.ust.crm.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class Visit {
    @NotNull(message = "visit id cannot be null")
    @PositiveOrZero(message = "invalid id")
    private long id;

    @NotNull(message = "visit must be realized to a client")
    private Client client;

    @FutureOrPresent(message = "visit date must be today or in the future")
    private LocalDateTime programmedVisitDate;

    @NotNull(message = "visit address quantity cannot be null")
    @NotBlank(message = "visit address cannot be empty")
    @Size(min = 10, message = "visit address is invalid")
    private String address;

    @NotEmpty(message = "visit purpose cannot be empty")
    @NotNull(message = "visit purpose cannot be null")
    @Size(min = 15, message = "visit purpose is invalid")
    private String purpose;

    @NotNull(message = "seller name cannot be null")
    @NotEmpty(message = "seller name cannot be empty")
    @Size(min = 3, max = 50, message = "seller name invalid length... min 3, max 50")
    private String seller;
}
