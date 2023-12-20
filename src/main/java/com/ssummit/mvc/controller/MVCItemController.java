package com.ssummit.mvc.controller;

import com.ssummit.dto.ItemDto;
import com.ssummit.mapper.ItemMapper;
import com.ssummit.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/items")
public class MVCItemController {
    private final ItemService service;
    private final ItemMapper mapper;

    public MVCItemController(ItemService service, ItemMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("")
    public String getAll(Model model) {
        model.addAttribute("items", service.listAll().stream().map(mapper::toDto).toList());
        return "items/viewAllItems";
    }

    @GetMapping("/add")
    public String create() {
        return "items/addItem";
    }

    @PostMapping("/add")
    public String create(@ModelAttribute("itemForm") ItemDto itemDto) {
        service.create(mapper.toEntity(itemDto));
        return "redirect:/items";
    }
}
