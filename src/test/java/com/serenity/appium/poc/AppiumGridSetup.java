package com.serenity.appium.poc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.openqa.grid.internal.utils.GridHubConfiguration;
import org.openqa.grid.internal.utils.SelfRegisteringRemote;
import org.openqa.grid.web.Hub;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.remote.MobileCapabilityType;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.AndroidServerFlag;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import net.serenitybdd.junit.runners.SerenityRunner;

@RunWith(SerenityRunner.class)
public class AppiumGridSetup {

    //https://github.com/isonic1/appium-mobile-grid 
    //1. get Android Devices
    //2. get Android Device Data
    //3. get iOS Devices
    //4. get iOS Device Data
    //create log file for each of the nodes
    
    //1. start appium server for each of the connected devices
    //2. Kill appium server if already up 
    //3. start grid server 
    //4. wait for grid server to be sucessfully up & running
    //get connected android emulators
    //get free ports
    //create json dynamically
    //OpenSTF Integration - http://www.vimalselvam.com/2016/08/07/appium-parallel-execution-on-openstf/
    //https://github.com/appium/appium-docker-android
    //wait for grid server to be sucessfully up & running
    //Get connected devices' count, create new json file and finish setup for automation
    
    
    //private static Hub hub;
    private static SelfRegisteringRemote remote;
    private String projectDirectory;
    
    public void mobileGridSetup(){
	try{
	       projectDirectory = System.getProperty("user.dir");
	       FileUtils.cleanDirectory(new File( projectDirectory + "/appium_device_log/") );
	       
	       getDevices().size();
	       stopAllServers(); 
	       startSeleniumHub();
	       generate_node_config(deviceIds.size());       
	       startAppiumServerToRegisterNodeWithSeleniumHUB(deviceIds.size());
	       //wait till all actions are successful or throw an error
	}catch(Exception e){
	    e.printStackTrace();
	}
    }
    
    public void mobileGridTearDown(){
	stopAllServers();
    }
    
    @Test
    public void SetupSeleniumGridAndAppiumNodesTest() {
	    try{
		       projectDirectory = System.getProperty("user.dir");
		       FileUtils.cleanDirectory(new File( projectDirectory + "/appium_device_log/") );
		       
		       getDevices().size(); //working
		       stopAllServers(); //working
		       startSeleniumHub(); //working
		       generate_node_config(deviceIds.size());//working       
		       startAppiumServerToRegisterNodeWithSeleniumHUB(deviceIds.size()); //working
		       stopAllServers();
                       System.out.println("stop here");
	        
	    }catch (Exception e) {
	       e.printStackTrace();
	    }
	  }
    
     //Check manually first java -jar selenium-server-standalone-3.0.1.jar -role hub ( http://elementalselenium.com/tips/52-grid )
    /*14:52:12.889 INFO - Selenium build info: version: '3.0.1', revision: '1969d75'
	14:52:12.890 INFO - Launching Selenium Grid hub
	2016-12-22 14:52:13.418:INFO::main: Logging initialized @838ms
	14:52:13.432 INFO - Will listen on 4444
	2016-12-22 14:52:13.483:INFO:osjs.Server:main: jetty-9.2.15.v20160210
	2016-12-22 14:52:13.508:INFO:osjsh.ContextHandler:main: Started o.s.j.s.ServletContextHandler@33cb5951{/,null,AVAILABLE}
	2016-12-22 14:52:13.532:INFO:osjs.ServerConnector:main: Started ServerConnector@6a396c1e{HTTP/1.1}{0.0.0.0:4444}
	2016-12-22 14:52:13.533:INFO:osjs.Server:main: Started @952ms
	14:52:13.534 INFO - Nodes should register to http://192.168.2.48:4444/grid/register/
	14:52:13.534 INFO - Selenium Grid hub is up and running*/

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
        	        
        	        if(isSeleniumHubRunning(10)){
        	            System.out.println("Selenium Grid is Running");
        	        }else{
        	            System.err.println("*** Selenium Grid is down");
        	        }
        	        
                        //?? check if hub is started properly ??//
        	        
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
    
     public boolean isSeleniumHubRunning(int timeOut){
	 int count = 0 ;
	 while(count < timeOut){
        	  try{    
        	             Thread.sleep(1000);
                 	     URL u = new URL ( "http://localhost:4444/grid/console");
                 	     HttpURLConnection huc =  ( HttpURLConnection )  u.openConnection (); 
                 	     huc.setRequestMethod ("GET");  //OR  huc.setRequestMethod ("HEAD"); 
                 	     huc.connect () ; 
                 	     int code = huc.getResponseCode() ;
                 	     System.out.println(code);
                 	     return true;
         	     }catch(Exception e){
         		 System.err.println("Selenium Grid is still down.....");  		 
         		 count++;
         		 //return false;
         	     } 
	 }
	 System.err.println("Selenium Grid failed to start up even after   " + timeOut + "  seconds");
	 return false;
     }
     
     public void stopAllServers(){
	 try{
        	     String s = null;
        	     String PID  = null;
        	     Process p = null;
        	     BufferedReader stdInput = null;
        	     
        	     //Stop appium
        	     p = Runtime.getRuntime().exec("pgrep -f  appium");
        	  		 
        	     stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
        	     
        	     while ((s = stdInput.readLine()) != null) {
        	                System.out.println("Appium Server Found   " + s);
        	                PID = s;
        	                Runtime.getRuntime().exec("kill -9 " + PID );
        	     }
        	           	         	     
        	     //Stop Selenim HUB - Method 1      	   Not Working
        	     /*p = Runtime.getRuntime().exec("pgrep -f selenium-server-standalone");
        	 
        	     stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
        	     
        	     while ((s = stdInput.readLine()) != null) {
        	                System.out.println(s);
        	                PID = s;
        	                Runtime.getRuntime().exec("kill -9 " + PID);
        	     }*/
        	     
        	            	     
        	   //Stop Selenim HUB - Method 2
        	     if (hub != null) { 
        		   hub.stop(); 
        	     }
        	     
	     
	 }catch(Exception e){
	     e.printStackTrace();
	 }
     }
     
     //Check manually if you're able to register node with Hub
     
     //METHOD 1
     //http://www.vimalselvam.com/2016/05/15/selenium-grid-for-appium-mobile-automation/ 
     //appium --nodeconfig EMULATOR_Nexus_4_1.json -p 4723 -bp 4724   
     /*//[Appium] Welcome to Appium v1.6.3
     [Appium] Non-default server args:
	 [Appium]   nodeconfig: 'EMULATOR_Nexus_4_1.json'
	 [debug] [Appium] Starting auto register thread for grid. Will try to register every 10000 ms.
	 [Appium] Appium REST http interface listener started on 0.0.0.0:4723
	 [debug] [Appium] Appium successfully registered with the grid on 127.0.0.1:4444
	 [HTTP] --> GET /wd/hub/status {}*/
     //Selenium HUB output >> Registered a node http://0.0.0.0:4723
     
     //METHOD 2
     //https://mattfritz.github.io/2015/05/08/scale-mobile-testing-using-appium-and-selenium-grid/  
     //appium --nodeconfig EMULATOR_Nexus_4_2.json -p 4823 -bp 4824  ( UDID is in json file itself )
     /*[Appium] Welcome to Appium v1.6.3
        [Appium] Non-default server args:
        [Appium]   port: 4823
        [Appium]   bootstrapPort: 4824
        [Appium]   nodeconfig: 'EMULATOR_Nexus_4_2.json'
        [debug] [Appium] Starting auto register thread for grid. Will try to register every 10000 ms.
        [Appium] Appium REST http interface listener started on 0.0.0.0:4823
        [debug] [Appium] Appium successfully registered with the grid on 127.0.0.1:4444
        [HTTP] --> GET /wd/hub/status {}*/
     
     //correct json format refer to https://github.com/Tanmoyray/AppiumParallelExecutionSample/tree/master/GITSERVER/JSONS
     
     //https://discuss.appium.io/t/unable-to-start-appium-service-by-appiumdriverlocalserivce/6324/18
     //https://github.com/appium/appium/blob/master/docs/en/advanced-concepts/grid.md
     
     //https://github.com/appium/appium/issues/1704
     //https://github.com/appium/java-client/tree/master/src/main/java/io/appium/java_client/service/local/flags
     
     //http://appium.io/slate/en/master/?ruby#appium-server-arguments - Appium Server Arguments
     //https://github.com/saikrishna321/AppiumTestDistribution/blob/c9b065cb084fa6d83272514784da455f7cf076fe/src/main/java/com/appium/manager/AppiumManager.java
     
     //https://github.com/appium/java-client/blob/master/docs/The-starting-of-an-app-using-Appium-node-server-started-programmatically.md#which-parameters-also-can-be-defined
     //https://github.com/appium/appium/blob/9441a89f7e368f0c621db3a0cf9ab074c045ac3a/docs/en/writing-running-appium/server-args.md
     
     //https://github.com/appium/java-client/issues/544
     
     public void generate_node_config(int devicesCount){
             int localCount = 1;
	     int portNumber = 4700;
             
	 while(localCount <= devicesCount){
                	 //?? put sequence is not maintained
                	  JSONObject capData = new JSONObject();	
                	  capData.put("applicationName", "dummy Android 1");
                	  capData.put("browserName", "Android");
                	  capData.put("platformName", "ANDROID");
                	  capData.put("maxInstances", 1);
                
                	  JSONArray capArray = new JSONArray();
                	  capArray.add(capData);
                  
                	  JSONObject parentData = new JSONObject();
                	  JSONObject configData = new JSONObject();
                	  
                	  configData.put("cleanUpCycle", 2000);
                	  configData.put("timeout", 30000);
                	  configData.put("proxy", "org.openqa.grid.selenium.proxy.DefaultRemoteProxy");
                	  configData.put("host", "127.0.0.1");
                	  configData.put("port", portNumber);
                	  configData.put("maxSession", 1);
                	  configData.put("register", true);
                	  configData.put("registerCycle", 5000);
                	  configData.put("hubPort", 4444);
                	  configData.put("hubHost", "192.168.2.48");
                                
                          parentData.put("capabilities", capArray);
                          parentData.put("configuration", configData);
                	           
                	  try (FileWriter file = new FileWriter(projectDirectory + "/node_configs/device_" + localCount + ".json")) {
                		file.write(parentData.toJSONString());
                		System.out.println("Successfully Copied JSON Object to File...");
                		System.out.println("\nJSON Object: " + parentData);
                	} catch (Exception e) {
                	    e.printStackTrace();
                	}
                	  
                	  localCount++;
                	  portNumber++;
	 }
	  
	 System.out.println("generate_node_config is Success"); 
     }
     
     //https://github.com/appium/java-client/blob/master/docs/The-starting-of-an-app-using-Appium-node-server-started-programmatically.md
     //http://appium.github.io/java-client/io/appium/java_client/service/local/AppiumServiceBuilder.html#withLogFile-java.io.File-
     public void startAppiumServerToRegisterNodeWithSeleniumHUB(int devicesCount){
	 int localCount = 1;
	 int portNumber = 4700;
	 int bootstrapPortNumber = 4800;
	 String UDID = null;
	 
	 while(localCount <= devicesCount){
	     String nodeConfigFilePath = projectDirectory + "/node_configs/device_" + localCount + ".json";
	     File logFile = new File( projectDirectory + "/appium_device_log/device_log" + localCount + ".log");
	     
	     UDID = deviceIds.get(localCount-1);
	     
        	     AppiumDriverLocalService driverLocalService1 = AppiumDriverLocalService
                             .buildService(new AppiumServiceBuilder()
                             	.withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
        				.usingDriverExecutable(new File("/usr/local/bin/node"))
                                     .usingPort(portNumber)
                                     .withLogFile(logFile)
                                     .withArgument(AndroidServerFlag.BOOTSTRAP_PORT_NUMBER, Integer.toString(bootstrapPortNumber) )
                                     .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                                     .withArgument(GeneralServerFlag.LOG_LEVEL, "debug")
                                     .withArgument(GeneralServerFlag.CONFIGURATION_FILE, nodeConfigFilePath)
                             .withCapabilities(new DesiredCapabilities(ImmutableMap.of(MobileCapabilityType.UDID, UDID))));
                     
        	     driverLocalService1.start();
             	
                     if( isAppiumServerRunning(15) ){
                         System.out.println("Appium Server is Running For Device   " + UDID );
        	     }else{
        	            System.err.println("*** Appium Server is down");
        	    }
	     
                     localCount++;
                     portNumber++;
                     bootstrapPortNumber++;
	 }
	 System.out.println("startAppiumServerToRegisterNodeWithSeleniumHUB is Success");
     }
         
     public ArrayList<String> PID_Devices = new ArrayList<String>();
     public static int globalDevicesCount = 0;
     public boolean isAppiumServerRunning(int timeOut){
	 int count = 0;
	 String s = null;
	 //String PID  = null;
	 Process p = null;
	 BufferedReader stdInput = null;
	 int localDeviceCount = 0; 
	 PID_Devices.clear();
	 
	 while(count < timeOut){
        	 try{
                	           Thread.sleep(1000);
                	           p = Runtime.getRuntime().exec("pgrep -f  appium");
        	  		 
                          	   stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
                          	     
                          	     while ((s = stdInput.readLine()) != null) {
                          	                System.out.println("Appium Server Found   " + s);
                          	                PID_Devices.add(s);                 	              
                          	     }
                          	     
                          	     if ( PID_Devices.size() > 0 ){
                          		localDeviceCount = PID_Devices.size();
                          	     }
                          	     
                          	     if( localDeviceCount > globalDevicesCount ){ 
                          		globalDevicesCount = localDeviceCount;
                	                 return true;
                          	     }else{
                          		 count++;
                          		 System.err.println("Appium Server is still down.....");  
                          		 continue;
                          	     }
        	 }catch(Exception e){
        	     e.printStackTrace();
        	     count++;  
        	 }
	 }
	 
	 System.err.println("Appium Server failed to start even after   " + timeOut + "  seconds");
	 return false;
     }
            
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
	
     public static List<String> deviceIds = new ArrayList<String>(); 
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
				deviceIds.add(deviceID);
				
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
                 
     //Get free ports https://github.com/sameer49/Appium-Grid-For-Android/blob/master/src/libs/AvailabelPorts.java
     
     /*Before you run this program, make sure to try it out from command line with below command
      * appium --nodeconfig EMULATOR_Nexus_4_1.json --port 4445
      * In case you do something wrong as appium --nodeconfig EMULATOR_Nexus_4_1.json --bp 2252, you will get nice output about flags usage
     */
              
}
