package com.serenity.appium.poc;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.grid.common.GridRole;

import org.openqa.grid.common.RegistrationRequest;

import org.openqa.grid.internal.utils.GridHubConfiguration;
import org.openqa.grid.internal.utils.SelfRegisteringRemote;
import org.openqa.grid.selenium.proxy.DefaultRemoteProxy;
import org.openqa.grid.web.Hub;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.server.SeleniumServer;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.AndroidServerFlag;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import net.serenitybdd.junit.runners.SerenityRunner;

@RunWith(SerenityRunner.class)
public class FlipkartAppParallelTest {

    //1. start appium server for each of the connected devices
    //2. Kill appium server if already up 
    //3. start grid server
    //get connected android emulators
    
    //private static Hub hub;
    private static SelfRegisteringRemote remote;
   
    
    @Test
    public void onStart() {
	    try {
		       getDevices();
		       stopAllServers();
		       startSeleniumHub();
		       startAppiumServerToRegisterEmulatorNodeWithSeleniumHUB();
		       stopAllServers();
		      //generate_node_config
                       System.out.println("stop here");
	        
	    } catch (Exception e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    }
	  }
    
     //Selenium Users google group - Programmatically open Selenium Hub and Node - ErrorDefaultRemoteProxy unknown version
     Hub hub = null;
     public void startSeleniumHub(){
	 try{
	                String strIP = "localhost";
	     
        	        GridHubConfiguration config = new GridHubConfiguration();
        	        config.setTimeout(30000000);
        	        config.setHost(strIP);
        	        config.setPort(4444);
        	        
        	        hub = new Hub(config);
        	        hub.start();
        
        	        
        	        //
/*        	        RegistrationRequest req = new RegistrationRequest();
        		req.setRole(GridRole.NODE);
        		
        		Map<String, Object> nodeConfiguration = new HashMap<>();
        		
        	        nodeConfiguration.put(RegistrationRequest.PROXY_CLASS, DefaultRemoteProxy.class.getName());
        	        nodeConfiguration.put(RegistrationRequest.MAX_SESSION, 5);
        	        nodeConfiguration.put(RegistrationRequest.PORT, 5555);
        	        nodeConfiguration.put(RegistrationRequest.HOST, 4444);
        	        nodeConfiguration.put(RegistrationRequest.AUTO_REGISTER, true);
        	        nodeConfiguration.put(RegistrationRequest.REGISTER_CYCLE, 5000);
        	        nodeConfiguration.put(RegistrationRequest.HUB_HOST, "localhost");
        	        nodeConfiguration.put(RegistrationRequest.HUB_PORT, 4444);
        	        nodeConfiguration.put("remoteHost", "http://" + strIP + ":5555");
        	        nodeConfiguration.put("url", "http://" + strIP + ":5555");
        	        nodeConfiguration.put("role", "node");
        	        nodeConfiguration.put("hub", "http://localhost:4444/grid/register");
        	        
        	        URL remoteURL = new URL("http://" + strIP + ":5555");
        		
        	        nodeConfiguration.put(RegistrationRequest.REMOTE_HOST, remoteURL.toExternalForm());
        	        req.setConfiguration(nodeConfiguration);
        	        
        	        DesiredCapabilities capabilities2 = DesiredCapabilities.chrome();
        	        capabilities2.setCapability("browserName", "chrome");
        	        capabilities2.setCapability("platform", "MAC");
        	        capabilities2.setCapability("ensureCleanSession", "true");
        	        capabilities2.setCapability("maxInstances", 5);
        	        req.addDesiredCapability(capabilities2);
        	        
        	        SelfRegisteringRemote remote = new SelfRegisteringRemote(req);
        	        remote.setRemoteServer(new SeleniumServer(req.getConfiguration()));
        	        remote.startRemoteServer();
        	        remote.startRegistrationProcess();     */		
        	        
        	       //Thread.currentThread().join();
        	       
	 }catch(Exception e){
	     e.printStackTrace();
	 }
     }
    
     public void stopAllServers(){
	 try{
        	     String s = null;
        	     String PID  = null;
        	     
        	     //Stop Selenim HUB - Method 1
        	     Process p = Runtime.getRuntime().exec("pgrep -f selenium-server-standalone");
        	 
        	     BufferedReader stdInput = new BufferedReader(new 
        	                 InputStreamReader(p.getInputStream()));
        	     
        	     while ((s = stdInput.readLine()) != null) {
        	                System.out.println(s);
        	                PID = s;
        	            }
        	     
        	     Runtime.getRuntime().exec("kill -9 " + PID);
        	     
        	     //Stop appium
        	     p = Runtime.getRuntime().exec("pgrep -f  appium");
        	  		 
        	      stdInput = new BufferedReader(new 
        	                 InputStreamReader(p.getInputStream()));
        	     
        	     while ((s = stdInput.readLine()) != null) {
        	                System.out.println(s);
        	                PID = s;
        	            }
        	    
        	     Runtime.getRuntime().exec("kill -9 " + PID );
        	     
        	   //Stop Selenim HUB - Method 2
        	     if (hub != null) { 
        		   hub.stop(); 
        	     }
        	     
	     
	 }catch(Exception e){
	     e.printStackTrace();
	 }
     }
     
     //Check manually if you're able to register node with Hub
     //appium --nodeconfig EMULATOR_Nexus_4_1.json
     //https://discuss.appium.io/t/unable-to-start-appium-service-by-appiumdriverlocalserivce/6324/18
     //https://github.com/appium/appium/blob/master/docs/en/advanced-concepts/grid.md
     //https://github.com/appium/appium/issues/1704
     //https://github.com/appium/java-client/tree/master/src/main/java/io/appium/java_client/service/local/flags
     //http://appium.io/slate/en/master/?ruby#appium-server-arguments - Appium Server Arguments
     //https://github.com/saikrishna321/AppiumTestDistribution/blob/c9b065cb084fa6d83272514784da455f7cf076fe/src/main/java/com/appium/manager/AppiumManager.java
     public void startAppiumServerToRegisterEmulatorNodeWithSeleniumHUB(){
	 try{
        	 String nodeConfigFilePath = "/Users/vikram-anna/Documents/Noa/Workspace/Mobile-Automation/Android-Automation/serenityAppiumFlipkart/EMULATOR_Nexus_4_1.json";
        	 
        	 AppiumDriverLocalService driverLocalService = AppiumDriverLocalService
        			.buildService(new AppiumServiceBuilder()
        					.withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
        					.usingDriverExecutable(new File("/usr/local/bin/node"))
        					//.withIPAddress("127.0.0.1")
        					.usingPort(4445)
        					//.withArgument(GeneralServerFlag.UIID, "123456")    					
        					//.withArgument(AndroidServerFlag.BOOTSTRAP_PORT_NUMBER,
        					//		"" + 2252)
        					//.withArgument(GeneralServerFlag.CHROME_DRIVER_PORT,
        					//		"" + client.getChromeDriverport())
        					//.withArgument(GeneralServerFlag.NO_RESET)
        					.withArgument(GeneralServerFlag.SESSION_OVERRIDE)
        					.withArgument(GeneralServerFlag.LOG_LEVEL, "debug")
        					.withArgument(GeneralServerFlag.CONFIGURATION_FILE, nodeConfigFilePath));
        	//Logger.info("Server url: " + driverLocalService.getUrl());
        	driverLocalService.start();
        	System.out.println("test");
	 }catch(Exception e){
	     e.printStackTrace();
	 }
     }
     
     //Works
     /*Before you run this program, make sure to try it out from command line with below command
      * appium --nodeconfig EMULATOR_Nexus_4_1.json --port 4445
      * In case you do something wrong as appium --nodeconfig EMULATOR_Nexus_4_1.json --bp 2252, you will get nice output about flags usage
     */
     /*public void startAppiumServerToRegisterEmulatorNodeWithSeleniumHUB(){
	 try{
        	 String nodeConfigFilePath = "/Users/vikram-anna/Documents/Noa/Workspace/Mobile-Automation/Android-Automation/serenityAppiumFlipkart/EMULATOR_Nexus_4_1.json";
        	 
        	 AppiumDriverLocalService driverLocalService = AppiumDriverLocalService
        			.buildService(new AppiumServiceBuilder()
        					.withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
        					.usingDriverExecutable(new File("/usr/local/bin/node"))
        					//.withIPAddress("127.0.0.1")
        					.usingPort(4445)
        					//.withArgument(GeneralServerFlag.UIID, "123456")    					
        					//.withArgument(AndroidServerFlag.BOOTSTRAP_PORT_NUMBER,
        					//		"" + 2252)
        					//.withArgument(GeneralServerFlag.CHROME_DRIVER_PORT,
        					//		"" + client.getChromeDriverport())
        					//.withArgument(GeneralServerFlag.NO_RESET)
        					.withArgument(GeneralServerFlag.SESSION_OVERRIDE)
        					.withArgument(GeneralServerFlag.LOG_LEVEL, "debug")
        					.withArgument(GeneralServerFlag.CONFIGURATION_FILE, nodeConfigFilePath));
        	//Logger.info("Server url: " + driverLocalService.getUrl());
        	driverLocalService.start();
        	System.out.println("test");
	 }catch(Exception e){
	     e.printStackTrace();
	 }
     }*/
     
     //Get connected devices https://github.com/sameer49/Appium-Grid-For-Android/blob/master/src/libs/DeviceConfiguration.java
        CommandPrompt cmd = new CommandPrompt();
	Map<String, String> devices = new HashMap<String, String>();
	
             public void startADB() throws Exception{
        		String output = cmd.runCommand("start-server");
        		String[] lines = output.split("\n");
        		if(lines.length==1)
        			System.out.println("adb service already started");
        		else if(lines[1].equalsIgnoreCase("* daemon started successfully *"))
        			System.out.println("adb service started");
        		else if(lines[0].contains("internal or external command")){
        			System.out.println("adb path not set in system varibale");
        			System.exit(0);
        		}
        	}
     
             public void stopADB() throws Exception{
        	 cmd.runCommand("kill-server");
             }
	
     public Map<String, String> getDevices() throws Exception{
		
		startADB(); // start adb service
		String output = cmd.runCommand("devices");
		String[] lines = output.split("\n");

		if(lines.length<=1){
			System.out.println("No Device Connected");
			stopADB();
			System.exit(0);	// exit if no connected devices found
		}
		
		for(int i=1;i<lines.length;i++){
			lines[i]=lines[i].replaceAll("\\s+", "");
			
			if(lines[i].contains("device")){
				lines[i]=lines[i].replaceAll("device", "");
				String deviceID = lines[i];
				String model = cmd.runCommand("-s "+deviceID+" shell getprop ro.product.model").replaceAll("\\s+", "");
				String brand = cmd.runCommand("-s "+deviceID+" shell getprop ro.product.brand").replaceAll("\\s+", "");
				String osVersion = cmd.runCommand("-s "+deviceID+" shell getprop ro.build.version.release").replaceAll("\\s+", "");
				String deviceName = brand+" "+model;
				
				devices.put("deviceID"+i, deviceID);
				devices.put("deviceName"+i, deviceName);
				devices.put("osVersion"+i, osVersion);
				
				System.out.println("Following device is connected");
				System.out.println(deviceID+" "+deviceName+" "+osVersion+"\n");
			}else if(lines[i].contains("unauthorized")){
				lines[i]=lines[i].replaceAll("unauthorized", "");
				String deviceID = lines[i];
				
				System.out.println("Following device is unauthorized");
				System.out.println(deviceID+"\n");
			}else if(lines[i].contains("offline")){
				lines[i]=lines[i].replaceAll("offline", "");
				String deviceID = lines[i];
				
				System.out.println("Following device is offline");
				System.out.println(deviceID+"\n");
			}
		}
		return devices;
	}
     
       /* Process p;
	ProcessBuilder builder;
     public String runCommand(String command) throws InterruptedException, IOException
	{
	    try{
     		String os = System.getProperty("os.name");
     		//System.out.println(os);
     		
     		// build cmd proccess according to os
     		if(os.contains("Windows")) // if windows
     		{
     			builder = new ProcessBuilder("cmd.exe","/c", command);
     			builder.redirectErrorStream(true);
     			Thread.sleep(1000);
     			p = builder.start();
     		}
     		else{ // If Mac
     		        String[] temp = { "/Users/vikram-anna/Library/Android/sdk/platform-tools/adb", "start-server" };
     		        p = Runtime.getRuntime().exec(temp);
     			//p = Runtime.getRuntime().exec(command);
     		}
     		
     		// get std output
     		BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
     		String line="";
     		String allLine="";
     		int i=1;
     		while((line=r.readLine()) != null){
     	//		System.out.println(i+". "+line);
     			allLine=allLine+""+line+"\n";
     			if(line.contains("Console LogLevel: debug"))
     				break;
     			i++;
     		}
     		return allLine;
	}catch(Exception e){
	    e.printStackTrace();
	    return null;
	}
	}*/
     
     //Get free ports https://github.com/sameer49/Appium-Grid-For-Android/blob/master/src/libs/AvailabelPorts.java
     
     
    
}
