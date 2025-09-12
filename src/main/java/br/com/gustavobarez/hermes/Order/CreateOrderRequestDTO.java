package br.com.gustavobarez.hermes.Order;

public record CreateOrderRequestDTO(String description, String originAddress, String deliveryAddress, Status status) {

}
