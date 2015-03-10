package com.copili.feeder.controller;

import com.copili.feeder.repository.mongodb.CopiRepository;
import com.copili.feeder.repository.mongodb.OngRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/ongs")
public class OngsController {

    @Autowired
    private OngRepository ongRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String loadOngs() {
        return "ongs";
    }

    @RequestMapping(value = "new", method = RequestMethod.GET)
    public String newOng() {
        return "new_ong";
    }

}