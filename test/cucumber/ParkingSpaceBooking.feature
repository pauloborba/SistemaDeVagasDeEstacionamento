Feature: Parking space booking
  As a system user
  I want to book a parking space
  So I can park on the parking space I'd booked

# Reserva de Vagas Controller

  Scenario: Alterar reserva de vaga
    Given O sistema possui o usuario "joao" cadastrado com preferencia no setor "CIn" "sem" uso preferencial
    And O usuario "joao" esta logado no sistema
    And A vaga "CIn-02" pertence ao setor "CIn" "sem" uso preferencial
    And A vaga "CIn-06" pertence ao setor "CIn" "sem" uso preferencial
    And O usuario logado possui uma reserva na vaga "CIn-02"
    When O usuario logado tenta reservar a vaga "CIn-06"
    Then O sistema altera a reserva de vaga do usuário "joao" que era "CIn-02" para a vaga "CIn-06"

  Scenario: Falha ao Alterar reserva de vaga por causa de preferencia
    Given O sistema possui o usuario "joao" cadastrado com preferencia no setor "CIn" "sem" uso preferencial
    And O usuario "joao" esta logado no sistema
    And A vaga "CIn-03" pertence ao setor "CIn" "sem" uso preferencial
    And A vaga "CIn-05" pertence ao setor "CIn" "com" uso preferencial
    And O usuario logado possui uma reserva na vaga "CIn-03"
    When O usuario logado tenta reservar a vaga "CIn-05"
    Then O sistema não permite a reserva da vaga "CIn-05"

# Reserva de Vagas Web
  @ignore
  Scenario: Alterar reserva de vaga web
    Given Eu estou logado no sistema como "Joao"
    And Eu estou na tela inicial
    And Eu seleciono a opcao "Parking spot list" no menu inicial
    When Eu seleciono a opcao "Reservar" na vaga com descricao "N6-CIn"
    Then O sistema apresenta uma mensagem de vaga reservada com sucesso

  @ignore
  Scenario: Falha ao tentar reservar vaga por causa de preferencia web
    Given Eu estou logado no sistema como "Joao"
    And Eu estou na tela inicial
    And Eu seleciono a opcao "Parking spot list" no menu inicial
    When Eu seleciono a opcao "Reservar" na vaga com descricao "N5-CIn"
    Then O sistema apresenta uma mensagem de que a vaga nao pode ser reservada
