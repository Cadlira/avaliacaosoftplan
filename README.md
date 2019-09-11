[TOCM]

[TOC]

___
# Avaliação Softplan
Este projeto tem como finalidade implementar as soluções aos exercícios propostos pela equipe da Softplan para o seu processo seletivo.


### Organização do projeto
Para este processo seletivo o projeto foi implementado utilizando Spring boot visto a facilidade de implementação utilizando TDD, injeção de
dependência e padrões de projeto como o Strategy ou factory.

O projeto em si foi dividido em dois pacotes com o objetivo de separar os dois exercícios propostos, e dentro desses pacotes foram criados outros pacotes de forma a haver uma separação mais clara entre as camadas de cada exercício.


##### Exercício 1
-----
Nesse exercício foi proposto adicionar um novo comportamento a uma classe já existente, mantendo, contudo, o comportamento anterior. Para isso, foi necessário propor em um solução que atendesse a esse requisito. Desta forma, decidi criar uma abstração para a classe já existente (interface ou classe abstrata) e criar uma nova classe para atender o novo comportamento, utilizando como base o padrão strategy para responder a esse requisito.
Com o intuito de ajudar na visualização da arquitetura e implementação da solução, resolvi criar um esboço de diagrama de classe, como pode ser visto abaixo:

![](https://i.imgur.com/z5rCL7z.png)

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