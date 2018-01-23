package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {
    @FindBy(xpath = "//div[contains(@class, 'bp-area header_more_nav')]//div[@class= 'alt-menu-mid']")
    WebElement menuItems;

    @FindBy(xpath = "//div[@class = 'sbrf-div-list-inner --area bp-area header_more_nav']//a[contains(@aria-label, 'Раздел Застраховать себя  и имущество')]/../div[contains(@class, 'alt-menu-collapser__area_cols_3')]")
    WebElement subMenu;

    public MainPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public void selectMainMenu(String menuItem){
        menuItems.findElement(By.xpath(".//*[contains(text(), '" + menuItem + "')]")).click();
    }

    public void selectSubMenu(String menuItem){
        subMenu.findElement(By.xpath(".//*[contains(text(), '" + menuItem + "')]")).click();
    }
}