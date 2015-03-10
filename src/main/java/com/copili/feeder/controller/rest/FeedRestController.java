package com.copili.feeder.controller.rest;

import com.copili.feeder.domain.*;
import com.copili.feeder.repository.mongodb.CopiRepository;
import com.copili.feeder.repository.mongodb.FeedEntryRepository;
import com.copili.feeder.repository.mongodb.FeedRepository;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/api/feeds")
public class FeedRestController extends BaseRestController {

    private final static Logger log = LoggerFactory.getLogger(FeedRestController.class);

    @Autowired
    private FeedRepository feedRepository;

    @Autowired
    private FeedEntryRepository feedEntryRepository;

    @Autowired
    private CopiRepository copiRepository;

    //----------------------------------------------------------------------
    // Feeds
    //----------------------------------------------------------------------

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getFeeds() {
        log.info("Loading Feeds");
        List<Feed> feeds = feedRepository.findAll();
        return new ResponseEntity<>(ApiResponse.ok(feeds), HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity saveFeed(@RequestBody Feed feed) {
        log.info("Saving Feed");
        if (null == feed || StringUtils.isBlank(feed.getName()) || StringUtils.isBlank(feed.getUrl())) {
            return new ResponseEntity<>(ApiResponse.withError("El Feed no es correcto"), HttpStatus.BAD_REQUEST);
        }
        Feed savedFeed = feedRepository.findOneByUrl(feed.getUrl());
        if (null != savedFeed) {
            return new ResponseEntity<>(ApiResponse.withError("Ya existe un Feed con la URL " + feed.getUrl()), HttpStatus.BAD_REQUEST);
        }
        feed.setLastProcessedFeedEntry(new Date(0));
        return new ResponseEntity<>(ApiResponse.ok(feedRepository.save(feed)), HttpStatus.OK);
    }

    @RequestMapping(value = "{feedId}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity deleteFeed(@PathVariable("feedId") String feedId) {
        log.info("Deleting Feed {}", feedId);
        if (StringUtils.isBlank(feedId)) {
            return new ResponseEntity<>(ApiResponse.withError("El ID es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        Feed feed = feedRepository.findOne(feedId);
        if (null == feed) {
            return new ResponseEntity<>(ApiResponse.withError("No existe una Feed con ese ID"), HttpStatus.NOT_MODIFIED);
        }
        feedRepository.delete(feed);
        return new ResponseEntity(HttpStatus.OK);
    }

    //----------------------------------------------------------------------
    // Feed Entries
    //----------------------------------------------------------------------

    @RequestMapping(value = "{feedId}/entries/{feedEntryId}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity deleteFeedEntry(@PathVariable("feedId") String feedId, @PathVariable("feedEntryId") String feedEntryId) {
        log.info("Deleting Feed Entry {}", feedEntryId);
        if (StringUtils.isBlank(feedId) || StringUtils.isBlank(feedEntryId)) {
            return new ResponseEntity<>(ApiResponse.withError("El ID es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        FeedEntry feedEntry = feedEntryRepository.findOneByFeedIdAndId(feedId, feedEntryId);
        if (null == feedEntry) {
            return new ResponseEntity<>(ApiResponse.withError("No existe una entrada con ese ID"), HttpStatus.NOT_MODIFIED);
        }
        feedEntryRepository.delete(feedEntry);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "all-entries", method = RequestMethod.GET)
    public ResponseEntity loadFeedEntries() {
        Pageable pageRequest = new PageRequest(0, 10);
        Page<FeedEntry> feedEntries = feedEntryRepository.findAll(pageRequest);
        return new ResponseEntity<>(ApiResponse.ok(feedEntries.getContent()), HttpStatus.OK);
    }

    @RequestMapping(value = "{feedId}/entries/{feedEntryId}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity processFeedEntry(@PathVariable("feedId") String feedId, @PathVariable("feedEntryId") String feedEntryId, @RequestBody FeedEntryProcess fep) {
        if (StringUtils.isBlank(feedId) || StringUtils.isBlank(feedEntryId)) {
            return new ResponseEntity<>(ApiResponse.withError("El ID es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (null == fep.getStateCode()) {
            return new ResponseEntity<>(ApiResponse.withError("Es necesario seleccionar un Estado"), HttpStatus.BAD_REQUEST);
        }
        FeedEntry feedEntry = feedEntryRepository.findOneByFeedIdAndId(feedId, feedEntryId);
        if (null == feedEntry) {
            return new ResponseEntity<>(ApiResponse.withError("No existe una entrada con ese ID"), HttpStatus.BAD_REQUEST);
        }

        Copi previousCopi = copiRepository.findOneByUrl(feedEntry.getUri());
        if (null != previousCopi) {
            copiRepository.delete(previousCopi);
        }

        String[] words = StringUtils.split(fep.getWords(), ",");

        // Guardamos el Copi en MongoDB
        CopiExperience copi = new CopiExperience();
        copi.setText(feedEntry.getTitle());
        copi.setState(fep.getStateCode());
        copi.setUrl(feedEntry.getUri());
        copi.setKeywords(Arrays.asList(words));
        copi.setSource(feedEntry.getSource());
        copi.setPublishedDate(feedEntry.getPublishedDate());

        CopiExperience indexedCopi = copiRepository.save(copi);
        if (null == indexedCopi) {
            return new ResponseEntity<>(ApiResponse.withError("No se pudo indexar el Copi"), HttpStatus.BAD_REQUEST);
        }

        // Marcamos la entrada de feed como procesada
        feedEntry.setProcessed(true);
        feedEntry.setState(fep.getStateCode());
        feedEntry.setMatchedWords(Arrays.asList(words));
        feedEntryRepository.save(feedEntry);

        return new ResponseEntity(HttpStatus.OK);
    }


}
