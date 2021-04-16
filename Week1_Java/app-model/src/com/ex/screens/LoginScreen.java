package com.ex.screens;

import com.ex.AbstractApplication;
import com.ex.services.Service;

public class LoginScreen implements Screen{
    @Override
    public String doScreen(AbstractApplication app) {
        Service service = (Service)app.getContext().get("Service");
        System.out.println(service.getAllData());
        System.out.println("Login");
        return null;
    }
}
