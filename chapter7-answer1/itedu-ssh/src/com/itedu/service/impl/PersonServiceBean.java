package com.itedu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.itedu.bean.Person;
import com.itedu.service.PersonService;

@Transactional
public class PersonServiceBean implements PersonService {
	@Resource private SessionFactory sessionFactory; 
	
	public void save(Person person) {
		// 使用persist()方法，和JTA规范保持一致，与save()作用一样
		sessionFactory.getCurrentSession().persist(person); 
	}
	
	/**
	 * 当对象处于游离状态时，这时才需要对它进行更新
	 */
	public void update(Person person) {
		// 使用merge()方法，和JTA规范保持一致，把对游离状态对象的更新同步到数据库
		sessionFactory.getCurrentSession().merge(person); 
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED, readOnly=true)
	public Person getPerson(Integer personid) {
		return (Person) sessionFactory.getCurrentSession().get(Person.class, personid);
	}
	
	public void delete(Integer personid) {
		/**
		 * 建议使用load()方法，load()方法比get()方法性能要好一些，
		 * 因为get()方法有一个数据装配的过程，也即把数据从数据库查询出来之后，还要把数据封装到实体对象里面去，
		 * 而load()方法没有封装的过程，相对来说，效率要高很多。
		 */
		sessionFactory.getCurrentSession().delete(
				sessionFactory.getCurrentSession().load(Person.class, personid));
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED, readOnly=true)
	@SuppressWarnings("unchecked")
	public List<Person> getPersons() {
		return sessionFactory.getCurrentSession().createQuery("from Person").list();
	}
}
