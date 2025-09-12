package br.com.gustavobarez.hermes.Courier;

import jakarta.validation.constraints.NotBlank;

public record CreateCourierRequestDTO(@NotBlank(message = "Name cannot be empty or null") String name) {

}
