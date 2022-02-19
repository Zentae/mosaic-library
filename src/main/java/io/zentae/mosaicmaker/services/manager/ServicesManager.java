package io.zentae.mosaicmaker.services.manager;


import org.slf4j.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class ServicesManager {

    private final Logger logger;
    private final List<Service> serviceList = new ArrayList<>();

    public ServicesManager(Logger logger) {
        this.logger = logger;
    }

    public ServicesManager register(Stream<Class<? extends Service>> services) {
        services.forEach(service ->  {
            try {
                Constructor<?> constructor = service.getConstructor(ServicesManager.class);
                serviceList.add((Service)constructor.newInstance(this));
            } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
                logger.error("Failed to register service shutting down...");
                System.exit(-1);
            }
        });

        return this;
    }

    public <T extends Service> Optional<T> getService(Class<T> generic) {
        for(Service service : serviceList) {
            if(service.getClass().equals(generic))
                return Optional.of(generic.cast(service));
        }

        return Optional.empty();
    }

    public Logger getLogger() {
        return logger;
    }
}
