package com.ust.crm.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@Builder
@AllArgsConstructor
public class Stage {
    @NotNull(message = "Stage id cannot be null")
    @PositiveOrZero(message = "invalid id")
    private long id;

    @NotNull(message = "stage name cannot be null")
    @NotEmpty(message = "stage name cannot be empty")
    @Size(min = 4, max = 30, message = "stage name invalid length... min 3, max 30")
    private String name;

    @Positive(message = "invalid stage order, must be greater than 0")
    private int order;
}
