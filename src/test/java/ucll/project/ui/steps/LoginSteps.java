package ucll.project.ui.steps;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import ucll.project.ui.ChromeDriverHelper;
import ucll.project.ui.HomePage;
import ucll.project.ui.Page;
import ucll.project.ui.RegisterPage;
import static junit.framework.TestCase.*;

public class LoginSteps {

    private WebDriver driver;
    private String path = "http://localhost:8080/Controller";

    private Page currentPage;

    @Before
    public void setUp(){
        this.driver = ChromeDriverHelper.getDriver();
    }

    @After
    public void clean(){
        driver.get(path+"?command=Logout");
        driver.quit();
    }

    @Given("Aangemeld als 'Lector'")
    public void Aangemeld_als_Lector(){
        //hardcoded, lector kan niet registreren
        HomePage page = PageFactory.initElements(driver, HomePage.class);
        page.setUseridField("908846");
        page.setPasswordField("t");
        page.submitValid();
    }


    @When("'Lector' op de hoofdpagina kijkt")
    public void lectorOpDeHoofdpaginaKijkt() {
        currentPage = PageFactory.initElements(driver, HomePage.class);
    }

    @Then("Staat Overview bij de header")
    public void staatOverviewBijDeHeader() {
        assertEquals("Home", driver.getTitle());
        assertTrue(((HomePage)currentPage).containsheaderwith("Aanwezige Studenten"));
    }

    @Then("Naam van gebruiker is zichtbaar")
    public void naamVanGebruikerIsZichtbaar() {
        assertEquals("Home", driver.getTitle());
        assertTrue(((HomePage)currentPage).containsWelcomeTextWithUserId("jens"));
    }

    @Then("Logoutbutton werkt")
    public void logoutbuttonWerkt() {
        HomePage page = PageFactory.initElements(driver, HomePage.class);
        page.SubmitLogout();
        assertFalse(((HomePage)currentPage).userIdIsDisplayed());
        assertFalse(((HomePage)currentPage).passwordIsDisplayed());
    }
}
