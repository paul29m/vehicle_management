package com.paul.vehiclemanagement.model;

import com.paul.vehiclemanagement.domain.VehicleType;
import lombok.*;

import java.io.Serializable;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class VehiclePartModel  implements Serializable {

    private Long vehiclePartId;

    private String name;

    private List<VehicleType> vehicleTypes;
}
