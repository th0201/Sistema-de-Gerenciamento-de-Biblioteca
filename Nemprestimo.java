import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Nemprestimo {
    private String dataEmprestimo;
    private String dataDevolucao;
    private AlunoCSV aluno;
    private Livro livro;

    private static final String ARQUIVO_EMPRESTIMOS = "emprestimos.csv"; // Nome do arquivo CSV
    private static List<Emprestimo> emprestimos = new ArrayList<>();

    public Nemprestimo(String dataEmprestimo, String dataDevolucao, AlunoCSV aluno, Livro livro) {
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

    public AlunoCSV getAluno() {
        return aluno;
    }

    public Livro getLivro() {
        return livro;
    }

    public static List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    // Salva os empréstimos no arquivo CSV
    public static void salvarEmprestimosEmArquivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_EMPRESTIMOS))) {
            for (Emprestimo emprestimo : emprestimos) {
                String linha = emprestimo.getDataEmprestimo() + "," +
                               emprestimo.getDataDevolucao() + "," +
                               emprestimo.getAluno().getNome() + "," +
                               emprestimo.getLivro().getTitulo();
                writer.write(linha);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar os empréstimos no arquivo.");
        }
    }

    // Carrega os empréstimos do arquivo CSV
    public static void carregarEmprestimosDeArquivo() {
        emprestimos.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_EMPRESTIMOS))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                String dataEmprestimo = dados[0];
                String dataDevolucao = dados[1];
                String nomeAluno = dados[2];
                String tituloLivro = dados[3];

                // Aqui você precisaria encontrar o aluno e o livro pelos seus nomes/títulos
                AlunoCSV aluno = new AlunoCSV(nomeAluno); // Simplificado
                Livro livro = new Livro(tituloLivro); // Simplificado

                Emprestimo emprestimo = new Emprestimo(dataEmprestimo, dataDevolucao, aluno, livro);
                emprestimos.add(emprestimo);
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar os empréstimos do arquivo.");
        }
    }

    public void criarEmprestimo(AlunoCSV aluno, Livro livro) {
        if (livro.getQuantidadeDisponivel() > 0) {
            System.out.println("========================================================================\n");
            Emprestimo novoEmprestimo = new Emprestimo(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    LocalDate.now().plusDays(14).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    aluno, livro);
            emprestimos.add(novoEmprestimo);
            livro.setQuantidadeDisponivel(livro.getQuantidadeDisponivel() - 1);
            System.out.println("Empréstimo criado para o aluno " + aluno.getNome() + " do livro " + livro.getTitulo());

            // Salva o novo empréstimo no arquivo
            salvarEmprestimosEmArquivo();

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
            salvarEmprestimosEmArquivo(); // Atualiza o arquivo após remover
            System.out.println("Empréstimo removido para o aluno: " + nomeDoAluno);
            System.out.println("===================================================================");
        } else {
            System.out.println("Nenhum empréstimo encontrado para o aluno: " + nomeDoAluno);
        }
    }

    // Método para editar
    public void editarEmprestimo(String nomeDoAluno, List<Emprestimo> emprestimos) {
        Emprestimo emprestimoEditar = null;
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n================================================================================\n");
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getAluno().getNome().equals(nomeDoAluno)) {
                emprestimoEditar = emprestimo;
                break;
            }
        }

        if (emprestimoEditar != null) {
            emprestimoEditar.visualizarEmprestimo();

            System.out.print("Digite a nova data de empréstimo (ou pressione Enter para manter): ");
            String novaDataEmprestimo = scanner.nextLine();
            if (!novaDataEmprestimo.isEmpty()) {
                emprestimoEditar.setDataEmprestimo(novaDataEmprestimo);
            }

            System.out.print("Digite a nova data de devolução (ou pressione Enter para manter): ");
            String novaDataDevolucao = scanner.nextLine();
            if (!novaDataDevolucao.isEmpty()) {
                emprestimoEditar.setDataDevolucao(novaDataDevolucao);
            }

            salvarEmprestimosEmArquivo(); // Atualiza o arquivo após a edição
            System.out.println("Empréstimo editado com sucesso.");
        } else {
            System.out.println("Nenhum empréstimo encontrado para o aluno: " + nomeDoAluno);
        }
    }

     // Metodo para buscar um empréstimo pela data de empréstimo
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
        System.out.println("Data do Empréstimo: " + this.dataEmprestimo);
        System.out.println("Data de Devolução: " + this.dataDevolucao);
        System.out.println("Aluno: " + this.aluno.getNome());
        System.out.println("Livro: " + this.livro.getTitulo());
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
