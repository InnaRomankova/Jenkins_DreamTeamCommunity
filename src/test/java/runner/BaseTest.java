package runner;

import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;
import runner.order.OrderForTests;
import runner.order.OrderUtils;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Listeners({FilterForTests.class, OrderForTests.class, ExceptionListener.class})
public abstract class BaseTest {

    private WebDriver driver;
    private Map<Integer, WebDriverWait> waitMap = new HashMap<>();

    private OrderUtils.MethodsOrder<Method> methodsOrder;

    protected static Logger log = LogManager.getLogger();

    @BeforeClass
    protected void beforeClass() {
        methodsOrder = OrderUtils.createMethodsOrder(
                Arrays.stream(this.getClass().getMethods())
                        .filter(m -> m.getAnnotation(Test.class) != null && m.getAnnotation(Ignore.class) == null)
                        .collect(Collectors.toList()),
                m -> m.getName(),
                m -> m.getAnnotation(Test.class).dependsOnMethods());
    }

    @BeforeSuite
    protected void beforeSuite(ITestContext context) {
        log.info(ReportUtils.getReportHeader(context));
    }

    @BeforeMethod
    protected void beforeMethod(Method method) {
        Reporter.log("TEST RUN", true);
        log.info("RUN " + this.getClass().getName() + "." +  method.getName());
        try {
            if (!methodsOrder.isGroupStarted(method) || methodsOrder.isGroupFinished(method)) {
                clearData();
                startDriver();
                getWeb();
                loginWeb();
            } else {
                getWeb();
            }
        } catch (Exception e) {
            closeDriver();
            throw new RuntimeException(e);
        } finally {
            methodsOrder.markAsInvoked(method);
        }
    }

    protected void clearData() {
        log.info("CLEAR DATA");
        JenkinsUtils.clearData();
    }

    protected void loginWeb() {
        log.info("LOGIN");
        ProjectUtils.login(driver);
    }

    protected void getWeb() {
        log.info("GET WEB PAGE");
        ProjectUtils.get(driver);
    }

    protected void startDriver() {
        log.info("BROWSER OPEN");
        driver = BaseUtils.createDriver();
    }

    protected void stopDriver() {
        try {
            ProjectUtils.logout(driver);
        } catch (Exception ignore) {
        }

        closeDriver();
    }

    protected void closeDriver() {
        if (driver != null) {
            driver.quit();
            waitMap = new HashMap<>();
            log.info("BROWSER CLOSED");
        }
    }

    @AfterMethod
    protected void afterMethod(Method method, ITestResult testResult) {
        if (!testResult.isSuccess() && BaseUtils.isServerRun()) {
            BaseUtils.captureScreenFile(driver, method.getName(), this.getClass().getName());
        }

        if (!testResult.isSuccess() && !BaseUtils.isServerRun()) {
            Allure.getLifecycle().addAttachment(
                    "screenshot", "image/png", "png"
                    , ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)
            );
        }

        if (!testResult.isSuccess() || methodsOrder.isGroupFinished(method)) {
            stopDriver();
        }

        log.info(ReportUtils.getTestStatistics(method, testResult));
    }

    protected WebDriverWait getWait(int seconds) {
        return waitMap.computeIfAbsent(seconds, duration -> new WebDriverWait(driver, Duration.ofSeconds(duration)));
    }

    protected WebDriver getDriver() {
        return driver;
    }
}
