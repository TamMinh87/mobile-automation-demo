package com.poc.mobiletest.core.drivers;

import java.security.InvalidParameterException;

public class ProducerFactory {
    
    private ProducerFactory() {
        throw new IllegalStateException("ProducerFactory class");
      }
    
    public static AFactory getFactory(String choice){
        if (choice == null) {
            return null;
        }
        switch(choice){
            case "DriverType":
                return new DriverTypeFactory();
            case "MobileCapability":
                return new CapabilityFactory();
            case "DeviceFarm":
                return new DeviceFarmFactory();
            default:
                throw new InvalidParameterException("The '" + choice + "' is unsupported.");
        }
    }

}
