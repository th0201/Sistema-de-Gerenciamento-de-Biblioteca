package projeto;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class Pessoa {
    protected String nome;
    protected int idade;
    protected String cpf;
    protected String senha;
    protected String email;
    protected boolean autenticado = false;
    Scanner ent = new Scanner(System.in);
    
    protected static List<Pessoa> pessoa = new ArrayList<>();

    public boolean login(String email, String senha){
        return this.email.equals(email) && this.senha.equals(senha);
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

    public void cadastrarPessoa(){
        System.out.println("------------------------------");
        System.out.println("PÁGINA DE CADASTRO DE PESSOAS");
        System.out.println("------------------------------");

        System.out.print("NOME:");
        this.nome = ent.nextLine();
        System.out.print("IDADE:");
        this.idade = ent.nextInt();
        ent.nextLine();
        System.out.print("CPF:");
        this.cpf = ent.nextLine();
        System.out.print("EMAIL:");
        this.email = ent.nextLine();
        System.out.print("SENHA:");
        this.senha = ent.nextLine();
        pessoa.add(this);

        System.out.println("Pessoa cadastrada!");
    }

    public void listarPessoas() {
        if (pessoa.isEmpty()) {
            System.out.println("Nenhuma pessoa cadastrada.");
        } else {
            System.out.println("\n-----------------------------");
            System.out.println("LISTA DE PESSOAS CADASTRADAS:");
            System.out.println("-----------------------------");
            for (int i = 0; i < pessoa.size(); i++) {
                System.out.println("NOME: " + pessoa.get(i).getNome());
                System.out.println("IDADE: " + pessoa.get(i).getIdade());
                System.out.println("CPF: " + pessoa.get(i).getCpf());
                System.out.println("EMAIL: " + pessoa.get(i).getEmail());
                System.out.println("Senha: " + pessoa.get(i).getSenha());
                System.out.println("----------------------------------");
            }
        }
    }

    public void editarPessoa() {
        System.out.println("Digite o CPF da pessoa a ser editada: ");
        String cpf = ent.nextLine();
        for (Pessoa p : pessoa) {
            if (p.getCpf().equals(cpf)) {
                System.out.println("Editar os dados de " + p.getNome());
                System.out.print("NOME: ");
                p.setNome(ent.nextLine());
                System.out.print("IDADE: ");
                p.setIdade(ent.nextInt());
                ent.nextLine();
                System.out.print("CPF: ");
                p.setCpf(ent.nextLine());
                System.out.print("EMAIL: ");
                p.setEmail(ent.nextLine());
                System.out.print("SENHA: ");
                p.setSenha(ent.nextLine());
                System.out.println("Dados editados com sucesso!");
                return;
            }
        }
        System.out.println("CPF não encontrado!");
    }

    public void visualizarPessoa() {
    }

    public void buscarPessoa() {
    }

    public void removerPessoa() {
    }
}
