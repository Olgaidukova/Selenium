package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Strahovanie {
    @FindBy(xpath = "//h1[contains(text(),'Страхование путешественников')]")
    public WebElement title;

    @FindBy(xpath = "//a//img [contains(@src,'banner-zashita-traveler')]")
    public WebElement button;

    public Strahovanie (WebDriver driver){
        PageFactory.initElements(driver, this);
        Wait<WebDriver> wait = new WebDriverWait(driver,10, 1000);
        wait.until(ExpectedConditions.visibilityOf(title));
        wait.until(ExpectedConditions.visibilityOf(button));
    }
}
