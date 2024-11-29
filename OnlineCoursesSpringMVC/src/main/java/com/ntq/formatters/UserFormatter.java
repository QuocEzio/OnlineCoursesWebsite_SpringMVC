/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntq.formatters;

import com.ntq.pojo.Users;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author QuocEzio
 */
public class UserFormatter implements Formatter<Users>{

    @Override
    public String print(Users user, Locale locale) {
        return String.valueOf(user.getUserId());
    }

    @Override
    public Users parse(String userID, Locale locale) throws ParseException {
        Users u = new Users();
        u.setUserId(Integer.parseInt(userID));
        return u;
    }
    
}
