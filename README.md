# Sistema de Vagas de Estacionamento [![Build Status](https://travis-ci.org/pauloborba/SistemaDeVagasDeEstacionamento.svg?branch=master)](https://travis-ci.org/pauloborba/SistemaDeVagasDeEstacionamento)

Projeto da disciplina de Engenharia de Software e Sistemas - CIn/UFPE
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
command line: `test-app -Dgeb.env=chrome functional:cucumber --stacktrace --verbose`
