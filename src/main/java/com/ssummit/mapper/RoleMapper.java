package com.ssummit.mapper;

import com.ssummit.dto.RoleDto;
import com.ssummit.model.Role;
import com.ssummit.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Component
public class RoleMapper {
    private final ModelMapper mapper;
    private final RoleRepository repository;

    public RoleMapper(ModelMapper mapper, RoleRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public RoleDto toDto(Role role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setId(role.getId());
        roleDto.setTitle(role.getTitle());
        roleDto.setDescription(role.getDescription());
        return roleDto;
    }

    public Role toEntity(RoleDto roleDto) {
        Long roleId = roleDto.getId();
        Role role;

        if (roleId != null) {
            Optional<Role> existingRole = repository.findById(roleId);

            if (existingRole.isPresent()) {
                role = existingRole.get();
                role.setTitle(roleDto.getTitle());
                role.setDescription(roleDto.getDescription());
            } else {
                throw new EntityNotFoundException("Role with ID " + roleId + " not found");
            }
        } else {
            role = new Role();
            role.setTitle(roleDto.getTitle());
            role.setDescription(roleDto.getDescription());
        }
        return repository.save(role);
    }
}
