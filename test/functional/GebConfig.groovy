import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.MarionetteDriver

environments {
    chrome {
        def osPath = System.getProperty("os.name").toLowerCase().split(" ").first()

        def chromeDriver = new File("chromedrivers", osPath).listFiles(new FilenameFilter() {
            @Override
            boolean accept(File dir, String name) { !dir.hidden }
        }).first()

        System.setProperty("webdriver.chrome.driver", chromeDriver.getAbsolutePath())

        driver = { new ChromeDriver() }
    }

    firefox {
        def osPath = System.getProperty("os.name").toLowerCase().split(" ").first()

        def geckoDriver = new File("geckodrivers", osPath).listFiles(new FilenameFilter() {
            @Override
            boolean accept(File dir, String name) { !dir.hidden }
        }).first()

        System.setProperty("webdriver.gecko.driver", geckoDriver.getAbsolutePath())

        driver = { new MarionetteDriver() }
    }
}

waiting {
    timeout = 6
    retryInterval = 0.5
    slow { timeout = 12 }
    reallyslow { timeout = 24 }
}