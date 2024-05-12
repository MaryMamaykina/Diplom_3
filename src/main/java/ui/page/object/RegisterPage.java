package ui.page.object;

import io.qameta.allure.Step;
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

    @Step("Ожидание загрузки страницы регистрации (появление кнопки 'Зарегистрироваться')")
    public void waitForLoadingRegisterPage(){
        new WebDriverWait(webDriver, Duration.ofMillis(2000L))
                .until(ExpectedConditions.elementToBeClickable(registerButton));
    }
    @Step("Заполнение поля имя (клик на инпут, очистка, заполнение)")
    public void fillInFieldName(String someName){
        clickSomewhere(inputName);
        clearField(inputName);
        fillInField(inputName, someName);
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
    @Step("Клик по кнопке 'Зарегистрироваться'")
    public void clickRegisterButton(){
        clickSomewhere(registerButton);
    }
    @Step("Клик по кнопке 'Войти'")
    public void clickLoginButton(){
        clickSomewhere(loginButton);
    }
    @Step("Ожидание появления сообщения об ошибке")
    public void waitForError(){
        new WebDriverWait(webDriver, Duration.ofMillis(2000L))
                .until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
    }
    @Step("Получение текста сообщения об ошибке")
    public String getErrorText () {
        return webDriver.findElement(errorMessage).getText();
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
}
