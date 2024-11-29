/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntq.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ntq.pojo.Users;
import com.ntq.repository.UsersRepository;

import com.ntq.services.UsersService;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author QuocEzio
 */
@Service("userDetailsService")
public class UserServiceImpl implements UsersService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private UsersRepository usersRepository;
    
   
    

    @Override
    public Users getUserById(int id) {
        return this.usersRepository.getUserById(id);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users u = this.getUserByUsername(username);
        if (u == null) {
            throw new UsernameNotFoundException("Invalid User!");
        }

        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(u.getUserRole()));
        return new org.springframework.security.core.userdetails.User(
                u.getUsername(), u.getPassword(), authorities);
    }

    @Override
    public List<Users> getUsers() {
        return this.usersRepository.getUsers();
    }

    @Override
    public Users getUserByUsername(String username) {
        return this.usersRepository.getUserByUsername(username);
    }

    @Override
    @Transactional
    public Users addUser(Users u) {
        if (!u.getFile().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(u.getFile().getBytes(),
                            ObjectUtils.asMap("resource_type", "auto"));
                
                u.setAvatar(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(CoursesServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        u.setPassword(bCryptPasswordEncoder.encode(u.getPassword()));
        
        return this.usersRepository.addUser(u);
    }

    @Override
    public void addOrUpdate(Users u) {
        if (!u.getFile().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(u.getFile().getBytes(),
                            ObjectUtils.asMap("resource_type", "auto"));
                
                u.setAvatar(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(CoursesServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        u.setPassword(bCryptPasswordEncoder.encode(u.getPassword()));
        this.usersRepository.addOrUpdate(u);
    }

    @Override
    public void deleteUser(int id) {
         this.usersRepository.deleteUser(id);
    }

    @Override
    public void updateUser(Users u) {
        if (!u.getFile().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(u.getFile().getBytes(),
                            ObjectUtils.asMap("resource_type", "auto"));
                
                u.setAvatar(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(CoursesServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
         this.usersRepository.updateUser(u);
    }

    @Override
    public int getUserIdByUsername(String username) {
       return this.usersRepository.getUserIdByUsername(username);    }

    @Override
    public Users getUserByEmail(String email) {
        return this.usersRepository.getUserByEmail(email);
    }

    @Override
    public void updatePassword(int userId, String newPassword) {
               String encodedPassword = bCryptPasswordEncoder.encode(newPassword);
        this.usersRepository.updatePassword(userId, encodedPassword);

    }

    @Override
    public boolean verifyEmail(String email) {
         Users user = this.usersRepository.getUserByEmail(email);
        return user != null;
    }
    
    

   
} 
