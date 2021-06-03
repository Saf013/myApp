package org.hillel.controller.tl;

import org.hillel.controller.converter.VehicleMapper;
import org.hillel.controller.dto.VehicleDto;
import org.hillel.persistence.entity.VehicleEntity;
import org.hillel.service.TicketClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Controller
public class VehicleTLController {
    private List<VehicleEntity> list;

    private final TicketClient ticketClient;

    private final VehicleMapper vehicleMapper;

    public VehicleTLController(TicketClient ticketClient, VehicleMapper vehicleMapper) {
        this.ticketClient = ticketClient;
        this.vehicleMapper = vehicleMapper;
    }

    @GetMapping("/vehicles")
    public String vehiclesPage(Model model) {
        Collection<VehicleEntity> allVehicles = ticketClient.findAllVehicles();
        model.addAttribute("vehicles", allVehicles);
        return "vehicles_view";
    }

    @GetMapping("/vehicles/delete/{vehicleId}")
    public RedirectView deleteVehicle(@PathVariable("vehicleId") long id) {
        ticketClient.removeByIdVehicle(id);
        return new RedirectView("/tl/vehicle/vehicles");
    }

    @GetMapping("/vehicles/sort")
    public String sort(Model model) {
        //Collection<VehicleEntity> vehicleEntities = ticketClient.pageSortVehicle(0, 10, VehicleEntity_.ID, Sort.Direction.DESC);
        model.addAttribute("sort", list);
        return "vehicles_sort_view";
    }

    @PostMapping("/vehicles/sorting")
    public RedirectView sorting(@ModelAttribute("vehSort") VehicleDto vehicleDto) {
        list = ticketClient.pageSortVehicle(vehicleDto.getPage(), vehicleDto.getPageSize(), vehicleDto.getExp(), vehicleDto.getDirection());
        return new RedirectView("/tl/vehicles/sort");
    }

    @GetMapping("/vehicles/{vehicleId}")
    public String showAllInfo(@PathVariable("vehicleId") long id, Model model){
        Optional<VehicleEntity> byIdVehicle = ticketClient.findByIdVehicle(id);
        VehicleEntity entity = byIdVehicle.get();
        model.addAttribute("show", entity);
        return "vehicle_show_all";
    }

}
