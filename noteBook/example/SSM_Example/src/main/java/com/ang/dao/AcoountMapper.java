package com.ang.dao;

import com.ang.domain.Acoount;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface AcoountMapper {
//    查询所有
    List<Acoount> findAll();
//    保存数据
    void save(Acoount acoount);

}
