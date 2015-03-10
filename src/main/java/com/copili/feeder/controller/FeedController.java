package com.copili.feeder.controller;

import com.copili.feeder.domain.FeedEntry;
import com.copili.feeder.repository.mongodb.FeedEntryRepository;
import com.copili.feeder.repository.mongodb.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/feeds")
public class FeedController {

    @Autowired
    private FeedEntryRepository feedEntryRepository;

    @Autowired
    private StateRepository stateRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String loadFeedsPage() {
        return "feeds";
    }

    @RequestMapping(value = "all-entries", method = RequestMethod.GET)
    public String loadFeedEntries() {
        return "feed_entries";
    }

    @RequestMapping(value = "{feedId}/entries/{entryId}", method = RequestMethod.GET)
    public String loadFeedEntry(@PathVariable String feedId, @PathVariable String entryId, Model model) {
        FeedEntry feedEntry = feedEntryRepository.findOneByFeedIdAndId(feedId, entryId);
        model.addAttribute("feedEntry", feedEntry);
        model.addAttribute("states", stateRepository.findAll(new Sort(Sort.Direction.ASC, "state")));
        return "feed_entry_detail";
    }

}