package com.example.project_spring.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.project_spring.dao.ImageDAO;
import com.example.project_spring.entity.Image;
import com.example.project_spring.entity.RoomPolicy;

@Repository
public class ImageDAOImpl implements ImageDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<Image> getAll() {
		Session session = sessionFactory.openSession();
		try {
			List<Image> result = session.createQuery("from Image", Image.class).getResultList();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public Image find(Integer id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			Image img = session.get(Image.class, id);
			session.getTransaction().commit();
			return img;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public void insert(Image obj) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.save(obj);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback(); // Rollback nếu có lỗi
		} finally {
			session.close();
		}
	}

	@Override
	public void update(Image obj) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.update(obj);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback(); // Rollback nếu có lỗi
		} finally {
			session.close();
		}
	}

	@Override
	public void delete(Integer id) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.delete(find(id));
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback(); // Rollback nếu có lỗi
		} finally {
			session.close();
		}
	}

	@Override
	public List<Image> getImageByRoom(String roomId) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			List<Image> result = session.createQuery("from Image where room.roomId = :roomId", Image.class)
					.setParameter("roomId", roomId).getResultList();
			session.getTransaction().commit();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public void bulkInsert(List<Image> images) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			for (Image obj : images) {
				session.save(obj);
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
	}

	@Override
	public void deleteByRoom(String roomId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("delete from Image where room.roomId = :roomId")
					.setParameter("roomId", roomId);
			query.executeUpdate();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback(); // Rollback nếu có lỗi
		} finally {
			session.close();
		}
	}

}
