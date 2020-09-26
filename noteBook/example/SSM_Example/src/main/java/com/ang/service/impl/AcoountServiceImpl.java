package com.ang.service.impl;

import com.ang.dao.AcoountMapper;
import com.ang.domain.Acoount;
import com.ang.service.AcoountService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service("acoountService")
public class AcoountServiceImpl implements AcoountService {

    @Autowired
    private AcoountMapper acoountMapper;

    @Override
    public List<Acoount> findall() {
        return acoountMapper.findAll();
    }

    @Override
    public void save(Acoount acoount) {
        acoountMapper.save(acoount);
    }


    /*@Override
    public List<Acoount> findall() {
        List<Acoount> all = null;
        try {
            InputStream resourceAsStream = Resources.getResourceAsStream("MapperConfig.xml");
            SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
            SqlSession sqlSession = build.openSession();
            AcoountMapper mapper = sqlSession.getMapper(AcoountMapper.class);
            all = mapper.findAll();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return all;
    }

    @Override
    public void save(Acoount acoount) {
        try {
            InputStream resourceAsStream = Resources.getResourceAsStream("MapperConfig.xml");
            SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
            SqlSession sqlSession = build.openSession();
            AcoountMapper mapper = sqlSession.getMapper(AcoountMapper.class);
//            Acoount acoount = new Acoount();
//            acoount.setId(id);
//            acoount.setName(name);
//            acoount.setMoney(money);
            mapper.save(acoount);
            sqlSession.commit();
            sqlSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
