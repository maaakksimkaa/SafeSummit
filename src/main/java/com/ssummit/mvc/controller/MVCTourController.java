package com.ssummit.mvc.controller;

import com.ssummit.dto.TourDto;
import com.ssummit.mapper.TourMapper;
import com.ssummit.model.Tour;
import com.ssummit.service.TourService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/tours")
public class MVCTourController {
    private final TourService service;
    private final TourMapper mapper;

    public MVCTourController(TourService service, TourMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("")
    public String getAll(Model model) {
        List<TourDto> tours = service.listAll().stream().map(mapper::toDto).toList();
        for(TourDto dto : tours) {
            log.info(String.valueOf(dto.getStart_date()));
            log.info(String.valueOf(dto.getEnd_date()));
            log.info(String.valueOf(dto.getDescription()));
            log.info(String.valueOf(dto.getTitle()));
        }
        model.addAttribute("tours", service.listAll().stream().map(mapper::toDto).toList());
        return "tours/viewAllTours";
    }

    @GetMapping("/add")
    public String create() {
        return "tours/addTour";
    }

    @PostMapping("/add")
    public String create(@ModelAttribute("tourForm") TourDto tourDto) {
        service.create(mapper.toEntity(tourDto));
        return "redirect:/tours";
    }

}
