
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Emprestimo {
    private String dataEmprestimo;
    private String dataDevolucao;
    private Aluno aluno;
    private Livro livro;

    private static List<Emprestimo> emprestimos = new ArrayList<>();

    public Emprestimo(String dataEmprestimo, String dataDevolucao, Aluno aluno, Livro livro) {
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.aluno = aluno;
        this.livro = livro;
    }

    public String getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(String dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public String getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(String dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Aluno getAluno() {
        return aluno;
    }    

    public Livro getLivro() {
        return livro;
    }

    public static List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public void criarEmprestimo(Aluno aluno, Livro livro) {
        if (livro.getQuantidadeDisponivel() > 0) {
            System.out.println("========================================================================\n");
            Emprestimo novoEmprestimo = new Emprestimo("dataEmprestimo", "dataDevolucao", aluno, livro);
            emprestimos.add(novoEmprestimo);
            livro.setQuantidadeDisponivel(livro.getQuantidadeDisponivel() - 1);
            System.out.println("Empréstimo criado para o aluno " + aluno.getNome() + " do livro " + livro.getTitulo());
            System.out.println("\n=========================================================================\n");
        } else {
            System.out.println("O livro " + livro.getTitulo() + " não está disponível para empréstimo.");
            System.out.println("\n=========================================================================\n");
        }
    }

    public void removerEmprestimo(String nomeDoAluno) {
        Emprestimo emprestimoRemover = null;
        for (Emprestimo emprestimo : emprestimos) {
            System.out.println("==================================================================\n");
            if (emprestimo.getAluno() != null && emprestimo.getAluno().getNome().equals(nomeDoAluno)) {
                emprestimoRemover = emprestimo;
                break;
            }
        }

        if (emprestimoRemover != null) {
            emprestimos.remove(emprestimoRemover);
            System.out.println("-------------------------------------------------------------------\n");
            System.out.println("\nEmpréstimo removido para o aluno: \n" + nomeDoAluno);
            System.out.println("\n-----------------------------------------------------------------\n");
            System.out.println("\n=================================================================\n");
        } else {
            System.out.println("-------------------------------------------------------------------\n");
            System.out.println("\nNenhum empréstimo encontrado para o aluno: \n" + nomeDoAluno);
            System.out.println("-------------------------------------------------------------------\n");
            System.out.println("\n=================================================================\n");
        }
    }

    //Metodo para editar
    public void editarEmprestimo(String nomeDoAluno, List<Emprestimo> emprestimos) {
    Emprestimo emprestimoEditar = null;
     // Solicitando novas informações
    Scanner scanner = new Scanner(System.in);

    // Procurando o empréstimo na lista
    System.out.println("\n================================================================================\n");
    for (Emprestimo emprestimo : emprestimos) {
        if (emprestimo.getAluno().getNome().equals(nomeDoAluno)) {
            emprestimoEditar = emprestimo;
            break;
        }
    }

    // Verificando se o empréstimo foi encontrado
    if (emprestimoEditar != null) {
        // Exibindo detalhes atuais
        System.out.println("\nDetalhes atuais do empréstimo:\n");
        emprestimoEditar.visualizarEmprestimo();

        System.out.print("Digite a nova data de empréstimo (ou pressione Enter para manter): \n");
        String novaDataEmprestimo = scanner.nextLine();
        if (!novaDataEmprestimo.isEmpty()) {
            emprestimoEditar.setDataEmprestimo(novaDataEmprestimo);
        }

        System.out.print("Digite a nova data de devolução (ou pressione Enter para manter): \n");
        String novaDataDevolucao = scanner.nextLine();
        if (!novaDataDevolucao.isEmpty()) {
            emprestimoEditar.setDataDevolucao(novaDataDevolucao);
        }
    
        System.out.println("\nEmpréstimo editado com sucesso.\n");
        System.out.println("\n=============================================================================\n");
    } else {
        System.out.println("\nNenhum empréstimo encontrado para o aluno: " + nomeDoAluno);
        System.out.println("\n==============================================================================\n");
    }
    }

    //Metodo para buscar um empréstimo pela data de empréstimo
    public void buscarEmprestimo(String dataEmprestimo) {
    for (Emprestimo emprestimo : emprestimos) {
        // Exemplo: buscar pelo atributo dataEmprestimo
        if (emprestimo.getDataEmprestimo().equals(dataEmprestimo)) {
            System.out.println("Empréstimo encontrado com a data: " + dataEmprestimo);
            emprestimo.visualizarEmprestimo(); // Exibe os detalhes do empréstimo encontrado
            return;
        }
    }
    System.out.println("Empréstimo não encontrado com a data: " + dataEmprestimo);
    }


    public void visualizarEmprestimo() {
        System.out.println("=========================================================================");
        System.out.println("\n=== Detalhes do Empréstimo ===\n");
        System.out.println("\nData do Empréstimo: " + this.dataEmprestimo);
        System.out.println("\nData de Devolução: " + this.dataDevolucao);
        System.out.println("\nAluno: " + (this.aluno != null ? this.aluno.getNome() : "Aluno não associado"));
        System.out.println("\nLivro: " + (this.livro != null ? this.livro.getTitulo() : "Livro não associado\n"));
        System.out.println("==========================================================================");
    }

    public void listarEmprestimosAtrasados() {
        System.out.println("==========================================================================\n");
        System.out.println("\n=== Listando Empréstimos Atrasados ===\n");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate hoje = LocalDate.now();

        boolean temAtrasados = false;

        for (Emprestimo emprestimo : emprestimos) {
            LocalDate dataDevolucao = LocalDate.parse(emprestimo.getDataDevolucao(), formatter);
            if (dataDevolucao.isBefore(hoje)) {
                System.out.println("---------------------------------------------------------------\n");
                emprestimo.visualizarEmprestimo();
                temAtrasados = true;
                System.out.println("\n---------------------------------------------------------------\n");
            }
        }

        if (!temAtrasados) {
            System.out.println("Nenhum empréstimo atrasado encontrado.");
        }
        System.out.println("==========================================================================");
    }

    public void listarEmprestimos() {
        if (emprestimos.isEmpty()) {
            System.out.println("===================================================================");
            System.out.println("\nNenhum empréstimo encontrado.\n");
            System.out.println("===================================================================");
            return;
        }
        
        System.out.println("=======================================================================");
        System.out.println("\nListando todos os empréstimos:\n");
        for (Emprestimo emprestimo : emprestimos) {
            System.out.println("-------------------------------------------------------------------");
            emprestimo.visualizarEmprestimo();
            System.out.println("-------------------------------------------------------------------");
            System.out.println("\n===================================================================");
            System.out.println(); // Linha em branco para separação
        }
    }
}
