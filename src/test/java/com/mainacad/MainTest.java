package com.mainacad;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class MainTest {


    //Пример DataProvider метода
    @DataProvider
    public Object[] myDataProvider() {
        return new Object[]{
                "Data 1 from my data provider",
                "Data 2 from my data provider",
                "Data 3 from my data provider",
                "Data 4 from my data provider",
                "Data 5 from my data provider"
        };
    }

    //Пример теста с использованием DataProvider
    @Test(dataProvider = "myDataProvider")
    public void testWithDataProvider(String inputData) throws InterruptedException {
        System.out.println("Test received: " + inputData);
        Thread.sleep(1000);
        Assert.assertTrue(true);
    }


    @DataProvider
    public Object[] testDataProvider () throws IOException {
        BufferedReader device = new BufferedReader(new FileReader("deviceList.txt"));
        List<String> lines = new ArrayList<>();
        String line;
        while ((line = device.readLine()) != null) {
            lines.add(line);
            // System.out.println(device);
        }

        Object[] objects = lines.toArray();
// If you want to convert to a String[]

        return objects;
    }


    //TODO: Напишите @DataProvider метод
    // метод должен считать данные из файла deviceList.txt и привести их к типу Object[]
    // справка для чтения файла: https://stackoverflow.com/questions/2977075/java-how-to-read-a-txt-file-to-an-array-of-strings


    //TODO: Перепишите тест testGoogleSearch с использованием @DataProvider
    // Тест должен выполнится для каждого девайса из списка в deviceList.txt

    @Test(dataProvider = "testDataProvider")
    public void testGoogleSearch(String device) throws InterruptedException {
        //Передаём девайс для эмуляции и создаём драйвер
        WebDriver driver = Main.getDriver(device);

        driver.get("http://www.google.com/xhtml");
        Thread.sleep(3000);

        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("automation");
        searchBox.submit();
        Thread.sleep(3000);

        driver.quit();
    }

}
