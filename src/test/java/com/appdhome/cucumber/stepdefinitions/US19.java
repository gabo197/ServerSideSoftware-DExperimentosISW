package com.appdhome.cucumber.stepdefinitions;

import com.appdhome.cucumber.GenericWebDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;

public class US19 {
    GenericWebDriver webDriver;

    public US19() {
        webDriver = new GenericWebDriver();
    }
    @Given("el trabajador se encuentra en la sección principal")
    public void elTrabajadorSeEncuentraEnLaSecciónPrincipal() {
        webDriver.webDriver.findElement(By.xpath("/html/body/app-root/app-login/mat-card/div[1]/mat-toolbar")).isDisplayed();

    }

    @When("ingrese a la sección de su perfil")
    public void ingreseALaSecciónDeSuPerfil() {
    }

    @Then("se visualizará su perfil completo")
    public void seVisualizaráSuPerfilCompleto() {
    }

    @Then("se visualizará su perfil con datos incorrectos")
    public void seVisualizaráSuPerfilConDatosIncorrectos() {
    }

    @Then("se visualizará su perfil con datos incompletos")
    public void seVisualizaráSuPerfilConDatosIncompletos() {
    }
}
