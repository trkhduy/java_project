package com.example.project_spring.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.project_spring.dao.RoomDAO;
import com.example.project_spring.dto.room.CreateDTO;
import com.example.project_spring.dto.room.UpdateDTO;
import com.example.project_spring.entity.Room;

@Repository
public class RoomDAOImpl implements RoomDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	RoomTypeDAOImpl roomTypeDAOImpl;

	@Autowired
	HotelDAOImpl hotelDAOImpl;

	@Override
	public List<Room> getAll() {
		Session session = sessionFactory.openSession();
		try {
			return session.createQuery("from Room", Room.class).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public Room find(String id) {
		Session session = sessionFactory.openSession();
		try  {
			session.beginTransaction();
			Room r = session.get(Room.class, id);
			session.getTransaction().commit();
			return r;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return null;
	}

	@Override
	public void insert(Room obj) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.save(obj);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
	}

	@Override
	public void update(Room obj) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.update(obj);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
	}

	@Override
	public void delete(String id) {
		Session session = sessionFactory.openSession();
		try  {
			session.beginTransaction();
			session.delete(find(id));
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Room> search(String name) {
		Session session = sessionFactory.openSession();
		try {
			Query query = session.createQuery("from Room where roomName like :name");
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
	public List<Room> checkName(String name) {
		// TODO Auto-generated method stub

		Session session = sessionFactory.openSession();
		try {
			Query query = session.createQuery("from Room where roomName like :name");
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
	public List<Room> paginate(Integer pageNo, Integer pageSize) {
		Session session = sessionFactory.openSession();
		try {
			List<Room> list = session.createQuery("from Room", Room.class).setFirstResult((pageNo - 1) * pageSize)
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
			total = session.createQuery("from Room", Room.class).list().size();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return total;
	}

	@Override
	public List<Room> searchPage(String keyWord, Integer pageNo, Integer pageSize) {
		Session session = sessionFactory.openSession();
		try {
			List<Room> list = session.createQuery("from Room where lower(roomName) like lower(:keyword)", Room.class)
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
			total = session.createQuery("from Room where lower(roomName) like lower(:keyword)", Room.class)
					.setParameter("keyword", "%" + keyWord.toLowerCase() + "%").list().size();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return total;
	}

	@Override
	public List<Room> getRoomByType(Integer roomTypeId) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			List<Room> result = session.createQuery("from Room where roomType.roomTypeId = :roomTypeId", Room.class).setParameter("roomTypeId", roomTypeId).getResultList();
			session.getTransaction().commit();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}
//	public Room mapToCreate(CreateDTO createDTO) {
//		Room r = new Room();
//		r.setRoomId(createDTO.getRoomId());
//		r.setRoomName(createDTO.getRoomName());
//		r.setBedType(createDTO.getBedType());
//		r.setAvailability(createDTO.getAvailability());
//		r.setImage(createDTO.getImage());
//		r.setPricePerNight(createDTO.getPricePerNight());
//		r.setRoomSize(createDTO.getRoomSize());
//		r.setSalePrice(createDTO.getSalePrice());
//		r.setRoomType(roomTypeDAOImpl.find(createDTO.getRoomType()));
//		r.setHotel(hotelDAOImpl.find(createDTO.getHotel()));
//		return r;
//	}
//
//	public Room mapToUpdate(UpdateDTO updateDTO) {
//		Room r = new Room();
//		r.setRoomId(updateDTO.getRoomId());
//		r.setRoomName(updateDTO.getRoomName());
//		r.setBedType(updateDTO.getBedType());
//		r.setAvailability(updateDTO.getAvailability());
//		r.setImage(updateDTO.getImage());
//		r.setPricePerNight(updateDTO.getPricePerNight());
//		r.setRoomSize(updateDTO.getRoomSize());
//		r.setSalePrice(updateDTO.getSalePrice());
//		r.setRoomType(roomTypeDAOImpl.find(updateDTO.getRoomType()));
//		r.setHotel(hotelDAOImpl.find(updateDTO.getHotel()));
//		return r;
//	}

}
