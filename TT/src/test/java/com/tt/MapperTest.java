package com.tt;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tt.mapper.ClassMapper;

@SpringBootTest
public class MapperTest {
    @Autowired
    ClassMapper classmapper;

    @Test
    public void academyclassjoin() {
        List<Map<String, String>> resultlist = classmapper.selectClassAcademyjoin("human");
        System.out.println(resultlist);
    }
}
