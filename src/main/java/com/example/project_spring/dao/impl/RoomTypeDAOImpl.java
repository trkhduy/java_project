package com.example.project_spring.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.project_spring.dao.RoomTypeDAO;
import com.example.project_spring.dto.roomType.CreateDTO;
import com.example.project_spring.dto.roomType.UpdateDTO;
import com.example.project_spring.entity.RoomType;

@Repository
public class RoomTypeDAOImpl implements RoomTypeDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<RoomType> getAll() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		try {
			List<RoomType> result = session.createQuery("from RoomType", RoomType.class).getResultList();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RoomType> search(String typeName) {
		Session session = sessionFactory.openSession();

		try {
			Query query = session.createQuery("from RoomType where lower(typeName) like :name");
			query.setParameter("name", "%" + typeName.toLowerCase() + "%");
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RoomType> checkTypeName(String typeName) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		try {
			Query query = session.createQuery("from RoomType where typeName like :name");
			query.setParameter("name", typeName);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public RoomType find(Integer id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			RoomType rt = session.get(RoomType.class, id);
			session.getTransaction().commit();
			return rt;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public void insert(RoomType obj) {
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
	public void update(RoomType obj) {
		// TODO Auto-generated method stub
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
	public List<RoomType> paginate(Integer pageNo, Integer pageSize) {
		Session session = sessionFactory.openSession();
		try {
			List<RoomType> list = session.createQuery("from RoomType", RoomType.class)
					.setFirstResult((pageNo - 1) * pageSize).setMaxResults(pageSize).getResultList();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public int count() {
		int total = 0;
		Session session = sessionFactory.openSession();
		try {
			total = session.createQuery("from RoomType", RoomType.class).list().size();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return total;
	}

	@Override
	public List<RoomType> searchPage(String keyWord, Integer pageNo, Integer pageSize) {
		Session session = sessionFactory.openSession();
		try {
			List<RoomType> list = session
					.createQuery("from RoomType where lower(typeName) like lower(:keyword)", RoomType.class)
					.setParameter("keyword", "%" + keyWord + "%").setFirstResult((pageNo - 1) * pageSize)
					.setMaxResults(pageSize).getResultList();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public int countPage(String keyWord) {
		int total = 0;
		Session session = sessionFactory.openSession();
		try {
			total = session.createQuery("from RoomType where lower(typeName) like lower(:keyword)", RoomType.class)
					.setParameter("keyword", "%" + keyWord.toLowerCase() + "%").list().size();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return total;
	}

	public RoomType mapToCreate(CreateDTO createDTO) {
		RoomType rt = new RoomType();
		rt.setTypeName(createDTO.getTypeName());
		rt.setDescription(createDTO.getDescription());
		rt.setMaxOccupancy(createDTO.getMaxOccupancy());
		return rt;
	}

	public RoomType mapToUpdate(UpdateDTO updateDTO) {
		RoomType rt = new RoomType();
		rt.setRoomTypeId(updateDTO.getRoomTypeId());
		rt.setTypeName(updateDTO.getTypeName());
		rt.setDescription(updateDTO.getDescription());
		rt.setMaxOccupancy(updateDTO.getMaxOccupancy());
		return rt;
	}

}
