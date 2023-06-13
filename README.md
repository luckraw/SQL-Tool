
Este projeto é uma ferramenta de linha de comando para executar consultas SQL e realizar diversas operações em um banco de dados MySQL. Ele oferece funcionalidades como consulta de dados, inserção de dados, atualização de dados, exclusão de dados e geração de relatórios.

## Tecnologias Utilizadas

- Java
- JDBC
- MySQL

## Como Usar

Certifique-se de ter o Java instalado em seu sistema.
Clone ou faça o download do projeto SQLTool do repositório no GitHub.
Abra o projeto em seu ambiente de desenvolvimento Java preferido.
Atualize as seguintes variáveis na classe SQLTool com as credenciais do seu banco de dados MySQL:

`DB_URL`: A URL do seu banco de dados MySQL.

`DB_USERNAME`: O nome de usuário do seu banco de dados MySQL.

`DB_PASSWORD`: A senha do seu banco de dados MySQL.

### Compile e execute o projeto.

Ao executar a ferramenta SQLTool, você será solicitado a inserir seu nome de usuário e senha para autenticação. Se a autenticação for bem-sucedida, você verá o menu principal com as opções disponíveis. Escolha uma opção digitando o número correspondente.

`Opção 1: Consulta de Dados` - Permite que você execute consultas SELECT no banco de dados. Insira a consulta SELECT quando solicitado, e a ferramenta exibirá o resultado.

`Opção 2: Inserção de Dados` - Permite que você insira dados no banco de dados. Insira a consulta INSERT quando solicitado, e a ferramenta executará a consulta e exibirá o número de linhas inseridas.

`Opção 3: Atualização de Dados` - Permite que você atualize dados no banco de dados. Insira a consulta UPDATE quando solicitado, e a ferramenta executará a consulta e exibirá o número de linhas atualizadas.

`Opção 4: Exclusão de Dados` - Permite que você exclua dados do banco de dados. Insira a consulta DELETE quando solicitado, e a ferramenta executará a consulta e exibirá o número de linhas excluídas.

`Opção 5: Gerar Relatório` - Gera um relatório de vendas consultando dados da tabela "sales" no banco de dados. O relatório será salvo como um arquivo de texto com o nome "sales_report.txt" no diretório do projeto.

`Opção 6: Sair` - Encerra a ferramenta SQLTool.

## Observação

Certifique-se de ter a conectividade necessária com o banco de dados e o driver JDBC para o MySQL configurados corretamente em seu projeto. Substitua os espaços reservados (DB_URL, DB_USERNAME e DB_PASSWORD) no código pelos valores reais correspondentes à conexão com seu banco de dados MySQL.
