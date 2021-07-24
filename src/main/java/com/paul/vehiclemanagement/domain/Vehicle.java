package com.paul.vehiclemanagement.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "vehicle",uniqueConstraints = {
        @UniqueConstraint(columnNames = "VIN")
})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vehicleId;

    @Column(nullable = false)
    private String VIN;

    @Column
    private String plateNumber;

    @Column
    private LocalDate dateOfRegistration;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vehicleTypeId", nullable = false)
    private VehicleType vehicleType;

}
