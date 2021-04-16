package com.ex.services;

import com.ex.daos.Dao;

import java.util.ArrayList;
import java.util.List;


public class DataService implements Service{
    private Dao dao;

    public DataService(){}
    public DataService(Dao dao) {
        this.dao = dao;
    }

    public List getAllData() {
        return dao.getAllData();
    }
}
