package org.mannschaftssport.boundary.ACL;

import javax.enterprise.context.ApplicationScoped;
import java.util.concurrent.atomic.AtomicInteger;

@ApplicationScoped
public class SpielerpassDummy {
    private static AtomicInteger idCounter = new AtomicInteger();


    private Integer createID(){
        return idCounter.getAndIncrement();
    }

    public SpielerpassDTO egenerateSpielerpassDTO(){
        return new SpielerpassDTO(createID(), "name");
    }
}
