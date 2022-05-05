Feature: Como trabajador quiero visualizar mi perfil para verificar si mis datos son correctos.
  Scenario: El trabajador visualiza su perfil completo
    Given el trabajador se encuentra en la sección principal
    When ingrese a la sección de su perfil
    Then se visualizará su perfil completo
  Scenario: El trabajador visualiza su perfil con datos incorrectos
    Given el trabajador se encuentra en la sección principal
    When ingrese a la sección de su perfil
    Then se visualizará su perfil con datos incorrectos
  Scenario: El trabajador visualiza su perfil con datos incompletos
    Given el trabajador se encuentra en la sección principal
    When ingrese a la sección de su perfil
    Then se visualizará su perfil con datos incompletos
