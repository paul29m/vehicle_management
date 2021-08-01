package com.paul.vehiclemanagement.model;

import com.paul.vehiclemanagement.domain.VehiclePart;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString(exclude = "vehiclePartId")
public class VehiclePartModel implements Serializable {

    private Long vehiclePartId;

    @NotEmpty(message = "Vehicle Part Name cannot be empty.")
    @Size(min = 1, max = 255, message = "Part name size must be between 1 and 255.")
    @Pattern(regexp = "[a-zA-Z0-9 ]+", message = "Part name must not contain special characters.")
    private String name;

    public VehiclePartModel(VehiclePart vehiclePart) {
        this.vehiclePartId = vehiclePart.getVehiclePartId();
        this.name = vehiclePart.getName();
    }
}
