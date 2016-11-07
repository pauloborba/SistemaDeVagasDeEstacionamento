#++ Reportar problemas com a vaga (vaga danificada ou uso incorreto).
##if($ReportParkingSpaceProblem)
Feature: Report parking space problem
  As a system user
  I want to send a problem report
  So that I can notify the admin about the problem

Scenario: Send a problem report
  Given the system has stored the user "fulano" with "Área II" as preferred sector
  And the user is logged in the system as "fulano"
  And The system has no problem report stored with Title "irregularidade na vaga E5"
  When the user tries to send the report with Title "irregularidade na vaga E5", Sector "Área II" and Description "Carro de placa XXX -1112 mal estacionado"
  Then the system stores the report "irregularidade na vaga E5"

Scenario: Send a problem report that already exists in the system
  Given the system has stored the user "fulano" with "Área II" as preferred sector
  And the user is logged in the system as "fulano"
  And The system has a problem report stored with Title "irregularidade na vaga E5"
  When the user tries to send the report with Title "irregularidade na vaga E5", Sector "Área II" and Description "Carro de placa XXX -1112 mal estacionado"
  Then the system doesn't store the report "irregularidade na vaga E5" with description "Carro de placa XXX -1112 mal estacionado"

Scenario: Send a problem report web
  Given I signed up in the system as "chico" with "CIn" as preferred sector
  When I go to report parking space problem page
  And I fill the report informations with Title "Carro mal estacionado", Sector "Cin" and Description "Carro de placa XXX -1111"
  And I send the problem report
  Then I shoud see a message indicating that the report was created

Scenario: Send a problem report that already exists in the system web
  Given I signed up in the system as "joao" with "CIn" as preferred sector
  When I go to report parking space problem page
  And I fill the report informations with Title "irregularidade na vaga E5", Sector "Cin" and Description "Carro de placa XXX -1111"
  And I send the problem report
  And I try to send a different problem report with title "irregularidade na vaga E5"
  Then I shoud see an error message
##end