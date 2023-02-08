package edu.miu.cs.cs425.student.service.serviceimpl;


import edu.miu.cs.cs425.student.model.Role;
import edu.miu.cs.cs425.student.repository.RoleRepository;
import edu.miu.cs.cs425.student.service.RoleService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll(Sort.by("name"));
    }

}
