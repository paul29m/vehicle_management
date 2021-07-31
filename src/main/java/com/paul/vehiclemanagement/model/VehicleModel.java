package com.paul.vehiclemanagement.model;

import com.paul.vehiclemanagement.Utils.VehicleDateUtils;
import com.paul.vehiclemanagement.domain.Vehicle;
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
public class VehicleModel implements Serializable {

    private Long vehicleId;

    @NotEmpty(message = "VIN cannot be empty.")
    @Size(min = 5, max = 25)
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "VIN must not contain special characters.")
    private String VIN;

    @NotEmpty(message = "Plate Number cannot be empty.")
    @Size(min = 5, max = 10)
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "Plate Number must not contain special characters.")
    private String plateNumber;

    @Pattern(regexp = VehicleDateUtils.REGEX_DATE_PATTERN, message = "Date is not valid.")
    private String dateOfRegistration;

    private VehicleTypeModel vehicleTypeModel;

    public VehicleModel(Vehicle v) {
        this.vehicleId = v.getVehicleId();
        this.plateNumber = v.getPlateNumber();
        this.VIN = v.getVIN();
        this.dateOfRegistration = v.getDateOfRegistration().format(VehicleDateUtils.FORMATTER);
        this.vehicleTypeModel = VehicleTypeModel.builder()
                .name(v.getVehicleType().getName())
                .vehicleTypeId(v.getVehicleType().getVehicleTypeId())
                .build();
    }
}
