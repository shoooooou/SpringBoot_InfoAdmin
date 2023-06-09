package com.example.springboot;

import java.util.List;

import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class MyDataDaoImpl implements MyDataDao<MyData> {
	
	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager entityManager;

	public MyDataDaoImpl() {
		super();
		// TODO 自動生成されたコンストラクター・スタブ
	}

	
	@Override
	public List<MyData> getAll(){
		Query query = entityManager.createQuery("from MyData");
		@SuppressWarnings("unchecked")
		List<MyData> list = query.getResultList();
		entityManager.close();
		return list;
	}
}
