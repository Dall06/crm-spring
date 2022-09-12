package com.ust.crm.utils;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;



import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateUtil {
    @JsonDeserialize(using = LocalDateDeserializer.class)
    public LocalDate get() {
        return LocalDate.now();
    }



    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    public LocalDateTime getDateTime() {
        return LocalDateTime.now();
    }
}