package com.paul.vehiclemanagement;

import com.paul.vehiclemanagement.Utils.VehicleDateUtils;
import com.paul.vehiclemanagement.domain.Vehicle;
import com.paul.vehiclemanagement.domain.VehicleType;
import com.paul.vehiclemanagement.model.VehicleModel;
import com.paul.vehiclemanagement.model.VehicleTypeModel;
import com.paul.vehiclemanagement.repository.VehicleRepository;
import com.paul.vehiclemanagement.repository.VehicleTypeRepository;
import com.paul.vehiclemanagement.service.VehicleManagementService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.Optional;


@SpringBootTest
class   VehicleManagementApplicationTests {

    @Autowired
	private VehicleManagementService vehicleManagementService;

	@MockBean
	private VehicleRepository vehicleRepository;

    @MockBean
    private VehicleTypeRepository vehicleTypeRepository;

    @BeforeEach
	public void setUp(){
        VehicleType type = VehicleType.builder()
                .vehicleTypeId(1L)
                .name("PASSENGER VEHICLE")
                .build();

        Vehicle vehicle = Vehicle.builder()
                .vehicleId(1L)
                .VIN("WDBHA28E5VF557666")
                .plateNumber("CJ01AAA")
                .build();

        Mockito.when(vehicleRepository.findById(vehicle.getVehicleId())).thenReturn(Optional.of(vehicle));
        Mockito.when(vehicleRepository.findById(2L)).thenReturn(Optional.empty());
        Mockito.when(vehicleTypeRepository.findByName("PASSENGER VEHICLE")).thenReturn(Optional.of(type));
        Mockito.when(vehicleRepository.save(ArgumentMatchers.any(Vehicle.class))).thenReturn(getTestEntity());
	}

    @Test
    public void whenExistingID_thenVehicleShouldExist() {
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

    @Test
    public void whenExistingVehicle_thenVehicleShouldUpdate() {
        VehicleModel model = getTestModel();
        Optional<VehicleModel> foundVehicle = vehicleManagementService.saveOrUpdate(model);

        Assertions.assertEquals(foundVehicle.isPresent(), true);
        Assertions.assertEquals(foundVehicle.get().getVehicleId(), model.getVehicleId());
        Assertions.assertEquals(foundVehicle.get().getPlateNumber(), model.getPlateNumber());
    }

    private static VehicleModel getTestModel(){
        VehicleTypeModel typeModel = VehicleTypeModel.builder().name("PASSENGER VEHICLE").build();
        VehicleModel model = new VehicleModel(1l,"WDBHA28E5VF557666", "CJ01AAB", "10/10/2020",typeModel);
        return model;
    }

    private static Vehicle getTestEntity() {
        return Vehicle.builder()
                .vehicleId(1L)
                .VIN("WDBHA28E5VF557666")
                .plateNumber("CJ01AAB")
                .dateOfRegistration(LocalDate.parse("10/10/2020", VehicleDateUtils.FORMATTER))
                .build();
    }
}
