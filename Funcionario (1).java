package projeto;

class Funcionario extends Pessoa {
    private boolean administrador;
    
public boolean getAdministrador(){
    return administrador;
}

public void setAdministrador(boolean administrador) {
    this.administrador = administrador;
}

    public void cadastrarFuncionario() {
        super.cadastrarPessoa();
        System.out.println("----------------------------------");
        System.out.println("CADASTRAR FUNCIONARIO");
        System.out.println("-----------------------------------");

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

        System.out.println("Funcionario cadastrado com sucesso!");
    }

    public void editarFuncionario() {
        System.out.println("Digite seu numero de cpf:");
        String cpf = entt.nextLine();

        for (Pessoa p : pessoa) {
            if(p instanceof funcionario  && p.getCpf().equals(cpf)){
                System.out.println("Editar dados do(a) funcionario(a): " + p.getNome());
                funcionario funcionario = (funcionario) p;
                super.editarPessoa();System.out.print("NOME:");
                funcionario.setnome = ent.nextLine();
                System.out.print("IDADE:");
                funcionario.setidade = ent.nextInt();
                ent.nextLine();
                System.out.print("CPF:");
                funcionario.setcpf = ent.nextLine();
                System.out.print("EMAIL:");
                funcionario.setemail = ent.nextLine();
                System.out.print("SENHA:");
                funcionario.setsenha = ent.nextLine();
                System.out.println("Dados editados com sucesso!");
     return;
}
        }
        System.out.println("cpf não encontrado");
    }

    public void buscarFuncionario(String cpf) {
        for(Pessoa p: pessoa){
            System.out.println("nenhum funcionario encontrado.");
        }else {
            System.out.println("\n-------------------------------");
            System.out.println(   "  BUSCANDO FUNCIONARIOS "       );
            System.out.println("----------------------------------");

            for (Pessoa p : pessoa) {
                if (p instanceof funcionario) {
                    funcionario funcionario = (funcionario) p;
                    System.out.println("NOME: " + p.getNome());
                    System.out.println("IDADE: " + p.getIdade());
                    System.out.println("CPF: " + p.getCpf());
                    System.out.println("EMAIL: " + p.getEmail());
                    System.out.println("SENHA: " + funcionario.funcionario());
                    System.out.println("funcionario encontrado");
    }

    public void listarFuncionario() {
        if (pessoa.isEmpty()) {
            System.out.println("Nenhum funcionario cadastrado.");
        } else {
            System.out.println("\n-------------------------------");
            System.out.println("LISTA DE FUNCIONARIOS CADASTRADOS");
            System.out.println("----------------------------------");

            for (Pessoa p : pessoa) {
                if (p instanceof funcionario) {
                    funcionario funcionario = (funcionario) p;
                    System.out.println("NOME: " + p.getNome());
                    System.out.println("IDADE: " + p.getIdade());
                    System.out.println("CPF: " + p.getCpf());
                    System.out.println("EMAIL: " + p.getEmail());
                    System.out.println("SENHA: " + funcionario.funcionario());
                    System.out.println("----------------------------------");
                }
            }
        }            
    }

    
    
    

public void removerAluno() {
            
            }
        }
    

    public void visualizarFuncionario() { 
    }

        }

    }

}
    
