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
    private final By inputName = By.xpath("//label[text()='Имя']/parent::div/input");
    private final By inputEmail = By.xpath("//label[text()='Email']/parent::div/input");
    private final By inputPassword = By.xpath("//input[@type='password']");
    private final By loginButton = By.xpath("//a[text()='Войти']");
    private final By errorMessage = By.xpath("//p[text()='Некорректный пароль']");

    public void waitForLoadingRegisterPage(){
        new WebDriverWait(webDriver, Duration.ofMillis(2000L))
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
    public void waitForError(){
        new WebDriverWait(webDriver, Duration.ofMillis(2000L))
                .until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
    }
    public String getErrorText () {
        return webDriver.findElement(errorMessage).getText();
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
