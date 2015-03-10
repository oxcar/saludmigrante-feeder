package com.copili.feeder.controller.rest;

import com.copili.feeder.domain.CopiChange;
import com.copili.feeder.domain.CopiChangeForm;
import com.copili.feeder.domain.CopiLearn;
import com.copili.feeder.domain.CopiLearnForm;
import com.copili.feeder.repository.mongodb.CopiRepository;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/api/copis")
public class CopisRestController extends BaseRestController {

    private final static Logger log = LoggerFactory.getLogger(CopisRestController.class);

    @Autowired
    private CopiRepository copiRepository;

    //----------------------------------------------------------------------
    // Feeds
    //----------------------------------------------------------------------

    @RequestMapping(value = "learn", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity saveCopiLearn(@RequestBody CopiLearnForm copiLearnForm) {
        log.info("Saving Copi Learn");
        List<CopiLearn> copis = copiLearnForm.toLearnCopis();
        if (CollectionUtils.isEmpty(copis)) {
            return new ResponseEntity<>(ApiResponse.withError("No se ha guardado ningun Copi"), HttpStatus.BAD_REQUEST);
        }
        for (CopiLearn copiLearn : copis) {
            copiRepository.save(copiLearn);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "change", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity saveCopiChange(@RequestBody CopiChangeForm copiChangeForm) {
        log.info("Saving Copi Change");
        CopiChange copiChange = copiChangeForm.toCopiChange();
        CopiChange savedCopiChange = copiRepository.save(copiChange);
        if (null == savedCopiChange) {
            return new ResponseEntity<>(ApiResponse.withError("No se ha guardado ningun Copi"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

}
