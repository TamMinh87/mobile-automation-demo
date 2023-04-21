package com.poc.mobiletest.core.drivers;

import com.poc.mobiletest.core.models.MobileCapability;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BrowserStackCapability implements IMobileCapability{

    public DesiredCapabilities getCapability(MobileCapability cap) {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("autoAcceptAlerts", cap.isAutoAcceptAlerts());
        // Set your access credentials
        caps.setCapability("browserstack.user", cap.getBrowserStackUser());
        caps.setCapability("browserstack.key", cap.getBrowserStackKey());

        // Set URL of the application under test
        caps.setCapability("app", cap.getBrowserStackAppUrl());

        // Specify device and os_version for testing
        caps.setCapability("device", cap.getDevice());
        caps.setCapability("os_version", cap.getOs_version());

        // Specify language and locale for testing
        caps.setCapability("language", cap.getLanguage());
        caps.setCapability("locale", cap.getLocale());
        caps.setCapability("browserstack.idleTimeout", "300");
        caps.setCapability("autoGrantPermissions", "true");
        return caps;
    }
}
