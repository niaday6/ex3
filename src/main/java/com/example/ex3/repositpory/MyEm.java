package com.example.ex3.repositpory;

import com.example.ex3.entity.MyData;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository //dao
public class MyEm {
@PersistenceContext
    EntityManager em;

public List<MyData> findAll(){
    String sql = "select m from MyData m";
    TypedQuery<MyData> query = em.createQuery(sql, MyData.class);

    List<MyData>list = query.getResultList();
    return list;
}
@Transactional
public void insert(MyData myData){
    em.persist(myData);
}
@Transactional
    public void update(MyData myData){
    em.merge(myData);
}

public MyData findById(Long id){
    MyData myData = em.find(MyData.class,id);
    return myData;
}
@Transactional
    public void deleteById(Long id){
    MyData myData = em.find(MyData.class,id);
    em.remove(myData);
}



}
