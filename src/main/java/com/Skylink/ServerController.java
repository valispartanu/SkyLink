package com.Skylink;

import DroneController.Drone;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ServerController {

    @GetMapping(value = "/")
    public String index() {
        return "index";
    }

}
