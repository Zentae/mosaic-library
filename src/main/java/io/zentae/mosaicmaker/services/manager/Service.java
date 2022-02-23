package io.zentae.mosaicmaker.services.manager;

public abstract class Service {

    private final ServicesManager servicesManager;

    public Service(ServicesManager servicesManager) {
        this.servicesManager = servicesManager;
    }

    protected ServicesManager getServices() {
        return servicesManager;
    }

    /**
     * Exit the program with status code -1.
     * @param message the message to display before shutting down.
     */
    protected void shutdown(String message) {
        System.exit(-1);
    }
}
