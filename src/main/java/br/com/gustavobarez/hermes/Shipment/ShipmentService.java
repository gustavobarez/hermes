package br.com.gustavobarez.hermes.Shipment;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import br.com.gustavobarez.hermes.Courier.CourierResponseDTO;
import br.com.gustavobarez.hermes.Courier.CourierService;
import br.com.gustavobarez.hermes.Order.OrderResponseDTO;
import br.com.gustavobarez.hermes.Order.OrderService;

@Service
public class ShipmentService {

    private final ShipmentRepository repository;
    private final CourierService courierService;
    private final OrderService orderService;

    public ShipmentService(ShipmentRepository repository, CourierService courierService, OrderService orderService) {
        this.repository = repository;
        this.courierService = courierService;
        this.orderService = orderService;
    }

    public ShipmentResponseDTO createShipment(ShipmentRequestDTO request) {

        var courier = courierService.findById(request.courierId());
        var order = orderService.findById(request.orderId());

        Shipment shipment = Shipment.builder()
                .order(order.get())
                .courier(courier.get())
                .attributedAt(request.attributedAt())
                .createdAt(LocalDateTime.now())
                .build();

        repository.save(shipment);

        CourierResponseDTO courierResponseDTO = new CourierResponseDTO(courier.get());
        OrderResponseDTO orderResponseDTO = new OrderResponseDTO(order.get());

        ShipmentResponseDTO response = new ShipmentResponseDTO(shipment.getId(), orderResponseDTO, courierResponseDTO, shipment.getAttributedAt());


        return response;
    }

}
