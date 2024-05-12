package ui.page.object;

import io.qameta.allure.Step;
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
    private final By headlineLogin = By.xpath("//h2[text()='Вход']");
    private final By constructorButton = By.xpath("//p[text()='Конструктор']");
    private final By stellarBurgersLogo = By.className("AppHeader_header__logo__2D0X2");

    @Step("Ожидание загрузки страницы входа (появление надписи 'Вход')")
    public void waitForLoadingLoginPage(){
        waitForSomethingToBeVisible(headlineLogin);
    }
    @Step("Заполнение поля e-mail (клик на инпут, очистка, заполнение)")
    public void fillInFieldEmail(String someEmail){
        clickSomewhere(inputEmail);
        clearField(inputEmail);
        fillInField(inputEmail, someEmail);
    }
    @Step("Заполнение поля пароль (клик на инпут, очистка, заполнение)")
    public void fillInFieldPassword(String somePassword){
        clickSomewhere(inputPassword);
        clearField(inputPassword);
        fillInField(inputPassword, somePassword);
    }
    @Step("Клик по кнопке 'Войти'")
    public void clickToLoginButton() {
        clickSomewhere(loginButton);
    }
    @Step("Клик по кнопке 'Зарегистироваться'")
    public void goToRegisterPage(){
        clickSomewhere(registerButton);
    }
    @Step("Клик по кнопке 'Восстановить пароль'")
    public void goToPasswordRecoveryPage(){
        clickSomewhere(passwordRecoveryButton);
    }
    @Step("Клик по кнопке 'Конструктор'")
    public void clickToConstructorButton() {
        clickSomewhere(constructorButton);
    }
    @Step("Клик по логотипу Stellar Burgers")
    public void clickToStellarBurgersLogo(){
        clickSomewhere(stellarBurgersLogo);
    }
    @Step("Получение текста заголовка страницы")
    public String getTextFromHeadline() {
        return webDriver.findElement(headlineLogin).getText();
    }

    @Step("Клик по элементу в dom")
    private void clickSomewhere(By locator){
        webDriver.findElement(locator).click();
    }
    @Step("Очистка поля ввода")
    private void clearField(By locator){
        webDriver.findElement(locator).clear();
    }
    @Step("Заполнение поля ввода")
    private void fillInField(By locator, String text){
        webDriver.findElement(locator).sendKeys(text);
    }
    @Step("Ожидание, что элемент в dom станет видимым")
    private void waitForSomethingToBeVisible(By locator){
        new WebDriverWait(webDriver, Duration.ofMillis(2000L))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
