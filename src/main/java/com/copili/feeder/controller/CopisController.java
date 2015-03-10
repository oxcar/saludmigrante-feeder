package com.copili.feeder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/copis")
public class CopisController {

    @RequestMapping(value = "learn/new", method = RequestMethod.GET)
    public String newLearnCopi() {
        return "new_copi_learn";
    }

    @RequestMapping(value = "change/new", method = RequestMethod.GET)
    public String newChangeCopi() {
        return "new_copi_change";
    }

}