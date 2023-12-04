package com.example.project_spring.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.project_spring.dao.AccountDAO;
import com.example.project_spring.entity.Acc;

@Repository
public class AccountImpl implements AccountDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Acc get(String username) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		try {
			Acc acc = session.createQuery("from Acc where username = :username", Acc.class)
					.setParameter("username", username).uniqueResult();
			return acc;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}
}
