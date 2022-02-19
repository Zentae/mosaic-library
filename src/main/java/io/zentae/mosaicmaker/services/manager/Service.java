package io.zentae.mosaicmaker.services.manager;

public abstract class Service {

    private final ServicesManager servicesManager;

    public Service(ServicesManager servicesManager) {
        this.servicesManager = servicesManager;
    }

    protected ServicesManager getServices() {
        return servicesManager;
    }

    protected void shutdown(String message, Exception exception) {
        servicesManager.getLogger().error(message + " " + exception.getMessage());
        System.exit(-1);
    }

    protected void shutdown(String message) {
        shutdown(message, null);
    }
}
