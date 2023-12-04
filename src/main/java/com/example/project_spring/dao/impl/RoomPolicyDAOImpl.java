package com.example.project_spring.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.project_spring.dao.RoomPolicyDAO;
import com.example.project_spring.entity.RoomPolicy;

@Repository
public class RoomPolicyDAOImpl implements RoomPolicyDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<RoomPolicy> getAll() {
		Session session = sessionFactory.openSession();
		try {
			List<RoomPolicy> result = session.createQuery("from RoomPolicy", RoomPolicy.class).getResultList();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public RoomPolicy find(Integer id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			RoomPolicy rp = session.get(RoomPolicy.class, id);
			session.getTransaction().commit();
			return rp;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public void insert(RoomPolicy obj) {
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
	public void update(RoomPolicy obj) {
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
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.delete(find(id));
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Override
	public void bulkInsert(List<RoomPolicy> roomPolicies) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			for (RoomPolicy obj : roomPolicies) {
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
			Query query = session.createQuery("delete from RoomPolicy where room.roomId = :roomId")
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
	public List<RoomPolicy> getByPolicy(Integer policyId) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			List<RoomPolicy> result = session.createQuery("from RoomPolicy where policy.policyId = :policyId", RoomPolicy.class).setParameter("policyId", policyId).getResultList();
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
	public List<RoomPolicy> getByRoom(String roomId) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			List<RoomPolicy> result = session.createQuery("from RoomPolicy where room.roomId = :roomId", RoomPolicy.class).setParameter("roomId", roomId).getResultList();
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
