package com.ceshiren.page;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public BasePage() {
        if(null == driver){
            this.driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
            driver.get("https://work.weixin.qq.com/wework_admin/loginpage_wx");

            if(!Paths.get("cooke.yaml").toFile().exists()){
                WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(15), java.time.Duration.ofSeconds(2));
                wait.until(
                        webDriver -> StringUtils.contains(webDriver.getCurrentUrl(),"wework_admin/frame")
                );

                Set<Cookie> cookies = driver.manage().getCookies();
                ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
                try {
                    mapper.writeValue(new File("cooke.yaml"),cookies);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            List<HashMap<String,Object>> cookies = null;
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            TypeReference<List<HashMap<String,Object>>> typeReference = new TypeReference<List<HashMap<String, Object>>>() {};
            try {
                cookies = mapper.readValue(Paths.get("cooke.yaml").toFile(),typeReference);
            } catch (IOException e) {
                e.printStackTrace();
            }
            cookies.stream().filter(
                    cookie ->
                            StringUtils.contains(cookie.get("domain").toString(),
                                    ".work.weixin.qq.com"))
                    .forEach(cookie ->{
                        System.out.println("cookie" + cookie);
                        Cookie cookie1 = new Cookie(
                                cookie.get("name").toString(),
                                cookie.get("value").toString()
                        );
                        System.out.println("cookie1" + cookie1);
                        driver.manage().addCookie(cookie1);
                    });
            driver.navigate().refresh();
        }
    }

    public WebElement find(By by){
        WebElement element = driver.findElement(by);
        HighElement(element);
        //截图
        screen();
        //元素高亮去除
        UnHighElement(element);
        return element;
    }

    public List<WebElement> finds(By by){
        List<WebElement> elementList = driver.findElements(by);
        return elementList;
    }

    public void quite(){
        driver.quit();//浏览器退出操作
    }

    //截图
    private void screen(){
        long now = System.currentTimeMillis();
        File screenshotAs = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File screenJpg = Paths.get("jpg", now + ".jpg").toFile();
        try {
            FileUtils.copyFile(screenshotAs,screenJpg);
            //allure报告添加截图  --  allure添加附件

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //js 元素高亮

    private WebDriver HighElement(WebElement webElement){
        if(driver instanceof JavascriptExecutor)
            ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid red'",webElement);
        return driver;

    }
    private WebDriver UnHighElement(WebElement webElement){
        if(driver instanceof JavascriptExecutor)
            ((JavascriptExecutor)driver).executeScript("arguments[0].style.border=''",webElement);
        return driver;
    }

}
