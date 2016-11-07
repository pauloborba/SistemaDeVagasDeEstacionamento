Feature: Gestor de Vagas
  As um usuário do sistema cadastrado como gestor de vagas
  I quero ser capaz de manipular e ter acesso às informarções das vagas
  so that eu posso gerenciar a logística do sistema

  Scenario: Informações sobre a reserva
    Given O sistema possui o usuário "Fulano" cadastrado com preferencia no setor "CIn"
    And O usuário "Fulano" está logado no sistema
    And As vagas "11" e "2016" dos setores "CIn" e "CCEN" estão livres
    When "Fulano" cria uma reserva da vaga "11" para o horário das "13" às "15" horas do dia corrente
    Then O Sistema reserva a vaga "11" para "Fulano"
    And A reserva da vaga "11" recebe o valor "13h" para Hora de entrada
    And A reserva da vaga "11" recebe o valor "15h" para Hora de saída


#    Scenario: Tempo de reserva da vaga acabou
#      Given "Fulano" reservou a vaga "11" das "13h" às "15h"
#      And o horário do sistema passou das 15h
#      And "Fulano" ainda está na vaga "11"
#      Then A vaga "11" recebe o status "red"