package com.poc.mobiletest.core.drivers;

import org.openqa.selenium.remote.DesiredCapabilities;

import com.poc.mobiletest.core.models.MobileCapability;

public interface IMobileCapability {
    DesiredCapabilities getCapability(MobileCapability cap);
}
