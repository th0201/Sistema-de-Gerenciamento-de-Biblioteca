package projeto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Funcionario extends Pessoa {
    private boolean administrador;
    private String senha; 
    private List<Funcionario> funcionarios = new ArrayList<>();

    Scanner ent = new Scanner(System.in);

    public List<Funcionario> getFuncionario() {
        return funcionarios;
    }

    public boolean isAdministrador() {
        return administrador;
    }

public String getSenha(){
    return senha;
}
    public void setFuncionario() {
    this.funcionarios = funcionarios;

} 
public void isAdministrador(boolean administrador){
this.administrador = administrador;
}

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void cadastrarFuncionario() {
System.out.print("Digite o numero do CPF:");
String cpf = ent.nextLine();

for (Funcionario a : funcionarios){
    if(a.getcpf().equals(cpf)){
        System.out.println("Funcionario já cadastrado!");
        return;
    }
}
System.out.println("Deseja se cadastrar como funcionario?(sim/não)");
String resposta = ent.nextLine();

if(resposta.equalsIgnorecase("sim")){
        System.out.println("----------------------------------");
        System.out.println("CADASTRAR FUNCIONÁRIO"              );
        System.out.println("-----------------------------------");

        Funcionario novoFuncionario = new Funcionario();
        novoFuncionario.cadastrarpessoa();
        System.out.print("NOME: ");
        novoFuncionario.setNome(ent.nextLine());
        System.out.print("IDADE: ");
        novoFuncionario.setIdade(ent.nextInt());
        System.out.print("CPF: ");
        novoFuncionario.setCpf(ent.nextLine());
        System.out.print("EMAIL: ");
        novoFuncionario.setEmail(ent.nextLine());
        System.out.print("SENHA: ");
        novoFuncionario.setSenha(ent.nextLine());
        funcionario.add(novoFuncionario);
        System.out.println("Funcionario cadastrado com sucesso!");
}else{
    System.out.println("cadastro de funcionario cancelado.");
}
    }

    
    public void editarFuncionario() {
        System.out.print("Digite  o  numero do CPF: ");
        String cpf = ent.nextLine();
        for (Funcionario a : funcionarios) {
            if (a.getCpf().equals(cpf)) {
                System.out.println("Editar dados do(a) funcionário(a): " + a.getNome());
                a.editarpessoa();
                System.out.println("NOME: ");
                a.setNome(ent.nextLine());
                System.out.println("IDADE: ");
                a.setIdade(ent.nextLine());
                System.out.println("CPF: ");
                a.setCpf(ent.nextLine());
                System.out.println("EMAIL: ");
                a.setEmail(ent.nextLine());
                System.out.println("SENHA: ");
                a.setSenha(ent.nextLine());
                System.out.println("Dados editados com sucesso!");
                return;
            }
        }
        System.out.println("CPF não encontrado.");
    }
    
public void buscarFuncionario(){
    System.out.print("Digite o numero do CPF:");
    String cpf = ent.nextLine();
    boolean funcionarioEncontrado = false;

    for (Funcionario a : funcionarios){
        if(a.getCpf().equals(cpf)){
            funcionarioEncontrado = true;
            System.out.println("NOME:" + a.getNome());
            System.out.println("IDADE:" + a.getIdade());
            System.out.println("CPF:"+ a.getCpf());
            System.out.println("EMAIL:"+ a.getEmail());
            System.out.println("SENHA:"+ a.getSenha());
            System.out.println("---------------------");
            return;
        }
        if(!funcionarioEncontrado) {
            System.out.println("Funcionario não cadastrado!");
        }
    }
}
    
    public void listarFuncionarios() {
        if (pessoa.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado.");
        } else {
            System.out.println("\n-------------------------------");
            System.out.println("LISTA DE FUNCIONÁRIOS CADASTRADOS");
            System.out.println("----------------------------------");

            for (Funcionario a : funcionarios) {
                    System.out.println("NOME: " + funcionario.getNome());
                    System.out.println("IDADE: " + funcionario.getIdade());
                    System.out.println("CPF: " + funcionario.getCpf());
                    System.out.println("EMAIL: " + funcionario.getEmail());
                    System.out.println("SENHA: " + funcionario.getSenha());
                    System.out.println("----------------------------------");
                }
            }
        }
    
    public void removerFuncionario() {
        System.out.print("Digite o numero do CPF: ");
        String cpf = ent.nextLine();
        boolean funcionarioencontrado = false;

        for (Funcionario a : funcionarios) {
            if (a.getCpf().equals(cpf)) {
                funcionarioEncontrado = true;
                System.out.println("Funcionário(a):" + a.getNome());
                System.out.println("Deseja remove-lo? (sim/não)");
                String resposta = ent.nextLine();
                if(resposta.equalsIgnorecase("sim")) {
                    funcionarios.remove(a);
                    System.out.println("Funcionario removido.");
                    return;
                } else{
                    System.out.println("Remoção de funcionario cancelada.");
                }
            }
        }

        if (!funcionarioEncontrado) {
            System.out.println("Funcionário não cadastrado!");
        }
    }

    public void visualizarFuncionario() {
        if(funcionarios.isEmpty()) {
System.out.println("Não há funcionarios para visualizar.");
return;
        }
        System.out.print("Digite o CPF do funcionário que deseja visualizar: ");
        String cpf = ent.nextLine();
        boolean funcionarioEncontrado = false;

        for (Funcionario a : funcionarios) {
            if (a.getCpf().equals(cpf)) {
                funcionarioEncontrado = true;
                System.out.println("Detalhes do Funcionario: ");
                System.out.println("NOME: " + a.getNome());
                System.out.println("IDADE: " + a.getIdade());
                System.out.println("CPF: " + a.getCpf());
                System.out.println("EMAIL: " + a.getEmail());
                System.out.println("ADMINISTRADOR: " + (a.isAdministrador()? "sim" : "não"));
                System.out.println("----------------------------------");
                return;
            }
        }

        if (!funcionarioEncontrado) {
            System.out.println("CPF invalido.");
        }
    }
}



