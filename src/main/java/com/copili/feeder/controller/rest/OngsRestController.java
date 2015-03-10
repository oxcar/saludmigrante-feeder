package com.copili.feeder.controller.rest;

import com.copili.feeder.domain.Ong;
import com.copili.feeder.repository.mongodb.OngRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/api/ongs")
public class OngsRestController extends BaseRestController {

    private final static Logger log = LoggerFactory.getLogger(OngsRestController.class);

    @Autowired
    private OngRepository ongRepository;

    //----------------------------------------------------------------------
    // Ongs
    //----------------------------------------------------------------------

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getOngs() {
        log.info("Saving Copi Learn");
        List<Ong> ongs = ongRepository.findAll();
        return new ResponseEntity<>(ApiResponse.ok(ongs), HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity saveOng(@RequestBody Ong ong) {
        log.info("Saving ONG");
        ongRepository.save(ong);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "{ongId}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity deleteOng(@PathVariable String ongId) {
        log.info("Deleting ONG");
        Ong ong = ongRepository.findOne(ongId);
        if(null == ong) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        ongRepository.delete(ong);
        return new ResponseEntity(HttpStatus.OK);
    }

}
