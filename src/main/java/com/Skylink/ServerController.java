package com.Skylink;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ServerController {

    @GetMapping(value = "/")
    public String index() {
        return "index";
    }
}
