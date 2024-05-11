import api.dto.CreateUserSuccessfulResponse;
import api.staticmethodsandvariables.UserAPI;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ui.data.*;
import ui.page.object.*;

public class AuthorizationTest {
    private WebDriver webDriver;
    UserAPI userMaria;
    private String userAssesToken;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().clearDriverCache().clearResolutionCache().setup();
        webDriver = new ChromeDriver();

        userMaria = new UserAPI();
        userAssesToken = userMaria.createUserMaria().as(CreateUserSuccessfulResponse.class).getAccessToken().replace("Bearer ","");
        System.out.println(userAssesToken);
    }


    @Test
    public void doesLoginWithLoginAccountButtonWorks (){

        MainPage objMainPage = new MainPage(webDriver);
        objMainPage.openPage();
        objMainPage.clickToLoginAccountButton();

        LoginPage objLoginPage = new LoginPage(webDriver);
        objLoginPage.waitForLoadingLoginPage();
        objLoginPage.fillInFieldEmail(Registration.EMAIL);
        objLoginPage.fillInFieldPassword(Registration.PASSWORD);
        objLoginPage.clickToLoginButton();

        objMainPage.waitForLoadingMainPage();
        String actualText = objMainPage.getTextFromButton();
        String expectedText = "Оформить заказ";
        Assert.assertEquals("Authorization doesn't work", expectedText, actualText);

    }
    @Test
    public void doesLoginWithPersonalAccountButtonWorks (){

        MainPage objMainPage = new MainPage(webDriver);
        objMainPage.openPage();
        objMainPage.clickToPersonalAccountButton();

        LoginPage objLoginPage = new LoginPage(webDriver);
        objLoginPage.waitForLoadingLoginPage();
        objLoginPage.fillInFieldEmail(Registration.EMAIL);
        objLoginPage.fillInFieldPassword(Registration.PASSWORD);
        objLoginPage.clickToLoginButton();

        objMainPage.waitForLoadingMainPage();
        String actualText = objMainPage.getTextFromButton();
        String expectedText = "Оформить заказ";
        Assert.assertEquals("Authorization doesn't work", expectedText, actualText);
    }

    @Test
    public void doesLoginWithLoginButtonFromRegisterPageWorks (){

        MainPage objMainPage = new MainPage(webDriver);
        objMainPage.openPage();
        objMainPage.clickToLoginAccountButton();

        LoginPage objLoginPage = new LoginPage(webDriver);
        objLoginPage.waitForLoadingLoginPage();
        objLoginPage.goToRegisterPage();

        RegisterPage objRegistrationPage = new RegisterPage(webDriver);
        objRegistrationPage.waitForLoadingLoginPage();
        objRegistrationPage.clickLoginButton();

        objLoginPage.waitForLoadingLoginPage();
        objLoginPage.fillInFieldEmail(Registration.EMAIL);
        objLoginPage.fillInFieldPassword(Registration.PASSWORD);
        objLoginPage.clickToLoginButton();

        objMainPage.waitForLoadingMainPage();
        String actualText = objMainPage.getTextFromButton();
        String expectedText = "Оформить заказ";
        Assert.assertEquals("Authorization doesn't work", expectedText, actualText);
    }

    @Test
    public void doesLoginWithPasswordRecoveryButtonWorks (){

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
        objLoginPage.fillInFieldEmail(Registration.EMAIL);
        objLoginPage.fillInFieldPassword(Registration.PASSWORD);
        objLoginPage.clickToLoginButton();

        objMainPage.waitForLoadingMainPage();
        String actualText = objMainPage.getTextFromButton();
        String expectedText = "Оформить заказ";
        Assert.assertEquals("Authorization doesn't work", expectedText, actualText);
    }
    @After
    public void teardown() {
        userMaria.deleteUser(userAssesToken);
        MainPage objMainPage = new MainPage(webDriver);
        objMainPage.openPage();
        String text = objMainPage.getTextFromButton();
        switch (text){
            case "Оформить заказ":
                objMainPage.clickToLoginAccountButton();

                ProfilePage objProfilePage = new ProfilePage(webDriver);
                objProfilePage.waitForLoadingProfilePage();
                objProfilePage.clickToExitButton();

                LoginPage objLoginPage = new LoginPage(webDriver);
                objLoginPage.waitForLoadingLoginPage();

                break;
            case "Войти в аккаунт":
                break;
        }
        webDriver.quit();

    }
}
