package com.ex;


public class Main {
    public static void main(String[] args) {
        Repository dao = new Dao();
        Service service = new Service();
        service.setDao(dao);// I know I am using the production dao

    }
}
