package com.ex;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class MotdServiceBean implements DisposableBean {
    protected List<String> motds = new ArrayList<>();
    protected int defaultMotdIndex;

    public MotdServiceBean() {
        System.out.println("Creating service bean");
    }

    // one a spring bean has an instance to this bean
    // it will be able to invoke this method.
    public String getMotd(int motdIndex) {
        if(motdIndex < 0 || motdIndex > motds.size()) {
            return motds.get(defaultMotdIndex);
        }
        return motds.get(motdIndex);
    }

    // this example will be configured use the custom init
    // lifecycle hook to invoke this method
    @PostConstruct
    public void initMotds() {
        System.out.println("Initializing Motds");
        motds.addAll(Arrays.asList("Good Morning",
                "The first step of a journey is always the most difficult",
                "You always pass failure on your way to success",
                "It always seems impossible, until it is done",
                "Positive anything is always better than negative nothing"));
    }

    // this example will be configured to inject a value from
    // the application into this class property using
    // setter injection
    public void setDefaultMotdIndex(int defaultMotdIndex) {
        System.out.println("Setting defaultMotdIndex");
        this.defaultMotdIndex = defaultMotdIndex;
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Destroying MotdServiceBean");
    }
}
