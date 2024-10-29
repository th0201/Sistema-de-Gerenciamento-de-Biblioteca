import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.nio.charset.StandardCharsets;

public class Funcionario extends Pessoa {
    private boolean administrador;
    private String senha;
    private String ARQUIVO_FUNCIONARIOS = "funcionarios.txt";
    private List<Funcionario> funcionarios = new ArrayList<>();

    public Funcionario() {
        lerArquivo();
    }
    
    public Funcionario(String nome, int idade, String cpf, String email) {
        super(nome, idade, cpf, email);
    }

    public void cadastrarFuncionario() {
        System.out.print("Digite o número do CPF: ");
        String cpf = ent.nextLine();

        for (Funcionario a : funcionarios) {
            if (a.getCpf().equals(cpf)) {
                System.out.println("Funcionário já cadastrado!");
                return;
            }
        }
        Aluno aluno = new Aluno();
        for (Aluno a : aluno.getAlunos()) {
            if (a.getCpf().equals(cpf)) {
                System.out.println("CPF já cadastrado na lista de alunos!");
                return;
            }
        }

        System.out.println("Deseja se cadastrar como funcionário? (sim/não)");
        String resposta = ent.nextLine();

        if (resposta.equalsIgnoreCase("sim")) {
            System.out.println("\n------------------------");
            System.out.println("CADASTRAR FUNCIONÁRIO");
            System.out.println("------------------------");

            Funcionario novoFuncionario = new Funcionario();
            novoFuncionario.cadastrarPessoa();
            funcionarios.add(novoFuncionario);

            salvarDadosArquivo();
            System.out.println("Funcionário cadastrado com sucesso!");
        } else {
            System.out.println("Cadastro de funcionário cancelado.");
        }
    }

    public void atualizarArquivo() {
        try (FileWriter escritor = new FileWriter(ARQUIVO_FUNCIONARIOS, StandardCharsets.ISO_8859_1, false)) {
            escritor.write("Nome;Idade;CPF;Email;Senha;\n");

            for (Funcionario a : funcionarios) {
                escritor.write(a.getNome() + ";" + a.getIdade() + ";" + a.getCpf() + ";"
                        + a.getEmail() + ";" + a.getSenha() + ";" + "\n");
            }
            escritor.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao atualizar o arquivo: " + e.getMessage());
        }
    }

    public void editarFuncionario() {
        System.out.print("Digite o número do CPF: ");
        String cpf = ent.nextLine();
        for (Funcionario a : funcionarios) {
            if (a.getCpf().equals(cpf)) {
                System.out.println("Editar dados do(a) funcionário(a): " + a.getNome());
                System.out.println();
                a.editarPessoa();

                atualizarArquivo();
                System.out.println("Dados editados com sucesso!");
                return;
            }
        }
        System.out.println("CPF não encontrado.");
    }

    public void buscarFuncionario() {
        System.out.print("Digite o número do CPF: ");
        String cpf = ent.nextLine();
        boolean funcionarioEncontrado = false;

        for (Funcionario a : funcionarios) {
            if (a.getCpf().equals(cpf)) {
                funcionarioEncontrado = true;
                System.out.println();
                System.out.println("NOME: " + a.getNome());
                System.out.println("IDADE: " + a.getIdade());
                System.out.println("CPF: " + a.getCpf());
                System.out.println("EMAIL: " + a.getEmail());
                System.out.println("SENHA: " + a.getSenha());
                System.out.println("-------------------------");
                return;
            }
        }
        if (!funcionarioEncontrado) {
            System.out.println("Funcionário não cadastrado!");
        }
    }

    public void listarFuncionarios() {
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado.");
        } else {
            System.out.println("\n-------------------------------");
            System.out.println("LISTA DE FUNCIONÁRIOS CADASTRADOS");
            System.out.println("----------------------------------");

            for (Funcionario a : funcionarios) {
                System.out.println("NOME: " + a.getNome());
                System.out.println("IDADE: " + a.getIdade());
                System.out.println("CPF: " + a.getCpf());
                System.out.println("EMAIL: " + a.getEmail());
                System.out.println("SENHA: " + a.getSenha());
                System.out.println("----------------------------------");
            }
        }
    }

    public void removerFuncionario() {
        System.out.print("Digite o número do CPF: ");
        String cpf = ent.nextLine();
        boolean funcionarioEncontrado = false;

        for (Funcionario a : funcionarios) {
            if (a.getCpf().equals(cpf)) {
                funcionarioEncontrado = true;
                System.out.println("Funcionário(a): " + a.getNome());
                System.out.println("Deseja removê-lo? (sim/não)");
                String resposta = ent.nextLine();
                if (resposta.equalsIgnoreCase("sim")) {
                    funcionarios.remove(a);
                    atualizarArquivo();
                    System.out.println("Funcionário removido.");
                    return;
                } else {
                    System.out.println("Remoção de funcionário cancelada.");
                }
            }
        }

        if (!funcionarioEncontrado) {
            System.out.println("Funcionário não cadastrado!");
        }
    }

    public void salvarDadosArquivo() {
        try {
            boolean arquivoExiste = new File(ARQUIVO_FUNCIONARIOS).exists();
            FileWriter escritor = new FileWriter(ARQUIVO_FUNCIONARIOS, StandardCharsets.ISO_8859_1, true);

            if (!arquivoExiste) {
                escritor.write("Nome;Idade;CPF;Email;Senha;Matrícula;Curso\n");
            }

            for (Funcionario a : funcionarios) {
                escritor.write(a.getNome() + ";" + a.getIdade() + ";" + a.getCpf() + ";"
                        + a.getEmail() + ";" + a.getSenha() + ";" + "\n");
            }
            // Escrever todos os dados no arquivo
            escritor.flush();
            escritor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void lerArquivo() {
        File arquivo = new File(ARQUIVO_FUNCIONARIOS);
        // Verifica se o arquivo existe antes de fazer a leitura
        if (!arquivo.exists()) {
            return;
        }
        try {
            // Abrir o leitor para ler o arquivo
            BufferedReader leitor = new BufferedReader(new FileReader(ARQUIVO_FUNCIONARIOS));
            String linha;
            boolean primeiraLinha = true;

            // Ler cada linha inteira no arquivo, ignorando a primeira linha
            while ((linha = leitor.readLine()) != null) {
                // Ignora a primeira linha
                if (primeiraLinha) {
                    primeiraLinha = false;
                    continue;
                }

                // Dividir a linha em partes usando o ponto e vírgula como separador
                String[] partes = linha.split(";");
                String nome = partes[0];
                int idade = Integer.parseInt(partes[1]); // converte para string
                String cpf = partes[2];
                String email = partes[3];
                String senha = partes[4];

                Funcionario a = new Funcionario(nome, idade, cpf, email);
                a.setSenha(senha);
                funcionarios.add(a);
            }
            leitor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void visualizarFuncionario() {
        if (funcionarios.isEmpty()) {
            System.out.println("Não há funcionarios para visualizar.");
            return;
        }
        System.out.print("Digite o CPF do funcionario que deseja visualizar: ");
        String cpf = ent.nextLine();
        boolean funcionarioEncontrado = false;

        for (Funcionario a : funcionarios) {
            if (a.getCpf().equals(cpf)) {
                funcionarioEncontrado = true;
                System.out.println("----------------------------------");
                System.out.println("Detalhes do Funcionario: ");
                System.out.println("NOME: " + a.getNome());
                System.out.println("IDADE: " + a.getIdade());
                System.out.println("CPF: " + a.getCpf());
                System.out.println("EMAIL: " + a.getEmail());
                System.out.println("----------------------------------");
                return;
            }
        }
        if (!funcionarioEncontrado) {
            System.out.println("CPF inválido.");
        }
    }

    public boolean isAdministrador() {
        return administrador;
    }

    public void isAdministrador(boolean administrador) {
        this.administrador = administrador;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }
}
