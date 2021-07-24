package com.paul.vehiclemanagement.model;

import com.paul.vehiclemanagement.domain.VehiclePart;
import lombok.*;

import java.io.Serializable;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class VehicleTypeModel implements Serializable {

    private Long vehicleTypeId;

    private String name;

    private List<VehiclePart> vehicleParts;
}
