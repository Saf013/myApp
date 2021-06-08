package org.hillel.controller.tl;

import org.hillel.controller.dto.JourneyDto;
import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.service.TicketClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@Controller
public class JourneyTLController {

    private List<JourneyEntity> list;
    private TicketClient ticketClient;

    public JourneyTLController(TicketClient ticketClient) {
        this.ticketClient = ticketClient;
    }

    @GetMapping("/journeys/sort")
    public String journeys(Model model){
        model.addAttribute("sort", list);
        return "journeys_sort_view";
    }

    @PostMapping("/journeys/sorting")
    public RedirectView sortingJourneys(@ModelAttribute("joSort")JourneyDto journeyDto){
        list = ticketClient.pageSortJourney(journeyDto.getPage(), journeyDto.getPageSize(),
                journeyDto.getExp(), journeyDto.getDirection());
        return new RedirectView("/tl/journeys/sort");
    }

    @GetMapping("/journeys/{journeyId}")
    public String showAllInfo(@PathVariable("journeyId") long id, Model model){
        Optional<JourneyEntity> byIdJourney = ticketClient.findByIdJourney(id);
        JourneyEntity entity = byIdJourney.get();
        model.addAttribute("show", entity);
        return "journey_show_all";
    }
}
