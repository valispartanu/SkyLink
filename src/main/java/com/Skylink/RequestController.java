package com.Skylink;

import Client.Request;
import Control.Admin;
import DroneController.Drone;
import DroneController.DroneRequest;
import DroneController.DroneStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import Control.Scheduler;

import java.util.Objects;

@RestController
public class RequestController {

    Scheduler scheduler;
    Admin admin;

    @Autowired
    public RequestController(Scheduler scheduler){
        this.scheduler = scheduler;
        this.admin = Admin.getInstance();
    }

    @PostMapping(value = "/orderDrone", consumes = "application/json", produces = "application/json")
    public Integer orderDrone(@RequestBody DroneRequest droneRequest) {
        System.out.println("StartX:" + droneRequest.getStartX() + " StartY:"+ droneRequest.getStartY() + " DestinationX:" +
                droneRequest.getDestinationX() + " DestinationY:"+ droneRequest.getDestinationY() + " Capacity:" + droneRequest.getCapacity());
        Request request = new Request(droneRequest.getStartX(), droneRequest.getStartY(),
                    droneRequest.getDestinationX(), droneRequest.getDestinationY(), droneRequest.getCapacity());
        return Objects.requireNonNull(scheduler.assignDrone(request)).getId();
    }

    @PostMapping(value = "/statusDrone", consumes = "application/json", produces = "application/json")
    public Integer statusDrone(@RequestBody Integer id) {
//        System.out.println("id:" + id);
        Drone drone = admin.getDroneById(id);
        if(drone.getStatus() == DroneStatus.TOPICKUP)
            return 0;
        else
            return admin.getDroneById(id).getPercentage();

    }

}
