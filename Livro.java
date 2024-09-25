import java.util.ArrayList;
import java.util.Scanner;

public class Livro {
    private String titulo;
    private String autor;
    private int ano;
    private int quantidadeDisponivel;
    private boolean reservado;

    private static ArrayList<Livro> livros = new ArrayList<>();

    public Livro (String titulo, String autor, int ano, int quantidadeDisponivel) {
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    //get set

    public String getTitulo (){
        return titulo;
    }

    public void setTilulo (String titulo) {
        this.titulo = titulo;
    }

    public String getAutor () {
        return autor;
    }

    public void setAutor (String autor) {
        this.autor = autor;
    }

    public int getAno () {
        return ano;
    }

    public void setAno (int ano) {
        this.ano = ano;
    }

    public int getQuantidadeDisponivel () {
        return quantidadeDisponivel; 
    }

    public void setQuantidadeDisponivel (int quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public boolean isReservado() {
        return reservado;
    }

    public void setReservado(boolean reservado) {
        this.reservado = reservado;
    }

    
     // Metodo para adicionar um livro à biblioteca
     public void adicionarLivro() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o título do livro: ");
        String titulo = scanner.nextLine();

        System.out.print("Digite o autor do livro: ");
        String autor = scanner.nextLine();

        System.out.print("Digite o ano do livro: ");
        int ano = scanner.nextInt();

        System.out.print("Digite a quantidade disponível: ");
        int quantidadeDisponivel = scanner.nextInt();

        System.out.print("O livro está reservado? (true/false): ");
        boolean reservado = scanner.nextBoolean();

       
        Livro novoLivro = new Livro(titulo, autor, ano, quantidadeDisponivel);

        livros.add(novoLivro);

        // Exibindo a confirmação
        System.out.println("\nLivro adicionado com sucesso!");
        System.out.println("Título: " + novoLivro.getTitulo());
        System.out.println("Autor: " + novoLivro.getAutor());
        System.out.println("Ano: " + novoLivro.getAno());
        System.out.println("Quantidade Disponível: " + novoLivro.getQuantidadeDisponivel());
        System.out.println("Reservado: " + (novoLivro.isReservado() ? "Sim" : "Não"));
    }

    // Metodo para listar todos os livros da biblioteca
    public void listarLivros() {
        if (livros.isEmpty()) {
            System.out.println("Não há livros cadastrados.");
        } else {
            System.out.println("Livros cadastrados:");
            for (Livro livro : livros) {
                System.out.println("Título: " + livro.getTitulo() + ", Autor: " + livro.getAutor() +
                                   ", Ano: " + livro.getAno() + ", Quantidade: " + livro.getQuantidadeDisponivel() +
                                   ", Reservado: " + (livro.isReservado() ? "Sim" : "Não"));
            }
        }
    }

    // Metodo para remover um livro da biblioteca, levando em consideração se está reservado
    public void removerLivro(String titulo, boolean reservado) {
        if (!reservado) {
            // Remover livro
            System.out.println("Livro removido: " + titulo);
        } else {
            System.out.println("O livro " + titulo + " está reservado e não pode ser removido.");
        }
    }

    // Metodo para editar as informações de um livro
    public void editarLivro() {
       
    }

    // Metodo para buscar um livro
    public void buscarLivro() {
        
       
    }

    // Metodo para visualizar os detalhes de um livro específico
    public void visualizar() {
        // Exibe os detalhes do livro
        System.out.println("Título: " + this.titulo);
        System.out.println("Autor: " + this.autor);
        System.out.println("Ano: " + this.ano);
        System.out.println("Quantidade Disponível: " + this.quantidadeDisponivel);
    }

    // Método para listar os livros que estão emprestados
    public void listarLivrosEmprestados() {
        // Lógica para listar livros que estão emprestados. fazer depois
        System.out.println("Listando livros emprestados.");
    }

    // Método para listar os livros que estão fora de estoque
    public void listarLivrosForaDeEstoque() {
        if (quantidadeDisponivel == 0) {
            System.out.println("Livro fora de estoque: " + this.titulo);
        }
    }

}
