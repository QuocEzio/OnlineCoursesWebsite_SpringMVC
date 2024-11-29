/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ntq.services;

import com.ntq.pojo.Users;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;


/**
 *
 * @author QuocEzio
 */
public interface UsersService extends UserDetailsService{
    Users getUserById(int id);
    List<Users> getUsers();
    Users getUserByUsername(String username);
    Users addUser(Users user);
    void addOrUpdate(Users u);
    void deleteUser(int id);
    void updateUser(Users user);
    int getUserIdByUsername(String username);
     Users getUserByEmail(String email);
     
      void updatePassword(int userId, String newPassword);
    boolean verifyEmail(String email);
   
}
