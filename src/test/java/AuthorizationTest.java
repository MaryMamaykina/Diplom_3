import api.dto.CreateUserSuccessfulResponse;
import api.staticmethodsandvariables.UserAPI;
import data.UserWithPassword;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ui.page.object.*;

@RunWith(Parameterized.class)
public class AuthorizationTest {
    private WebDriver webDriver;
    UserAPI userAPI;
    UserWithPassword userMamaria;

    private String userAssesToken;

    private final String browserName;
    public AuthorizationTest(String browserName) {
        this.browserName = browserName;
    }

    @Parameterized.Parameters
    public static Object[][] browsers() {
        return new Object[][]{
                {"Яндекс"},
                {"Chrome"}
        };
    }

    @Before
    public void setup() {
        WebDriverManager.chromedriver().clearDriverCache().clearResolutionCache().setup();
        if (browserName.equals("Яндекс")){
            System.setProperty("webdriver.chrome.driver", "src/main/resources/yandexdriver.exe");
        }
        webDriver = new ChromeDriver();

        userAPI = new UserAPI();
        userMamaria = new UserWithPassword("Мария", "MaryMamaykina@mail.ru", "MaryMamaykina");
        userAPI.createUser(userMamaria).as(CreateUserSuccessfulResponse.class);
    }

    @Test
    @DisplayName("Вход по кнопке 'Войти в аккаунт' работает?")
    @Description("Проверка входа по кнопке 'Войти в аккаунт' на главной")
    public void doesLoginWithLoginAccountButtonWork (){

        MainPage objMainPage = new MainPage(webDriver);
        objMainPage.openPage();
        objMainPage.clickToLoginAccountButton();

        LoginPage objLoginPage = new LoginPage(webDriver);
        objLoginPage.waitForLoadingLoginPage();
        objLoginPage.fillInFieldEmail(userMamaria.getEmail());
        objLoginPage.fillInFieldPassword(userMamaria.getPassword());
        objLoginPage.clickToLoginButton();

        objMainPage.waitForLoadingMainPage();
        String actualText = objMainPage.getTextFromButton();
        String expectedText = "Оформить заказ";
        Assert.assertEquals("Authorization doesn't work", expectedText, actualText);

    }
    @Test
    @DisplayName("Вход по кнопке 'Личный кабинет' работает?")
    @Description("Проверка входа через кнопку 'Личный кабинет'")
    public void doesLoginWithPersonalAccountButtonWork (){

        MainPage objMainPage = new MainPage(webDriver);
        objMainPage.openPage();
        objMainPage.clickToPersonalAccountButton();

        LoginPage objLoginPage = new LoginPage(webDriver);
        objLoginPage.waitForLoadingLoginPage();
        objLoginPage.fillInFieldEmail(userMamaria.getEmail());
        objLoginPage.fillInFieldPassword(userMamaria.getPassword());
        objLoginPage.clickToLoginButton();

        objMainPage.waitForLoadingMainPage();
        String actualText = objMainPage.getTextFromButton();
        String expectedText = "Оформить заказ";
        Assert.assertEquals("Authorization doesn't work", expectedText, actualText);
    }

    @Test
    @DisplayName("Вход по кнопке входа на странице регистрации работает?")
    @Description("Проверка входа через кнопку в форме регистрации")
    public void doesLoginWithLoginButtonFromRegisterPageWork (){

        MainPage objMainPage = new MainPage(webDriver);
        objMainPage.openPage();
        objMainPage.clickToLoginAccountButton();

        LoginPage objLoginPage = new LoginPage(webDriver);
        objLoginPage.waitForLoadingLoginPage();
        objLoginPage.goToRegisterPage();

        RegisterPage objRegistrationPage = new RegisterPage(webDriver);
        objRegistrationPage.waitForLoadingRegisterPage();
        objRegistrationPage.clickLoginButton();

        objLoginPage.waitForLoadingLoginPage();
        objLoginPage.fillInFieldEmail(userMamaria.getEmail());
        objLoginPage.fillInFieldPassword(userMamaria.getPassword());
        objLoginPage.clickToLoginButton();

        objMainPage.waitForLoadingMainPage();
        String actualText = objMainPage.getTextFromButton();
        String expectedText = "Оформить заказ";
        Assert.assertEquals("Authorization doesn't work", expectedText, actualText);
    }

    @Test
    @DisplayName("Вход по кнопке входа на странице восстановления пароля работает?")
    @Description("Проверка входа через кнопку в форме восстановления пароля")
    public void doesLoginFromPasswordRecoveryPageWork (){

        MainPage objMainPage = new MainPage(webDriver);
        objMainPage.openPage();
        objMainPage.clickToLoginAccountButton();

        LoginPage objLoginPage = new LoginPage(webDriver);
        objLoginPage.waitForLoadingLoginPage();
        objLoginPage.goToPasswordRecoveryPage();

        PasswordRecoveryPage objPasswordRecoveryPage = new PasswordRecoveryPage(webDriver);
        objPasswordRecoveryPage.waitForLoadingPasswordRecoveryPage();
        objPasswordRecoveryPage.clickToLoginButton();


        objLoginPage.waitForLoadingLoginPage();
        objLoginPage.fillInFieldEmail(userMamaria.getEmail());
        objLoginPage.fillInFieldPassword(userMamaria.getPassword());
        objLoginPage.clickToLoginButton();

        objMainPage.waitForLoadingMainPage();
        String actualText = objMainPage.getTextFromButton();
        String expectedText = "Оформить заказ";
        Assert.assertEquals("Authorization doesn't work", expectedText, actualText);
    }
    @After
    public void teardown() {
        userAssesToken = userAPI.loginUser(userMamaria).as(CreateUserSuccessfulResponse.class).getAccessToken().replace("Bearer ","");
        userAPI.deleteUser(userAssesToken);
        webDriver.quit();
    }
}
