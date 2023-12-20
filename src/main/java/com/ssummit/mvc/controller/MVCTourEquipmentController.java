package com.ssummit.mvc.controller;

import com.ssummit.dto.TourEquipmentDto;
import com.ssummit.mapper.TourEquipmentMapper;
import com.ssummit.service.TourEquipmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/equipments")
public class MVCTourEquipmentController {
    private final TourEquipmentService service;
    private final TourEquipmentMapper mapper;

    public MVCTourEquipmentController(TourEquipmentService service, TourEquipmentMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("")
    public String getAll(Model model) {
        model.addAttribute("equipments", service.listAll().stream().map(mapper::toDto).toList());
        return "equipments/viewAllEquipments";
    }

    @GetMapping("/add")
    public String create() {
        return "equipments/addEquipment";
    }

    @PostMapping("/add")
    public String create(@ModelAttribute("equipmentForm") TourEquipmentDto equipmentDto) {
        service.create(mapper.toEntity(equipmentDto));
        return "redirect:/equipments";
    }
}
