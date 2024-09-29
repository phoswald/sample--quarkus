package com.github.phoswald.sample.security;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;

@Entity
@Table(name = "user_")
@UserDefinition
public class UserEntity {

	@Id
    @Column(name = "username_")
    @Username
    public String username;

    @Column(name = "password_")
    @Password
    public String password;

    @Column(name = "roles_")
    @Roles
    public String roles;

//    /**
//     * Adds a new user in the database
//     * @param username the user name
//     * @param password the unencrypted password (it will be encrypted with bcrypt)
//     * @param role the comma-separated roles
//     */
//    public static void add(String username, String password, String role) {
//        User user = new User();
//        user.username = username;
//        user.password = BcryptUtil.bcryptHash(password);
//        user.role = role;
//        user.persist();
//    }
}
