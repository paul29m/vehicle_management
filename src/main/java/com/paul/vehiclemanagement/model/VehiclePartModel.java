package com.paul.vehiclemanagement.model;

import com.paul.vehiclemanagement.domain.VehiclePart;
import lombok.*;

import java.io.Serializable;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString(exclude = "vehiclePartId")
public class VehiclePartModel implements Serializable {

    private Long vehiclePartId;

    private String name;

    public VehiclePartModel(VehiclePart vehiclePart) {
        this.vehiclePartId = vehiclePart.getVehiclePartId();
        this.name = vehiclePart.getName();
    }
}
