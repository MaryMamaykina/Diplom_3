package ui.page.object;

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
    public void waitForLoadingProfilePage(){
        waitForSomethingToBeClickable(exitButton);
    }
    public void clickToExitButton() {
        clickSomewhere(exitButton);
    }

    private void clickSomewhere(By locator){
        webDriver.findElement(locator).click();
    }
    private void waitForSomethingToBeClickable(By locator){
        new WebDriverWait(webDriver, Duration.ofMillis(2000L))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }
}
