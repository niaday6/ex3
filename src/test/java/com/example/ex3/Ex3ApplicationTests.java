package com.example.ex3;

import com.example.ex3.entity.MyData;
import com.example.ex3.repositpory.MyDataRepository;
import com.example.ex3.web.dto.SampleDTO;

import groovy.transform.builder.Builder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@SpringBootTest
class Ex3ApplicationTests {
@PersistenceContext
    EntityManager em;

@Autowired
    MyDataRepository myDataRepository;

    @Test
    void contextLoads() {
        SampleDTO dto = SampleDTO.builder()
                .first("First1")
                .last("Last1")
                .regTime(LocalDateTime.now())
                .sno(1L)
                .build();
        System.out.println(dto);
        SampleDTO dto2 = dto.toBuilder()
                .sno(2L)
                .regTime(LocalDateTime.now())
                .build();
        System.out.println(dto2);
    }

    @Test
    @Transactional
    public void 엔티티매니저_연습(){
        MyData myData = em.find(MyData.class,1L);
        System.out.println(myData);
        myData.setName("김하나");
        myData = em.find(MyData.class,1L);
        System.out.println(myData);
    }


    @Test
    @Transactional
    public void 엔티티매니저_연습_persist(){
        MyData d3 = new MyData();
        d3.setName("홍길동");
        d3.setAge(42);
        d3.setMail("hong@213");
        d3.setMemo("나는 홍길동");
        em.persist(d3);
        TypedQuery<MyData> query =
                em.createQuery("select m from MyData m", MyData.class);
        List<MyData> resultList = query.getResultList();
        for(MyData member : resultList){
            System.out.println("member = " + member);
        }
    }

@Test
    public void list_mydata(){
        List<MyData> list = myDataRepository.getListDesc();
        list.stream().forEach(i->{
            System.out.println(i);
        });

}
@Test
    public void update_mydata(){
        myDataRepository.updateMyData("테스트입니다.",2L);
        List<MyData> list = myDataRepository.findAll();
        list.stream().forEach(i->{
            System.out.println(i);
        });
}

@Test
    public void update_mydata2(){
        MyData myData = MyData.builder()
                .id(3L)
                .memo("수정된 메모").build();
        myDataRepository.updateMyDataMemo(myData);
        List<MyData> list = myDataRepository.findAll();
        list.stream().forEach(i->{
            System.out.println(i);
        });


}
    @Test
    public void list_mydata3(){
        Pageable pageable = PageRequest.of(0,10, Sort.by("id").descending());
        Page<Object[]> list =  myDataRepository.getListWithQueryObject(0L,pageable);
        for (Object[] objects : list){

            for(Object object : objects){
                System.out.println(object);
            }
        }

    }

    @Test
    public void list_mydata4(){
        List<Object[]> list =  myDataRepository.getNativeResult();
        for (Object[] objects : list){

            for(Object object : objects){
                System.out.println(object);
            }
        }

    }
}
