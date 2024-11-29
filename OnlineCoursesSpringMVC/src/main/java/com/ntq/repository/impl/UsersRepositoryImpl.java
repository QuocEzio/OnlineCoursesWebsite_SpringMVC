/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntq.repository.impl;

import com.ntq.pojo.Users;
import com.ntq.repository.UsersRepository;
import java.util.Date;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
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
public class UsersRepositoryImpl implements UsersRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public Users getUserById(int id) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        return s.get(Users.class, id);
    }

    @Override
    public List<Users> getUsers() {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        Query q = s.createQuery("From Users");
        return q.getResultList();
    }

    @Override
    public Users getUserByUsername(String username) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Users.findByUsername");
        q.setParameter("username", username);

        return (Users) q.getSingleResult();
    }

    @Override
    public Users addUser(Users user) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        if (user.getUserRole() == null) {
            user.setUserRole("ROLE_STUDENT");
        }
        user.setCreatedAt(new Date());
        s.save(user);
        return user;
    }

    @Override
    public void addOrUpdate(Users u) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        if (u.getUserId() != null) {
            s.update(u);
        } else {
            s.save(u);
        }
    }

    @Override
    public void deleteUser(int id) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        Users u = this.getUserById(id);
        if (u != null) {
            s.delete(u);
        }
    }

    @Override
    public void updateUser(Users user) {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        user.setCreatedAt(new Date());
        s.update(user);

    }

    @Override
    public int getUserIdByUsername(String username) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        String sql = "SELECT u.userId FROM Users u WHERE u.username = :username";

        return (Integer) session.createQuery(sql)
                .setParameter("username", username)
                .uniqueResult();

    }

    @Override
    public Users getUserByEmail(String email) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Users> q = b.createQuery(Users.class);
        Root root = q.from(Users.class);
        q.select(root);
        q.where(b.equal(root.get("email"), email));
        Query query = session.createQuery(q);
        try {
            return (Users) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void updatePassword(int userId, String newPassword) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query query = session.createQuery("UPDATE Users SET password = :password WHERE userId = :userId");
        query.setParameter("password", newPassword);
        query.setParameter("userId", userId);
        query.executeUpdate();
    }
}
