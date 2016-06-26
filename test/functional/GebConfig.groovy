import org.openqa.selenium.chrome.ChromeDriver
//import org.openqa.selenium.firefox.MarionetteDriver

def prepareWebDriver(String driver) {
    def osPath = System.getProperty("os.name").toLowerCase().split(" ").first()

    def webDriver = new File("${driver}drivers", osPath).listFiles({ File dir, String name -> !dir.hidden } as FilenameFilter).first()

    System.setProperty("webdriver.${driver}.driver", webDriver.getAbsolutePath())
}

environments {
    chrome {
        prepareWebDriver("chrome")

        driver = { new ChromeDriver() }
    }

    firefox {
        prepareWebDriver("gecko")

        //driver = { new MarionetteDriver() }
        // O suporte ao Firefox teve de ser desfeito por que exigia o Grails 2.5.4
    }
}

waiting {
    timeout = 15
    retryInterval = 0.5
    slow { timeout = 30 }
    reallyslow { timeout = 60 }
}