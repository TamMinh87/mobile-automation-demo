package com.poc.mobiletest.core.drivers;

import com.poc.mobiletest.core.models.MobileCapability;

public class BrowserStackHub implements IDeviceFarm{

    @Override
    public String getHub(MobileCapability mobileCapability) {
        return mobileCapability.getHub();
    }

}
