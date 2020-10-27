# ngp
Projeto de uma WEB Api Rest da empresa Nativa Gerencimento de Patrimônios

Para solução do desafio eu optei por usar o Spring Boot na construção da API Rest.
Optei pelo Spring pela familiaridade com o framework e agilidade que eles nos dá. Usei o git para controle do repositório, foram criados 4 branches:
 - Develop: Branch de desenvolvimento;
 - Feature_login, feature_marca, feature_patrimonio(Cada entidade foi trabalhada de maneira isolada em cada branch).
 
 Utilizei o JWT para autenticação por Token.

A lógica usada para as regras de negócios foram simples, verificações de acordo com o que o desafio pedia foram feitas para que o projeto respeitasse o que foi proposto.
Na entidade Patrimonio um (Math.abs(new Random().nextLong()) foi usado para gerar o n_tombo aleatorio, inteiro e positivo. Um método foi criado para verificar se o json que é passado no método put vem com o n_tombo, caso venha, uma exception é lançada.

Já para as entidades User e Marca, a regra de negócio são similares. Um método verifica se o email_user(nome_marca) que será cadastrado já existe na db, caso existe, uma exception é lançada. Para o método PUT a dificuldade foi em o código perceber que um JSON com um o email da propria entidade poderia ser alterado mas esse email já existe e uma exception n pode ser lançada, então uma verificação por id foi feita para saber se quem deseja atualizar é o mesmo id passado no json, caso o id seja o mesmo, ele permite q a alteração da entidade seja feita.

Obs.: Gostei muito do desafio, quem lê de primeira parece ser fácil, mas quando vamos implementar vemos que tem um grau de dificuldade maior.
