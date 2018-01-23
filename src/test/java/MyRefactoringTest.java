import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import pages.MainPage;
import pages.SendAppPage;
import pages.Strahovanie;
import pages.Vibor;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class MyRefactoringTest extends BaseTest {



    @Test
    public void newInsurenceTest () throws Exception  {
        driver.get(baseUrl + "/");
        MainPage mainPage = new MainPage(driver);
        mainPage.selectMainMenu("Застраховать себя");
        mainPage.selectSubMenu("Страхование путешественников");

        Strahovanie strahovanie = new Strahovanie(driver);
        assertEquals("Страхование путешественников",strahovanie.title.getText());
        strahovanie.button.click();
        ArrayList<String> NewTab = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(NewTab.get(1));

        Vibor vibor = new Vibor(driver);
        vibor.selectSum("Минимальная");
        vibor.selectIssueButton();

        SendAppPage sendAppPage = new SendAppPage(driver);
        sendAppPage.fillField("Фамилия застрахованного", "Petrov");
        sendAppPage.fillField("Имя застрахованного", "Petr");
        sendAppPage.fillField("Дата рождения застрахованного", "01.01.1988");
        sendAppPage.fillField("Фамилия", "Иванов");
        sendAppPage.fillField("Имя", "Иван");
        sendAppPage.fillField("Отчество", "Петрович");
        sendAppPage.fillField("Дата рождения", "01.01.1988");
        sendAppPage.sex("мужской");
        sendAppPage.fillField("Серия", "1900");
        sendAppPage.fillField("Номер", "555555");
        sendAppPage.fillField("Дата выдачи", "01.01.2008");
        sendAppPage.fillField("Кем выдан", "Там");


        assertEquals("Petrov", sendAppPage.insuredSurName.getAttribute("value"));
        assertEquals("Petr", sendAppPage.insuredName.getAttribute("value"));
        assertEquals("01.01.1988", sendAppPage.insuredBirthDate.getAttribute("value"));

        assertEquals("Иванов", sendAppPage.surname.getAttribute("value"));
        assertEquals("Иван", sendAppPage.name.getAttribute("value"));
        assertEquals("Петрович", sendAppPage.middlename.getAttribute("value"));
        assertEquals("", sendAppPage.birthDate.getAttribute("value"));
        assertEquals("female", sendAppPage.female.getAttribute("name"));
        assertEquals("1900", sendAppPage.passportSeries.getAttribute("value"));
        assertEquals("555555", sendAppPage.passportNumber.getAttribute("value"));
        assertEquals("01.01.2008", sendAppPage.issueDate.getAttribute("value"));
        assertEquals("Там", sendAppPage.issuePlace.getAttribute("value"));



        sendAppPage.continueClick();
        sendAppPage.checkError("Заполнены не все обязательные поля");
    }
}
