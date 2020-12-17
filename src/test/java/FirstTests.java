import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.assertTrue;

public class FirstTests {

    WebDriver driver; //utworzenie pustego pola driver, aby było dostępne we wszystkich metodach
    WebDriverWait wait;


    public void highlightElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
    }

    @Before //warunki wstepne
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
        //ustawiamy property ze wskazaniem chromedriver, którego użyjemy w testach
        driver = new ChromeDriver();
        System.out.println("Wykonuję się tutaj! przed metodą testową");
        wait = new WebDriverWait(driver, 10);
    }


    @Test //kroki testowe - po prostu test do wykonania
    public void firstTest() {
        driver.get("https://dev.to"); //przejdź na stronę dev.to
        WebElement sideBarVideo = driver.findElement(By.xpath("/html/body/div[9]/div/div/div[1]/aside/nav[1]/div/a[3]")); //znajdz element week
        highlightElement(sideBarVideo);

        //sideBarVideo.click(); //klikamy w weekBtn
    }
    //<a href="/top/week" class="crayons-tabs__item ">Week</a>


    @Test
    public void openFirstVideoPage() throws InterruptedException {
        driver.get("https://dev.to");
        WebElement weekElement = driver.findElement(By.xpath("//a[@href='/top/week']"));
        highlightElement(weekElement);
        weekElement.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("article-link-534722")));
        WebElement firstPost = driver.findElement(By.id("article-link-534722"));
        highlightElement(firstPost);
        firstPost.click();
//<a href="/top/week" class="crayons-tabs__item ">Week</a>

    }

    @Test
    public void higlightFirstVideo() {
        driver.get("https://dev.to/videos");
        WebElement clickVideo = driver.findElement(By.className("video-image"));
        highlightElement(clickVideo);
        clickVideo.click();
    }

    // wejdz na strone dev.to
    // kliknij w podcasts
    // wybierz pierwszy podcast - pobiorę nazwę pierwszego podcastu z listy
    // sprawdzić czy jestem na odpowiedniej stronie czy tytuł podcastu się zgadza
    // sprawdzić czy mogę nacisnąć play w podcascie


    @Test
    public void selectFirstPodcast() {
        driver.get("https://dev.to/");
        WebElement selectFirstPodcast = driver.findElement(By.partialLinkText("Podcasts"));
        selectFirstPodcast.click();
        wait.until(ExpectedConditions.urlToBe("https://dev.to/pod"));
        WebElement clickFirstPodcast = driver.findElement(By.cssSelector(".content >h3:first-child"));
        String podcastTitleFromList = clickFirstPodcast.getText();

        String firstPodcastsFromListLink = driver.findElement(By.cssSelector("#substories > a:first-child")).getAttribute("href");
        clickFirstPodcast.click();
        wait.until(ExpectedConditions.urlToBe(firstPodcastsFromListLink));
        WebElement secondPodcast = driver.findElement(By.cssSelector(".title > h1:nth-child(2)"));
        highlightElement(secondPodcast);
        String podcastTitleOnNextPage = secondPodcast.getText();
        assertTrue(podcastTitleFromList.contains(podcastTitleOnNextPage));
        WebElement clickPlay = driver.findElement(By.className("record"));
        clickPlay.click();

        WebElement initializing = driver.findElement(By.className("status-message"));
        wait.until(ExpectedConditions.invisibilityOf(initializing));
        WebElement recordWrapper = driver.findElement(By.className("record-wrapper"));
        boolean isPodcastPlayed = recordWrapper.getAttribute("class").contains("playing");


        assertTrue(isPodcastPlayed);
    }

    @Test

    public void writeSearchTest(){
        driver.get("https://dev.to/");
        WebElement search = driver.findElement(By.cssSelector("#header-search > form > input.crayons-header--search-input.crayons-textfield"));
        highlightElement(search);
        search.sendKeys("testing");
        search.sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".crayons-story__indention > h3:first-child")));
        WebElement firstElement = driver.findElement(By.cssSelector(".crayons-story__indention > h3:first-child"));
        String textFromFirstElement = firstElement.getText();
        assertTrue(textFromFirstElement.contains(search.getText()));

    }



    //driver.findElement(By.xpath("//a[@href='https://dev.to/videos']"))
    //driver - uzywamy przegladarki do znalezienia elementu
    //findElement() - tej metody uzywamy aby zlokalizowac dany element
    //By.xpath() - uzyjemy lokatora xpath
    // //a[@href='https://dev.to/videos']
    // znajdz mi element a - //a
    // ktory posiada atrybut href - @href
    // o wartosci rownej https://dev.to/videos

/*    @Test //kroki testowe
    public void secondTest(){
        System.out.println("Drugi test");
    }*/

 /*   @After
    public void tearDown(){
        driver.quit();
        System.out.println("Po każdej metodzie testowej");
    }*/
}
