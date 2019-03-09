package com.example.demo.DefaultUsers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DefaultUsersRepository extends JpaRepository<DefaultUsers, String> {

    @Query(value = "SELECT user_login.username, user_login.email, default_users.license, default_users.cardnum FROM default_users INNER JOIN user_login ON user_login.username = default_users.username WHERE user_login.token = ?1", nativeQuery = true)
    String getUserWithToken(String token);

}
