package com.ex.screens;

import com.ex.AbstractApplication;

public class HomeScreen implements Screen{
    @Override
    public String doScreen(AbstractApplication app) {
        System.out.println("Home");
        return "Login";
    }
}
