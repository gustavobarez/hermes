package br.com.gustavobarez.hermes.Delivery;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "deliveries")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @Column(name = "created_At")
    private LocalDateTime createdAt;

    @Column(name = "updated_At")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_At")
    private LocalDateTime deletedAt;

}
