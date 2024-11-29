/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntq.repository.impl;

import com.ntq.pojo.Cart;
import com.ntq.repository.CartRepository;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author QuocEzio
 */
@Repository
@Transactional
public class CartRepositoryImpl implements CartRepository{
    
    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public Cart addToCart(int courseId, String username) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Cart cart = new Cart();
        cart.setCourseId(courseId);
        cart.setUsername(username);
        session.save(cart);
        return cart;
    }

    @Override
    public void removeFromCart(int cartId) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Cart cart = session.get(Cart.class, cartId);
        if (cart != null) {
            session.delete(cart);
        }
        
    }

    @Override
    public Cart getCartById(int cartId) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        return session.get(Cart.class, cartId);
    }

    @Override
    public List<Cart> getListCart() {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Cart> q = b.createQuery(Cart.class);
        Root root = q.from(Cart.class);
        q.select(root); 
        
        Query query = s.createQuery(q);
        return query.getResultList();
    }

    @Override
    public List<Cart> getCartByUsername(String username) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        
      
        CriteriaQuery<Cart> q = b.createQuery(Cart.class);
        Root<Cart> root = q.from(Cart.class);
        
        
        Predicate usernamePredicate = b.equal(root.get("username"), username);
        
        
        q.select(root).where(usernamePredicate);
       
       
        return  s.createQuery(q).getResultList();
    }

    @Override
    public void removeAllCart(List<Cart> carts) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        for (Cart cart : carts) {
           s.delete(cart);
        }
    }

     
    
    
}
