package konstrukt;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import page.MainPage;
import webdriver.Browser;
import webdriver.WebDriverManagment;

import static org.junit.Assert.assertTrue;
import static url.UrlConfig.MAIN_URL;
import static webdriver.Browser.CHROME;
import static webdriver.Browser.YANDEX;

@RunWith(Parameterized.class)
public class KonstruktSectionRefTest {
    private MainPage mainPage;
    private WebDriver driver;
    private final Browser browser;
    private WebDriverManagment webDriverManagment;

    public KonstruktSectionRefTest(Browser browser) {
        this.browser = browser;
    }

    @Parameterized.Parameters
    public static Object[][] getEnterAccount() {
        return new Object[][]{
                {YANDEX},
                {CHROME},
        };
    }

    @Before
    public void setUp() {
        webDriverManagment = new WebDriverManagment();
        driver = webDriverManagment.setDriver(browser);

        mainPage = new MainPage(driver);
        driver.get(MAIN_URL);
    }

    @Step("Проверка переходов к разделу соусы")
    @DisplayName("Проверка переходов к разделу соусы")
    @Test
    public void checkSauseRefTest() {
        mainPage.clickSauseButton();
        assertTrue(mainPage.isSauseSectionVisible());
    }

    @Step("Проверка переходов к разделу начинки")
    @DisplayName("Проверка переходов к разделу начинки")
    @Test
    public void checkFilingRefTest() {
        mainPage.clickFillingButton();
        assertTrue(mainPage.isFillingSectionVisible());
    }

    @Step("Проверка переходов к разделу булки")
    @DisplayName("Проверка переходов к разделу булки")
    @Test
    public void checkBreadRefTest() {
        mainPage.clickFillingButton();

        mainPage.clickBreadButton();
        assertTrue(mainPage.isBreadSectionVisible());
    }


    @After
    public void setDown() {
        driver.quit();
    }
}
