package com.ex;

import com.ex.controllers.DataController;
import com.ex.daos.Dao;
import com.ex.daos.FileDao;
import com.ex.daos.MongoDao;
import com.ex.screens.HomeScreen;
import com.ex.screens.LoginScreen;
import com.ex.screens.Screen;
import com.ex.services.DataService;
import com.ex.services.Service;

import java.util.HashMap;
import java.util.Map;

public class MyApplication extends AbstractApplication{
    private Screen currentScreen;

    @Override
    public void run() {
        init();
        currentScreen = ((HashMap<String, Screen>)context.get("Screens")).get("Home");

        while(currentScreen != null) {
            String next = currentScreen.doScreen(this);
            currentScreen = ((HashMap<String, Screen>)context.get("Screens")).get(next);
        }
//        Service service = (Service) context.get("Service");
//        System.out.println(service.getAllData());
    }

    private void init() {
        context = new HashMap<String, Object>();
        Dao dao = new FileDao();
        Service service = new DataService(dao);
        DataController controller = new DataController(service);
        context.put("Dao", dao);
        context.put("Service", service);
        context.put("DataController", controller);

        initScreens();
    }

    private void initScreens() {
        context.put("Screens", new HashMap<String, Screen>());
        ((HashMap<String, Screen>)context.get("Screens")).put("Home", new HomeScreen());
        ((HashMap<String, Screen>)context.get("Screens")).put("Login", new LoginScreen());

    }
}
