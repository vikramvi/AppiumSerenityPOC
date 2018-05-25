package com.serenity.appium.poc.utils;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReadToastMessage {
    private static String path = "screenshots";
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");

    private String takeScreenshot(WebDriver driver) {
        File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String targetFileName = dateFormat.format(new Date()) + ".jpg";
        File targetFile = new File(path, targetFileName);
        try {
            FileUtils.copyFile(sourceFile, targetFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return targetFileName;
    }

    public String readToastMessage(WebDriver driver) {
        String imageName = takeScreenshot(driver);
        String result = null;
        File imageFile = new File(path, imageName);
        try {
            ITesseract instance = new Tesseract();
            File tessDataFolder = LoadLibs.extractTessResources("tessdata");
            instance.setDatapath(tessDataFolder.getAbsolutePath());
            result = instance.doOCR(imageFile);
            System.out.println("Page contents = " + result);
        } catch (TesseractException e) {
            e.printStackTrace();
        }
        return result;
    }
}



