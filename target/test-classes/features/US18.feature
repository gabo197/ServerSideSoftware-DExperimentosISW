Feature: Como trabajador quiero iniciar sesión en la aplicación para visualizar si tengo nuevas solicitudes de trabajo.
  Scenario: El trabajador inicia sesión con éxito
    Given el trabajador se encuentra en la sección de inicio
    When complete su nombre de usuario y su contraseña
    Then se iniciará sesión en su cuenta

  Scenario: El trabajador inicia sesión con un usuario incorrecto
    Given el trabajador se encuentra en la sección de inicio
    When complete un nombre de usuario incorrecto y su contraseña
    Then no se iniciará sesión en su cuenta

  Scenario: El trabajador inicia sesión con una contraseña incorrecta
    Given el trabajador se encuentra en la sección de inicio
    When complete su nombre de usuario y una contraseña incorrecta
    Then no se iniciará sesión en su cuenta
