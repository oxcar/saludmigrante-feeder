package com.copili.feeder.controller.rest;

import com.copili.feeder.domain.State;
import com.copili.feeder.repository.mongodb.StateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/api/states")
public class StateRestController extends BaseRestController {

    private final static Logger log = LoggerFactory.getLogger(StateRestController.class);

    @Autowired
    private StateRepository stateRepository;

    //----------------------------------------------------------------------
    // Feeds
    //----------------------------------------------------------------------

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getStates() {
        log.info("Loading States");
        List<State> states = stateRepository.findAll(new Sort(Sort.Direction.ASC, "state"));
        return new ResponseEntity<>(ApiResponse.ok(states), HttpStatus.OK);
    }

}
