
import org.openqa.selenium.chrome.ChromeDriver

driver = {
    File file = new File("chromedrivers/chromedriver.exe");


    System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
    new ChromeDriver();
}

baseUrl = "http://localhost:8070/"