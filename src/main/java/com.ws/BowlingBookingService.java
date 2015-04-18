package com.ws;

import com.ws.impl.BowlingBooking;

import javax.ws.rs.Path;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@Path("/")
public class BowlingBookingService extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(BowlingBooking.class);
        return classes;

    }
}