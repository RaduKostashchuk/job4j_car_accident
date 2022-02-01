package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;

import java.util.List;

@Controller
public class IndexControl {
    private final AccidentMem store;

    public IndexControl(AccidentMem store) {
        this.store = store;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Accident> accidents = store.getAllAccidents();
        model.addAttribute("accidents", accidents);
        return "index";
    }
}