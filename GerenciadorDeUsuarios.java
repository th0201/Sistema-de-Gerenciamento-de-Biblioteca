import java.util.Scanner;

public class GerenciadorDeUsuarios {
    boolean continuar = false;
    int opcao;
    Scanner scanner = new Scanner(System.in);
    Aluno aluno = new Aluno();
    Funcionario funcionario = new Funcionario();

    public void realizaCadastro() {
        System.out.println("Realizar cadastro de aluno ou de funcionario? ");
        String resposta = scanner.nextLine();

        if (resposta.equalsIgnoreCase("aluno")) {
            aluno.cadastrarAluno();
        } else if (resposta.equalsIgnoreCase("funcionario")) {
            funcionario.cadastrarFuncionario();
        }
    }

    public void verificaCadastro() {
        if (aluno.getAlunos() == null || funcionario.getFuncionarios() == null) {
            System.out.println("Nenhum aluno cadastrado no sistema.");
            System.out.println("Você deve se cadastrar primeiro.");
            realizaCadastro();
        }
    }

    public void menuAluno() {
        boolean executando;
        do {
            System.out.println("\n==== Menu Aluno ====");
            System.out.println("1. Cadastrar Aluno");
            System.out.println("2. Editar Aluno");
            System.out.println("3. Buscar Aluno");
            System.out.println("4. Listar Alunos");
            System.out.println("5. Remover Aluno");
            System.out.println("6. Visualizar Aluno");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            executando = true;

            switch (opcao) {
                case 1:
                    aluno.cadastrarAluno();
                    break;
                case 2:
                    aluno.editarAluno();
                    break;
                case 3:
                    aluno.buscarAluno();
                    break;
                case 4:
                    aluno.listarAlunos();
                    break;
                case 5:
                    aluno.removerAluno();
                    break;
                case 6:
                    aluno.visualizarAluno();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    executando = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (executando);
    }

    public void menuFuncionario() {
        boolean executando;
        do {
            System.out.println("\n==== Menu Funcionário ====");
            System.out.println("1. Cadastrar Funcionário");
            System.out.println("2. Editar Funcionário");
            System.out.println("3. Buscar Funcionário");
            System.out.println("4. Listar Funcionários");
            System.out.println("5. Remover Funcionário");
            System.out.println("6. Visualizar Funcionário");
            System.out.println("7. Menu Empréstimo");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            executando = true;

            switch (opcao) {
                case 1:
                    funcionario.cadastrarFuncionario();
                    break;
                case 2:
                    funcionario.editarFuncionario();
                    break;
                case 3:
                    funcionario.buscarFuncionario();
                    break;
                case 4:
                    funcionario.listarFuncionarios();
                    break;
                case 5:
                    funcionario.removerFuncionario();
                    break;
                case 6:
                    funcionario.visualizarFuncionario();
                    break;
                case 7:
                    Emprestimo emprestimo = new Emprestimo();
                    emprestimo.menuEmprestimo();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    executando = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (executando);
    }

    public void fazerLogin() {
        verificaCadastro();
        boolean loginBemSucedido = false;
        int tentativas = 0;

        while (!loginBemSucedido) {
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("Senha: ");
            String senha = scanner.nextLine();

            for (Aluno a : aluno.getAlunos()) {
                if (a.login(email, senha)) {
                    System.out.println("Login bem-sucedido! Bem-vindo(a), " + a.getNome() + "!");
                    loginBemSucedido = true;
                    menuAluno();
                    return;
                }
            }

            for (Funcionario f : funcionario.getFuncionarios()) {
                if (f.login(email, senha)) {
                    System.out.println("Login bem-sucedido! Bem-vindo(a), " + f.getNome() + "!");
                    loginBemSucedido = true;
                    menuFuncionario();
                    return;
                }
            }
            if (!loginBemSucedido) {
                System.out.println("Email ou senha incorretos. Tente novamente.");
            }
            tentativas++;
            if (tentativas == 3) {
                System.out.println("Número máximo de tentativas atingido. Tente novamente mais tarde.");
                return;
            }
        }
        scanner.close();
    }
}
