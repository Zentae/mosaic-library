package io.zentae.mosaicmaker.services.manager;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class ServicesManager {

    private final List<Service> serviceList = new ArrayList<>();

    /**
     * Register {@link Service Services}.
     * @param services the {@link Service Services} to register.
     * @return {@link ServicesManager this}.
     */
    public ServicesManager register(Stream<Class<? extends Service>> services) {
        services.forEach(service ->  {
            try {
                Constructor<?> constructor = service.getConstructor(ServicesManager.class);
                serviceList.add((Service)constructor.newInstance(this));
            } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
                System.exit(-1);
            }
        });

        return this;
    }

    /**
     * Get a {@link Service} by its {@link Class}.
     * @param generic the {@link Service}'s {@link Class}.
     * @return the targeted {@link Service}.
     */
    public <T extends Service> Optional<T> getService(Class<T> generic) {
        for(Service service : serviceList) {
            if(service.getClass().equals(generic))
                return Optional.of(generic.cast(service));
        }

        return Optional.empty();
    }
}
