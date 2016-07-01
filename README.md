# Sistema de vagas de estacionamento

Projeto da disciplina de Engenharia de Software e Sistemas 2016.1 - CIn/UFPE
## Requisitos
- Grails version: 2.4.3
- JDK version: 1.7.0_80

## Configurando

### Pelo IntelliJ:
Selecione a opção Open:

![alt tag](http://i.imgur.com/zdPOypk.png)

Escolha a pasta em que você baixou o projeto

![alt tag](http://i.imgur.com/Wfh6o8I.png)

Se aparecer a mensagem *Grails SDK not found*:

![alt tag](http://i.imgur.com/y2295W9.png)

Clique em **Configure Grails SDK** e selecione o path do sdk instalado no seu computador:

![alt tag](http://i.imgur.com/u4T3S9o.png)

Abra as configurações do módulo ( botão direito em cima do projeto > Open Module Settings):

![alt tag](http://i.imgur.com/UivdgLo.png)

Na aba *Project*, escolha o **Project SDK** para o *1.7.0_80*, e o Project Language Level para *7 - Diamonds, ARM, multi-catch etc.*

![alt tag](http://i.imgur.com/fRL80tB.png)

Espere as configurações serem sincronizadas:

![alt tag](http://i.imgur.com/1ij9Kmw.png)


Na estrutura do projeto (atalho _Alt+1_), para cada sub-pasta imediata de **test**, clicar em **Mark directory as > Test Source root:** 

![alt tag](http://i.imgur.com/LVHdZzt.png)

Editar configurações de execução:

![alt tag](http://i.imgur.com/l2fr9VR.png)

- Para desenvolvimento:
![alt tag](http://i.imgur.com/WVxKYvy.png)
command line: `run-app --stacktrace --verbose`

- Para testes:
![alt tag](http://i.imgur.com/q17bNAv.png)
command line: `test-app -Dgeb.env=chrome functional:cucumber --stacktrace --verbose` (Google Chrome)
command line: `test-app -Dgeb.env=firefox functional:cucumber --stacktrace --verbose` (Mozilla Firefox)

**Obs:** 
Não é possível usar o Cucumber devidamente para validar o uso do plugin Shiro em testes de controlador, mais detalhes em http://mrdustmite.blogspot.com.br/2010/09/integration-tests-with-shiro-and-nimble.html.

-------------------------------------------------------------------------------------------------------------------

Integração com o Travis-ci <br />
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