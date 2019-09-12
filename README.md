___
# Avaliação Softplan
Este projeto tem como finalidade implementar as soluções aos exercícios propostos pela equipe da Softplan para o seu processo seletivo.


### Organização do projeto
Para este processo seletivo o projeto foi implementado utilizando Spring boot visto a facilidade de implementação utilizando TDD, injeção de
dependência e padrões de projeto como o Strategy ou factory.

O projeto em si foi dividido em dois pacotes com o objetivo de separar os dois exercícios propostos, e dentro desses pacotes foram criados outros pacotes de forma a haver uma separação mais clara entre as camadas de cada exercício.


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
