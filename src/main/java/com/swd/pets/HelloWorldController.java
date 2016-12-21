package com.swd.pets;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Administrator on 2016/12/21.
 */
@Controller
@RequestMapping("/")
public class HelloWorldController {
    @RequestMapping(method = RequestMethod.GET)
    public String HelloSpringMVC(ModelMap model) {
        model.addAttribute("Hello", "Hello SpringMVC");
        return "helloSpringmvc";
    }

    @RequestMapping(value="/hiSpringmvc", method = RequestMethod.GET)
    public String HiSpringMVC(ModelMap model) {
        model.addAttribute("Hi", "Hi SpringMVC");
        return "hiSpringmvc";
    }
}
