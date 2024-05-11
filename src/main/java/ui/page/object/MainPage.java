package ui.page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private final WebDriver webDriver;

    public MainPage (WebDriver webDriver) {
        this.webDriver = webDriver;
    }
    private final By personalAccountButton = By.xpath("//p[text()='Личный Кабинет']");
    private final By loginAccountButton = By.className("button_button__33qZ0");
    private final By headlineAssembleBurger = By.xpath("//h1[text()='Соберите бургер']");


    public void openPage() {
        webDriver.get("https://stellarburgers.nomoreparties.site/");
        waitForSomethingToBeClickable(loginAccountButton);
    }
    public void waitForLoadingMainPage(){
        new WebDriverWait(webDriver, Duration.ofMillis(2000L))
                .until(ExpectedConditions.elementToBeClickable(headlineAssembleBurger));
    }
    public void clickToLoginAccountButton (){
        clickSomewhere(loginAccountButton);
    }
    public void clickToPersonalAccountButton(){
        clickSomewhere(personalAccountButton);
    }
    public String getTextFromButton() {
        return webDriver.findElement(loginAccountButton).getText();
    }

    private void clickSomewhere(By locator){
        webDriver.findElement(locator).click();
    }
    private void waitForSomethingToBeClickable(By locator){
        new WebDriverWait(webDriver, Duration.ofMillis(2000L))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

}
