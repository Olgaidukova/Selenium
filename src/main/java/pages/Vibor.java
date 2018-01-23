package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Vibor {
    @FindBy(xpath = "//h3 [text()='Выберите сумму страховой защиты']/parent::section")
    WebElement programma;

    @FindBy(xpath = "//span [contains(text(),'Оформить')]")
    WebElement issueBatton;



    public Vibor(WebDriver driver){
        PageFactory.initElements(driver, this);
        Wait<WebDriver> wait = new WebDriverWait(driver,10, 1000);
        wait.until(ExpectedConditions.visibilityOf(programma));
        JavascriptExecutor js =(JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView(true);",programma);
        wait.until(ExpectedConditions.visibilityOf(issueBatton));

    }

    public void selectSum(String menuItem){
        programma.findElement(By.xpath(".//div[text()='"+ menuItem +"']")).click();

    }

    public void selectIssueButton(){
        issueBatton.click();
    }


}
