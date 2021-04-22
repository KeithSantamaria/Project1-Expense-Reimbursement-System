package com.ex;

import java.util.Collection;

public class Service {
    private Repository dao;

    public Service(){}

    public Service(Repository dao) {
        this.dao = dao;
    }

    public Collection getAllData() {
        return this.dao.findAll();
    }

    public void setDao(Repository dao) {
        this.dao = dao;
    }
}
