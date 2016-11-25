This project shows Appium and Serenity framework integration to run sample test case on native Android app.

Before Run Settings

1.  Have a android emulator or device connected and running
2.  Appium server needs to be started externally
3.  ANDROID_HOME to be set
4.  appium.platformVersion  =  set_valid_value
5.  appium.deviceName  =  set_valid_value
6. appium.app  =  set_full_path


Note:

1. Please run and review the code
2. If you think something needs to be improved; please file issue or submit PR.


How To Run From Command Line

1. mvn clean verify



Living Documentation

1. http://vikramvi.github.io/AppiumSerenityPOC




Interrogating Methods:

Method 1:

	1.1  with arc command

	1.2  Run arc command which takes caps from appium.txt and launches app on mobile in this case

	1.3 You can now easily play with app with commands like
	page_class, page, @driver.methods,  @driver.find_element(:id,....).click

Method 2:

	2.1 with irb command

	2.2 run irb command

	2.3 Later run below commands in sequenc

	2.3.1 require 'appium_lib'

	2.3.2 caps = {
	caps: {
	platformName: "Android",
	platformVersion: "5.0.1",
	deviceName: "d6e4f8d6",
	app: 'FULL_PATH/Flipkart.3.0.apk',
	newCommandTimeout: '9999'
	},
	appium_lib: {
	server_url: 'http://localhost:4723/wd/hub',
	wait: 300
	}
	}

	2.3.3 Appium::Driver.new(caps).start_driver

	2.3.4 Appium.promote_appium_methods Object

	2.4  You can now easily play with app with commands like
	page_class, page, @driver.methods,  @driver.find_element(:id,....).click



Why this is better than using Appium Client ?

	I find this Ruby way of playing with app ( REPL ) much easier than Appium Client as later is slower and buggier.
