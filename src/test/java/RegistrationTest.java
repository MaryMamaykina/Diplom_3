import api.dto.CreateUserSuccessfulResponse;
import api.staticmethodsandvariables.UserAPI;
import data.UserWithPassword;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ui.page.object.LoginPage;
import ui.page.object.MainPage;
import ui.page.object.RegisterPage;

@RunWith(Parameterized.class)
public class RegistrationTest {
    private WebDriver webDriver;
    UserAPI userAPI;
    UserWithPassword userMamaria;

    private String userAssesToken;

    private final String browserName;
    public RegistrationTest (String browserName) {
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
        if (browserName.equals("Яндекс")) {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/yandexdriver.exe");
        }
        webDriver = new ChromeDriver();
        userMamaria = new UserWithPassword ("Мария", "MaryMamaykina@mail.ru", "MaryMamaykina");
    }

    @Test
    public void doesRegistrationWork(){
        MainPage objMainPage = new MainPage(webDriver);
        objMainPage.openPage();
        objMainPage.clickToLoginAccountButton();

        LoginPage objLoginPage = new LoginPage(webDriver);
        objLoginPage.waitForLoadingLoginPage();
        objLoginPage.goToRegisterPage();

        RegisterPage objRegistrationPage = new RegisterPage(webDriver);
        objRegistrationPage.waitForLoadingRegisterPage();
        objRegistrationPage.fillInFieldName(userMamaria.getName());
        objRegistrationPage.fillInFieldEmail(userMamaria.getEmail());
        objRegistrationPage.fillInFieldPassword(userMamaria.getPassword());
        objRegistrationPage.clickRegisterButton();

        objLoginPage.waitForLoadingLoginPage();
        objLoginPage.fillInFieldEmail(userMamaria.getEmail());
        objLoginPage.fillInFieldPassword(userMamaria.getPassword());
        objLoginPage.clickToLoginButton();

        objMainPage.waitForLoadingMainPage();
        String actualText = objMainPage.getTextFromButton();
        String expectedText = "Оформить заказ";
        Assert.assertEquals("Registration doesn't work", expectedText, actualText);
    }
    @Test
    public void doesNotRegistrationWorkWithShortPassword(){
        MainPage objMainPage = new MainPage(webDriver);
        objMainPage.openPage();
        objMainPage.clickToLoginAccountButton();

        LoginPage objLoginPage = new LoginPage(webDriver);
        objLoginPage.waitForLoadingLoginPage();
        objLoginPage.goToRegisterPage();

        RegisterPage objRegistrationPage = new RegisterPage(webDriver);
        objRegistrationPage.waitForLoadingRegisterPage();
        objRegistrationPage.fillInFieldName(userMamaria.getName());
        objRegistrationPage.fillInFieldEmail(userMamaria.getEmail());
        objRegistrationPage.fillInFieldPassword(userMamaria.getShortPassword());
        objRegistrationPage.clickRegisterButton();
        objRegistrationPage.waitForError();

        String actualText = objRegistrationPage.getErrorText();
        String expectedText = "Некорректный пароль";
        Assert.assertEquals("I can register with a short password", expectedText, actualText);
    }

    @After
    public void teardown() {
        MainPage objMainPage = new MainPage(webDriver);
        objMainPage.openPage();
        String textFromButton = objMainPage.getTextFromButton();
        if (textFromButton.equals("Оформить заказ")){
            userAPI = new UserAPI();
            userAssesToken = userAPI.loginUser(userMamaria).as(CreateUserSuccessfulResponse.class).getAccessToken().replace("Bearer ","");
            userAPI.deleteUser(userAssesToken);
        }
        webDriver.quit();
    }



}
