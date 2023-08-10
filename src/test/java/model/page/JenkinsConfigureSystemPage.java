package model.page;

import io.qameta.allure.Step;
import model.page.base.MainBasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import runner.TestUtils;

import java.util.List;

public class JenkinsConfigureSystemPage extends MainBasePage {

    @FindBy(name = "primaryView")
    private WebElement defaultView;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveButton;

    @FindBy(xpath = "//textarea[@name='system_message']")
    private WebElement setSystemMessage;

    @FindBy(xpath = "//div[text()='SMTP server']/following-sibling::div/input")
    private WebElement smtpServerFieldExtendedEmailNotifications;

    @FindBy(xpath = "//div[text()='SMTP Port']/following-sibling::div/input")
    private WebElement smtpPortFieldExtendedEmailNotifications;

    @FindBy(xpath = "//div[@class='setting-main help-sibling']//button[text()='Advanced...']")
    private WebElement advancedButtonExtendedEmailNotifications;

    @FindBy(css = "div.credentials-select-control button")
    private WebElement addButtonExtendedEmailNotifications;

    @FindBy(xpath = "//span[@title='Jenkins Credentials Provider']")
    private WebElement jenkinsCredentialProvider;

    @FindBy(css = "#credentialsDialog_c input[name='_.username']")
    private WebElement usernameInputFieldInAddCredentialsPopUpWindow;

    @FindBy(css = "#credentialsDialog_c input[name='_.password']")
    private WebElement passwordInputFieldInAddCredentialsPopUpWindow;

    @FindBy(css = "#credentialsDialog_c button#credentials-add-submit-button")
    private WebElement addButtonAddCredentialsPopUpWindow;

    @FindBy(xpath = "//div[@class='setting-main help-sibling']//span[text()='Use SSL']")
    private WebElement useSSLCheckboxExtendedEmailNotifications;

    @FindBy(xpath = "//div[@class='setting-main help-sibling']//input[@name='_.useSsl']")
    private WebElement useSSLCheckboxExtendedEmailVerification;

    @FindBy(xpath = "//button[text()='Default Triggers...']")
    private WebElement defaultTriggersButton;

    @FindBy(xpath = "//div[@class='setting-main']/span/label[@class='attach-previous ']")
    private List<WebElement> defaultTriggersList;

    @FindBy(xpath = "//label[text()='Always']/../input")
    private WebElement defaultTriggerAlwaysVerification;

    @FindBy(xpath = "//label[text()='Always']")
    private WebElement defaultTriggerAlwaysCheckbox;

    @FindBy(xpath = "//label[text()='Success']/../input")
    private WebElement defaultTriggerSuccessVerification;

    @FindBy(xpath = "//label[text()='Success']")
    private WebElement defaultTriggerSuccessCheckbox;

    @FindBy(xpath = "//input[@class='jenkins-input validated  '][@name='_.smtpHost']")
    private WebElement smtpServerFieldEmailNotifications;

    @FindBy(xpath = "(//button[text()='Advanced...'])[last()]")
    private WebElement advancedButtonEmailNotifications;

    @FindBy(xpath = "//label[text()='Use SMTP Authentication']")
    private WebElement useSMTPAuthenticationCheckbox;

    @FindBy(xpath = "//label[text()='Use SMTP Authentication']/../input")
    private WebElement useSMTPAuthenticationVerification;

    @FindBy(xpath = "//div[@class='jenkins-form-item tr ']//input[@name='_.username']")
    private WebElement userNameSMTPAuthentication;

    @FindBy(xpath = "//div[@class='jenkins-form-item tr ']//input[@name='_.password']")
    private WebElement passwordSMTPAuthentication;

    @FindBy(xpath = "//div[@class='jenkins-form-item tr has-help jenkins-form-item--tight']//label/span[text()='Use SSL']")
    private WebElement useSSLEmailNotificationsCheckbox;

    @FindBy(xpath = "//div[@class='jenkins-form-item tr has-help jenkins-form-item--tight']//input[@name='_.useSsl']")
    private WebElement useSSLEmailCheckboxVerification;

    @FindBy(xpath = "//div[@class='jenkins-form-item tr  has-help']//input[@name='_.smtpPort']")
    private WebElement smtpPortFieldEmailNotifications;

    @FindBy(xpath = "//label[text()='Test configuration by sending test e-mail']")
    private WebElement testConfigurationBySendingTestEmailCheckbox;

    @FindBy(xpath = "//input[@name='sendTestMailTo']")
    private WebElement testEmailRecipientInputField;

    @FindBy(xpath = "//button[text()='Test configuration']")
    private WebElement testConfigurationButton;

    @FindBy(xpath = "//select[@name='_.credentialsId']")
    private WebElement credentialsDropdown;

    @FindBy(xpath = "//div[@class='jenkins-validate-button__container__status']//div[@class='ok']")
    private WebElement testConfigurationMessage;

    @FindBy(xpath = "//div[text()='Content Token Reference']")
    private WebElement contentTokenReference;

    @FindBy(xpath = "//input[@name='_.listId']")
    private WebElement listIdInputField;

    @FindBy(css = "#footer")
    private WebElement footer;

    public JenkinsConfigureSystemPage(WebDriver driver) {
        super(driver);
    }

    @Step("Select in 'Default view' dropdown option '{nameView}'")
    public JenkinsConfigureSystemPage selectDefaultView(String nameView) {
        TestUtils.scrollToElement_PlaceInCenter(getDriver(), defaultView);
        getWait(5).until(TestUtils.ExpectedConditions.elementIsNotMoving(defaultView)).click();
        getDriver().findElement(By.xpath(String.format("//option[@value='%s']", nameView))).click();

        return this;
    }

    @Step("Enter text in 'System Message' field")
    public JenkinsConfigureSystemPage setSystemMessage(String text) {
        setSystemMessage.clear();
        setSystemMessage.sendKeys(text);

        return this;
    }

    public JenkinsConfigureSystemPage inputSmtpServerFieldExtendedEmailNotifications(String smtpServer) {
        TestUtils.scrollToElement_PlaceInCenter(getDriver(), smtpServerFieldExtendedEmailNotifications);
        getWait(5).until(TestUtils.ExpectedConditions.elementIsNotMoving(smtpServerFieldExtendedEmailNotifications));
        getWait(2).until(ExpectedConditions.visibilityOf(smtpServerFieldExtendedEmailNotifications)).clear();
        smtpServerFieldExtendedEmailNotifications.sendKeys(smtpServer);

        return this;
    }

    public JenkinsConfigureSystemPage inputSmtpPortFieldExtendedEmailNotifications(String smtpPort) {
        getWait(2).until(ExpectedConditions.visibilityOf(smtpPortFieldExtendedEmailNotifications)).clear();
        smtpPortFieldExtendedEmailNotifications.sendKeys(smtpPort);

        return this;
    }

    public JenkinsConfigureSystemPage clickAdvancedButtonExtendedEmailNotification() {
        advancedButtonExtendedEmailNotifications.click();

        return this;
    }

    public JenkinsConfigureSystemPage clickAddCredentialButton() {
        getWait(10).until(ExpectedConditions.elementToBeClickable(addButtonExtendedEmailNotifications)).click();
        getWait(2).until(ExpectedConditions.elementToBeClickable(jenkinsCredentialProvider)).click();

        return this;
    }

    public JenkinsConfigureSystemPage selectCreatedCredentials(String email) {
        credentialsDropdown.click();
        new Select(credentialsDropdown).selectByVisibleText(email + "/******");

        return this;
    }

    public JenkinsConfigureSystemPage inputUsernameIntoAddCredentialPopUpWindow(String username) {
        getWait(5).until(ExpectedConditions.visibilityOf(usernameInputFieldInAddCredentialsPopUpWindow)).sendKeys(username);

        return this;
    }

    public JenkinsConfigureSystemPage inputPasswordIntoAddCredentialPopUpWindow(String password) {
        getWait(5).until(ExpectedConditions.visibilityOf(passwordInputFieldInAddCredentialsPopUpWindow)).sendKeys(password);

        return this;
    }

    public JenkinsConfigureSystemPage clickAddButtonAddCredentialPopUp() {
        new Actions(getDriver()).scrollToElement(addButtonAddCredentialsPopUpWindow);
        getWait(5).until(ExpectedConditions.elementToBeClickable(addButtonAddCredentialsPopUpWindow)).click();

        return this;
    }

    public JenkinsConfigureSystemPage checkUseSSLCheckbox() {
        new Actions(getDriver()).scrollToElement(useSSLCheckboxExtendedEmailNotifications).perform();
        getWait(5).until(ExpectedConditions.visibilityOf(useSSLCheckboxExtendedEmailNotifications));
            useSSLCheckboxExtendedEmailNotifications.click();

        return this;
    }

    public JenkinsConfigureSystemPage clickDefaultTriggersButton() {
        TestUtils.scrollToElement_PlaceInCenter(getDriver(), advancedButtonEmailNotifications);
        getWait(10).until(TestUtils.ExpectedConditions.elementIsNotMoving(advancedButtonEmailNotifications));
        defaultTriggersButton.click();

        return this;
    }

    public JenkinsConfigureSystemPage checkAlwaysDefaultTriggers() {
        for (WebElement trigger : defaultTriggersList) {
            if (trigger.getText().equals("Always")) {
                TestUtils.scrollToElement_PlaceInCenter(getDriver(), trigger);
                getWait(10).until(TestUtils.ExpectedConditions.elementIsNotMoving(trigger));
                    trigger.click();
            }
        }

        return this;
    }

    public JenkinsConfigureSystemPage checkSuccessDefaultTriggers() {
        for (WebElement trigger : defaultTriggersList) {
            if (trigger.getText().equals("Success")) {
                trigger.click();
            }
        }

        return this;
    }

    public JenkinsConfigureSystemPage inputSmtpServerFieldEmailNotifications(String smtpServer) {
        TestUtils.scrollToElement(getDriver(), smtpServerFieldEmailNotifications);
        getWait(5).until(ExpectedConditions.visibilityOf(smtpServerFieldEmailNotifications)).clear();
        smtpServerFieldEmailNotifications.sendKeys(smtpServer);

        return this;
    }

    public JenkinsConfigureSystemPage clickAdvancedButtonEmailNotification() {
        TestUtils.scrollToEnd(getDriver());
        getWait(5).until(TestUtils.ExpectedConditions.elementIsNotMoving(advancedButtonEmailNotifications));
        advancedButtonEmailNotifications.click();

        return this;
    }

    public JenkinsConfigureSystemPage clickUseSMTPAuthenticationCheckbox() {
        if (!useSMTPAuthenticationVerification.isSelected()) {
            getWait(2).until(ExpectedConditions.elementToBeClickable(useSMTPAuthenticationCheckbox)).click();
        }

        return this;
    }

    public JenkinsConfigureSystemPage inputUserNameAndPasswordSMTPAuthentication(String username, String password) {
        getWait(10).until(ExpectedConditions.visibilityOf(userNameSMTPAuthentication)).sendKeys(username);
        passwordSMTPAuthentication.sendKeys(password);

        return this;
    }

    public JenkinsConfigureSystemPage checkUseSSLCheckboxEmailNotifications() {
        TestUtils.scrollToElement_PlaceInCenter(getDriver(), useSSLEmailNotificationsCheckbox);
        getWait(5).until(TestUtils.ExpectedConditions.elementIsNotMoving(useSSLEmailNotificationsCheckbox));
            useSSLEmailNotificationsCheckbox.click();

        return this;
    }

    public JenkinsConfigureSystemPage inputSmtpPortEmailNotificationsField(String port) {
        smtpPortFieldEmailNotifications.clear();
        smtpPortFieldEmailNotifications.sendKeys(port);

        return this;
    }

    public HomePage clickSaveButton() {
        TestUtils.scrollToElement(getDriver(), saveButton);
        saveButton.click();

        return new HomePage(getDriver());
    }

    public JenkinsConfigureSystemPage unCheckUseSSLCheckboxExtendedEmailNotifications() {
        new Actions(getDriver()).scrollToElement(useSSLCheckboxExtendedEmailNotifications).perform();
        if (useSSLCheckboxExtendedEmailVerification.isSelected()) {
            useSSLCheckboxExtendedEmailNotifications.click();
        }

        return this;
    }

    public JenkinsConfigureSystemPage unCheckDefaultTriggerAlwaysCheckbox() {
        if (defaultTriggerAlwaysVerification.isSelected()) {
            defaultTriggerAlwaysCheckbox.click();
        }

        return this;
    }

    public JenkinsConfigureSystemPage unCheckDefaultTriggerSuccessCheckbox() {
        TestUtils.scrollToElement_PlaceInCenter(getDriver(), defaultTriggerSuccessVerification);
        getWait(5).until(TestUtils.ExpectedConditions.elementIsNotMoving(defaultTriggerSuccessVerification));
        if (defaultTriggerSuccessVerification.isSelected()) {
            defaultTriggerSuccessCheckbox.click();
        }

        return this;
    }

    public JenkinsConfigureSystemPage unCheckSMTPAuthenticationCheckbox() {
        TestUtils.scrollToElement_PlaceInCenter(getDriver(), useSMTPAuthenticationVerification);
        getWait(5).until(TestUtils.ExpectedConditions.elementIsNotMoving(useSMTPAuthenticationVerification));
        if (useSMTPAuthenticationVerification.isSelected()) {
            useSMTPAuthenticationCheckbox.click();
        }

        return this;
    }

    public JenkinsConfigureSystemPage unCheckUseSSLCheckboxEmailNotifications() {
        getWait(5).until(ExpectedConditions.elementToBeClickable(useSSLEmailCheckboxVerification));
        if (useSSLEmailCheckboxVerification.isSelected()) {
            useSSLEmailNotificationsCheckbox.click();
        }

        return this;
    }
}
