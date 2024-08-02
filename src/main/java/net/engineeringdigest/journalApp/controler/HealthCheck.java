package net.engineeringdigest.journalApp.controler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {
    @GetMapping("/health-check")
    public String healthCheck(){
        return "ok";
    }
    @GetMapping("/print-hello")
    public String printHello(){
        return "Hello Everyone";
    }
    @GetMapping("/user/id")
    public String getUser() {
        return "manish kumar";
    }
    @GetMapping("/greet/{name}")
    public String greet(@PathVariable String name) {
        return "karishma " + name + "!";
    }

}
