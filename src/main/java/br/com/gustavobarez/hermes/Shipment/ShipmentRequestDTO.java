package br.com.gustavobarez.hermes.Shipment;

import java.time.LocalDateTime;

public record ShipmentRequestDTO(Long orderId, Long courierId, LocalDateTime attributedAt) {

}
