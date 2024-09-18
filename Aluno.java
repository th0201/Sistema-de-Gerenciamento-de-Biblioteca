import java.util.Scanner;

class Aluno extends Pessoa {
    private String matricula;
    private String curso;
    Scanner ent = new Scanner(System.in);

    public void cadastrarAluno() {
        super.cadastrarPessoa();
        System.out.println("MATRÍCULA:");
        this.matricula = scanner.nextLine();
        System.out.println("CURSO:");
        this.curso = scanner.nextLine();
    }

    public void editarAluno() {
    }

    public void buscarAluno() {
    }

    public void listarAlunos() {
    }

    public void removerAluno() {
    }

    public void visualizarAluno() {
    }
}