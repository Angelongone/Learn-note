package com.ang;

import com.ang.domain.Acoount;
import com.ang.service.impl.AcoountServiceImpl;
import org.junit.Test;

import java.util.List;

public class SsmTest {

    @Test
    public void test12(){
        AcoountServiceImpl acoountService = new AcoountServiceImpl();
        List<Acoount> findall = acoountService.findall();
        System.out.println(findall);
    }
}
