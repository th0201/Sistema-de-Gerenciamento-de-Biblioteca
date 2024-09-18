package projeto;
import java.util.ArrayList;
import java.util.Scanner;

abstract class Pessoa {
    protected String nome;
    protected int idade;
    protected String cpf;
    protected String senha;
    protected String email;
    protected boolean autenticado = false;
    Scanner ent = new Scanner(System.in);
    protected static ArrayList<Pessoa> pessoa = new ArrayList<>();

    public boolean login(String email, String senha){
        return this.email.equals(email) && this.senha.equals(senha);
    }

    public void cadastrarPessoa(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("NOME:");
        this.nome = scanner.nextLine();
        System.out.println("IDADE:");
        this.idade = scanner.nextInt();
        scanner.nextLine();
        System.out.println("CPF:");
        this.cpf = scanner.nextLine();
        System.out.println("EMAIL:");
        this.email = scanner.nextLine();
        System.out.println("SENHA:");
        this.senha = scanner.nextLine();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    

    public void listarPessoas() {
        if (pessoa.isEmpty()) {
            System.out.println("Nenhuma pessoa cadastrada.");
        } else {
            System.out.println("Lista de Pessoas:");
            for (int i = 0; i < pessoa.size(); i++) {
                System.out.println("NOME: " + pessoa.get(i).getNome());
                System.out.println("IDADE: " + pessoa.get(i).getIdade());
                System.out.println("CPF: " + pessoa.get(i).getCpf());
                System.out.println("EMAIL: " + pessoa.get(i).getEmail());
                System.out.println("Senha: " + pessoa.get(i).getSenha());
            }
        }
    }

    public void editarPessoa() {
        listarPessoas();
        System.out.println("Digite o CPF: ");
        String cpf = ent.nextLine();

        if (this.cpf.equals(cpf)){
        
    }

    public void visualizarPessoa() {

    }

    public void buscarPessoa() {

    }

    public void removerPessoa() {

    }

    

    
}