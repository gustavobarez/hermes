package br.com.gustavobarez.hermes.Order;

public record UpdateOrderRequestDTO(String description, String originAddress, String deliveryAddress, Status status) {

}
