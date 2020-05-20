# Cadastro-de-Usuarios
>Projeto que realiza o cadastro de usuários utilizando Java 8, JSP e banco de dados PostgreSQL, feito com o objetivo de treinar principais recursos do JSP.

## Conteúdo
* [Tecnologias utilizadas](#tecnologias-utilizadas)
* [Para utilizar](#para-utilizar)
* [Sobre o Projeto](#sobre-o-projeto)

## Tecnologias utilizadas
- [Java 8](https://docs.oracle.com/en/java/)
- [JavaServer Pages](https://docs.oracle.com/javaee/5/tutorial/doc/bnajo.html)
- Server: [Apache Tomcat 7](https://tomcat.apache.org/download-70.cgi)
- Banco de Dados: [PostgreSQL 9.1](https://www.postgresql.org/)

## Para utilizar
- Verificar se os arquivos .jar (presentes em ./WebContent/WEB-INF/lib foram adicionados ao PATH
- No pacote.classe: connection.SingleConnection; Verificar URL, user e password referente ao seu banco de dados
- Fazer backup do banco presente no arquivo sql.sql

## Sobre o Projeto
1. Primeira página do sistema (index.jsp): é onde o usuário deverá digitar um login e senha previamente cadastrado no banco de dados ou usar o padrão -> login:admin/senha:admin (que não pode ser alterado pela aplicação). Caso o login/senha for incorreto a aplicação não irá prosseguir.

![index](https://user-images.githubusercontent.com/38672183/82370328-c1264b80-99ee-11ea-86c5-0d06999a7249.png) 

2. Segunda página do sistema (acessoliberado.jsp): tela de acesso liberado ao sistema, onde haverá dois ícones, um para cadastrar usuários e outro para cadastrar produtos(*ainda em construção*).

![bem_vindo](https://user-images.githubusercontent.com/38672183/82370332-c1bee200-99ee-11ea-95fe-3b8477e4e960.png)

3. Terceira página do sistema (cadastrousuario.jsp): tela onde o usuário deverá se cadastrar no sistema, respeitando as validações de cada campo. Também poderá pesquisar por meio do **nome** do usuário, onde aparecerá algumas informações que foram cadastradas no banco, bem como inserir [telefones](#observacao)*, editar, excluir e fazer o download da imagem (exibida em miniatura) e PDF cadastrados. O usuário tabém terá ícones para voltar a página ou sair do sistema.

3.1. Foi utilizado um Webservice chamado [ViaCEP](https://viacep.com.br/) para consultar  informações com base no Código de Endereçamento Postal (CEP) do Brasil.

![cadastro](https://user-images.githubusercontent.com/38672183/82370323-c08db500-99ee-11ea-8281-a9121dc41f67.png)

<a name="observacao"></a><i>*Observação: tela de telefones permite ao usuário cadastrar telefones.</i>
![telefone](https://user-images.githubusercontent.com/38672183/82370329-c1bee200-99ee-11ea-8a92-c772d5cb1c8d.png)




