package com.example.project_spring.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.project_spring.dao.RoomFacilityDAO;
import com.example.project_spring.entity.RoomFacility;

@Repository
public class RoomFacilityDAOImpl implements RoomFacilityDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<RoomFacility> getAll() {
		Session session = sessionFactory.openSession();
		try {
			List<RoomFacility> result = session.createQuery("from RoomFacility", RoomFacility.class).getResultList();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public RoomFacility find(Integer id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			RoomFacility rf = session.get(RoomFacility.class, id);
			session.getTransaction().commit();
			return rf;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public void insert(RoomFacility obj) {
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
	public void update(RoomFacility obj) {
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
	public void bulkInsert(List<RoomFacility> roomFacilities) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			for (RoomFacility obj : roomFacilities) {
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
			Query query = session.createQuery("delete from RoomFacility where room.roomId = :roomId")
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

	@Override
	public List<RoomFacility> getByFacility(Integer facilityId) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			List<RoomFacility> result = session.createQuery("from RoomFacility where facility.facilityId = :facilityId", RoomFacility.class).setParameter("facilityId", facilityId).getResultList();
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
	public List<RoomFacility> getByRoom(String roomId) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			List<RoomFacility> result = session.createQuery("from RoomFacility where room.roomId = :roomId", RoomFacility.class).setParameter("roomId", roomId).getResultList();
			session.getTransaction().commit();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}
}
