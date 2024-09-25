
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Emprestimo {
    private String dataEmprestimo;
    private String dataDevolucao;
  

    
    private static List<Emprestimo> emprestimos = new ArrayList<>();

    
    public Emprestimo(String dataEmprestimo, String dataDevolucao) {
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        
    }

    // Get e Set
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

     
   


//     //////////////////////////////////////
//     // Classe Emprestimo
// public class Emprestimo {
//     // Atributos
//     private String dataEmprestimo;
//     private String dataDevolucao;
//     private Aluno aluno;
//     private LivroBiblioteca livro;

//     // Construtor
//     public Emprestimo(String dataEmprestimo, String dataDevolucao, Aluno aluno, LivroBiblioteca livro) {
//         this.dataEmprestimo = dataEmprestimo;
//         this.dataDevolucao = dataDevolucao;
//         this.aluno = aluno;
//         this.livro = livro;
//     }

    // Métodos existentes...

    // // Adicionando o método getAluno()
    // public Aluno getAluno() {
    //     return this.aluno;
    // }

    // // Adicionando o método getLivro()
    // public LivroBiblioteca getLivro() {
    //     return this.livro;
    // }

    // Outros métodos...
//}
//////////////////////////////////

// Método para remover um empréstimo pela matricula do aluno
public void removerEmprestimo(String nomeDoAluno, List<Emprestimo> emprestimos) {
    // Procurando o empréstimo na lista
    Aluno emprestimoRemover = null;
    for (Aluno emprestimo : emprestimos) {
        // Aqui chamamos o método getAluno() para obter o aluno associado ao empréstimo
        if (emprestimo.getMatricula() != null && emprestimo.getMatricula().equals(nomeDoAluno)) {
            emprestimoRemover = emprestimo;
            break;
        }
    }

    // Se o empréstimo foi encontrado, removê-lo da lista
    if (emprestimoRemover != null) {
        emprestimos.remove(emprestimoRemover);
        System.out.println("Empréstimo removido para o aluno: " + nomeDoAluno);
    } else {
        System.out.println("Nenhum empréstimo encontrado para o aluno: " + nomeDoAluno);
    }
}

/////////////////////

    // Método para criar um novo empréstimo
    public void criarEmprestimo(Aluno aluno, Livro livro) {
        // Verifica se o livro está disponível
        if (livro.getQuantidadeDisponivel() > 0) {
            // Cria um novo empréstimo
            Emprestimo novoEmprestimo = new Emprestimo("dataEmprestimo", "dataDevolucao");
            emprestimos.add(novoEmprestimo);
            // Reduz a quantidade disponível do livro
            livro.setQuantidadeDisponivel(livro.getQuantidadeDisponivel() - 1);
            System.out.println("Empréstimo criado para o aluno " + aluno.getNome() + " do livro " + livro.getTitulo());
        } else {
            System.out.println("O livro " + livro.getTitulo() + " não está disponível para empréstimo.");
        }
    }

    // Método para remover um empréstimo pelo nome do aluno
    // public void removerEmprestimo(String nomeDoAluno, List<Emprestimo> emprestimos) {
    // // Procurando o empréstimo na lista
    // Emprestimo emprestimoRemover = null;
    // for (Emprestimo emprestimo : emprestimos) {
    //     if (emprestimo.getAluno().getNome().equals(nomeDoAluno)) {
    //         emprestimoRemover = emprestimo;
    //         break;
    //     }
    // }

    // // Verificando se o empréstimo foi encontrado
    // if (emprestimoRemover != null) {
    //     emprestimos.remove(emprestimoRemover);
    //     System.out.println("Empréstimo do aluno " + nomeDoAluno + " removido com sucesso.");
    // } else {
    //     System.out.println("Nenhum empréstimo encontrado para o aluno: " + nomeDoAluno);
    // }
    // }

    // Método para editar os detalhes de um empréstimo
    public void editarEmprestimo(String nomeDoAluno, List<Emprestimo> emprestimos) {
    Emprestimo emprestimoEditar = null;
     // Solicitando novas informações
    Scanner scanner = new Scanner(System.in);

    // Procurando o empréstimo na lista
    for (Emprestimo emprestimo : emprestimos) {
        if (emprestimo.getAluno().getNome().equals(nomeDoAluno)) {
            emprestimoEditar = emprestimo;
            break;
        }
    }

    // Verificando se o empréstimo foi encontrado
    if (emprestimoEditar != null) {
        // Exibindo detalhes atuais
        System.out.println("Detalhes atuais do empréstimo:");
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

        // Aqui você pode adicionar mais edições, se necessário

        System.out.println("Empréstimo editado com sucesso.");
    } else {
        System.out.println("Nenhum empréstimo encontrado para o aluno: " + nomeDoAluno);
    }
    }

    // Método para buscar um empréstimo pela data de empréstimo
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

    // Método para listar todos os empréstimos
    public void listarEmprestimos(List<Emprestimo> emprestimos) {
    if (emprestimos.isEmpty()) {
        System.out.println("Nenhum empréstimo encontrado.");
        return; // Saia do método se não houver empréstimos
    }

    System.out.println("Listando todos os empréstimos:");
    for (Emprestimo emprestimo : emprestimos) {
        // Exibe detalhes de cada empréstimo
        emprestimo.visualizarEmprestimo();
        System.out.println(); // Linha em branco para separação
    }
    }
    
    // Método para visualizar os detalhes de um empréstimo
    public void visualizarEmprestimo() {
    System.out.println("=== Detalhes do Empréstimo ===");
    System.out.println("Data do Empréstimo: " + this.dataEmprestimo);
    System.out.println("Data de Devolução: " + this.dataDevolucao);
    
    // Verifica se o aluno e o livro estão disponíveis antes de exibir
    if (this.aluno != null) {
        System.out.println("Aluno: " + this.aluno.getNome());
    } else {
        System.out.println("Aluno não associado a este empréstimo.");
    }

    if (this.livro != null) {
        System.out.println("Livro: " + this.livro.getTitulo());
    } else {
        System.out.println("Livro não associado a este empréstimo.");
    }
    
    System.out.println("==============================");
    }

    // Método para listar os empréstimos que estão atrasados
    public void listarEmprestimosAtrasados() {
    System.out.println("=== Listando Empréstimos Atrasados ===");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    LocalDate hoje = LocalDate.now();

    boolean temAtrasados = false; // Flag para verificar se existem empréstimos atrasados

    for (Emprestimo emprestimo : emprestimos) {
        // Convertendo a data de devolução para LocalDate
        LocalDate dataDevolucao = LocalDate.parse(emprestimo.getDataDevolucao(), formatter);
        
        // Verifica se a data de devolução é anterior à data atual
        if (dataDevolucao.isBefore(hoje)) {
            emprestimo.visualizarEmprestimo(); // Chama o método para visualizar os detalhes do empréstimo
            temAtrasados = true; // Altera a flag para true se um empréstimo atrasado for encontrado
        }
    }

    if (!temAtrasados) {
        System.out.println("Nenhum empréstimo atrasado encontrado.");
    }
    System.out.println("=====================================");
    }
}
