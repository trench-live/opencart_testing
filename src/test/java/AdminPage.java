import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class AdminPage extends BasePage {
    // Locators
    protected static final By USERNAME_FIELD = By.xpath("//*[@id='input-username']");
    protected static final By PASSWORD_FIELD = By.xpath("//*[@id='input-password']");
    protected static final By LOGIN_BUTTON = By.xpath("//*[@id='form-login']/div[3]/button");
    protected static final String USERNAME = "user";
    protected static final String PASSWORD = "bitnami";
    protected static final By CATALOG_MENU = By.xpath("//*[@id='menu-catalog']/a");
    protected static final By CATEGORIES_SUBMENU = By.xpath("//*[@id='collapse-1']/li[1]/a");
    protected static final By PRODUCTS_SUBMENU = By.xpath("//*[@id='collapse-1']/li[2]/a");
    protected static final By ADD_BUTTON = By.xpath("//*[@id=\"content\"]/div[1]/div/div/a");
    protected static final By PRODUCT_NAME_FIELD = By.xpath("//*[@id='input-name-1']");
    protected static final By META_TAG_TITLE_FIELD = By.xpath("//*[@id='input-meta-title-1']");
    protected static final By CATEGORIES_FIELD = By.xpath("//*[@id='input-category']");
    protected static final By MODEL_FIELD = By.xpath("//*[@id='input-model']");
    protected static final By PRODUCT_SEO_TAB = By.xpath("//*[@id='form-product']//a[text()='SEO']");
    protected static final By CATEGORY_SEO_TAB = By.xpath("//*[@id='form-category']//a[text()='SEO']");
    protected static final By PRODUCT_DATA_TAB = By.xpath("//*[@id='form-product']//a[text()='Data']");
    protected static final By PRODUCT_LINKS_TAB = By.xpath("//*[@id='form-product']//a[text()='Links']");
    protected static final By SEO_URL_FIELD = By.xpath("//*[@id='input-keyword-0-1']");
    protected static final By MAIN_PAGE_LINK = By.xpath("//*[@id='header']/div/a/img");
    protected static final By CATEGORIES_TABLE_SECOND_PAGE = By.xpath("//*[@id='form-category']/div[2]/div[1]/ul/li[2]/a");
    protected static final By PRODUCTS_TABLE_SECOND_PAGE = By.xpath("//*[@id='form-product']/div[2]/div[1]/ul/li[2]/a");
    protected static final By DEVICES_CATEGORY = By.xpath("//*[@id='form-category']//table//tr[.//td[contains(text(), 'Devices')]]/td[2]");
    protected static final By DEVICES_DROPDOWN_MENU = By.xpath("//*[@id='autocomplete-category']/li/a");
    protected static final By SEARCH_FIELD = By.xpath("//input[@name='search']");
    protected static final By SEARCH_ICON = By.xpath("//i[@class='fa-solid fa-magnifying-glass']");
    protected static final By ALERT = By.xpath("//*[@id='alert']/div");
    protected static final By SEARCH_HEADER = By.xpath("//*[@id='content']/h1");
    protected static final By SAVE_BUTTON = By.xpath("//*[@id=\"content\"]/div[1]/div/div/button");
    protected static final By DELETE_CHECKBOX = By.xpath(".//input[@type='checkbox' and @name='selected[]']");
    protected static final By DELETE_PRODUCT_BUTTON = By.xpath("//*[@id=\"content\"]/div[1]/div/div/button[3]");
    protected static final By TABLE_PRODUCT_ROW = By.xpath("//table//tbody/tr");
    protected static final By TABLE_PRODUCT_NAME = By.xpath(".//td[3]");

    public AdminPage(WebDriver driver) {
        super(driver);
    }

    public void openAdminPage() {
        driver.get(BASE_URL + "/administration");
    }

    public void findAndClickElement(By locator, By frameLocator, int maxAttempts) {
        for (int attempt = 0; attempt < maxAttempts; attempt++) {
            try {
                if (frameLocator != null) {
                    try {
                        WebElement frame = new WebDriverWait(driver, Duration.ofSeconds(3))
                                .until(ExpectedConditions.presenceOfElementLocated(frameLocator));
                        driver.switchTo().frame(frame);
                    } catch (TimeoutException | NoSuchFrameException e) {
                        System.out.println("Error switching to frame: " + e.getMessage());
                    }
                }

                WebElement element = new WebDriverWait(driver, Duration.ofSeconds(3))
                        .until(ExpectedConditions.presenceOfElementLocated(locator));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
                new WebDriverWait(driver, Duration.ofSeconds(3))
                        .until(ExpectedConditions.elementToBeClickable(element));

                try {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
                    return;
                } catch (StaleElementReferenceException e) {
                    System.out.println("StaleElementReferenceException on click (attempt " + (attempt + 1) + "): " + e.getMessage());
                }
            } catch (TimeoutException | StaleElementReferenceException e) {
                System.out.println("Error (attempt " + (attempt + 1) + "): " + e.getMessage());
            } finally {
                try {
                    if (frameLocator != null) {
                        driver.switchTo().defaultContent();
                    }
                } catch (Exception e) {
                    System.out.println("Failed to return to default content: " + e.getMessage());
                }
            }
        }
        System.out.println("Failed to click element after " + maxAttempts + " attempts with locator " + locator);
    }

    public void findAndClickElement(By locator) {
        findAndClickElement(locator, null, 3);
    }

    public WebElement findElement(By locator, By frameLocator, int maxAttempts) {
        for (int attempt = 0; attempt < maxAttempts; attempt++) {
            try {
                if (frameLocator != null) {
                    try {
                        WebElement frame = new WebDriverWait(driver, Duration.ofSeconds(10))
                                .until(ExpectedConditions.presenceOfElementLocated(frameLocator));
                        driver.switchTo().frame(frame);
                    } catch (TimeoutException | NoSuchFrameException e) {
                        System.out.println("Error switching to frame: " + e.getMessage());
                    }
                }

                WebElement element = new WebDriverWait(driver, Duration.ofSeconds(10))
                        .until(ExpectedConditions.presenceOfElementLocated(locator));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
                new WebDriverWait(driver, Duration.ofSeconds(10))
                        .until(ExpectedConditions.elementToBeClickable(element));
                return element;
            } catch (TimeoutException | StaleElementReferenceException e) {
                System.out.println("Error (attempt " + (attempt + 1) + "): " + e.getMessage());
            } finally {
                try {
                    if (frameLocator != null) {
                        driver.switchTo().defaultContent();
                    }
                } catch (Exception e) {
                    System.out.println("Failed to return to default content: " + e.getMessage());
                }
            }
        }
        System.out.println("Failed to find element after " + maxAttempts + " attempts with locator " + locator);
        return null;
    }

    public WebElement findElement(By locator) {
        return findElement(locator, null, 3);
    }

    public void clickCatalog(By locator, By menuLocator, By frameLocator, int maxAttempts) {
        for (int attempt = 0; attempt < maxAttempts; attempt++) {
            try {
                if (frameLocator != null) {
                    try {
                        WebElement frame = new WebDriverWait(driver, Duration.ofSeconds(2))
                                .until(ExpectedConditions.presenceOfElementLocated(frameLocator));
                        driver.switchTo().frame(frame);
                    } catch (TimeoutException | NoSuchFrameException e) {
                        System.out.println("Error switching to frame: " + e.getMessage());
                    }
                }

                WebElement element = new WebDriverWait(driver, Duration.ofSeconds(2))
                        .until(ExpectedConditions.presenceOfElementLocated(locator));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
                new WebDriverWait(driver, Duration.ofSeconds(1))
                        .until(ExpectedConditions.elementToBeClickable(element));

                try {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
                    return;
                } catch (StaleElementReferenceException e) {
                    System.out.println("StaleElementReferenceException on click (attempt " + (attempt + 1) + "): " + e.getMessage());
                    reopenCatalog(menuLocator);
                    continue;
                }
            } catch (TimeoutException | StaleElementReferenceException e) {
                System.out.println("Error (attempt " + (attempt + 1) + "): " + e.getMessage());
                reopenCatalog(menuLocator);
                continue;
            } finally {
                try {
                    if (frameLocator != null) {
                        driver.switchTo().defaultContent();
                    }
                } catch (Exception e) {
                    System.out.println("Failed to return to default content: " + e.getMessage());
                }
            }
        }
        System.out.println("Failed to click element after " + maxAttempts + " attempts with locator " + locator);
    }

    private void reopenCatalog(By catalogMenuLocator) {
        try {
            WebElement catalogMenu = new WebDriverWait(driver, Duration.ofSeconds(1))
                    .until(ExpectedConditions.elementToBeClickable(catalogMenuLocator));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", catalogMenu);
        } catch (TimeoutException | StaleElementReferenceException e) {
        }
    }

    public void deleteProductsByName(List<String> productNames, By productRowLocator, By checkboxLocator,
                                     By deleteButtonLocator, By productNameLocator, By frameLocator) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productRowLocator));

            for (String productName : productNames) {
                boolean productFound = false;
                List<WebElement> productRows = driver.findElements(productRowLocator);

                for (WebElement row : productRows) {
                    try {
                        WebElement nameElement = wait.until(ExpectedConditions.visibilityOf(
                                row.findElement(productNameLocator)));
                        String actualProductName = nameElement.getText().trim().split("\n")[0];

                        if (actualProductName.equals(productName)) {
                            productFound = true;
                            WebElement checkbox = row.findElement(checkboxLocator);

                            retryScrollAndClick(checkbox, 3);
                            System.out.println("Selected product '" + productName + "' for deletion");
                            break;
                        }
                    } catch (Exception e) {
                        System.out.println("Error processing row for product '" + productName + "': " + e.getMessage());
                    }
                }

                if (!productFound) {
                    System.out.println("Warning: Product '" + productName + "' not found in the table");
                }
            }

            WebElement deleteButton = wait.until(ExpectedConditions.presenceOfElementLocated(deleteButtonLocator));
            retryScrollAndClick(deleteButton, 5);

            try {
                Alert alert = wait.withTimeout(Duration.ofSeconds(5))
                        .until(ExpectedConditions.alertIsPresent());
                alert.accept();
            } catch (TimeoutException e) {
                System.out.println("No confirmation alert appeared - continuing anyway");
            }

        } catch (Exception e) {
            System.out.println("Error in deleteProductsByName: " + e.getMessage());
            throw e;
        }
    }

    private void retryScrollAndClick(WebElement element, int maxAttempts) throws InterruptedException {
        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            try {
                if (attempt % 2 == 0) {
                    ((JavascriptExecutor)driver).executeScript(
                            "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'});", element);
                } else {
                    ((JavascriptExecutor)driver).executeScript(
                            "arguments[0].scrollIntoView(true);", element);
                }

                Thread.sleep(300);

                ((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
                return;

            } catch (Exception e) {
                System.out.println("Attempt " + attempt + " failed: " + e.getMessage());
                if (attempt == maxAttempts) {
                    throw e;
                }
            }
        }
    }

    public void login() {
        findElement(USERNAME_FIELD).sendKeys(USERNAME);
        findElement(PASSWORD_FIELD).sendKeys(PASSWORD);
        findAndClickElement(LOGIN_BUTTON);
    }

    public void openStartPage() {
        findAndClickElement(MAIN_PAGE_LINK);
    }

    public void openMainPage() {
        driver.get(BASE_URL);
    }

    public void openCatalog(By submenuLocator) {
        clickCatalog(submenuLocator, CATALOG_MENU, null, 3);
    }

    public void openCategoriesPage() {
        openCatalog(CATEGORIES_SUBMENU);
    }

    public void openProductsPage() {
        openCatalog(PRODUCTS_SUBMENU);
    }

    public void clickSaveButton() {
        findAndClickElement(SAVE_BUTTON);
    }

    public void clickAddButton() {
        findAndClickElement(ADD_BUTTON);
    }

    public void enterNameAndMetaTag(String name, String metaTag) {
        findElement(PRODUCT_NAME_FIELD).sendKeys(name);
        findElement(META_TAG_TITLE_FIELD).sendKeys(metaTag);
    }

    public void productEnterSeoUrl(String seoUrl) {
        findAndClickElement(PRODUCT_SEO_TAB);
        findAndClickElement(SEO_URL_FIELD);
        findElement(SEO_URL_FIELD).sendKeys(seoUrl);
    }

    public void categoryEnterSeoUrl(String seoUrl) {
        findAndClickElement(CATEGORY_SEO_TAB);
        findAndClickElement(SEO_URL_FIELD);
        findElement(SEO_URL_FIELD).sendKeys(seoUrl);
    }

    public void enterModel(String modelName) {
        findAndClickElement(PRODUCT_DATA_TAB);
        findElement(MODEL_FIELD).sendKeys(modelName);
    }

    public void selectCategory(String categoryName) {
        findAndClickElement(PRODUCT_LINKS_TAB);
        findElement(CATEGORIES_FIELD).sendKeys(categoryName);
        findAndClickElement(DEVICES_DROPDOWN_MENU);
    }

    public void addNewCategory(String categoryName, String seoName) {
        clickAddButton();
        enterNameAndMetaTag(categoryName, categoryName);
        categoryEnterSeoUrl(seoName);
        clickSaveButton();
    }

    public void addNewProduct(String productName, String categoryName, String seoName) {
        clickAddButton();
        enterNameAndMetaTag(productName, productName);
        enterModel(productName);
        selectCategory(categoryName);
        productEnterSeoUrl(seoName);
        clickSaveButton();
    }

    public void search(String searchText) {
        findElement(SEARCH_FIELD).sendKeys(searchText);
        findAndClickElement(SEARCH_ICON);
    }

    public String devicesCategoryGetText() {
        return findElement(DEVICES_CATEGORY).getText();
    }

    public void clickSecondPageCategoriesTable() {
        findAndClickElement(CATEGORIES_TABLE_SECOND_PAGE);
    }

    public void clickSecondPageProductsTable() {
        findAndClickElement(PRODUCTS_TABLE_SECOND_PAGE);
    }

    public String getAlertText() {
        return findElement(ALERT).getText();
    }

    public String getSearchResultHeaderText() {
        return findElement(SEARCH_HEADER).getText();
    }
}