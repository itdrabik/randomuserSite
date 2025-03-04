package com.nordea.tests.gui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class GuiTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void guiTest() throws IOException {
        driver.get("https://randomuser.me/");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[@data-value]")));

        List<WebElement> icons = driver.findElements(By.xpath("//li[@data-value]"));
        Assert.assertEquals(icons.size(), 6, "Not exactly 6 icons found!");

        List<String> values = new ArrayList<>();

        for (int i = 0; i < icons.size(); i++) {
            WebElement icon = icons.get(i);
            String title = icon.getAttribute("data-title");
            icon.click();

            if (i == 0) {
                wait.until(ExpectedConditions.attributeToBeNotEmpty(icon, "data-value"));
                try { Thread.sleep(500); } catch (InterruptedException ignored) {}
            }

            wait.until(ExpectedConditions.attributeToBeNotEmpty(icon, "data-value"));

            String value = icon.getAttribute("data-value");
            System.out.println("Extracted: " + title + " -> " + value);

            values.add(value);
        }

        Assert.assertEquals(values.size(), 6, "Not exactly 6 values captured!");

        saveToCsv(values);
    }

    private void saveToCsv(List<String> values) throws IOException {
        String fileName = "test_results_" + System.currentTimeMillis() + ".csv";
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(String.join(",", values) + "\n");
        }
        System.out.println("Saved results to: " + fileName);
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
