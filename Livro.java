package projeto;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Livro {
    private String titulo;
    private String autor;
    private int ano;
    private int quantidadeDisponivel;
    private String corredor;
    private String prateleira;
    Scanner scanner = new Scanner(System.in);
    protected static List<Livro> livro = new ArrayList<>();

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public void setQuantidadeDisponivel(int quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public String getCorredor() {
        return corredor;
    }

    public void setCorredor(String corredor) {
        this.corredor = corredor;
    }

    public String getPrateleira() {
        return prateleira;
    }

    public void setPrateleira(String prateleira) {
        this.prateleira = prateleira;
    }

    public void cadastrarLivro() {
        System.out.println("\n------------------------------");
        System.out.println(" PÁGINA DE CADASTRO DE LIVROS");
        System.out.println("------------------------------");

        System.out.print("TÍTULO: ");
        this.titulo = scanner.nextLine();
        System.out.print("AUTOR: ");
        this.autor = scanner.nextLine();
        System.out.print("ANO DA EDIÇÃO: ");
        this.ano = scanner.nextInt();
        System.out.print("QUANTIDADE DISPONÍVEL: ");
        this.quantidadeDisponivel = scanner.nextInt();
        scanner.nextLine();
        System.out.print("CORREDOR: ");
        this.corredor = scanner.nextLine();
        System.out.print("PRATELEIRA: ");
        this.prateleira = scanner.nextLine();
        livro.add(this);

        registrarEmArquivo();
    }

    private void registrarEmArquivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("livros.txt", true))) {
            writer.write("TÍTULO: " + this.titulo);
            writer.newLine();
            writer.write("AUTOR: " + this.autor);
            writer.newLine();
            writer.write("ANO DA EDIÇÃO: " + this.ano);
            writer.newLine();
            writer.write("QUANTIDADE DISPONÍVEL: " + this.quantidadeDisponivel);
            writer.newLine();
            writer.write("CORREDOR: " + this.corredor);
            writer.newLine();
            writer.write("PRATELEIRA: " + this.prateleira);
            writer.newLine();
            writer.write("----------------------------------");
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao gravar informações no arquivo: " + e.getMessage());
        }
    }

    public void listarLivro() {
        if (livro.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
        } else {
            System.out.println("\n-----------------------------");
            System.out.println("LISTA DE LIVROS CADASTRADOS:");
            System.out.println("-----------------------------");
            for (int i = 0; i < livro.size(); i++) {
                System.out.println("TÍTULO: " + livro.get(i).getTitulo());
                System.out.println("AUTOR: " + livro.get(i).getAutor());
                System.out.println("ANO DA EDIÇÃO: " + livro.get(i).getAno());
                System.out.println("QUANTIDADE DISPONÍVEL: " + livro.get(i).getQuantidadeDisponivel());
                System.out.println("CORREDOR: " + livro.get(i).getCorredor());
                System.out.println("PRATELEIRA: " + livro.get(i).getPrateleira());
                System.out.println("----------------------------------");
            }
        }
    }

    public void editarLivro() {
        System.out.println("Digite o título do livro a ser editado: ");
        String titulo = scanner.nextLine();
        for (Livro l : livro) {
            if (l.getTitulo().equals(titulo)) {
                System.out.println("Editar o livro " + l.getTitulo());
                System.out.println("TÍTULO: ");
                l.titulo = scanner.nextLine();
                System.out.println("AUTOR: ");
                l.autor = scanner.nextLine();
                System.out.println("ANO DA EDIÇÃO: ");
                l.ano = scanner.nextInt();
                System.out.println("QUANTIDADE DISPONÍVEL: ");
                l.quantidadeDisponivel = scanner.nextInt();
                scanner.nextLine();
                System.out.println("CORREDOR: ");
                l.corredor = scanner.nextLine();
                System.out.println("PRATELEIRA: ");
                l.prateleira = scanner.nextLine();
                System.out.println("Livro editado com sucesso!");
                return;
            }
        }
        System.out.println("Livro não encontrado!");
    }

    public void visualizarLivro() {
        System.out.println("Digite o título do livro que deseja visualizar: ");
        String titulo = scanner.nextLine();
        for (Livro l : livro) {
            if (l.getTitulo().equals(titulo)) {
                System.out.println("TÍTULO: " + l.getTitulo());
                System.out.println("AUTOR: " + l.getAutor());
                System.out.println("ANO DA EDIÇÃO: " + l.getAno());
                System.out.println("QUANTIDADE DISPONÍVEl: " + l.getQuantidadeDisponivel());
                System.out.println("CORREDOR: " + l.getCorredor());
                System.out.println("PRATELEIRA: " + l.getPrateleira());
                System.out.println("----------------------------------");
                return;
            }
        }
        System.out.println("Livro não encontrado.");
    }

    public void buscarLivro() {
        System.out.println("Digite o título do livro para buscar: ");
        String titulo = scanner.nextLine();
        for (Livro l : livro) {
            if (l.getTitulo().equals(titulo)) {
                System.out.println("TÍTULO: " + l.getTitulo());
                System.out.println("AUTOR: " + l.getAutor());
                System.out.println("ANO DA EDIÇÃO: " + l.getAno());
                System.out.println("QUANTIDADE DISPONÍVEl: " + l.getQuantidadeDisponivel());
                System.out.println("CORREDOR: " + l.getCorredor());
                System.out.println("PRATELEIRA: " + l.getPrateleira());
                System.out.println("----------------------------------");
                return;
            }
        }
        System.out.println("Livro não encontrado.");
    }

    public void removerLivro() {
        System.out.println("Digite o título do livro a ser removido: ");
        String titulo = scanner.nextLine();
        for (int i = 0; i < livro.size(); i++) {
            if (livro.get(i).getTitulo().equals(titulo)) {
                livro.remove(i);
                System.out.println("Livro removido com sucesso!");
                return;
            }
        }
        System.out.println("Livro não encontrado.");
    }

    public void listarLivrosForaDeEstoque() {
        System.out.println("\nLIVROS FORA DE ESTOQUE:");
        boolean encontrou = false;
        for (Livro l : livro) {
            if (l.getQuantidadeDisponivel() == 0) {
                System.out.println("TÍTULO: " + l.getTitulo());
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhum livro está fora de estoque.");
        }
    }

    public String localizacaoDoLivro() {
        System.out.println("Digite o título do livro para localizar: ");
        String titulo = scanner.nextLine();
        for (Livro l : livro) {
            if (l.getTitulo().equals(titulo)) {
                System.out.println("CORREDOR: " + l.getCorredor() + "\nPRATELEIRA: " + l.getPrateleira());
            }
        }
        return ("Livro não encontrado!");
    }
}
