import java.util.Scanner;

public class SistemaDeBiblioteca {
    private static Scanner scanner = new Scanner(System.in);
    private static GerenciadorDeUsuarios usuario = new GerenciadorDeUsuarios();
    private static Livro livro = new Livro();
    private static Emprestimo emprestimo = new Emprestimo();

    public static void main(String[] args) {
        boolean continuar;
        System.out.println("Faça login ou se cadastre em nosso sistema.");
        do {
            System.out.println("\n==== Menu Principal ====");
            System.out.println("1. Se cadastrar no sistema");
            System.out.println("2. Fazer login");
            System.out.println("3. Menu aluno");
            System.out.println("4. Menu funcionário");
            System.out.println("5. Menu livro");
            System.out.println("6. Menu emprestimo");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            continuar = true; // Presume que o usuário quer continuar no menu

            switch (opcao) {
                case 1:
                    usuario.realizaCadastro();
                    break;
                case 2:
                    usuario.fazerLogin();
                    break;
                case 3: 
                    usuario.menuAluno();
                    break;
                case 4:
                    usuario.menuFuncionario();
                    break;
                case 5:
                    livro.menuLivro();
                    break;
                case 6:
                    emprestimo.menuEmprestimo();
                    break;
                case 7:
                    continuar = false;
                    System.out.println("Programa encerrado.");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (continuar);
    }
}
