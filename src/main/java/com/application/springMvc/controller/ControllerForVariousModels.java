package com.application.springMvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Example of using Model, ModelMap and ModelAndView
 * @author Ihor Savchenko
 * @version 1.0
 */
@Controller
public class ControllerForVariousModels {

    @GetMapping("/showViewPage")
    public String passParametersWithModel(Model model) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("spring", "mvc");
        model.addAttribute("message", "Ihor Savchenko");
        model.mergeAttributes(map);
        return "viewPage";
    }

    @GetMapping("/printViewPage")
    public String passParametersWithModelMap(ModelMap map) {
        Map<String, String> map2 = new HashMap<String, String>();
        map2.put("spring", "mvc");
        map.addAttribute("welcomeMessage", "welcome");
        map.addAttribute("message", "Ihor Savchenko");
        map.mergeAttributes(map2);
        return "viewPage";
    }

    @GetMapping("/goToViewPage")
    public ModelAndView passParametersWithModelAndView() {
        ModelAndView modelAndView = new ModelAndView("viewPage");
        modelAndView.addObject("message", "Ihor Savchenko");
        return modelAndView;
    }

}
