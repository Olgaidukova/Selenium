package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class SendAppPage  {

    @FindBy(xpath = "//span[text()='Продолжить']")
    WebElement button;

    @FindBy(xpath = "//input[@name = 'insured0_surname']")
    public WebElement insuredSurName;

    @FindBy(xpath = "//input[@name = 'insured0_name']")
    public WebElement insuredName;

    @FindBy(xpath = "//input[@name = 'insured0_birthDate']")
    public WebElement insuredBirthDate;

    @FindBy(name = "surname")
    public WebElement surname;

    @FindBy(name = "name")
    public WebElement name;

    @FindBy(name = "middlename")
    public WebElement middlename;

    @FindBy(name = "birthDate")
    public WebElement birthDate;

    @FindBy(name = "female")
    public WebElement female;

    @FindBy(name = "male")
    public WebElement male;

    @FindBy(name = "passport_series")
    public WebElement passportSeries;

    @FindBy(name = "passport_number")
    public WebElement passportNumber;

    @FindBy(name = "issueDate")
    public WebElement issueDate;

    @FindBy(name = "issuePlace")
    public WebElement issuePlace;

    @FindBy(xpath = "//div[@ng-show='tryNext && myForm.$invalid']")
    public WebElement error;

    public SendAppPage (WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void fillField(String fieldName, String value) {
        if (fieldName.equals("Фамилия застрахованного")) {
            fillElement(insuredSurName, value);
        }
            else if (fieldName.equals("Имя застрахованного")){
            fillElement(insuredName, value);
        }
            else if (fieldName.equals("Дата рождения застрахованного")){
            fillElement(insuredBirthDate, value);
        }
            else if (fieldName.equals("Фамилия")) {
            fillElement(surname, value);
        }
            else if (fieldName.equals("Имя")) {
            fillElement(name, value);
        }
            else if (fieldName.equals("Отчество")) {
            fillElement(middlename, value);
        }
            else if (fieldName.equals("Дата рождения")) {
            fillElement(birthDate, value);
        }
            else if (fieldName.equals("Серия")) {
            fillElement(passportSeries, value);
        }
            else if (fieldName.equals("Номер")) {
            fillElement(passportNumber, value);
        }
            else if (fieldName.equals("Дата выдачи")) {
            fillElement(issueDate, value);
        }
            else if (fieldName.equals("Кем выдан")) {
            fillElement(issuePlace, value);
        }

            else {
                throw new AssertionError("Поле '" + fieldName + "' отсутствует на странице");
        }
    }

    public void sex(String value) {

        if (value.equals("мужской")) {
            male.click();
        }
        else if (value.equals("женский")) {
            female.click();
        }
        else {
                throw new AssertionError("Поле '" + value + "' отсутствует на странице");
        }
    }
    protected void fillElement(WebElement element, String value)
    {
        element.clear();
        element.sendKeys(value);
    }
    public void continueClick() {
        button.click();
    }

    public void checkError(String stringError) {
        String actualValue = error.getText();
        Assert.assertTrue(String.format("Получено значение [%s]. Ожидаем [%s]", actualValue, stringError),
                actualValue.contains(stringError));


    }


}
