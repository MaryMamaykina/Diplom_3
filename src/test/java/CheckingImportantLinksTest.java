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

@RunWith(Parameterized.class)
public class CheckingImportantLinksTest {
    private WebDriver webDriver;

    private final String browserName;
    public CheckingImportantLinksTest (String browserName) {
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
    }

    @Test
    @DisplayName("Переход в личный кабинет по клику на кнопку 'Личный кабинет' работает?")
    @Description("Проверка перехода в личный кабинет по клику на кнопку 'Личный кабинет'")
    public void doesSwitchingToPersonalAccountWithPersonalAccountButtonWork(){
        MainPage objMainPage = new MainPage(webDriver);
        objMainPage.openPage();
        objMainPage.clickToPersonalAccountButton();

        LoginPage objLoginPage = new LoginPage(webDriver);
        objLoginPage.waitForLoadingLoginPage();
        String actualText = objLoginPage.getTextFromHeadline();
        String expectedText = "Вход";
        String errorText = "Switching to the personal account page with personal account button doesn't work";
        Assert.assertEquals(errorText, expectedText, actualText);
    }

    @Test
    @DisplayName("Переход на главную страницу по клику на кнопку 'Конструктор' работает?")
    @Description("Проверка перехода из личного кабинета на главную страницу по клику на кнопку 'Конструктор'")
    public void doesSwitchingToMainPageWithConstructorButtonWork(){
        MainPage objMainPage = new MainPage(webDriver);
        objMainPage.openPage();
        objMainPage.clickToPersonalAccountButton();

        LoginPage objLoginPage = new LoginPage(webDriver);
        objLoginPage.waitForLoadingLoginPage();
        objLoginPage.clickToConstructorButton();

        objMainPage.waitForLoadingMainPage();
        String actualText = objMainPage.getTextFromHeadline();
        String expectedText = "Соберите бургер";
        String errorText = "Switching to the main page with the constructor button doesn't work";
        Assert.assertEquals(errorText, expectedText, actualText);
    }

    @Test
    @DisplayName("Переход на главную страницу по клику на логотип 'Stellar Burgers' работает?")
    @Description("Проверка перехода из личного кабинета на главную страницу по клику на логотип 'Stellar Burgers'")
    public void doesSwitchingToMainPageWithStellarBurgersLogoWork(){
        MainPage objMainPage = new MainPage(webDriver);
        objMainPage.openPage();
        objMainPage.clickToPersonalAccountButton();

        LoginPage objLoginPage = new LoginPage(webDriver);
        objLoginPage.waitForLoadingLoginPage();
        objLoginPage.clickToStellarBurgersLogo();

        objMainPage.waitForLoadingMainPage();
        String actualText = objMainPage.getTextFromHeadline();
        String expectedText = "Соберите бургер";
        String errorText = "Switching to the main page with the Stellar Burgers logo doesn't work";
        Assert.assertEquals(errorText, expectedText, actualText);
    }

    @Test
    @DisplayName("Переход на таб 'Булки' работает?")
    @Description("Проверка работы перехода на таб 'Булки'")
    public void doesSwitchingToRollsTabWork() {
        MainPage objMainPage = new MainPage(webDriver);
        objMainPage.openPage();
        objMainPage.clickToFillingsTab();
        String actualText1 = objMainPage.getTextFromActiveTab();
        String expectedText1 = "Булки";
        String errorText1 = "Switching tabs doesn't work";
        Assert.assertNotEquals(errorText1, expectedText1, actualText1);

        objMainPage.clickToRollsTab();
        String actualText2 = objMainPage.getTextFromActiveTab();
        String expectedText2 = "Булки";
        String errorText2 = "Switching to the Rolls tab doesn't work";
        Assert.assertEquals(errorText2, expectedText2, actualText2);
    }

    @Test
    @DisplayName("Переход на таб 'Соусы' работает?")
    @Description("Проверка работы перехода на таб 'Соусы'")
    public void doesSwitchingToSaucesTabWork() {
        MainPage objMainPage = new MainPage(webDriver);
        objMainPage.openPage();
        objMainPage.clickToSaucesTab();
        String actualText = objMainPage.getTextFromActiveTab();
        String expectedText = "Соусы";
        String errorText = "Switching to the Sauces tab doesn't work";
        Assert.assertEquals(errorText, expectedText, actualText);
    }

    @Test
    @DisplayName("Переход на таб 'Начинки' работает?")
    @Description("Проверка работы перехода на таб 'Начинки'")
    public void doesSwitchingToFillingsTabWork() {
        MainPage objMainPage = new MainPage(webDriver);
        objMainPage.openPage();
        objMainPage.clickToFillingsTab();
        String actualText = objMainPage.getTextFromActiveTab();
        String expectedText = "Начинки";
        String errorText = "Switching to the Fillings tab doesn't work";
        Assert.assertEquals(errorText, expectedText, actualText);
    }

    @After
    public void teardown() {
        webDriver.quit();
    }
}
