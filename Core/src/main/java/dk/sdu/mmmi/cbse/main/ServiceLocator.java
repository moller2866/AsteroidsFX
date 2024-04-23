package dk.sdu.mmmi.cbse.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * ServiceLocator class
 * This class is used to locate services depending on the Interface type
 */
public class ServiceLocator<T> {

    private static final Map<Class, ServiceLocator<?>> service = new HashMap<>();
    private final List<T> services;

    private ServiceLocator(Class<T> serviceType) {
        ServiceLoader<T> loader = ServiceLoader.load(serviceType);
        services = loader.stream().map(ServiceLoader.Provider::get).collect(java.util.stream.Collectors.toList());
    }

    public static ServiceLocator getInstance(Class serviceType) {
        if (service.get(serviceType) == null) {
            service.put(serviceType, new ServiceLocator<>(serviceType));
        }
        return service.get(serviceType);
    }

    public List<T> getServices() {
        return services;
    }
}

