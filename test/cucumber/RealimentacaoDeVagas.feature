Feature: Realimenta��o de vagas
  As um gestor de vagas do sistema
  I quero realimentar vagas
  So eu possa alterar a situa��o de uma vaga
  
Scenario: Liberar uma vaga deixada como ocupada
Given A vaga "1" est� ocupada
When O gestor de vagas remove a reserva da vaga "1"
Then O sistema deixa a vaga "1" desocupada

Scenario: Reservar uma vaga que n�o foi ocupada atrav�s do sistema
Given A vaga "2" est� livre
When O gestor de vagas reserva a vaga "2"
Then O sistema reserva a vaga "2" para "Gestor de vagas"    

Scenario: Liberar uma vaga deixada como ocupada web
Given Eu estou logado como gestor de vagas
And A vaga "1" est� ocupada
When O gestor de vagas solicita a remo��o da reserva da vaga "1"
Then O sistema informa a vaga "1" como desocupada

Scenario: Reservar uma vaga que n�o foi ocupada atrav�s do sistema web
Given Eu estou logado como gestor de vagas
And A vaga "2" est� livre
When O gestor de vagas solicita a reserva da vaga "2"
Then O sistema informa a vaga "2" como reservada para "Gestor de vagas"   