package com.ssummit.mvc.controller;

import com.ssummit.dto.CheckpointDto;
import com.ssummit.mapper.CheckpointMapper;
import com.ssummit.service.CheckpointService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@Slf4j
@RequestMapping("/checkpoints")
public class MVCCheckpointController {
    private final CheckpointService service;
    private final CheckpointMapper mapper;

    public MVCCheckpointController(CheckpointService service, CheckpointMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("")
    public String getAll(Model model) {
        model.addAttribute("checkpoints", service.listAll().stream().map(mapper::toDto).toList());
        return "checkpoints/viewAllCheckpoints";
    }

    @GetMapping("/add")
    public String create() {
        return "checkpoints/addCheckpoint";
    }

    @PostMapping("/add")
    public String create(@ModelAttribute("checkpointForm") CheckpointDto checkpointDto) {
        checkpointDto.setCreatedDateTime(LocalDateTime.now());
        checkpointDto.setCreatedBy("ADMIN");
        checkpointDto.setCreatedBy("Yes");
        checkpointDto.setDeletedBy("No");
        checkpointDto.setUpdatedBy("No");
        service.create(mapper.toEntity(checkpointDto));
        return "redirect:/checkpoints";
    }

}
