package ui.page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PasswordRecoveryPage {
    private final WebDriver webDriver;
    public PasswordRecoveryPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
    private final By loginButton = By.xpath("//a[text()='Войти']");
    public void waitForLoadingPasswordRecoveryPage(){
        waitForSomethingToBeClickable(loginButton);
    }
    public void clickToLoginButton() {
        clickSomewhere(loginButton);
    }

    private void clickSomewhere(By locator){
        webDriver.findElement(locator).click();
    }
    private void waitForSomethingToBeClickable(By locator){
        new WebDriverWait(webDriver, Duration.ofMillis(2000L))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }
}
