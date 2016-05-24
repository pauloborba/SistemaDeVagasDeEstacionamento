Feature: Realimentação de vagas
  As um gestor de vagas do sistema
  I posso realimentar vagas
  So eu quero alterar a situação de uma vaga

<<<<<<< HEAD
Scenario: Reservar uma vaga que não foi ocupada através do sistema
<<<<<<< HEAD
Given A vaga "2" está livre
When O gestor de vagas reserva a vaga "2"
Then O sistema reserva a vaga "2"
=======
Given Eu estou logado como "Gestor de Vagas"
And A vaga "2" está livre
When O usuário reserva a vaga "2"
Then O sistema realiza a reserva da vaga "2" para "Gestor de Vagas"    
>>>>>>> 00bfc75b8bd751083460a3217a8ac2e2579078e3
=======
  Scenario: Liberar uma vaga deixada como ocupada
    Given Eu estou logado como "Gestor de Vagas"
    And A vaga "1" está ocupada
    When O usuário remove a reserva da vaga "1"
    Then O sistema deixa a vaga "1" desocupada
>>>>>>> 0a18618d6cfc725a15b525fffbb631ecd12a85f2

  Scenario: Reservar uma vaga que não foi ocupada através do sistema
    Given Eu estou logado como "Gestor de Vagas"
    And A vaga "2" está livre
    When O usuário reserva a vaga "2"
    Then O sistema realiza a reserva da vaga "2" para "Gestor de Vagas"

  Scenario: Liberar uma vaga deixada como ocupada web
    Given Eu estou logado como "Gestor de Vagas"
    And A vaga "1" está ocupada
    When O gestor de vagas solicita a remoção da reserva da vaga "1"
    Then O sistema informa a vaga "1" como desocupada

<<<<<<< HEAD
<<<<<<< HEAD
  Scenario: Reservar uma vaga que não foi ocupada através do sistema web
Given Eu estou logado como gestor de vagas
=======
Scenario: Reservar uma vaga que não foi ocupada através do sistema web
Given Eu estou logado como "Gestor de Vagas"
>>>>>>> 00bfc75b8bd751083460a3217a8ac2e2579078e3
And A vaga "2" está livre
When O gestor de vagas solicita a reserva da vaga "2"
Then O sistema informa a vaga "2" como reservada para "Gestor de Vagas"   
=======
  Scenario: Reservar uma vaga que não foi ocupada através do sistema web
    Given Eu estou logado como "Gestor de Vagas"
    And A vaga "2" está livre
    When O gestor de vagas solicita a reserva da vaga "2"
    Then O sistema informa a vaga "2" como reservada para "Gestor de Vagas"
>>>>>>> 0a18618d6cfc725a15b525fffbb631ecd12a85f2
