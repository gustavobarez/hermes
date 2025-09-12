package br.com.gustavobarez.hermes.Order;

public record CreateOrderResponseDTO(Long id, String description, String originAddress, String deliveryAddress,
        Status status) {
    public CreateOrderResponseDTO(Order order) {
        this(
                order.getId(),
                order.getDescription(),
                order.getOriginAddress(),
                order.getDeliveryAddress(),
                order.getStatus());
    }
}
