package com.ceshiren.page;
import org.openqa.selenium.By;


public class MainPage extends BasePage {

    private By contacts = By.cssSelector("#menu_contacts > span");


    public ConcatPage toConcatPage(){
        find(contacts).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new ConcatPage(driver);
    }
}
