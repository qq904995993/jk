package jk.dao;

import jk.model.po.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MenuDao extends JpaRepository<Menu, Long>, JpaSpecificationExecutor<Menu> {

}
