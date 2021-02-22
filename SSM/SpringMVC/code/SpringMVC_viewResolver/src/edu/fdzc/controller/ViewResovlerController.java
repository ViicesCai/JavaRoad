package edu.fdzc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CAI
 * @time 2021/2/15
 */
@Controller
public class ViewResovlerController {

    @RequestMapping("/handleplus")
    public String handleplus(Model model) {
        List<String> viewNames = new ArrayList<>();
        List<String> photoNames = new ArrayList<>();

        viewNames.add("CAI");
        viewNames.add("JACK");

        photoNames.add("Hello");

        model.addAttribute("video", viewNames);
        model.addAttribute("photo", photoNames);

        return "photo:/show";
    } 
}
