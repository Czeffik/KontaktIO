import org.openqa.selenium.chrome.ChromeDriver

waiting {
    timeout=10
}

environments{

    chrome{
        driver = {new ChromeDriver()}
    }

}