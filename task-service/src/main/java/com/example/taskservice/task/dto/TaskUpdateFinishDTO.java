package com.example.taskservice.task.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record TaskUpdateFinishDTO (
        @NotNull(message = "Must enter finish date")
        @Future(message = "finish_date of birth must be in the past")
        LocalDate finish_date
){
}
