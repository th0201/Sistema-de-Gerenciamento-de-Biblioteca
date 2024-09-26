
class Aluno extends Pessoa {
    private String matricula;
    private String curso;
    
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public void cadastrarAluno() {
        System.out.print("Digite o número do CPF: ");
        String cpf = ent.nextLine();

        for (Pessoa p : pessoa) {
            if(p.getCpf().equals(cpf)){
                if(p instanceof Aluno){
                    System.out.println("Aluno já cadastrado!");
                    return;
                }
                System.out.println("Pessoa com CPF " + cpf + " já cadastrada.");
                System.out.println("Deseja cadastrá-la como aluno? (sim/não)");
                String resposta = ent.nextLine();

                if (resposta.equalsIgnoreCase("sim")) {
                    System.out.println("Cadastrar dados de: " + p.getNome());
                    System.out.println("-----------------------------");
                    System.out.println("PÁGINA DE CADASTRO DE ALUNOS");
                    System.out.println("-----------------------------");

                    System.out.print("MATRÍCULA:");
                    matricula = ent.nextLine();
                    System.out.print("CURSO:");
                    curso = ent.nextLine();
                    System.out.println("Aluno cadastrado!");
                    return;
                } else {
                    System.out.println("Cadastro de aluno cancelado.");
                    return;
                }
            }
        }   
        System.out.println("\nOlá! Parece que você ainda não completou seu cadastro. Para continuar, por favor, preencha suas informações. ");
        super.cadastrarPessoa();
        System.out.print("MATRÍCULA: ");
        matricula = ent.nextLine();
        System.out.print("CURSO: ");
        curso = ent.nextLine();
        System.out.println("Aluno cadastrado!");
    }

    public void editarAluno() {
        System.out.println("Digite o número do CPF: ");
        String cpf = ent.nextLine();

        for (Pessoa p : pessoa) {
            if(p instanceof Aluno && p.getCpf().equals(cpf)){
                System.out.println("Editar dados do(a) aluno(a): " + p.getNome());
                Aluno aluno = (Aluno) p;
                super.editarPessoa();
                System.out.println("MATRÍCULA:");
                aluno.setMatricula(ent.nextLine());
                System.out.println("CURSO:");
                aluno.setCurso(ent.nextLine());
                System.out.println("Dados editados com sucesso!");
                return;
            }
        }
        System.out.println("CPF não encontrado!");
    }

    public void buscarAluno() {
    }

    public void listarAlunos() {
        if (pessoa.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
        } else {
            System.out.println("\n-----------------------------");
            System.out.println("LISTA DE ALUNOS CADASTRADOS");
            System.out.println("-----------------------------");

            for (Pessoa p : pessoa) {
                if (p instanceof Aluno) {
                    Aluno aluno = (Aluno) p;
                    System.out.println("NOME: " + aluno.getNome());
                    System.out.println("IDADE: " + aluno.getIdade());
                    System.out.println("CPF: " + aluno.getCpf());
                    System.out.println("EMAIL: " + aluno.getEmail());
                    System.out.println("MATRÍCULA: " + aluno.getMatricula());
                    System.out.println("CURSO: " + aluno.getCurso());
                    System.out.println("----------------------------------");
                }
            }
        }            
    }

    public void removerAluno() {
    }

    public void visualizarAluno() {    

    }
} 
