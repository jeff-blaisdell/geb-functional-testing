/*
	This is the Geb configuration file.
	
	See: http://www.gebish.org/manual/current/configuration.html
*/

import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver

baseUrl = "http://www.jostens.com/"

sauceUsername = System.getProperty('sauce.user.name')
sauceApiKey = System.getProperty('sauce.api.key')


waiting {
	timeout = 10
    retryInterval = 0.5
}

def setSharedCapabilities(capabilities) {
    capabilities.setCapability('build', "${buildName}")
    capabilities.setCapability('tags', ["${tagName}"])
}

environments {

    'windows7-chrome' {
        driver = {
            DesiredCapabilities capabilities = DesiredCapabilities.chrome()
            capabilities.setCapability('platform', "Windows 7")
            capabilities.setCapability('name', 'windows7-chrome')
            setSharedCapabilities(capabilities)
            new RemoteWebDriver(new URL("http://${sauceUsername}:${sauceApiKey}@ondemand.saucelabs.com:80/wd/hub"), capabilities)
        }
    }

}

// To run the tests with all browsers just run “./gradlew test”