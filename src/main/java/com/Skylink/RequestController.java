package com.Skylink;

import DroneController.DroneRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestController {

    @PostMapping(value = "/orderDrone")
    public String orderDrone(@RequestBody DroneRequest droneRequest) {
        tryRequest(droneRequest.getStartX(), droneRequest.getStartY(),
                    droneRequest.getDestinationX(), droneRequest.getDestinationY());
        //TODO implement response
        return "Success";
    }
}
