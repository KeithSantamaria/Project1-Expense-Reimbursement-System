package com.ex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Main {
    MotdServiceBean serviceBean;

    public Main() {
        System.out.println("Creating Main Bean");
    }

    @Autowired
    public Main(MotdServiceBean motdServiceBean) {
        System.out.println("Creating Main Bean, injecting service");
        this.serviceBean = motdServiceBean;
    }

    public void setServiceBean(MotdServiceBean serviceBean) {
        System.out.println("Injecting service Bean");
        this.serviceBean = serviceBean;
    }

    public static void main(String[] args) {
//        AbstractApplicationContext ac = new ClassPathXmlApplicationContext("application-context.xml");
//
//        Main m = ac.getBean(Main.class);
//
////        MotdServiceBean m0 = ac.getBean("MotdServiceBean", MotdServiceBean.class);
////
////        System.out.println(m0.getMotd(-1));
//
//        System.out.println(m.serviceBean.getMotd(0));
//        System.out.println(m.serviceBean.getMotd(-1));
//
//        ac.close();
        MotdServiceBean serviceBean = new MotdServiceBean();
        Main m = new Main(serviceBean);

        System.out.println(m.serviceBean.getMotd(0));
        System.out.println(m.serviceBean.getMotd(-1));
    }
}
