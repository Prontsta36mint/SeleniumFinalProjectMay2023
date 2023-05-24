package EnhancedFramework;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.time.Duration;

public class Setup {
    String browserName = "chrome";
    String url = "http://opensource-demo.orangehrmlive.com/";
    protected WebDriver driver;

    @BeforeTest
    public void Setup(){
        if (browserName.equalsIgnoreCase("chrome")){
            driver = new ChromeDriver();
            System.out.println("chrome browser open success");

        } else if (browserName.equalsIgnoreCase("edge")){
            driver = new EdgeDriver();
            System.out.println("edge browser open success");
        }

        //open Chrome browser
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        // navigate to login Page
        driver.get(url);


    }
     @AfterTest
     public void tearDown(){
        // close browser
         driver.close();
         System.out.println("browser close success");
     }

}
