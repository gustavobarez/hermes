package br.com.gustavobarez.hermes.Order;

public record OrderResponseDTO(Long id, String description, String originAddress, String deliveryAddress,
        Status status) {
    public OrderResponseDTO(Order order) {
        this(
                order.getId(),
                order.getDescription(),
                order.getOriginAddress(),
                order.getDeliveryAddress(),
                order.getStatus());
    }
}
