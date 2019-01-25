package jk.dao;

import jk.model.po.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface RoleDao extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role> {

}
