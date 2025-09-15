package br.com.gustavobarez.hermes.Shipment;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.gustavobarez.hermes.Courier.CourierResponseDTO;
import br.com.gustavobarez.hermes.Courier.CourierService;
import br.com.gustavobarez.hermes.Order.OrderResponseDTO;
import br.com.gustavobarez.hermes.Order.OrderService;
import br.com.gustavobarez.hermes.utils.aws.AwsSnsService;

@Service
public class ShipmentService {

    private final ShipmentRepository repository;
    private final CourierService courierService;
    private final OrderService orderService;
    private final AwsSnsService awsSnsService;
    private static final Logger logger = LoggerFactory.getLogger(CourierService.class);

    public ShipmentService(ShipmentRepository repository, CourierService courierService, OrderService orderService,
            AwsSnsService awsSnsService) {
        this.repository = repository;
        this.courierService = courierService;
        this.orderService = orderService;
        this.awsSnsService = awsSnsService;
    }

    public ShipmentResponseDTO createShipment(ShipmentRequestDTO request) {
        logger.info("Iniciando processamento do service");
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

        ShipmentResponseDTO response = new ShipmentResponseDTO(shipment.getId(), orderResponseDTO, courierResponseDTO,
                shipment.getAttributedAt());

        awsSnsService.publishMessage("created-shipment", response);
        logger.info("Processamento concluído com sucesso");
        return response;
    }

}
