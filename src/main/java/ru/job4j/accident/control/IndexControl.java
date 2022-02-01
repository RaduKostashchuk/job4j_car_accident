package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;

import java.util.List;

@Controller
public class IndexControl {
    @GetMapping("/")
    public String index(Model model) {
        AccidentMem store = new AccidentMem();
        Accident accident1 = Accident.of("Инцидент №1", "Превышение скорости", "Приморская 23");
        Accident accident2 = Accident.of("Инцидент №2", "Парковка на газоне", "Есенина 45");
        store.add(accident1);
        store.add(accident2);
        List<Accident> accidents = store.getAllAccidents();
        model.addAttribute("accidents", accidents);
        return "index";
    }
}