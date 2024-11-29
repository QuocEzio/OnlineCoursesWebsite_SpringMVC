/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntq.services.impl;

import com.ntq.pojo.Cart;
import com.ntq.repository.CartRepository;
import com.ntq.services.CartService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author QuocEzio
 */
@Service
@Transactional
public class CartServiceImpl implements CartService{

    
    @Autowired
    private CartRepository cartRepository;
    
    @Override
    public Cart addToCart(int courseId, String username) {
        return this.cartRepository.addToCart(courseId, username);
    }

    @Override
    public void removeFromCart(int cartId) {
        this.cartRepository.removeFromCart(cartId);
    }

    @Override
    public Cart getCartById(int cartId) {
        return this.cartRepository.getCartById(cartId);
    }

    @Override
    public List<Cart> getListCart() {
        return this.cartRepository.getListCart();
    }

    @Override
    public List<Cart> getCartByUsername(String username) {
        return this.cartRepository.getCartByUsername(username);
    }

    @Override
    public void removeAllCart(List<Cart> carts) {
        this.cartRepository.removeAllCart(carts);
    }
    
    
}
