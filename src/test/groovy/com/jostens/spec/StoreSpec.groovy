package com.jostens.spec

import com.jostens.page.CustomerPage
import geb.spock.GebReportingSpec
import org.openqa.selenium.remote.RemoteWebDriver

class StoreSpec extends GebReportingSpec {

    def setup() {
        if (driver && driver instanceof RemoteWebDriver) {
            String sessionId = (((RemoteWebDriver) driver).getSessionId()).toString();
            System.out.println("SauceOnDemandSessionID=" + sessionId);
        }
    }

    def "I should be able to store page."() {

        when: "Navigate to the school store page."
        to CustomerPage, "1041120", "University-of-Tennessee"

        then: "We have successfully loaded the page."
        at CustomerPage

    }
}
