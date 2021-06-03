package org.hillel.controller.tl;

import org.hillel.controller.dto.JourneyDto;
import org.hillel.controller.dto.StopDto;
import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.persistence.entity.StopEntity;
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
public class StopTLController {
    private List<StopEntity> list;
    private final TicketClient ticketClient;

    public StopTLController(TicketClient ticketClient) {
        this.ticketClient = ticketClient;
    }

    @GetMapping("/stops/sort")
    public String stops(Model model){
        model.addAttribute("sort", list);
        return "stops_sort_view";
    }

    @PostMapping("/stops/sorting")
    public RedirectView sortingStops(@ModelAttribute("stopSort") StopDto stopDto){
        list = ticketClient.pageSortStops(stopDto.getPage(), stopDto.getPageSize(),
                stopDto.getExp(), stopDto.getDirection());
        return new RedirectView("/tl/stops/sort");
    }

    @GetMapping("/stops/{stopId}")
    public String showAllInfo(@PathVariable("stopId") long id, Model model){
        Optional<StopEntity> byIdStop = ticketClient.findByIdStop(id);
        StopEntity entity = byIdStop.get();
        model.addAttribute("show", entity);
        return "stop_show_all";
    }
}
