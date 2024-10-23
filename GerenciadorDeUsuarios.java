import java.util.Scanner;

public class GerenciadorDeUsuarios {
    boolean continuar = false;
    int opcao;
    Scanner ent = new Scanner(System.in);
    AlunoCSV aluno = new AlunoCSV();
    Funcionario funcionario = new Funcionario();

    public void realizaCadastro() {
        System.out.println("Realizar cadastro de aluno ou de funcionario? ");
        String resposta = ent.nextLine();

        if (resposta.equalsIgnoreCase("aluno")) {
            aluno.cadastrarAluno();
        } else if (resposta.equalsIgnoreCase("funcionario")) {
            funcionario.cadastrarFuncionario();
        }
    }

    public void verificaCadastro() {
        if (aluno.getAlunos() == null || funcionario.getFuncionario() == null) {
            System.out.println("Nenhum aluno cadastrado no sistema.");
            System.out.println("Você deve se cadastrar primeiro.");
            realizaCadastro();
        }
    }

    public void menuAluno() {
        this.continuar = !this.continuar;
        while (continuar) {
            System.out.println("MENU:");
            System.out.println("1. Editar Aluno");
            System.out.println("2. Buscar Aluno");
            System.out.println("3. Listar Alunos");
            System.out.println("4. Remover Aluno");
            System.out.println("5. Visualizar Aluno");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = ent.nextInt();
            ent.nextLine();

            switch (opcao) {
                case 1:
                    aluno.editarAluno();
                    break;
                case 2:
                    aluno.buscarAluno();
                    break;
                case 3:
                    aluno.listarAlunos();
                    break;
                case 4:
                    aluno.removerAluno();
                    break;
                case 5:
                    aluno.visualizarAluno();
                    break;
                case 6:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
        System.out.println("Programa encerrado.");
    }

    public void menuFuncionario() {
        while (continuar) {
            System.out.println("MENU:");
            System.out.println("1. Editar Aluno");
            System.out.println("2. Buscar Aluno");
            System.out.println("3. Listar Alunos");
            System.out.println("4. Remover Aluno");
            System.out.println("5. Visualizar Aluno");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = ent.nextInt();

            switch (opcao) {
                case 1:
                    funcionario.editarFuncionario();
                    break;
                case 2:
                    funcionario.buscarFuncionario();
                    break;
                case 3:
                    funcionario.listarFuncionarios();
                    break;
                case 4:
                    funcionario.removerFuncionario();
                    break;
                case 5:
                    funcionario.visualizarFuncionario();
                    break;
                case 6:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
        System.out.println("Programa encerrado.");
    }

    public void fazerLogin() {
        verificaCadastro();
        boolean loginBemSucedido = false;
        int tentativas = 0;

        while (!loginBemSucedido) {
            System.out.print("Email: ");
            String email = ent.nextLine();
            System.out.print("Senha: ");
            String senha = ent.nextLine();

            for (Aluno a : aluno.getAlunos()) {
                if (a.login(email, senha)) {
                    System.out.println("Login bem-sucedido! Bem-vindo(a), " + a.getNome() + "!");
                    loginBemSucedido = true;
                    menuAluno();
                    return;
                }
            }

            for (Funcionario f : funcionario.getFuncionario()) {
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
        ent.close();
    }
}
