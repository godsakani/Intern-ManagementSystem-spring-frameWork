package com.godydev;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    //handling request
    @GetMapping("")
    public String showHomepage() {
        return "index";
    }

}
