package com.paul.vehiclemanagement;

import com.paul.vehiclemanagement.domain.Vehicle;
import com.paul.vehiclemanagement.model.VehicleModel;
import com.paul.vehiclemanagement.repository.VehicleRepository;
import com.paul.vehiclemanagement.service.VehicleManagementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.Assert;

import java.util.Optional;


@SpringBootTest
class   VehicleManagementApplicationTests {

    @Autowired
	private VehicleManagementService vehicleManagementService;

	@MockBean
	private VehicleRepository vehicleRepository;

    @BeforeEach
	public void setUp(){
		Vehicle vehicle = Vehicle.builder()
                .vehicleId(1L)
                .VIN("WDBHA28E5VF557666")
                .build();
        Mockito.when(vehicleRepository.findById(vehicle.getVehicleId())).thenReturn(Optional.of(vehicle));
        Mockito.when(vehicleRepository.findById(2L)).thenReturn(Optional.empty());
	}

    @Test
    public void wheExistingID_thenVehicleShouldExist() {
        Long vehicleId = 1l;
        Optional<VehicleModel> foundVehicle = vehicleManagementService.getById(vehicleId);
        Assert.isTrue(foundVehicle.isPresent() && foundVehicle.get().getVehicleId().equals(vehicleId), "Vehicle Not found.");
    }

    @Test
    public void whenNonExistingID_thenVehicleShouldNotExist() {
        Long vehicleId = 11l;
        Optional<VehicleModel> foundVehicle = vehicleManagementService.getById(vehicleId);
        Assert.isTrue(!foundVehicle.isPresent(), "Vehicle should not be found.");
    }

}
