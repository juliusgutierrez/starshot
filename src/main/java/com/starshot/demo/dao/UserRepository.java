package com.starshot.demo.dao;

import com.starshot.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by juliusgutierrez on 18/05/2019.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
