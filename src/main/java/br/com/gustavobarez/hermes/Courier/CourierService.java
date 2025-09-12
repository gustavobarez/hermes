package br.com.gustavobarez.hermes.Courier;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class CourierService {

    private final CourierRepository repository;

    public CourierService(CourierRepository repository) {
        this.repository = repository;
    }

    public CourierResponseDTO createCourier(CourierRequestDTO request) {

        Courier courier = Courier.builder()
                .name(request.name())
                .createdAt(LocalDateTime.now())
                .build();

        repository.save(courier);

        CourierResponseDTO response = new CourierResponseDTO(courier);

        return response;

    }

    public Optional<Courier> findById(Long courierId) {
        return repository.findById(courierId);
    }

}
