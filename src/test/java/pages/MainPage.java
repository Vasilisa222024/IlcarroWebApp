package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class MainPage extends BasePage {
    public MainPage(WebDriver driver) {
        setDriver(driver);
        driver.get("https://ilcarro.web.app/");
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }

    public <T extends BasePage> T openTopMenu(String topMenuItem) {
        WebElement menuItem = driver.findElement(By.xpath("//a[contains(text(),'" + topMenuItem + "')]"));
        menuItem.click();

        switch (topMenuItem) {
            case "Search":
                return (T) new SearchPage(driver);
            case "Let the car work":
                return (T) new LetTheCarWorkPage(driver);
            case "Terms of use":
                return (T) new TermsOfUsePage(driver);
            case "Sign up":
                return (T) new SignUpPage(driver);
            case "Log in":
                return (T) new LogInPage(driver);
            default:
                throw new IllegalArgumentException("Somethings wrong" + topMenuItem);

        }
    }
}
