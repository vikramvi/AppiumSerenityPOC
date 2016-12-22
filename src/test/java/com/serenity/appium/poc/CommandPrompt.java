package com.serenity.appium.poc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandPrompt {
        Process p;
	ProcessBuilder builder;
	
	/**
	 * This method run command on windows and mac
	 * @param command to run  
	 */
	public String runCommand(String command) throws InterruptedException, IOException
	{
	  
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
        		       String[] temp = { "/Users/vikram-anna/Library/Android/sdk/platform-tools/adb", "dummy_command"};
        		       temp[1] = command;
        			p = Runtime.getRuntime().exec(temp);
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
	
	}
}
