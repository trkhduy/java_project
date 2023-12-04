package com.example.project_spring.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.project_spring.dao.PolicyDAO;
import com.example.project_spring.dto.policy.CreateDTO;
import com.example.project_spring.dto.policy.UpdateDTO;
import com.example.project_spring.entity.Policy;

@Repository
public class PolicyDAOImpl implements PolicyDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<Policy> getAll() {
		Session session = sessionFactory.openSession();
		try {
			List<Policy> result = session.createQuery("from Policy", Policy.class).getResultList();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public Policy find(Integer id) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			Policy p = session.get(Policy.class, id);
			session.getTransaction().commit();
			return p;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public void insert(Policy obj) {
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
	public void update(Policy obj) {
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Policy> search(String name) {
		Session session = sessionFactory.openSession();
		try {
			Query query = session.createQuery("from Policy where policyName like :name");
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
	public List<Policy> checkName(String name) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		try {
			Query query = session.createQuery("from Policy where policyName like :name");
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
	public List<Policy> paginate(Integer pageNo, Integer pageSize) {
		Session session = sessionFactory.openSession();
		try {
			List<Policy> list = session.createQuery("from Policy", Policy.class).setFirstResult((pageNo - 1) * pageSize)
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
	public int count() {
		int total = 0;
		Session session = sessionFactory.openSession();
		try {
			total = session.createQuery("from Policy", Policy.class).list().size();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return total;
	}

	@Override
	public List<Policy> searchPage(String keyWord, Integer pageNo, Integer pageSize) {
		Session session = sessionFactory.openSession();
		try {
			List<Policy> list = session
					.createQuery("from Policy where lower(policyName) like lower(:keyword)", Policy.class)
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
			total = session.createQuery("from Policy where lower(policyName) like lower(:keyword)", Policy.class)
					.setParameter("keyword", "%" + keyWord.toLowerCase() + "%").list().size();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return total;
	}

	public Policy mapToCreate(CreateDTO createDTO) {
		Policy p = new Policy();
		p.setPolicyName(createDTO.getpolicyName());
		return p;
	}

	public Policy mapToUpdate(UpdateDTO updateDTO) {
		Policy p = new Policy();
		p.setPolicyId(updateDTO.getpolicyId());
		p.setPolicyName(updateDTO.getpolicyName());
		return p;
	}

}
