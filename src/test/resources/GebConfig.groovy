/*
	This is the Geb configuration file.
	
	See: http://www.gebish.org/manual/current/configuration.html
*/


import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver

baseUrl = "http://www.jostens.com/"

waiting {
	timeout = 10
    retryInterval = 0.5
}

environments {
	
	// run via “./gradlew chromeTest”
	// See: http://code.google.com/p/selenium/wiki/ChromeDriver
	chrome {
		driver = { new ChromeDriver() }
	}
	
	// run via “./gradlew firefoxTest”
	// See: http://code.google.com/p/selenium/wiki/FirefoxDriver
	firefox {
		driver = { new FirefoxDriver() }
	}

}

// To run the tests with all browsers just run “./gradlew test”