import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;

public class Emprestimo {
    private String dataEmprestimo;
    private String dataDevolucao;
    private Aluno aluno;
    private Livro livro;
    
    Scanner scanner = new Scanner(System.in);
    SistemaDeBiblioteca sistemaDeBiblioteca = new SistemaDeBiblioteca();
    //private static Livro livro = new Livro();

    private static List<Emprestimo> emprestimos = new ArrayList<>();
    private final String CAMINHO_ARQUIVO = "emprestimos.txt";

    public Emprestimo() {

    }

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

    public void salvarEmprestimosArquivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_ARQUIVO, true))) {
            for (Emprestimo emprestimo : emprestimos) {
                writer.write(emprestimo.getDataEmprestimo() + ";" +
                        emprestimo.getDataDevolucao() + ";" +
                        emprestimo.getAluno().getNome() + ";" +
                        emprestimo.getLivro().getTitulo());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar empréstimos no arquivo: " + e.getMessage());
        }
    }

    public void carregarEmprestimosArquivo() {
        emprestimos.clear(); // Limpa a lista antes de carregar os dados do arquivo
        try (BufferedReader reader = new BufferedReader(new FileReader(CAMINHO_ARQUIVO))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length == 4) {
                    String dataEmprestimo = dados[0];
                    String dataDevolucao = dados[1];
                    aluno.setNome(dados[2]);
                    livro.setTitulo(dados[3]);
                    Emprestimo emprestimo = new Emprestimo(dataEmprestimo, dataDevolucao, aluno, livro);
                    emprestimos.add(emprestimo);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar empréstimos do arquivo: " + e.getMessage());
        }
    }

    public void removerEmprestimo() {
        System.out.print("Digite o nome do aluno para remover o empréstimo: ");
        String nomeDoAlunoRemover = scanner.nextLine();

        Emprestimo emprestimoRemover = null;
        for (Emprestimo emprestimo : emprestimos) {
            System.out.println("==================================================================\n");
            if (emprestimo.getAluno() != null && emprestimo.getAluno().getNome().equals(nomeDoAlunoRemover)) {
                emprestimoRemover = emprestimo;
                break;
            }
        }

        if (emprestimoRemover != null) {
            emprestimos.remove(emprestimoRemover);
            System.out.println("-------------------------------------------------------------------\n");
            System.out.println("\nEmpréstimo removido para o aluno: " + nomeDoAlunoRemover);
            System.out.println("-------------------------------------------------------------------\n");
            System.out.println("==================================================================\n");
        } else {
            System.out.println("-------------------------------------------------------------------\n");
            System.out.println("\nNenhum empréstimo encontrado para o aluno: " + nomeDoAlunoRemover);
            System.out.println("-------------------------------------------------------------------\n");
            System.out.println("==================================================================\n");
        }
    }

    // Método para editar
    public void editarEmprestimo(List<Emprestimo> emprestimos) {
        System.out.print("Digite o nome do aluno: ");
        String nomeDoAluno = scanner.nextLine();

        Emprestimo emprestimoEditar = null;

        // Procurando o empréstimo na lista
        System.out.println("\n================================================================================\n");
        for (Emprestimo emprestimo : emprestimos) {
            // Verifique se o aluno não é nulo antes de chamar getNome()
            if (emprestimo.getAluno() != null && emprestimo.getAluno().getNome().equals(nomeDoAluno)) {
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

    public void buscarEmprestimo() {
        System.out.print("Digite a data do empréstimo (formato dd/MM/yyyy): ");
        String dataEmprestimo = scanner.nextLine(); // Ler a entrada do usuário

        boolean encontrado = false; // Variável para verificar se o empréstimo foi encontrado

        for (Emprestimo emprestimo : emprestimos) {
            // Verifique se a data do empréstimo não é nula antes de chamar equals()
            if (emprestimo.getDataEmprestimo() != null && emprestimo.getDataEmprestimo().equals(dataEmprestimo)) {
                System.out.println("Empréstimo encontrado com a data: " + dataEmprestimo);
                emprestimo.visualizarEmprestimo(); // Exibe os detalhes do empréstimo encontrado
                encontrado = true; // Define que o empréstimo foi encontrado
                break; // Sai do loop ao encontrar o empréstimo
            }
        }

        if (!encontrado) {
            System.out.println("Empréstimo não encontrado com a data: " + dataEmprestimo);
        }
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

    public void devolvendoEmprestimo() {
        System.out.print("Digite o nome do aluno: ");
        String nomeDoAlunoDevolver = scanner.nextLine();

        System.out.print("Digite o título do livro: ");
        String tituloDoLivro = scanner.nextLine();

        Emprestimo emprestimoParaDevolucao = null;

        // Procurando o empréstimo na lista
        for (Emprestimo emprestimo : emprestimos) {
            // Verifique se o aluno não é nulo antes de chamar getNome()
            if (emprestimo.getAluno() != null && emprestimo.getAluno().getNome().equals(nomeDoAlunoDevolver) &&
                    emprestimo.getLivro() != null && emprestimo.getLivro().getTitulo().equals(tituloDoLivro)) {
                emprestimoParaDevolucao = emprestimo;
                break;
            }
        }

        // Verificando se o empréstimo foi encontrado
        if (emprestimoParaDevolucao != null) {
            // Aumentando a quantidade do livro devolvido
            Livro livro = emprestimoParaDevolucao.getLivro();
            livro.setQuantidadeDisponivel(livro.getQuantidadeDisponivel() + 1);

            // Removendo o empréstimo da lista de empréstimos ativos
            emprestimos.remove(emprestimoParaDevolucao);
            System.out.println("Empréstimo devolvido com sucesso para o aluno: " + nomeDoAlunoDevolver);
            System.out.println("Livro devolvido: " + tituloDoLivro);
        } else {
            System.out.println("Empréstimo não encontrado para o aluno: " + nomeDoAlunoDevolver + " com o livro: "
                    + tituloDoLivro);
        }
    }

    public void menuEmprestimo() {
        boolean executando;
        do {
            System.out.println("\n==== Menu Empréstimo ====");
            System.out.println("1. Cadastrar Empréstimo");
            System.out.println("2. Remover Empréstimo");
            System.out.println("3. Editar Empréstimo");
            System.out.println("4. Buscar Empréstimo");
            System.out.println("5. Listar Empréstimos");
            System.out.println("6. Visualizar Empréstimo");
            System.out.println("7. Listar Empréstimos Atrasados");
            System.out.println("8. Devolver Empréstimo");
            System.out.println("9. Menu Livro");
            System.out.println("0. Voltar ao Menu Funcionário");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();
            executando = true;

            switch (opcao) {
                case 1:
                    criarEmprestimo(aluno, livro);
                    break;
                case 2:
                    removerEmprestimo();
                    break;
                case 3:
                    editarEmprestimo(Emprestimo.getEmprestimos());
                    break;
                case 4:
                    buscarEmprestimo();
                    break;
                case 5:
                    listarEmprestimos();
                    break;
                case 6:
                    visualizarEmprestimo();
                    break;
                case 7:
                    listarEmprestimosAtrasados();
                    break;
                case 8:
                    devolvendoEmprestimo();
                    break;
                case 9:
                    Livro livro = new Livro();
                    livro.menuLivro();
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
}
