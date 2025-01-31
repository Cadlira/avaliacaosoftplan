___
# Avaliação Softplan
Este projeto tem como finalidade implementar as soluções aos exercícios propostos pela equipe da Softplan para o seu processo seletivo.


### Organização do projeto
Para este processo seletivo o projeto foi implementado utilizando Spring boot visto a facilidade de implementação utilizando TDD, injeção de
dependência e padrões de projeto como o Strategy ou factory.

O projeto em si foi dividido em dois pacotes com o objetivo de separar os dois exercícios propostos, e dentro desses pacotes foram criados outros pacotes de forma a haver uma separação mais clara entre as camadas de cada exercício.

O código está no repositório compartilhado, sendo que cada exercício está em uma branch separada:
- Exercicio 01 na branch `exercicio1`
- Exercicio 02 na branch `exercicio2`

Na branch master estão os dois exercício no projeto.

-----
##### Exercício 1
-----
Nesse exercício foi proposto adicionar um novo comportamento a uma classe já existente, mantendo, contudo, o comportamento anterior. Para isso, foi necessário propor em um solução que atendesse a esse requisito. Desta forma, decidi criar uma abstração para a classe já existente (interface ou classe abstrata) e criar uma nova classe para atender o novo comportamento, utilizando como base o padrão strategy para responder a esse requisito.
Com o intuito de ajudar na visualização da arquitetura e implementação da solução, resolvi criar um esboço de diagrama de classe, como pode ser visto abaixo:

![](https://i.imgur.com/z5rCL7z.png)

____
###### Como executar:
___

Para o exercício 1 não foi especificado nenhuma forma de execução, contudo decidi exibir o resultado no console da mesma forma que o exercício 2.
Como o projeto foi implementado com Spring Boot foi necessário adicionar o runner de command line do spring boot, podendo o mesmo ser executado seguindo os seguintes passo:
- No console, será necessário navegar até a pasta raíz do projeto e executar o comando:
`gradlew.bat clean bootRun`, conforme imagem:
![](https://i.imgur.com/2cxYMsO.jpg)
- Ao executar o resultado deverá ser parecido com o da imagem abaixo:
![](https://i.imgur.com/fQ1IsUW.jpg)
- Também é possível executarmos os testes unitários da aplicação através da linha de comando executando a linha seguinte: `gradlew.bat clean test --info`, conforme imagem abaixo:
![](https://i.imgur.com/ZmEob28.jpg)
- Teremos como resultado a imagem abaixo:
![](https://i.imgur.com/Nt95XHP.jpg)

_____

###### Anotações:
______

Para a implementação da solução proposta ao requisito levantado segui os passos abaixo, utilizando, sempre que possível, as melhores práticas do TDD e dos princípios de orientação a objeto.
- Como já tinhamos uma classe com um comportamento pré existente, resolvi criar inicialmente uma classe para testar todos os comportamentos previstos na mesma: `br.com.softplan.avaliacao.exercicio1.service.relatorio.observacao.GeradorObservacaoTest`. Com a classe implementada copiei o arquivo passado por vocês [GeradorObservação.java](https://drive.google.com/open?id=1pq8UFR0VKeFCmidKw5z-2g-duSPGLtbs) com o objetivo de fazer com que os testes criados passassem.
- Rodando os testes nessa classe sugiu a necessidade realizar o primeiro refactory nessa classe, pois o último teste criado não passou, pois lançou uma exceção de `NullPointerException`, sendo preciso realizar a seguinte modificação:

###### Código antigo:
```java
if (!lista.isEmpty()) 
		{
			return retornaCodigos(lista) + ".";
		}

```

###### Código novo:
```java
if (!CollectionUtils.isEmpty(lista))  {
		return retornaCodigos(lista) + ".";
}

```
- Em seguida, implementei a classe de teste para o novo comportamento proposto pelo exercício 1 e adicionei os métodos de teste para os fluxos conhecidos. `br.com.softplan.avaliacao.exercicio1.service.relatorio.observacao.GeradorObservacaoV2Test`.
- Com a classe e métodos de teste criados, criei as classes e métodos necessários para remover os erros de compilação. Nesse caso implementei a classe `br.com.softplan.avaliacao.exercicio1.service.relatorio.observacao.GeradorObservacaoV2.java` e o método.

```java
	public String geraObservacao(List asList) {
		return null;
	}
```
- Observando o princípio do DRY e de orientação a objetos foi visto a necessidade de criação de uma abstração para as classes `GerardorObservacao.java` e `GeradorObservacaoV2.java` com o objetivo de remover códigos duplicados comuns as duas implementações. Foi criada uma classe abstrata,  `br.com.softplan.avaliacao.exercicio1.service.relatorio.observacao.GeradorObservacaoInterface.java`, na qual foi implementado o método `public String geraObservacao(List asList)` e adicionado um método abstrato `protected abstract String retornaCodigos(List lista);` para ser implementado pelas classes filhas, conforme suas lógicas.
- Após a realização refactory rodei os testes da classe original, passando todos sem problema. Os testes da nova classe ainda não passaram, visto que ainda não foi implementada a solução.
- Antes de implementar o novo algoritmo, detectei uma necessidade de melhoria no código da classe disponibilizada `GeradorObservacao.java`, com o objetivo de melhorar a leitura e clareza do código.
- Como pensei na arquitetura levando em consideração que seria necessário um repositório para pegar as notas pelo número com a finalidade de obter o seu valor, foi necessário alterar a classe de teste do `GeradorObservacaoV2.java`  para considerar esse repositório, mockando o mesmo para testar apenas a lógica de negócio.
- Finalizada a regra de negócio, é necessário implementar a regra do strategy, a fim de selecionar a lógica utilizada. Desta forma, foi criada a classe de teste `RelatorioServiceTest`. Diferente dos testes das classes `GeradorObservacao.java` e `GeradorObservacaoV2.java`, essa classe de testes não utilizará mock, pois será utilizado as facilidades de injeção de dependência do Spring para implementar o padrão strategy.
- Finalizando a implementação desse exercício construi uma classe de *testSuite* para o exercício 1 `br.com.softplan.avaliacao.exercicio1.Exercicio1TestSuite.java` e executando a mesmo foi retornado um valor de cobertura de 97,3%, conforme imagem abaixo:
![](https://i.imgur.com/iZEnjEo.jpg)
- O código do exercício 1 encontra-se no repositório compartilhado com vocês na branch ***exercicio1***. O código dos dois exercícios estaram implementado na branch  ***master***.

-----
##### Exercício 2
-----
No exercício 2 foi apresentado um problema no qual era necessário a leitura de um conjunto de dados de entrada em um formato específico para seguinte tratamento e exibição.
O conjunto de dados foi entregue no formato JSON, cujo o arquivo entregue foi o [dados-entrada-servicos-composicao.json](https://drive.google.com/open?id=1V7CtZBMb7YN6snSVMbMNqAGlY0T8Q2lV).

Normalmente seria criado um DTO responsável pela conversão do JSON para java na camada de apresentação (camada da API REST, por exemplo), passando para a camada de negócio o objeto reconhecido pela mesma. Contudo, como não faz parte do escopo desse exercício a camada de apresentação e, há também a solicitação de uma melhor solução para o tratamento e exibição dos dados informados no arquivo, a carga do mesmo será realizado no services da aplicação.

Levantado as pré condições, elaborei um esboço de diagrama de classes para auxiliar no desenvolvimento do exercício proposto. Segue abaixo o esboço do diagrama de classe:
![](https://i.imgur.com/eSeH66O.png)

____
###### Como executar:
___

Para o exercício 2 foi solicitado que o resultado fosse executado e exibido no console. Como o projeto foi implementado com Spring Boot foi necessário adicionar o runner de command line do spring boot, podendo o mesmo ser executado seguindo os seguintes passo:
- No console, será necessário navegar até a pasta raíz do projeto e executar o comando:
`gradlew.bat clean bootRun`, conforme imagem:
![](https://i.imgur.com/2cxYMsO.jpg)
- Ao executar o resultado deverá ser parecido com o da imagem abaixo:
![](https://i.imgur.com/DM6tAlk.jpg)
- Também é possível executarmos os testes unitários da aplicação através da linha de comando executando a linha seguinte: `gradlew.bat clean test --info`, conforme imagem abaixo:
![](https://i.imgur.com/ZmEob28.jpg)
- Teremos como resultado a imagem abaixo:
![](https://i.imgur.com/Nt95XHP.jpg)

_____

###### Anotações:
______
Como no exercício 1 vou elencando as atividades que vou executando na medida que eu as faço.
-  Para iniciar o desenvolvimento da aplicação é necessário definir qual lógica devo validar primeiro. Observando o diagrama acima decidi iniciar os testes pela classe `ItemComposicao.java`, realizando testes no método `getValor()`. Para isso, será necessário *mockar* o objeto *Item* e seu método `getValorUnitario()` que será testado posteriormente.

- Com a classe `ItemComposicao.java` testada, podemos iniciar os testes das classes concretas de `Item.java`: `Insumo.java` e `Composicao.java`. Ao final da criação e execução dos testes dessas classes já deveremos possuir todas as classes do pacote `br.com.softplan.avaliacao.exercicio2.model` implementadas.

- **Uma observação importante referente ao desenvolvimento das funcionalidades, principalmente as responsáveis pelos cálculos dos valores, foi a necessiade da criação de uma classe utilitária para o tratamento dos valores `double` com a finalidade de ter um maior controle sobre os retornos das suas casas decimais.**

- Após a finalização dos testes e implementação das funcionalidades do pacote `br.com.softplan.avaliacao.exercicio2.model`, será construído os testes da classe de factory de **Item**: `ItemFactory.java`. Essa classe foi pensada utilizando o padrão **factory**, ficando responsável da criação do **Item** de acordo com o seu tipo.

- Com a finalidade de otimizar e melhorar a organização dos dados, pensei na criação de repositorios para guardar e recuperar os **Insumos** e **Composições** evitando objetos duplicados na memória.

- Este factory será utilizado na classe de serviço com o objetivo de instanciar a classe correta de acordo com o tipoItem informado no JSON de dados.

- Após finalizar os teste e implementações do ItemFactory farei os testes para os repositórios, mesmo não fazendo parte do escopo do projeto. Estes repositórios seram apenas Maps para armazenar os dados em memória.

- FInalizando o exercício 2, vou implementar a lógica da conversão dos dados de entrada existentes no JSON **[dados-entrada-servicos-composicao.json](https://drive.google.com/open?id=1V7CtZBMb7YN6snSVMbMNqAGlY0T8Q2lV)** para a estrutura previamente criada e finalizar realizando a chamada da lógica e exibindo os dados no console.

- Para esta etapa implementarei um service que será responsável pela conversão dos dados contidos no JSON em um DTO e, em seguida, a conversão do DTO na estrutura previamente implementada. Utilizando os princípios do TDD os testes para a lógica proposta para o service, sendo estes testes utilizando a estrutura do Spring framework, simulando assim o teste de toda as camadas envolvidas.

- Realizando os testes finais percebi que os dados contidos na planilha estão realizando um arredondamento que não consegui identificar como é feito, pois, mesmo fazendo as contas manualmente, não consegui chegar no valor. Tendo variações de centavos.

- Após finalizar os testes e implementações a cobertura de testes do execício 2 ficou conforme imagem abaixo:
![](https://i.imgur.com/pRLBN1x.jpg)
