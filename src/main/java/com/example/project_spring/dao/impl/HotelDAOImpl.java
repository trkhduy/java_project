package com.example.project_spring.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.project_spring.dao.HotelDAO;
import com.example.project_spring.entity.Hotel;
import com.example.spring_project.dto.hotel.updateDTO;

@Repository
public class HotelDAOImpl implements HotelDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Hotel> getAll() {
		Session session = sessionFactory.openSession();
		try {
			List<Hotel> result = session.createQuery("from Hotel", Hotel.class).getResultList();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public Hotel find(Integer id) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			Hotel hotel = session.get(Hotel.class, id);
			session.getTransaction().commit();
			return hotel;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public void insert(Hotel obj) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.save(obj);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Override
	public void update(Hotel obj) {	
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.update(obj);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
//		Session session = sessionFactory.getCurrentSession();
//		session.beginTransaction();
//		var ht = session.get(Hotel.class, id);
//		session.remove(ht);
//		session.getTransaction().commit();
	}

	public Hotel mapToEntity(updateDTO updateDTO) {
		Hotel hotel = new Hotel();
		hotel.setHotelId(updateDTO.getHotelId());
		hotel.setName(updateDTO.getName());
		hotel.setAddress(updateDTO.getAddress());
		hotel.setRating(updateDTO.getRating());
		hotel.setImage(updateDTO.getImage());
		return hotel;
	}

}
