/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ntq.repository;


import com.ntq.pojo.Users;
import java.util.List;



/**
 *
 * @author QuocEzio
 */
public interface UsersRepository {
    Users getUserById(int id);
    List<Users> getUsers();
    Users getUserByUsername(String username);
    Users addUser(Users user);
    void updateUser(Users user);
    void addOrUpdate(Users u);
    void deleteUser(int id);
    int getUserIdByUsername(String username);
    Users getUserByEmail(String email);
    void updatePassword(int userId, String newPassword);
    
}
