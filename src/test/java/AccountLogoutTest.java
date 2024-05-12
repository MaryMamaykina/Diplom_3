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
import ui.page.object.LoginPage;
import ui.page.object.MainPage;
import ui.page.object.ProfilePage;

@RunWith(Parameterized.class)
public class AccountLogoutTest {
    private WebDriver webDriver;
    UserAPI userAPI;
    UserWithPassword userMamaria;

    private String userAssesToken;

    private final String browserName;
    public AccountLogoutTest(String browserName) {
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
    @DisplayName("Кнопка'Выйти' в личном кабинете работает?")
    @Description("Проверка выхода по кнопке 'Выйти' в личном кабинете")
    public void doesButtonLogoutOnProfilePageWork(){
        MainPage objMainPage = new MainPage(webDriver);
        objMainPage.openPage();
        objMainPage.clickToLoginAccountButton();

        LoginPage objLoginPage = new LoginPage(webDriver);
        objLoginPage.waitForLoadingLoginPage();
        objLoginPage.fillInFieldEmail(userMamaria.getEmail());
        objLoginPage.fillInFieldPassword(userMamaria.getPassword());
        objLoginPage.clickToLoginButton();

        objMainPage.waitForLoadingMainPage();
        String actualText1 = objMainPage.getTextFromButton();
        String expectedText1 = "Войти в аккаунт";
        String errorText1 = "Problem with authorization";
        Assert.assertNotEquals(errorText1, expectedText1, actualText1);

        objMainPage.clickToPersonalAccountButton();

        ProfilePage objProfilePage = new ProfilePage(webDriver);
        objProfilePage.waitForLoadingProfilePage();
        objProfilePage.clickToExitButton();

        objLoginPage.waitForLoadingLoginPage();
        objLoginPage.clickToConstructorButton();

        objMainPage.waitForLoadingMainPage();
        String actualText2 = objMainPage.getTextFromButton();
        String expectedText2 = "Войти в аккаунт";
        String errorText2 = "The Logout button on the profile page doesn't work";
        Assert.assertEquals(errorText2, expectedText2, actualText2);

    }
    @After
    public void teardown() {
        userAssesToken = userAPI.loginUser(userMamaria).as(CreateUserSuccessfulResponse.class).getAccessToken().replace("Bearer ","");
        userAPI.deleteUser(userAssesToken);
        webDriver.quit();
    }
}
