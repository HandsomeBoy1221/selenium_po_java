package com.ceshiren.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ConcatPage extends BasePage{

    private By addLocator = By.xpath("//*[@class='ww_operationBar']/a[1]");
    private By memberTable = By.className("member_colRight_memberTable_td");

    public ConcatPage(WebDriver driver) {
        super(driver);
    }

    public MemberPage clickAddMember(){
        find(addLocator).click();
        return new MemberPage(driver);
    }

    public List<String> getMemberList(){
        List<WebElement> tdEleList = finds(memberTable);
        List<String> tdTitleList = new ArrayList<>();
        tdEleList.forEach(
                td -> {
                    String title = td.getAttribute("title");
                    System.out.println("title：" + title);
                    tdTitleList.add(title);
                }
        );
        return tdTitleList;
    }
}
