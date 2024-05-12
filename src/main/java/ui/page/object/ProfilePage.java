package ui.page.object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {
    private final WebDriver webDriver;
    public ProfilePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
    private final By exitButton = By.xpath("//button[text()='Выход']");
    @Step("Ожидание загрузки страницы профиля (появление кнопки 'Выход')")
    public void waitForLoadingProfilePage(){
        waitForSomethingToBeClickable(exitButton);
    }
    @Step("Клик по кнопке 'Выход'")
    public void clickToExitButton() {
        clickSomewhere(exitButton);
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
