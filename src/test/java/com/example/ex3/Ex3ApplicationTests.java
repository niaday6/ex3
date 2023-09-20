package com.example.ex3;

import com.example.ex3.web.dto.SampleDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest

class Ex3ApplicationTests {

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

}
