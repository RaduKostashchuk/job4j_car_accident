package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.service.AccidentService;
import ru.job4j.accident.service.RuleService;
import ru.job4j.accident.service.TypeService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AccidentControl {
    private final AccidentService accidentService;
    private final TypeService typeService;
    private final RuleService ruleService;

    public AccidentControl(AccidentService accidentService, TypeService typeService, RuleService ruleService) {
        this.accidentService = accidentService;
        this.typeService = typeService;
        this.ruleService = ruleService;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", typeService.getAll());
        model.addAttribute("rules", ruleService.getAll());
        return "accident/create";
    }

    @GetMapping("/update")
    public String edit(@RequestParam int id, Model model) {
        model.addAttribute("accident", accidentService.findById(id));
        return "accident/edit";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest request) {
        String[] ruleIds = request.getParameterValues("ruleIds");
        for (String ruleId : ruleIds) {
            int id = Integer.parseInt(ruleId);
            accident.addRule(ruleService.findById(id));
        }
        AccidentType type = typeService.findById(accident.getType().getId());
        accident.setType(type);
        accidentService.add(accident);
        return "redirect:/";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Accident accident) {
        Accident fromRep = accidentService.findById(accident.getId());
        fromRep.setName(accident.getName());
        accidentService.update(fromRep);
        return "redirect:/";
    }
}
