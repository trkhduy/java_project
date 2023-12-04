package com.example.project_spring.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.project_spring.dao.FacilityDAO;
import com.example.project_spring.dto.facility.CreateDTO;
import com.example.project_spring.dto.facility.UpdateDTO;
import com.example.project_spring.entity.Facility;


@Repository
public class FacilityDAOImpl implements FacilityDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<Facility> getAll() {
		Session session = sessionFactory.openSession();
		try {
			List<Facility> result = session.createQuery("from Facility", Facility.class).getResultList();
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
	public List<Facility> search(String name) {
		Session session = sessionFactory.openSession();
		try {
			Query query = session.createQuery("from Facility where facilityName like :name");
			query.setParameter("name", "%" + name + "%");
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
	public List<Facility> checkName(String name) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		try {
			Query query = session.createQuery("from Facility where facilityName like :name");
			query.setParameter("name", name);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;

	}

	@Override
	public Facility find(Integer id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			Facility f = session.get(Facility.class, id);
			session.getTransaction().commit();
			return f;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public void insert(Facility obj) {
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
	public void update(Facility obj) {
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
	public List<Facility> paginate(Integer pageNo, Integer pageSize) {
		Session session = sessionFactory.openSession();
		try {
			List<Facility> list = session.createQuery("from Facility", Facility.class)
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
			total = session.createQuery("from Facility", Facility.class).list().size();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return total;
	}

	@Override
	public List<Facility> searchPage(String keyWord, Integer pageNo, Integer pageSize) {
		Session session = sessionFactory.openSession();
		try {
			List<Facility> list = session
					.createQuery("from Facility where lower(facilityName) like lower(:keyword)", Facility.class)
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
			total = session.createQuery("from Facility where lower(facilityName) like lower(:keyword)", Facility.class)
					.setParameter("keyword", "%" + keyWord.toLowerCase() + "%").list().size();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return total;
	}

	public Facility mapToCreate(CreateDTO createDTO) {
		Facility f = new Facility();
		f.setFacilityName(createDTO.getFacilityName());
		return f;
	}

	public Facility mapToUpdate(UpdateDTO updateDTO) {
		Facility f = new Facility();
		f.setFacilityId(updateDTO.getFacilityId());
		f.setFacilityName(updateDTO.getFacilityName());
		return f;
	}

}
