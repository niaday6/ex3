package com.example.ex3.repositpory;

import com.example.ex3.entity.MyData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Repository
public interface MyDataRepository  extends JpaRepository<MyData, Long> {

    @Query("select m from MyData m order by m.id desc ")
    List<MyData> getListDesc();

    @Transactional
    @Modifying
    @Query("update MyData  m set m.memo = :memo where m.id = :id")
    int updateMyData(@Param("memo")String memo,@Param("id") Long id);


    @Transactional
    @Modifying
    @Query("update MyData m set m.memo = :#{#param.memo} where m.id = :#{#param.id}")
    int updateMyDataMemo(@Param("param") MyData mydata);


    @Query(value = "select m.id, m.name, m.mail, m.memo, current_date from MyData m " +
            " where m.id > :id",
            countQuery = "select count(m) from MyData m where m.id >:id")
    Page<Object[]> getListWithQueryObject(Long id, Pageable pageable);

    @Query(value = "select * from mydata where id > 0 ", nativeQuery = true)
    List<Object[]> getNativeResult();

}

