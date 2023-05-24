package Base;

public class CommonAPI {

    Logger log = LogManager.getLogger(CommonAPI.class.getName());
    Properties prop = Utility.loadProperties();
    String browserstackUsername = prop.getProperty("browserstack.username");
    String browserstackPassword = prop.getProperty("browserstack.password");

    String implicitWait = prop.getProperty("implicit.wait","5");
    String windowMaximize = prop.getProperty("browser.maximize","true");
    String takeScreenshots = prop.getProperty("take.screenshots","false");

    public class SetUp {
        Logger log = LogManager.getLogger(SetUp.class.getName());
        WebDriver driver;

        public void getCloudDriver(String envName, String os, String osVersion, String browserName, String browserVersion, String username, String password) throws MalformedURLException {
            @@ -53,12 +64,14 @@ public void setUp(@Optional("false") String useCloudEnv, @Optional("browserstack
                    @Optional("10") String osVersion, @Optional("chrome") String browserName, @Optional("110") String browserVersion,
                    @Optional("https://www.google.com") String url) throws MalformedURLException {
                if (useCloudEnv.equalsIgnoreCase("true")){
                    getCloudDriver(envName,os,osVersion,browserName,browserVersion,"mohammadtaseen_JXBGzu","Kfir1hMhAFD2zyjCiMzm");
                    getCloudDriver(envName,os,osVersion,browserName,browserVersion,browserstackUsername,browserstackPassword);
                } else if(useCloudEnv.equalsIgnoreCase("false")){
                    getLocalDriver(browserName);
                }
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(implicitWait)));
                if (windowMaximize.equalsIgnoreCase("true")){
                    driver.manage().window().maximize();
                }
                driver.get(url);
            }
            @AfterMethod
            @@ -70,38 +83,31 @@ public void tearDown(){

                //------------------------------------------------------------------------------------------------------------------
                //                                              selenium methods
                //------------------------------------------------------------------------------------------------------------------.
                //------------------------------------------------------------------------------------------------------------------

                public WebDriver getDriver() {
                    return driver;
                }
                public String getCurrentTitle(){
                    return driver.getTitle();
                }
                public String getElementText(String locator){
                    try {
                        return driver.findElement(By.cssSelector(locator)).getText();
                    }catch (Exception e){
                        return driver.findElement(By.xpath(locator)).getText();
                    }
                    public String getElementText(WebElement element){
                        return element.getText();
                    }
                    public void clickOn(String locator){
                        try {
                            driver.findElement(By.cssSelector(locator)).click();
                        }catch (Exception e){
                            driver.findElement(By.xpath(locator)).click();
                        }
                        public void clickOn(WebElement element){
                            element.click();
                        }
                        public void type(String locator, String text){
                            try {
                                driver.findElement(By.cssSelector(locator)).sendKeys(text);
                            }catch (Exception e){
                                driver.findElement(By.xpath(locator)).sendKeys(text);
                            }
                            public void type(WebElement element, String text){
                                element.sendKeys(text);

                            }
                            public void hoverOver(String locator){
                                public void hoverOver(WebElement element){
                                    Actions actions = new Actions(driver);
                                    try {
                                        actions.moveToElement(driver.findElement(By.cssSelector(locator))).build().perform();
                                    }catch (Exception e){
                                        actions.moveToElement(driver.findElement(By.xpath(locator))).build().perform();
                                    }
                                    actions.moveToElement(element).build().perform();
                                }
                                public void hoverOverAndClickOn(WebElement element){
                                    Actions actions = new Actions(driver);
                                    actions.moveToElement(element).click().build().perform();
                                }
                                public void waitFor(int seconds){
                                    try {
                                        @@ -110,25 +116,13 @@ public void waitFor(int seconds){
                                            throw new RuntimeException(e);
                                        }
                                    }
                                    public boolean isVisible(String locator){
                                        try {
                                            return driver.findElement(By.cssSelector(locator)).isDisplayed();
                                        }catch (Exception e){
                                            return driver.findElement(By.xpath(locator)).isDisplayed();
                                        }
                                        public boolean isVisible(WebElement element){
                                            return element.isDisplayed();
                                        }
                                        public boolean isInteractible(String locator){
                                            try {
                                                return driver.findElement(By.cssSelector(locator)).isEnabled();
                                            }catch (Exception e){
                                                return driver.findElement(By.xpath(locator)).isEnabled();
                                            }
                                            public boolean isInteractible(WebElement element){
                                                return element.isEnabled();
                                            }
                                            public boolean isChecked(String locator){
                                                try {
                                                    return driver.findElement(By.cssSelector(locator)).isSelected();
                                                }catch (Exception e){
                                                    return driver.findElement(By.xpath(locator)).isSelected();
                                                }
                                                public boolean isChecked(WebElement element){
                                                    return element.isSelected();
                                                }
                                            }
}
