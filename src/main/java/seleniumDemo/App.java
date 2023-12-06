package seleniumDemo;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import javax.swing.*;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args){
        WebDriver driver = new ChromeDriver();
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");


        //Implicit wait
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize(); //Finestra a massima grandezza
        //driver.get("http://www.wikipedia.org");

        //Explicit Wait
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("js-link-box-en"))));

        //Fluent wait
        //Wait fluentWait = new FluentWait<WebDriver>(driver).withTimeout(15, TimeUnit.SECONDS).pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);


/*
        //locators
            //id dell'elemento
        Long start = System.currentTimeMillis();
        driver.findElement(By.id("js-link-box-en"));
        Long end = System.currentTimeMillis();
        System.out.println("Tempo necessario per il locator su ID: "+(end-start)); //10

            //xpath, class del div, click destro e copy xpath
        start = System.currentTimeMillis();
        driver.findElement(By.xpath("//*[@id=\"www-wikipedia-org\"]/div[2]/div[5]"));
        end = System.currentTimeMillis();
        System.out.println("Tempo necessario per il locator su xpath: "+(end-start)); //8

            //cssSelector, se è presente class (click destro e copy selector)
        start = System.currentTimeMillis();
        driver.findElement(By.cssSelector("#www-wikipedia-org > div.central-featured > div.central-featured-lang.lang4"));
        end = System.currentTimeMillis();
        System.out.println("Tempo necessario per il locator su selettore css: "+(end-start)); //8

        //Ottenere il testo di css selector
        driver.findElement(By.cssSelector("#www-wikipedia-org > div.central-textlogo > h1 > span")).getText();
        //Oppure
        WebElement titleWP = driver.findElement(By.cssSelector("#www-wikipedia-org > div.central-textlogo > h1 > span"));
        String title = titleWP.getText();
        String expectedTest = "Wikipedia";
        //Test per verificare che il titolo sia uguale a quello che ci si aspetta

        if (title.equals(expectedTest)){
            System.out.println("Test superato");
        } else {
            System.out.println("Test non superato");
            driver.close();
            throw new Exception();
        }
        //System.out.println("Titolo: "+title);


        //Click su un elemento tramite id
        WebElement  engButton = driver.findElement(By.id("js-link-box-en"));
        engButton.click();
        String expText = "Welcome to Wikipedia,";
        WebElement titleAfterClick = driver.findElement(By.id("mp-welcome"));

        if (titleAfterClick.getText().equals(expText)){
            System.out.println("Test superato, è la pagina inglese");
        } else {
            System.out.println("Test non superato");
            driver.quit();
            throw new Exception();
        }

        //Inserire testo in un form
        WebElement searchBox = driver.findElement(By.id("searchInput"));
        String searchStr = "Selenium Webdriver";
        searchBox.sendKeys(searchStr);
        //Cliccare il pulsante ed effettuare la ricerca
        WebElement searchButton = driver.findElement(By.cssSelector("#search-form > fieldset > button"));
        searchBox.click();



        //Tabelle
        driver.get("C:\\Users\\andre\\IdeaProjects\\seleniumDemo\\tablePage.html");
        //Accesso ad un campo della tabella
        //trbody indica quale tabella, tr indica la riga, td indica un dato per la colonna
            //prima tabella, seconda riga, terza colonna
        System.out.println(driver.findElement(By.xpath("/html/body/table/tbody[1]/tr[2]/td[3]")).getText());
            //th invece di td se l'elemento è di header
        System.out.println(driver.findElement(By.xpath("/html/body/table/tbody[1]/tr[1]/th[3]")).getText());
            //Trova tutti gli elementi della tabella 1 che contengono elementi tr (in questo caso tutti gli elementi della tabella)
            //Per lo storage sfrutta una lista
        List<WebElement> listOfWebElement = driver.findElements(By.xpath("/html/body/table/tbody[1]/tr"));
        for(WebElement element : listOfWebElement){
            System.out.println(element.getText());
        }


        //Javascript executor, forza il browser a fare cose che selenium base non può
        driver.get("https://www.amazon.it/?&tag=goitab-21&ref=pd_sl_781ozcfkw6_e&adgrpid=156928205950&hvpone=&hvptwo=&hvadid=672253294785&hvpos=&hvnetw=g&hvrand=15263095034546384229&hvqmt=e&hvdev=c&hvdvcmdl=&hvlocint=&hvlocphy=1008588&hvtargid=kwd-10573980&hydadcr=10841_2191052");
        WebElement cart = driver.findElement(By.cssSelector("#nav-cart-count-container > span.nav-cart-icon.nav-sprite"));
        //casting di driver a JavascriptExecuter
        //Alcuni pulsanti non sono cliccabili in selenium dal metodo standard, in questo caso ad esempio si ha uno span
        //Se non sono presenti nel viewport, è necessario scorrere fino al raggiungimento dell'elemento

        //Click, arguments è l'array degli elementi che vengono passati come parametri
            //in questo caso si ha un parametro solo, cart, quindi in posizione 0 perché le posizioni partono da 0
        //Sull'arguments si specifica l'azione da svolgere su quell'elemento, click in questo caso
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", cart);

        //setTimeOut è un metodo che esegue una funzione dopo un ritardo specificato (in ms)
        //Script asincrono utile per attendere il completamento di determinate operazioni
        //arguments.length-1 si riferisce all'ultima posizione dell'array, dove è presente una funzione di callback
            //utile a notificare a Selenium il completamento dello script asincrono
        //In questo caso si esegue una funzione specificata dopo un ritardo di 1000 ms, la funzione da eseguire è
            //la funzione di callback fornita da selenium, che dopo il ritardo viene chiamata per segnalare a selenium
            //che lo script asincrono è completato
        ((JavascriptExecutor) driver).executeAsyncScript("window.setTimeout(arguments[arguments.length-1], 1000);");

        //Cambiare la pagina web
        //((JavascriptExecutor) driver).executeScript("window.location = 'https://wikipedia.com'");

        //Scorrere la pagina, (spostamento orizzontale, spostamento verticale)
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 1000)");

        //Alerts
        driver.get("C:\\Users\\andre\\IdeaProjects\\seleniumDemo\\alerts.html");
        //I 3 pulsanti che generano gli alert
        WebElement displayAlertButton = driver.findElement(By.cssSelector("body > button:nth-child(2)"));
        WebElement confirmationAlertButton = driver.findElement(By.cssSelector("body > button:nth-child(5)"));
        WebElement promptAlertButton = driver.findElement(By.cssSelector("body > button:nth-child(8)"));

        //Display Alert
        displayAlertButton.click();
        //Attendi tot tempo finché la condizione che l'alert sia presente non è verificata
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.alertIsPresent());
        //Cambia il contesto dal normale contenuto alla finestra di alert
        Alert displayAlert = driver.switchTo().alert();
        try {
            Thread.sleep(3000); //3 secondi di attesa prima di chiudere l'alert
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        displayAlert.accept(); //Chiusura alert

        //Confirmation Alert
        confirmationAlertButton.click();
        wait.until(ExpectedConditions.alertIsPresent());
        Alert confirmationAlert = driver.switchTo().alert();
        try {
            Thread.sleep(3000); //3 secondi di attesa prima di chiudere l'alert
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        confirmationAlert.accept(); //Chiusura alert

        promptAlertButton.click();
        wait.until(ExpectedConditions.alertIsPresent());
        Alert promptAlert = driver.switchTo().alert();
        //Inserire testo nella casella presente nell'alert
        promptAlert.sendKeys("Franco");
        System.out.println(promptAlert.getText());
        try {
            Thread.sleep(3000); //3 secondi di attesa prima di chiudere l'alert
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        promptAlert.accept(); //Chiusura alert


        //iframe, elementi html utilizzati per incorporare una pagina html all'interno di un'altra
        driver.get("https://www.w3schools.com/html/html_iframe.asp");

        //Dal frame genitore
        String title = driver.switchTo().frame(0).findElement(By.cssSelector("#main > h1")).getText();
        System.out.println(title);

        //Frame figlio (iframe)
            //Ci sono problemi con le finestre dei cookie, quindi le ho gestite con attese esplicite e click
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement cookieButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#accept-choices")));
        cookieButton.click();

        WebElement iframe = driver.findElement(By.cssSelector("#main > div:nth-child(7) > iframe"));
        driver.switchTo().frame(iframe); //Qui si passa al frame figlio
        WebElement iframeCookieButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#accept-choices")));
        iframeCookieButton.click();

        //Ricerca dell'elemento nel frame figlio
        driver.findElement(By.cssSelector("#subtopnav > a:nth-child(5)")).click();
        //System.out.println(label);


        //Gestione select
        driver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_select");

        //Sempre i maledetti cookie
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement cookieButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#accept-choices")));
        cookieButton.click();

        //L'elemento si trova in un iframe, quindi si fa switch
        driver.switchTo().frame(driver.findElement(By.id("iframeResult")));
        WebElement selectable = driver.findElement(By.id("cars"));

        //selectable è un webelement che rappresenta l'elemento html <select> che si vuole selezionare
        //Select è una classe di cui viene creata l'istanza select, che viene associato
            //all'elemento html <select> rappresentato da selectable, è così possibile sfruttare
            //i metodi della classe Select per interagire con quegli elementi
        Select select = new Select(selectable);
        select.selectByIndex(1); //Parte da 0, con 1 quindi prende il secondo elemento


        //Drop

        driver.get("https://jqueryui.com/droppable/");
        driver.switchTo().frame(driver.findElement(By.cssSelector("#content > iframe")));
        WebElement draggable = driver.findElement(By.id("draggable"));
        WebElement droppable = driver.findElement(By.id("droppable"));

        Actions dragAndDrop = new Actions(driver);
        dragAndDrop.dragAndDrop(draggable, droppable).build().perform();

        //Click destro
        Actions contextClick = new Actions(driver);
        contextClick.contextClick().build().perform();

        //Pressione di un tasto della tastiera
        Actions key = new Actions(driver);
        key.keyDown(Keys.ARROW_DOWN).build().perform();

 */
        //driver.quit();
    }
}
