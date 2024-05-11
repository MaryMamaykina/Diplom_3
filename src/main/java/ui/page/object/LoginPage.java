package ui.page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    private final WebDriver webDriver;

    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    private final By loginButton =By.xpath("//button[text()='Войти']");
    private final By inputEmail = By.name("name");
    private final By inputPassword = By.name("Пароль");
    private final By registerButton = By.xpath("//a[text()='Зарегистрироваться']");
    private final By passwordRecoveryButton = By.xpath("//a[text()='Восстановить пароль']");

    public void waitForLoadingLoginPage(){
        waitForSomethingToBeClickable(loginButton);
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
    public void clickToLoginButton() {
        clickSomewhere(loginButton);
    }
    public void goToRegisterPage(){
        clickSomewhere(registerButton);
    }
    public void goToPasswordRecoveryPage(){
        clickSomewhere(passwordRecoveryButton);
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
    private void waitForSomethingToBeClickable(By locator){
        new WebDriverWait(webDriver, Duration.ofMillis(2000L))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }
}
