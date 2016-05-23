<<<<<<< HEAD
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxProfile

driver = {
 File file = new File("C:/Users/Gilzélia/Desktop/Lucas/2016.1/ESS/Projeto/SistemaDeVagasDeEstacionamento-master/chromedrivers/chromedriver.exe"); //configurar com o enderço correto do chromedriver.
    System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
    new ChromeDriver();
} 

environments {
   // run as “grails -Dgeb.env=chrome test-app”
   // See: http://code.google.com/p/selenium/wiki/ChromeDriver
   chrome {
      driver = { File file = new File("C:/Users/Gilzélia/Desktop/Lucas/2016.1/ESS/Projeto/SistemaDeVagasDeEstacionamento-master/chromedrivers/chromedriver.exe"); //configurar com o enderço correto do chromedriver.
            System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
            new ChromeDriver(); }
   }

   // run as “grails -Dgeb.env=chrome test-app”
   // See: http://code.google.com/p/selenium/wiki/
   firefox {
    driver = { new FirefoxDriver() }
  }
}
=======
/**
 * Created by Reuel on 03/05/2016.
 */
import org.openqa.selenium.chrome.ChromeDriver

driver = {
    new ChromeDriver()
}
>>>>>>> e340233f5b7422d991772fa29dd6d217bbbcf457
