package jk.dao;

import jk.model.po.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserDao extends JpaRepository<User,Long>, JpaSpecificationExecutor<User> {

    @Query("FROM User Where username = :username")
    User getUserByUsername(@Param("username") String username);

}




