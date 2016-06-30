import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver

//import org.openqa.selenium.firefox.MarionetteDriver

def prepareWebDriver(String driver) {
    if (!System.getProperty("webdriver.${driver}.driver")) {
        def osPath = System.getProperty("os.name").toLowerCase().split(" ").first()

        def webDriver = new File("${driver}drivers", osPath).listFiles({ File dir, String name -> !dir.hidden } as FilenameFilter).first()

        System.setProperty("webdriver.${driver}.driver", webDriver.getAbsolutePath())
    }
}

environments {
    chrome {
        prepareWebDriver("chrome")

        driver = { new ChromeDriver() }
    }

    firefox {
        driver = { new FirefoxDriver() }
        // --------------------------------------
        // O suporte à versões mais recentes do Firefox teve de ser desfeito por que exigia o Grails 2.5.4
        //prepareWebDriver("gecko")
        
        //driver = { new MarionetteDriver() }
        // --------------------------------------
    }
}