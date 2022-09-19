package com.instantsystem.parkingapi.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.instantsystem.parkingapi.Dto.Parking;
import com.instantsystem.parkingapi.Enum.IsFree;

import java.util.List;

public interface ParkingService {

    List<Parking> getAvailableParking(int diffDistance, Double xPosition, Double yPosition) throws JsonProcessingException;
    List<Parking> getWithFreeCriteria(Double xPosition, Double yPosition,IsFree isFree) throws JsonProcessingException ;

}
