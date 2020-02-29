package com.Skylink;

import Client.Request;
import DroneController.Drone;
import DroneController.DroneRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import Control.Scheduler;

import java.util.Objects;

@RestController
public class RequestController {

    Scheduler scheduler;

    @Autowired
    public RequestController(Scheduler scheduler){
        this.scheduler = scheduler;
    }

    @PostMapping(value = "/orderDrone")
    public Drone orderDrone(@RequestBody DroneRequest droneRequest) {
        System.out.println("StartX:" + droneRequest.getStartX() + " StartY:"+ droneRequest.getStartY() + " DestinationX:" +
                droneRequest.getDestinationX() + " DestinationY:"+ droneRequest.getDestinationY() + " Capacity:" + droneRequest.getCapacity());
        Request request = new Request(droneRequest.getStartX(), droneRequest.getStartY(),
                    droneRequest.getDestinationX(), droneRequest.getDestinationY(), droneRequest.getCapacity());
        return Objects.requireNonNull(scheduler.assignDrone(request));
    }
}
