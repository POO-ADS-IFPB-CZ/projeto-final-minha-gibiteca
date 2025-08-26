# Minha Gibiteca

O **Minha Gibiteca** é um sistema desenvolvido em **Java** com interface gráfica em **Swing**, voltado para a organização e gerenciamento de títulos de gibis, revistas e histórias em quadrinhos.  
O projeto foi estruturado de forma a aplicar conceitos fundamentais de **Programação Orientada a Objetos (POO)**, **persistência de dados** e **separação de camadas** em software.

---

## Funcionalidades

### Interface Gráfica
- Tela principal que apresenta uma lista de títulos cadastrados.  
- Botões para **Cadastrar**, **Editar**, **Excluir** e **Atualizar** títulos.  
- Campo de pesquisa que permite **filtrar títulos** por nome, editora ou autor.  
- Diálogos de confirmação para operações críticas, como exclusão de registros.  

### Persistência de Dados
- Os dados são armazenados em um arquivo JSON localizado em `./data/titulos.json`.  
- O sistema cria automaticamente a pasta `data/` e o arquivo caso não existam.  
- Cada título recebe um identificador único gerado automaticamente.  
- Estrutura de armazenamento simples e legível, facilitando manutenção e inspeção.  

### Cadastro de Títulos
- Cada título possui os seguintes atributos:  
  - Nome  
  - Editora  
  - Autor  
- O cadastro e a edição são realizados por meio de um formulário com validação de campos obrigatórios.  

### Pesquisa e Filtro
- Permite localizar títulos a partir de fragmentos do nome, da editora ou do autor.  
- O resultado da pesquisa é exibido dinamicamente na lista principal.  

### Teste de Persistência
- O projeto inclui uma classe de teste (`MainTestDAO`) que valida exclusivamente a leitura e escrita de dados no arquivo JSON.  
- Este teste insere registros fictícios, persiste os dados e realiza a leitura em seguida, exibindo o resultado no console.  

---

## Execução

Na raiz do repositório, execute os seguintes comandos:

```bash
# Compilar os arquivos fonte
mkdir -p Gibiteca/out
find Gibiteca/src -name "*.java" -print0 | xargs -0 javac -encoding UTF-8 -d Gibiteca/out

# Executar a aplicação com interface gráfica
java -cp Gibiteca/out gibiteca.Main

# (Opcional) Executar apenas o teste de persistência
java -cp Gibiteca/out gibiteca.MainTestDAO
```

---

## Arquitetura do Projeto

A organização do projeto segue uma estrutura em camadas, com responsabilidades bem definidas:

- **model/** – Classes de domínio, representando as entidades principais (`Titulo`, `Personagem`, `Edicao`).  
- **controller/** – Camada de lógica de negócio, responsável por gerenciar operações sobre os dados (`TituloController`).  
- **persistence/** – Implementação da persistência em JSON (`TituloDAO`).  
- **view/** – Interface gráfica desenvolvida em Swing (`TelaPrincipal`, `TituloFormDialog`).  
- **Main** – Classe principal responsável pela inicialização da aplicação.  
