import org.openqa.selenium.chrome.ChromeDriver

enviroments{
    chrome{
        driver = { File file = new File("chromedrivers/chromedriver.exe"); //configurar com o enderço correto do chromedriver.
            System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
            new ChromeDriver(); }
    }
}