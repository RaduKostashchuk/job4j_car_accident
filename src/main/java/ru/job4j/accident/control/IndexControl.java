package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexControl {
    @GetMapping("/")
    public String index(Model model) {
        List<String> data = List.of("value1", "value2", "value3");
        model.addAttribute("user", "admin");
        model.addAttribute("data", data);
        return "index";
    }
}