package konstrukt;
import io.qameta.allure.Description;
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

import java.io.IOException;

import static org.junit.Assert.assertTrue;
import static url.UrlConfig.MAIN_URL;
import static webdriver.Browser.CHROME;
import static webdriver.Browser.YANDEX;

@RunWith(Parameterized.class)
public class KonstruktSectionRefTest
{
    private MainPage mainPage;
    private WebDriver driver;
    private Browser browser;
    private WebDriverManagment webDriverManagment;

    public KonstruktSectionRefTest(Browser browser)
    {
        this.browser = browser;
    }

    @Parameterized.Parameters
    public static Object[][] getEnterAccount()
    {
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


    @DisplayName("Проверка переходов к разделам")
    @Description("Поэтапная проверка перехода по разделам, нажимая сначала соусы, начинки, булки")
    @Test
    public void checkBurgerSectionRef()
    {
        mainPage.clickSauseButton();
        assertTrue(mainPage.isSauseSectionVisible());

        mainPage.clickFillingButton();
        assertTrue(mainPage.isFillingSectionVisible());

        mainPage.clickBreadButton();
        assertTrue(mainPage.isBreadSectionVisible());
    }

    @After
    public void setDown()
    {
        driver.quit();
    }
}
