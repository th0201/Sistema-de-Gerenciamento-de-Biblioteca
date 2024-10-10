import java.util.ArrayList;
import java.util.List;

public class Funcionario extends Pessoa {
    private boolean administrador;
    private String senha;
    private List<Funcionario> funcionarios = new ArrayList<>();

    public void cadastrarFuncionario() {
        System.out.print("Digite o numero do CPF:");
        String cpf = ent.nextLine();

        for (Funcionario a : funcionarios) {
            if (a.getCpf().equals(cpf)) {
                System.out.println("Funcionario já cadastrado!");
                return;
            }
        }
        System.out.println("Deseja se cadastrar como funcionario?(sim/não)");
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

            System.out.println("Funcionario cadastrado com sucesso!");
        } else {
            System.out.println("Cadastro de funcionario cancelado.");
        }
    }

    public void editarFuncionario() {
        System.out.print("Digite o numero do CPF: ");
        String cpf = ent.nextLine();
        for (Funcionario a : funcionarios) {
            if (a.getCpf().equals(cpf)) {
                System.out.println("Editar dados do(a) funcionário(a): " + a.getNome());
                a.editarPessoa();
                System.out.println("SENHA: ");
                a.setSenha(ent.nextLine());
                System.out.println("Dados editados com sucesso!");
                return;
            }
        }
        System.out.println("CPF não encontrado.");
    }

    public void buscarFuncionario() {
        System.out.print("Digite o numero do CPF:");
        String cpf = ent.nextLine();
        boolean funcionarioEncontrado = false;

        for (Funcionario a : funcionarios) {
            if (a.getCpf().equals(cpf)) {
                funcionarioEncontrado = true;
                System.out.println("NOME:" + a.getNome());
                System.out.println("IDADE:" + a.getIdade());
                System.out.println("CPF:" + a.getCpf());
                System.out.println("EMAIL:" + a.getEmail());
                System.out.println("SENHA:" + a.getSenha());
                System.out.println("-------------------------");
                return;
            }
            if (!funcionarioEncontrado) {
                System.out.println("Funcionario não cadastrado!");
            }
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

    public void visualizarFuncionario() {
        if (funcionarios.isEmpty()) {
            System.out.println("Não há funcionarios para visualizar.");
            return;
        }
        System.out.print("Digite o CPF do funcionário que deseja visualizar: ");
        String cpf = ent.nextLine();
        boolean funcionarioEncontrado = false;

        for (Funcionario a : funcionarios) {
            if (a.getCpf().equals(cpf)) {
                funcionarioEncontrado = true;
                System.out.println("Detalhes do Funcionario: ");
                System.out.println("NOME: " + a.getNome());
                System.out.println("IDADE: " + a.getIdade());
                System.out.println("CPF: " + a.getCpf());
                System.out.println("EMAIL: " + a.getEmail());
                System.out.println("ADMINISTRADOR: " + (a.isAdministrador() ? "sim" : "não"));
                System.out.println("----------------------------------");
                return;
            }
        }

        if (!funcionarioEncontrado) {
            System.out.println("CPF invalido.");
        }
    }

    public void removerFuncionario() {
        System.out.print("Digite o numero do CPF: ");
        String cpf = ent.nextLine();
        boolean funcionarioencontrado = false;

        for (Funcionario a : funcionarios) {
            if (a.getCpf().equals(cpf)) {
                funcionarioencontrado = true;
                System.out.println("Funcionário(a):" + a.getNome());
                System.out.println("Deseja remove-lo? (sim/não)");
                String resposta = ent.nextLine();
                if (resposta.equalsIgnoreCase("sim")) {
                    funcionarios.remove(a);
                    System.out.println("Funcionario removido.");
                    return;
                } else {
                    System.out.println("Remoção de funcionario cancelada.");
                }
            }
        }

        if (!funcionarioencontrado) {
            System.out.println("Funcionário não cadastrado!");
        }
    }

    public List<Funcionario> getFuncionario() {
        return funcionarios;
    }

    public boolean isAdministrador() {
        return administrador;
    }

    public String getSenha() {
        return senha;
    }
    
    public void isAdministrador(boolean administrador) {
        this.administrador = administrador;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
