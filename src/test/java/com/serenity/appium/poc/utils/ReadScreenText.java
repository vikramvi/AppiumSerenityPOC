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
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReadScreenText {
    private static String path = "screenshots";
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");

    private String takeScreenshot(WebDriver driver) {
        File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String targetFileName = dateFormat.format(new Date()) + ".png";
        File targetFile = new File(path, targetFileName);
        try {
            FileUtils.copyFile(sourceFile, targetFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return targetFileName;
    }

    public String readScreenText(WebDriver driver) throws TesseractException {
        String imageName = takeScreenshot(driver);
        String ocrData = null;
        File imageFile = new File(path, imageName);
        ITesseract instance = new Tesseract();
        File tessDataFolder = LoadLibs.extractTessResources("tessdata");
        instance.setDatapath(tessDataFolder.getAbsolutePath());
        ocrData = instance.doOCR(imageFile);
        System.out.println("Page contents = " + ocrData);
//        FileUtils.deleteQuietly(imageFile);
        return ocrData;
    }
}



