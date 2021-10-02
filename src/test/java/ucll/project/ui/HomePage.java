
package ucll.project.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class HomePage extends Page {

    @FindBy(id="userid")
    private WebElement useridField;

    @FindBy(id="password")
    private WebElement passwordField;

    @FindBy(id="Login")
    private WebElement loginButton;

    @FindBy(id="welcome")
    private WebElement welcomeText;

    @FindBy(id="Logout")
    private WebElement LogoutButton;

    public HomePage(WebDriver driver) {
        super(driver);
        driver.get(getPath()+"?command=Index");
    }

    public void setUseridField(String userid){
        useridField.clear();
        useridField.sendKeys(userid);
    }

    public void setPasswordField(String password){
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void submitValid(){loginButton.click();}

    public boolean containsheaderwith(String Header){
        List<WebElement> navElements = driver.findElements(By.tagName("li"));
        for(WebElement li: navElements){
            WebElement a = li.findElement(By.tagName("a"));
            String test = a.getText();
            if(Header.equals(test))
                return true;
        }
        return false;
    }

    public boolean containsWelcomeTextWithUserId(String userId) {
        return welcomeText.getText().contains(userId);
    }

    public void SubmitLogout() {
        loginButton.click();
    }



    public boolean userIdIsDisplayed() {
        return useridField.isDisplayed();
    }

    public boolean passwordIsDisplayed() {
        return passwordField.isDisplayed();
    }

}
