package com.paul.vehiclemanagement.model;

import com.paul.vehiclemanagement.domain.VehiclePart;
import com.paul.vehiclemanagement.domain.VehicleType;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class VehicleTypeModel implements Serializable {

    private Long vehicleTypeId;

    @NotEmpty(message = "Vehicle Type cannot be empty.")
    @Size(min = 1, max = 255, message = "Vehicle Type size must be between 1 and 25.")
    @Pattern(regexp = "[a-zA-Z0-9 ]+", message = "Vehicle Type must not contain special characters.")
    private String name;

    private List<VehiclePartModel> vehicleParts;

    private String vehiclePartsStringList;

    public VehicleTypeModel(VehicleType type) {
        this.vehicleTypeId = type.getVehicleTypeId();
        this.name = type.getName();
        if (type.getVehicleParts() != null && !type.getVehicleParts().isEmpty()) {
            this.vehicleParts = type.getVehicleParts().stream().map(VehiclePartModel::new).collect(Collectors.toList());
            this.vehiclePartsStringList = type.getVehicleParts().stream().map(VehiclePart::getName).collect(Collectors.joining(","));
        }
    }
}
