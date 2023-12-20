package com.ssummit.service;


import com.ssummit.model.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.ssummit.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import java.util.List;;


@Service
@Slf4j
public class RoleService {

  private final RoleRepository repository;

  protected RoleService(RoleRepository repository) {
    this.repository = repository;
  }


  public List<Role> listAll() {
    return repository.findAll();
  }

  public Role getOne(Long id) {
    return repository.findById(id).orElseThrow(() -> new NotFoundException("Row with such ID: " + id + " not found"));
  }

  public Role create(Role object) {
    object.setId((long) (repository.findAll().size() + 1));
    return repository.save(object);
  }

  public Role update(Role object) {
    return repository.save(object);
  }

  public void delete(Long id) {
    repository.deleteById(id);
  }

  public Page<Role> listAllPaginated(Pageable pageable) {
    return repository.findAll(pageable);
  }

}
