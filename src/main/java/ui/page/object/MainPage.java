package ui.page.object;

import io.qameta.allure.Step;
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
    private final By rollsTab = By.xpath("//span[text()='Булки']");
    private final By saucesTab = By.xpath("//span[text()='Соусы']");
    private final By fillingsTab = By.xpath("//span[text()='Начинки']");
    private final By activeTab = By.className("tab_tab_type_current__2BEPc");

    @Step("Открытие сайта и ожидание загрузки главной страницы (появление надписи 'Соберите бургер')")
    public void openPage() {
        webDriver.get("https://stellarburgers.nomoreparties.site/");
        waitForSomethingToBeVisible(headlineAssembleBurger);
    }
    @Step("Ожидание загрузки главной страницы (появление надписи 'Соберите бургер')")
    public void waitForLoadingMainPage(){
        waitForSomethingToBeVisible(headlineAssembleBurger);
    }
    @Step("Клик по кнопке 'Войти в аккаунт'")
    public void clickToLoginAccountButton(){
        clickSomewhere(loginAccountButton);
    }
    @Step("Клик по кнопке 'Личный кабинет'")
    public void clickToPersonalAccountButton(){
        clickSomewhere(personalAccountButton);
    }
    @Step("Клик по табу 'Булочки'")
    public void clickToRollsTab() {
        clickSomewhere(rollsTab);
    }
    @Step("Клик по табу 'Соусы'")
    public void clickToSaucesTab() {
        clickSomewhere(saucesTab);
    }
    @Step("Клик по табу 'Начинки'")
    public void clickToFillingsTab() {
        clickSomewhere(fillingsTab);
    }
    @Step("Получение текста кнопки, находящейся под списком ингредиентов")
    public String getTextFromButton() {
        return webDriver.findElement(loginAccountButton).getText();
    }
    @Step("Получение названия активного в данный момент таба")
    public String getTextFromActiveTab(){
        return webDriver.findElement(activeTab).getText();
    }
    @Step("Получение текста заголовка страницы")
    public String getTextFromHeadline(){
        return webDriver.findElement(headlineAssembleBurger).getText();
    }

    @Step("Клик по элементу в dom")
    private void clickSomewhere(By locator){
        webDriver.findElement(locator).click();
    }
    @Step("Ожидание, что элемент в dom станет видимым")
    private void waitForSomethingToBeVisible(By locator){
        new WebDriverWait(webDriver, Duration.ofMillis(2000L))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

}
