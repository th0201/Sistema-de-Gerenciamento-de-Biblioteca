import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Funcionario extends Pessoa {
    private boolean administrador;
    private String senha;
    private static final String ARQUIVO_FUNCIONARIOS = "funcionarios.txt";
    private static List<Funcionario> funcionarios = carregarFuncionarios();
    transient Scanner ent = new Scanner(System.in);

    public Funcionario() {
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

        System.out.println("Deseja se cadastrar como funcionário? (sim/não)");
        String resposta = ent.nextLine();

        if (resposta.equalsIgnoreCase("sim")) {
            System.out.println("\n------------------------");
            System.out.println("CADASTRAR FUNCIONÁRIO");
            System.out.println("------------------------");

            Funcionario novoFuncionario = new Funcionario();
            novoFuncionario.cadastrarPessoa();
            System.out.print("SENHA: ");
            novoFuncionario.setSenha(ent.nextLine());
            funcionarios.add(novoFuncionario);

            salvarFuncionarios();
            System.out.println("Funcionário cadastrado com sucesso!");
        } else {
            System.out.println("Cadastro de funcionário cancelado.");
        }
    }

    public void editarFuncionario() {
        System.out.print("Digite o número do CPF: ");
        String cpf = ent.nextLine();
        for (Funcionario a : funcionarios) {
            if (a.getCpf().equals(cpf)) {
                System.out.println("Editar dados do(a) funcionário(a): " + a.getNome());
                a.editarPessoa();
                System.out.print("Nova SENHA: ");
                a.setSenha(ent.nextLine());

                salvarFuncionarios();
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
                    salvarFuncionarios();
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

    private static void salvarFuncionarios() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_FUNCIONARIOS))) {
            for (Funcionario funcionario : funcionarios) {
                writer.write("NOME: " + funcionario.getNome() + "\n");
                writer.write("IDADE: " + funcionario.getIdade() + "\n");
                writer.write("CPF: " + funcionario.getCpf() + "\n");
                writer.write("EMAIL: " + funcionario.getEmail() + "\n");
                writer.write("SENHA: " + funcionario.getSenha() + "\n");
                writer.write("ADMINISTRADOR: " + funcionario.isAdministrador() + "\n");
                writer.write("------------------------\n");
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar funcionários: " + e.getMessage());
        }
    }

    private static List<Funcionario> carregarFuncionarios() {
        List<Funcionario> funcionariosCarregados = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_FUNCIONARIOS))) {
            String linha;
            Funcionario funcionario = null;
            while ((linha = reader.readLine()) != null) {
                if (linha.startsWith("NOME:")) {
                    funcionario = new Funcionario();
                    funcionario.setNome(linha.split(": ")[1]);
                } else if (linha.startsWith("IDADE:")) {
                    funcionario.setIdade(Integer.parseInt(linha.split(": ")[1]));
                } else if (linha.startsWith("CPF:")) {
                    funcionario.setCpf(linha.split(": ")[1]);
                } else if (linha.startsWith("EMAIL:")) {
                    funcionario.setEmail(linha.split(": ")[1]);
                } else if (linha.startsWith("SENHA:")) {
                    funcionario.setSenha(linha.split(": ")[1]);
                } else if (linha.startsWith("ADMINISTRADOR:")) {
                    funcionario.isAdministrador(Boolean.parseBoolean(linha.split(": ")[1]));
                    funcionariosCarregados.add(funcionario);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de funcionários não encontrado. Um novo será criado.");
        } catch (IOException e) {
            System.out.println("Erro ao carregar funcionários: " + e.getMessage());
        }
        return funcionariosCarregados;
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
}
