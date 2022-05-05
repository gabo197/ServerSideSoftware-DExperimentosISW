package com.appdhome.cucumber.stepdefinitions;

import com.appdhome.cucumber.GenericWebDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class US18 {
    public static WebDriver webDriver;

    public US18() {
        webDriver = GenericWebDriver.getWebDriver();
    }
    @Given("el trabajador se encuentra en la sección de inicio")
    public void elTrabajadorSeEncuentraEnLaSecciónDeInicio() {
        webDriver.get("https://appdhomet.netlify.app/");
        webDriver.manage().window().maximize();
        WebElement webElement;
        webElement = webDriver.findElement(By.xpath("/html/body/app-root/app-landing/html/body/nav/a[6]"));
        webElement.click();
    }

    @When("complete su nombre de usuario y su contraseña")
    public void completeSuNombreDeUsuarioYSuContraseña() {
        webDriver.findElement(By.xpath("//*[@id=\"mat-input-4\"]")).sendKeys("julissaponteT");
        webDriver.findElement(By.xpath("//*[@id=\"mat-input-5\"]")).sendKeys("julissaponteT");
        webDriver.findElement(By.xpath("/html/body/app-root/app-login/mat-card/form/button")).click();
    }

    @Then("se iniciará sesión en su cuenta")
    public void seIniciaráSesiónEnSuCuenta() {
        webDriver.findElement(By.xpath("/html/body/app-root/app-home-employee/div/div[1]/mat-toolbar")).isDisplayed();
    }

    @When("complete un nombre de usuario incorrecto y su contraseña")
    public void completeUnNombreDeUsuarioIncorrectoYSuContraseña() {
        webDriver.findElement(By.xpath("//*[@id=\"mat-input-4\"]")).sendKeys("julissaponte");
        webDriver.findElement(By.xpath("//*[@id=\"mat-input-5\"]")).sendKeys("julissaponteT");
        webDriver.findElement(By.xpath("/html/body/app-root/app-login/mat-card/form/button")).click();
    }

    @Then("no se iniciará sesión en su cuenta")
    public void noSeIniciaráSesiónEnSuCuenta() {
        webDriver.findElement(By.xpath("/html/body/app-root/app-login/mat-card/div[1]/mat-toolbar")).isDisplayed();
    }

    @When("complete su nombre de usuario y una contraseña incorrecta")
    public void completeSuNombreDeUsuarioYUnaContraseñaIncorrecta() {
        webDriver.findElement(By.xpath("//*[@id=\"mat-input-4\"]")).sendKeys("julissaponteT");
        webDriver.findElement(By.xpath("//*[@id=\"mat-input-5\"]")).sendKeys("julissaponte");
        webDriver.findElement(By.xpath("/html/body/app-root/app-login/mat-card/form/button")).click();
    }
}
