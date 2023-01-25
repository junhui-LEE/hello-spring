package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")  // 첫번째 => localhost:8080 으로 들어오면 호출된다.
    public String home(){
        return "home";   // 들어오면 home.html 이 호출이 된다.
    }
}
