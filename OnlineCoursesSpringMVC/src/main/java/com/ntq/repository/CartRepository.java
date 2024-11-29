/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntq.repository;

import com.ntq.pojo.Cart;
import java.util.List;

/**
 *
 * @author QuocEzio
 */
public interface CartRepository {

    Cart addToCart(int courseId, String username);

    void removeFromCart(int cartId);

    void removeAllCart(List<Cart> carts);

    Cart getCartById(int cartId);

    List<Cart> getListCart();

    List<Cart> getCartByUsername(String username);
}
