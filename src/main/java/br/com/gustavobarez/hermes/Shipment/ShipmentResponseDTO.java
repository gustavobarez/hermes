package br.com.gustavobarez.hermes.Shipment;

import java.time.LocalDateTime;

import br.com.gustavobarez.hermes.Courier.CourierResponseDTO;
import br.com.gustavobarez.hermes.Order.OrderResponseDTO;

public record ShipmentResponseDTO(Long id, OrderResponseDTO order, CourierResponseDTO courier,
        LocalDateTime attributedAt) {

}
