package sprint_2.service;


import sprint_2.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll();

    Role findById(Long idRole);

}
