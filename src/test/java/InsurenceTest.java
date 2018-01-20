import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;


/**
 * @author Ольга Гайдукова
 */
public class InsurenceTest {
    private WebDriver driver;
    private String baseUrl;


    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "drv/chromedriver.exe");

        //Шаг 1. Перейти на страницу http://www.sberbank.ru/ru/person
        baseUrl = "http://www.sberbank.ru/ru/person";
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(baseUrl);
    }

    @Test
    public void testInsurence () {
        Wait wait = new WebDriverWait(driver, 5, 1000);
        //Шаг 2. Нажать на – Застраховать себя и имущество
        driver.findElement(By.xpath("//*[contains(text(),'Застраховать себя')]")).click();

        //Шаг 3. Выбрать – Страхование путешественников
        driver.findElement(By.xpath("//*[contains(text(),'Страхование путешественников')]")).click();


        //Шаг 4. Проверить наличие на странице заголовка – Страхование путешественников
        Assert.assertEquals("Страхование путешественников", driver.findElement(By.xpath("//H1[not(@class='sr_only')]")).getText());

        //Шаг 5. Нажать на – Оформить Онлайн
        driver.findElement(By.xpath("//a//img[contains(@src,'banner-zashita-traveler')]")).click();

        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }

        //Шаг 6. На вкладке – Выбор полиса  выбрать сумму страховой защиты – Минимальная
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[contains(text(),\"Минимальная\")]"))));
        driver.findElement(By.xpath("//*[contains(text(),'Минимальная')]")).click();

        //Шаг 7. Нажать Оформить
        driver.findElement(By.xpath("//*[contains(text(),'Оформить')]")).click();

        //Шаг 8. Заполнение полей

        fillField(By.name("insured0_surname"), "Petrov");
        fillField(By.name("insured0_name"), "Petr");
        fillField(By.name("insured0_birthDate"), "01.01.1988");

        fillField(By.name("surname"), "Иванов");
        fillField(By.name("name"), "Иван");
        fillField(By.name("middlename"), "Петрович");
        fillField(By.name("birthDate"),"01.01.1988");
        driver.findElement(By.xpath("//*[@class=\"b-radio-field-entity ng-pristine ng-untouched ng-valid\"][@name=\"male\"]")).click();

        fillField(By.name("passport_series"),"1900");
        fillField(By.name("passport_number"),"555555");
        fillField(By.name("issueDate"), "01.01.2008");
        fillField(By.name("issuePlace"), "Там");


        //Шаг 9. Проверить, что все поля заполнены правильно
        Assert.assertEquals("Petrov", driver.findElement(By.xpath("//INPUT[@name='insured0_surname']")).getAttribute("value"));
        Assert.assertEquals("Petr", driver.findElement(By.xpath("//INPUT[@name='insured0_name']")).getAttribute("value"));
        Assert.assertEquals("01.01.1988", driver.findElement(By.name("insured0_birthDate")).getAttribute("value"));

        Assert.assertEquals("Иванов", driver.findElement(By.xpath("//INPUT[@name='surname']")).getAttribute("value"));
        Assert.assertEquals("Иван", driver.findElement(By.xpath("//INPUT[@name='name']")).getAttribute("value"));
        Assert.assertEquals("Петрович", driver.findElement(By.xpath("//INPUT[@name='middlename']")).getAttribute("value"));
        Assert.assertEquals("", driver.findElement(By.name("birthDate")).getAttribute("value"));

        Assert.assertEquals("1900", driver.findElement(By.xpath("//input[@placeholder='Серия']")).getAttribute("value"));
        Assert.assertEquals("555555", driver.findElement(By.xpath("//INPUT[@ng-model='formdata.insurer.documentList[0].DOCNUMBER']")).getAttribute("value"));
        Assert.assertEquals("01.01.2008", driver.findElement(By.name("issueDate")).getAttribute("value"));
        Assert.assertEquals("Там", driver.findElement(By.xpath("//TEXTAREA[@name='issuePlace']")).getAttribute("value"));


        //Шаг 10. Нажать продолжить
        driver.findElement(By.xpath("//SPAN[@ng-click='save()'][text()='Продолжить']")).click();

        //Шаг 11. Проверить, что появилось сообщение - Заполнены не все обязательные поля
        Assert.assertEquals("Заполнены не все обязательные поля", driver.findElement(By.xpath("//DIV[@ng-show='tryNext && myForm.$invalid']")).getText());
    }
    public void fillField (By locator, String value){
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(value);
    }


    @After
    public void afterTest () {
        driver.quit();
    }
}
