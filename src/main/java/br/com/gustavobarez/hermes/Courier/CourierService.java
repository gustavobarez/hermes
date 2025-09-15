package br.com.gustavobarez.hermes.Courier;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.gustavobarez.hermes.utils.aws.AwsSnsService;

@Service
public class CourierService {

    private final CourierRepository repository;

    private final AwsSnsService awsSnsService;

    public CourierService(CourierRepository repository, AwsSnsService awsSnsService) {
        this.repository = repository;
        this.awsSnsService = awsSnsService;
    }

    public CourierResponseDTO createCourier(CourierRequestDTO request) {
        Courier courier = Courier.builder()
                .name(request.name())
                .createdAt(LocalDateTime.now())
                .build();

        repository.save(courier);

        CourierResponseDTO response = new CourierResponseDTO(courier);

        awsSnsService.publishMessage("created-courier", response);

        return response;
    }

    public Optional<Courier> findById(Long courierId) {
        return repository.findById(courierId);
    }

}
