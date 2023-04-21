package com.poc.mobiletest.core.drivers;

import com.poc.mobiletest.core.models.MobileCapability;

public interface IDeviceFarm {
    String getHub(MobileCapability mobileCapability);
}
