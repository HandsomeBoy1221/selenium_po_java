package com.ceshiren.page;
import com.ceshiren.page.entity.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class MemberPage extends BasePage {

    private By username = By.id("username");
    private By tid = By.id("memberAdd_acctid");
    private By tel = By.xpath("//*[@class='ww_telInput']/input");
    private By save = By.linkText("保存");

    public MemberPage(WebDriver driver) {
        super(driver);
    }

    public ConcatPage addMemberTrue(User user){
        find(username).clear();
        find(username).sendKeys(user.getUsername());
        find(tid).clear();
        find(tid).sendKeys(user.getAcctid());
        find(tel).clear();
        find(tel).sendKeys(user.getTel());
        find(save).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new ConcatPage(driver);
    }

    public String addMemberFalse(){
        return "error";
    }

}
