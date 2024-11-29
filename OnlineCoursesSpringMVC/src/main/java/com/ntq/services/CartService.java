/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ntq.services;

import com.ntq.pojo.Cart;
import java.util.List;

/**
 *
 * @author QuocEzio
 */
public interface CartService {
    Cart addToCart(int courseId, String username);
    void removeFromCart(int cartId);
    Cart getCartById(int cartId);
    List<Cart> getListCart();
    List<Cart> getCartByUsername (String username);
    void removeAllCart(List<Cart> carts );
}
