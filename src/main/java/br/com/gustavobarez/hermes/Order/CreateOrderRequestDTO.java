package br.com.gustavobarez.hermes.Order;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateOrderRequestDTO(@NotBlank(message = "Description cannot be blank or null") String description,
        @NotBlank(message = "Origin Address cannot be blank or null") String originAddress,
        @NotBlank(message = "Delivery Address cannot be blank or null") String deliveryAddress,
        @NotNull(message = "Status cannot be blank or null") Status status) {

}
