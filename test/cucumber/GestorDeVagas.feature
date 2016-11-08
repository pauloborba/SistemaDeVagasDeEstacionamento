Feature: Gestor de Vagas
  As um usuário do sistema cadastrado como gestor de vagas
  I quero ser capaz de manipular e ter acesso às informarções das vagas
  so that eu posso gerenciar a logística do sistema


  Scenario: Informações sobre a reserva
    Given O sistema possui o usuário "Fulano" cadastrado com preferencia no setor "CIn"
    And O usuário "Fulano" está logado no sistema
    And As vagas "11" e "12" dos setores "CIn" e "CCEN" estão livres
    When "Fulano" cria uma reserva da vaga "11" para o horário das "13" às "15" horas do dia corrente
    Then O Sistema reserva a vaga "11" para "Fulano"
    And A reserva da vaga "11" recebe o valor "13" para Hora de entrada
    And A reserva da vaga "11" recebe o valor "15" para Hora de saída


  Scenario: Tempo de reserva da vaga acabou
    Given O sistema possui o usuário "Sicrano" cadastrado com preferencia no setor "CCEN"
    And O usuário "Sicrano" está logado no sistema
    And As vagas "11" e "12" dos setores "CIn" e "CCEN" estão livres
    And "Sicrano" reservou a vaga "12" das "7" às "8" horas do dia corrente
    When o horário do sistema passar das "8" horas
    And "Sicrano" ainda está na vaga "12"
    Then A reserva da vaga "12" recebe o status "red"


  Scenario: Informações sobre as vagas reservadas web
    Given Eu estou logado no sistema como "Gustavo" com preferência no setor "CIn"
    And Eu estou na página de home
    And Eu vou para página da listagem de vagas
    And Eu seleciono a opção Criar Vaga
    And Eu estou na página de criação de vaga
    And Eu crio uma vaga com descricao "13", no setor "CIn"
    And Eu estou na página de visualização da vaga
    And Eu vou para página da listagem de vagas
    When Eu seleciono a opção Reservar da vaga com descrição "13"
    And Eu crio uma reserva para a vaga para o horário de "8" às "10" horas do dia corrente
    Then Eu estou na página da listagem de vagas
    And Eu vejo "8h - 10h" na coluna Book da vaga com descrição "13"


  Scenario: Tempo de reserva da vaga acabou web
    Given Eu estou logado no sistema como "Alfredo" com preferência no setor "CCEN"
    And Eu estou na página de home
    And Eu vou para página da listagem de vagas
    And Eu seleciono a opção Criar Vaga
    And Eu estou na página de criação de vaga
    And Eu crio uma vaga com descricao "14", no setor "CCEN"
    And Eu estou na página de visualização da vaga
    And Eu vou para página da listagem de vagas
    And o horário do sistema passou das "6" horas
    When Eu seleciono a opção Reservar da vaga com descrição "14"
    And Eu crio uma reserva para a vaga para o horário de "5" às "6" horas do dia corrente
    Then Eu estou na página da listagem de vagas
    And Eu vejo o texto da coluna Book da vaga com descrição "14" na cor "red"