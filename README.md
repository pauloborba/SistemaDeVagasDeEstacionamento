# SistemaDeVagasDeEstacionamento
### Parking Space Manager (PSM)

Projeto da cadeira de Engenharia de Software e Sistemas (ESS) do CIn-UFPE, período 2016.1
## Requisitos
- Grails version: 2.4.3
- Groovy version: 2.3.6 
- JVM version: 1.7.0_80 (JDK 7)

## Configurando

### Pelo IntelliJ:
Na estrutura do projeto (atalho _Alt+1_) clicar com botão direito na pasta **test/functional > Mark directory as > Test Source root:** 

![alt tag](http://i.imgur.com/LVHdZzt.png)

Criar run configurations:

![alt tag](http://i.imgur.com/l2fr9VR.png)

ParkingSpaceManager - DEV:
![alt tag](http://i.imgur.com/WVxKYvy.png)
- command line: `run-app --stacktrace --verbose`

ParkingSpaceManager - TEST:
![alt tag](http://i.imgur.com/q17bNAv.png)
- command line: `test-app -Dgeb.env=chrome functional:cucumber --stacktrace --verbose`

**Obs:** Funciona apenas com o navegador Chrome.

Integração com o Travis-ci
Entre em [Travis-CI](https://travis-ci.org/) <br />
Selecione o botão no canto superior direito "Sign in with github" <br />
Clique em seu nome no canto superior direito <br />
Pressione o botão cinza "Sync" caso seus repositórios não estejam aparecendo <br />
Caso os repositórios não aparecam, dê log out e entre novamente <br />
Escolha o repositório que deseja testar, no caso o TA, e clique no botão cinza para que ele se torne verde <br />
Faça um commit qualquer para ativar a build do travis <br />
Caso você queira ver mais do stacktrace utilize "--verbose" logo após o comando "--stacktrace" no arquivo .travis.yml do seu repositório <br />
Para receber emails sobre se a build passou ou não, ative seu email no perfil do github <br />

-------------------------------------------------------------------------------------------------------------------