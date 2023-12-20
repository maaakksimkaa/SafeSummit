package com.ssummit.mvc.controller;

import com.ssummit.dto.RoleDto;
import com.ssummit.mapper.RoleMapper;
import com.ssummit.model.Role;
import com.ssummit.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/roles")
@Slf4j
public class MVCRoleController {
    private final RoleService service;
    private final RoleMapper mapper;

    public MVCRoleController(RoleService service, RoleMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("")
    public String getAll(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "5") int pageSize,
            Model model
    ) {
        PageRequest pageRequest = PageRequest.of(page-1, pageSize, Sort.by(Sort.Direction.ASC, "id"));
        Page<Role> rolePage = service.listAllPaginated(pageRequest);
        List<RoleDto> roleDtos = rolePage
                .stream()
                .map(mapper::toDto)
                .toList();

        model.addAttribute("roles", new PageImpl<>(roleDtos, pageRequest, rolePage.getTotalElements()));
        return "roles/viewAllRoles";
    }

    @GetMapping("/add")
    public String create() {
        return "roles/addRole";
    }

    @PostMapping("/add")
    public String create(@ModelAttribute("roleForm") RoleDto roleDto) {
        service.create(mapper.toEntity(roleDto));
        return "redirect:/roles";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/roles";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        RoleDto roleDto = mapper.toDto(service.getOne(id));
        if (roleDto != null) {
            model.addAttribute("roleForm", roleDto);
            return "roles/updateRole";
        } else {
            return "redirect:/roles";
        }
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("roleForm") RoleDto roleDto) {
        Role role = mapper.toEntity(roleDto);
        log.info("Role Id: " + role.getId());
        service.update(role);
        return "redirect:/roles";
    }

}
