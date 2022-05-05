Feature: Como trabajador quiero revisar el perfil de un cliente antiguo para tener su información de contacto.
  Scenario: El trabajador conoce el nombre completo del cliente
    Given el trabajador visualiza un listado de clientes
    When indique observar a detalle a un cliente
    Then se visualizará el nombre y apellido del cliente

  Scenario: El trabajador conoce el distrito donde vive el cliente
    Given el trabajador visualiza un listado de clientes
    When indique observar a detalle a un cliente
    Then se visualizará el distrito donde vive el cliente

  Scenario: El trabajador conoce el número telefonico del cliente
    Given el trabajador visualiza un listado de clientes
    When indique observar a detalle a un cliente
    Then se visualizará el número telefónico del cliente