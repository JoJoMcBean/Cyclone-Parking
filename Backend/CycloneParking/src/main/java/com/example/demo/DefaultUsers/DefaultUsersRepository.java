package com.example.demo.DefaultUsers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface DefaultUsersRepository extends JpaRepository<DefaultUsers, String> {

    @Query(value = "SELECT user_login.username, user_login.password, user_login.user_type, user_login.email, default_users.license, default_users.cardnum FROM default_users INNER JOIN user_login ON user_login.username = default_users.username WHERE user_login.token = ?1", nativeQuery = true)
    String getUserInfoWithToken(String token);

    @Modifying
    @Transactional
    @Query(value = "UPDATE user_login SET user_login.password = ?1, user_login.email = ?2 WHERE user_login.token = ?3", nativeQuery = true)
    void updateUserLoginInfo(String password, String email, String token);

    @Modifying
    @Transactional
    @Query(value = "UPDATE default_users SET default_users.license = ?1, default_users.cardnum = ?2 WHERE username = ?3", nativeQuery = true)
    void updateDefaultUsersInfo(String license, String cardNum, String username);

    @Query(value = "SELECT username from user_login WHERE token = ?1", nativeQuery = true)
    String getUsernameFromToken(String token);

}
