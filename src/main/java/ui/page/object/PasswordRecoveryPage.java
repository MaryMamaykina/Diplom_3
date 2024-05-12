package ui.page.object;

import io.qameta.allure.Step;
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

    @Step("Ожидание загрузки страницы восстановления пароля (появление кнопки 'Вход')")
    public void waitForLoadingPasswordRecoveryPage(){
        waitForSomethingToBeClickable(loginButton);
    }
    @Step("Клик по кнопке 'Вход'")
    public void clickToLoginButton() {
        clickSomewhere(loginButton);
    }

    @Step("Клик по элементу в dom")
    private void clickSomewhere(By locator){
        webDriver.findElement(locator).click();
    }
    @Step("Ожидание, что элемент в dom станет кликабельным")
    private void waitForSomethingToBeClickable(By locator){
        new WebDriverWait(webDriver, Duration.ofMillis(2000L))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }
}
