package com.Skylink;

import Client.Request;
import Control.Admin;
import DroneController.Drone;
import DroneController.DroneRequest;
import DroneController.DroneStatus;
import lombok.Getter;
import lombok.Setter;
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
    public RequestController(Scheduler scheduler, Admin admin){
        this.scheduler = scheduler;
        this.admin = admin;
    }

    @PostMapping(value = "/orderDrone", consumes = "application/json", produces = "application/json")
    public Warper orderDrone(@RequestBody DroneRequest droneRequest) {
        System.out.println("StartX:" + droneRequest.getStartX() + " StartY:"+ droneRequest.getStartY() + " DestinationX:" +
                droneRequest.getDestinationX() + " DestinationY:"+ droneRequest.getDestinationY() + " Capacity:" + droneRequest.getCapacity());
        Request request = new Request(droneRequest.getStartX(), droneRequest.getStartY(),
                    droneRequest.getDestinationX(), droneRequest.getDestinationY(), droneRequest.getCapacity());
        Drone drone = scheduler.assignDrone(request);
        Warper warper = new Warper();
        warper.id = drone.getId();
        warper.status = drone.getStatus();
        warper.x = drone.getX();
        warper.y = drone.getY();
        warper.percentage = 0;
        return warper;
    }

    @PostMapping(value = "/statusDrone", consumes = "application/json", produces = "application/json")
    public Warper statusDrone(@RequestBody Integer id) {
        Drone drone = admin.getDroneById(id);
        Warper warper = new Warper();
        warper.id = id;
        warper.status = drone.getStatus();
        warper.x = drone.getX();
        warper.y = drone.getY();
        warper.status = drone.getStatus();
        if(warper.status == DroneStatus.TOPICKUP) {
            warper.percentage = 0;
            return warper;
        } else if (warper.status == DroneStatus.SLEEPING) {
            warper.percentage = 100;
            return  warper;
        } else {
            warper.percentage = admin.getDroneById(id).getPercentage();
            if(warper.percentage >= 98) warper.status = DroneStatus.SLEEPING;
            return warper;
        }
    }

    private class Warper {
        @Getter
        private Integer id ;
        @Getter
        private Double x ;
        @Getter
        private Double y ;
        @Getter
        private DroneStatus status;
        @Getter
        private Integer percentage;
    }
}
