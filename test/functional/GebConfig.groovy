import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver

environments {
    chrome {
        if (!System.getProperty("webdriver.chrome.driver")) {
            def osPath = System.getProperty("os.name").toLowerCase().split(" ").first()

            def webDriver = new File("chromedrivers", osPath).listFiles({ File dir, String name -> !dir.hidden } as FilenameFilter).first()

            System.setProperty("webdriver.chrome.driver", webDriver.getAbsolutePath())
        }

        driver = { new ChromeDriver() }
    }

    firefox {
        driver = { new FirefoxDriver() }
    }
}