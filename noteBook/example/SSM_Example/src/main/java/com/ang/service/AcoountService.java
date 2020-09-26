package com.ang.service;

import com.ang.domain.Acoount;

import java.util.List;

public interface AcoountService {

    public List<Acoount> findall();

    void save(Acoount acoount);
}
