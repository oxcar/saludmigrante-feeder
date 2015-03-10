package com.copili.feeder.domain.listener;

import com.copili.feeder.domain.Copi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.PrePersist;

public class CopiListener {

    private final static Logger log = LoggerFactory.getLogger(CopiListener.class);

    @PrePersist
    protected void prePersist(Copi copi) {
        log.info("@PrePersist : {}", copi.getClass());
        copi.setIndexed(false);
    }

}
