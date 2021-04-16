package com.ex.controllers;

import com.ex.services.Service;

import java.util.List;

public class DataController {
    private Service dataService;

    public DataController() {
    }

    public DataController(Service dataService) {
        this.dataService = dataService;
    }

    public List getAllData() {
        return dataService.getAllData();
    }
}
