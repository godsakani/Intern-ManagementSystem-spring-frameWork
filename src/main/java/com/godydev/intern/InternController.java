package com.godydev.intern;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class InternController {
    @Autowired public InternService service;

    @GetMapping( "/interns" )
    public String showInternList(Model model) {
        List<Intern> listInterns = service.listAll();
          model.addAttribute("listInterns", listInterns);


        return "interns";
    }

    @GetMapping("/interns/new")
    public String showNewForm(Model model) {
        model.addAttribute("intern", new Intern());
        model.addAttribute("pageTitle", "Add New Intern ");

        return "intern_form";
    }

    @PostMapping("/interns/save")
    public String saveIntern (Intern intern, RedirectAttributes ra) {
        service.save(intern);
        ra.addFlashAttribute("message", "Intern has been saved successful.");
        return "redirect:/interns";
    }

    @GetMapping("/interns/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
          Intern intern = service.get(id);
          model.addAttribute("intern", intern);
          model.addAttribute("pageTitle", "Edit  Intern Details (ID: " + id + ")");

          return "intern_form";

        } catch (InternNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());

            return "redirect:/interns";
        }
    }
    @GetMapping("/interns/delete/{id}")
    public String deleteIntern(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
           service.delete(id);
           ra.addFlashAttribute("message", "The Intern ID " + id + " has been deleted. ");
        } catch (InternNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/interns";
    }

}
