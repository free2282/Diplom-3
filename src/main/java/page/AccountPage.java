package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountPage {
    private final By saveButton = By.xpath(".//*[text()='Сохранить']");
    private final By konstrukorButton = By.xpath(".//*[text()='Конструктор']");
    private final By logoButton = By.className("AppHeader_header__logo__2D0X2");
    private final By exitButton = By.xpath(".//*[text()='Выход']");
    private final WebDriver driver;

    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Проверка видимости кнопки сохранить на странице личного каинета")
    public Boolean checkButtonSave() {
        return driver.findElement(saveButton).isDisplayed();
    }

    @Step("Проверка видимости кнопки сохранить на странице личного каинета")
    public void waitButtonSave() {
        new WebDriverWait(driver, Duration.ofSeconds(8)).until(ExpectedConditions.visibilityOfElementLocated(saveButton));
    }

    @Step("Переход по ссылке-кнопке конструктор")
    public void clickKotstruktButton() {
        driver.findElement(konstrukorButton).click();
    }

    @Step("Переход по ссылке-кнопке логотипа")
    public void clickLogoButton() {
        driver.findElement(logoButton).click();
    }

    @Step("Нажатие кнопки выйход на странице личного каинета")
    public void clickExit() {
        driver.findElement(exitButton).click();
    }
}
