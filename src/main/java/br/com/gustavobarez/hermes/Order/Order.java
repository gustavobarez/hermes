package br.com.gustavobarez.hermes.Order;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String description;

    @NotBlank
    private String originAddress;

    @NotBlank
    private String deliveryAddress;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Status status;

    @Column(name = "created_At")
    private LocalDateTime createdAt;

    @Column(name = "updated_At")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_At")
    private LocalDateTime deletedAt;

}
