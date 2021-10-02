
package ucll.project.ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends Page {

    @FindBy(id="userid")
    private WebElement useridField;

    @FindBy(id="firstName")
    private WebElement firstNameField;

    @FindBy(id="lastName")
    private WebElement lastNameField;

    @FindBy(id="password")
    private WebElement passwordField;

    @FindBy(id="signUp")
    private WebElement signUpButton;

    public RegisterPage(WebDriver driver) {
        super(driver);
        driver.get(getPath()+"?command=register");//refactor controller
    }

    public void setUseridField(String userid) {
        useridField.clear();
        useridField.sendKeys(userid);
    }

    public void setFirstNameField(String firstName) {
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
    }

    public void setLastNameField(String lastName) {
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }

    public void setPasswordField(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void setSignUpButton(){signUpButton.clear();}
}
