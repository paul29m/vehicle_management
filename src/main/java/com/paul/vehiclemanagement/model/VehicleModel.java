package com.paul.vehiclemanagement.model;

import com.paul.vehiclemanagement.Utils.DateUtils;
import com.paul.vehiclemanagement.domain.Vehicle;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class VehicleModel  implements Serializable {

    private Long vehicleId;

    private String VIN;

    private String plateNumber;

    private String dateOfRegistration;

    private VehicleTypeModel vehicleTypeModel;

    public VehicleModel(Vehicle v) {
        this.plateNumber = v.getPlateNumber();
        this.VIN = v.getVIN();
        this.dateOfRegistration = v.getDateOfRegistration().format(DateUtils.formater);
        this.vehicleTypeModel = VehicleTypeModel.builder().name(v.getVehicleType().getName()).build();
    }
}
