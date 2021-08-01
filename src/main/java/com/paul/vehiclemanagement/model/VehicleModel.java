package com.paul.vehiclemanagement.model;

import com.paul.vehiclemanagement.Utils.VehicleDateUtils;
import com.paul.vehiclemanagement.domain.Vehicle;
import com.paul.vehiclemanagement.domain.VehicleType;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class VehicleModel implements Serializable {

    private Long vehicleId;

    @NotEmpty(message = "VIN cannot be empty.")
    @Size(min = 5, max = 25, message = "VIN size must be between 5 and 25.")
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "VIN must not contain special characters.")
    private String VIN;

    @NotEmpty(message = "Plate Number cannot be empty.")
    @Size(min = 3, max = 10, message = "Plate Number size must be between 3 and 10")
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "Plate Number must not contain special characters.")
    private String plateNumber;

    @Pattern(regexp = VehicleDateUtils.REGEX_DATE_PATTERN, message = "Date of Registration is not valid.")
    private String dateOfRegistration;

    private VehicleTypeModel vehicleTypeModel;

    public VehicleModel(Vehicle v) {
        this.vehicleId = v.getVehicleId();
        this.plateNumber = v.getPlateNumber();
        this.VIN = v.getVIN();
        if(v.getDateOfRegistration() instanceof LocalDate) {
            this.dateOfRegistration = v.getDateOfRegistration().format(VehicleDateUtils.FORMATTER);
        }

        if(v.getVehicleType() instanceof VehicleType) {
            this.vehicleTypeModel = VehicleTypeModel.builder()
                    .name(v.getVehicleType().getName())
                    .vehicleTypeId(v.getVehicleType().getVehicleTypeId())
                    .build();
        }
    }
}
