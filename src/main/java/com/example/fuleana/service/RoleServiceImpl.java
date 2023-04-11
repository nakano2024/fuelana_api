package com.example.fuleana.service;

import com.example.fuleana.entity.Role;
import com.example.fuleana.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role getRoleByPk(Long roleId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(()->new EntityNotFoundException("Role Not Found with ID:" + roleId));
        return role;
    }
}
