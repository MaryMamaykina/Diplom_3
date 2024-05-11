package ui.page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RegisterPage {
    private final WebDriver webDriver;
    public RegisterPage (WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    private final By registerButton = By.xpath("//button[text()='Зарегистрироваться']");
    private final By inputName = By.xpath("//label[text()='Имя']/parent::div");
    private final By inputEmail = By.xpath("//label[text()='Email']/parent::div");
    private final By inputPassword = By.name("Пароль");
    private final By loginButton = By.xpath("//a[text()='Войти']");

    public void waitForLoadingLoginPage(){
        new WebDriverWait(webDriver, Duration.ofMillis(500L))
                .until(ExpectedConditions.elementToBeClickable(registerButton));
    }
    public void fillInFieldName(String someName){
        clickSomewhere(inputName);
        clearField(inputName);
        fillInField(inputName, someName);
    }
    public void fillInFieldEmail(String someEmail){
        clickSomewhere(inputEmail);
        clearField(inputEmail);
        fillInField(inputEmail, someEmail);
    }
    public void fillInFieldPassword(String somePassword){
        clickSomewhere(inputPassword);
        clearField(inputPassword);
        fillInField(inputPassword, somePassword);
    }
    public void clickRegisterButton(){
        clickSomewhere(registerButton);
    }
    public void clickLoginButton(){
        clickSomewhere(loginButton);
    }

    private void clickSomewhere(By locator){
        webDriver.findElement(locator).click();
    }
    private void clearField(By locator){
        webDriver.findElement(locator).clear();
    }
    private void fillInField(By locator, String text){
        webDriver.findElement(locator).sendKeys(text);
    }
}
