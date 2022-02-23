package io.zentae.mosaicmaker;

public enum StorageUnit {

    B("Byte", 1),
    KB("Kilobyte", 1024),
    MB("Megabyte", 1048576),
    GB("Gigabyte", 1073741824);

    private final long byteValue;
    private final String name;

    StorageUnit(String name, long byteValue) {
        this.byteValue = byteValue;
        this.name = name;
    }

    /**
     * @param name the target {@link StorageUnit Unit}.
     * @return the target {@link StorageUnit Unit} by its name;
     */
    public static StorageUnit getByName(String name) {
        for(StorageUnit storageUnit : StorageUnit.values()) {
            if(storageUnit.getName().equals(name))
                return storageUnit;
        }

        return null;
    }

    /**
     * @return the byte value of the {@link StorageUnit Unit}.
     */
    public long getByteValue() {
        return byteValue;
    }

    /**
     * @return the name of the {@link StorageUnit Unit}.
     */
    public String getName() {
        return name;
    }

    /**
     * Converts a {@link StorageUnit Unit} into another one.
     * @param storageUnit the target {@link StorageUnit Unit}.
     * @param value the value to convert.
     * @return the converted value from the source {@link StorageUnit Unit} into the target one.
     */
    public long to(StorageUnit storageUnit, long value) {
        double convertScale = (storageUnit.getByteValue() / byteValue);
        double destinationByteValue = storageUnit.getByteValue();
        double sourceByteValue = byteValue;

        if(destinationByteValue > sourceByteValue) {
            return (long)Math.ceil(value / convertScale);
        } else if(destinationByteValue < sourceByteValue) {
            return (long) Math.ceil(value * convertScale);
        }

        return value;
    }
}
