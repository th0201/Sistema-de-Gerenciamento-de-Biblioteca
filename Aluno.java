import java.util.ArrayList;
import java.util.List;

public class Aluno extends Pessoa {
    private String matricula;
    private String curso;
    private List<Aluno> alunos = new ArrayList<>();

    public void cadastrarAluno() {
        System.out.print("Digite o número do CPF: ");
        String cpf = ent.nextLine();

        for (Aluno a : alunos) {
            if (a.getCpf().equals(cpf)) {
                System.out.println("Aluno já cadastrado!");
                return;
            }
        }
        System.out.println("Deseja se cadastrar como aluno? (sim/não)");
        String resposta = ent.nextLine();

        if (resposta.equalsIgnoreCase("sim")) {
            System.out.println("-----------------------------");
            System.out.println("PÁGINA DE CADASTRO DE ALUNOS");
            System.out.println("-----------------------------");

            Aluno novoAluno = new Aluno();
            novoAluno.cadastrarPessoa();
            System.out.print("MATRÍCULA:");
            novoAluno.setMatricula(ent.nextLine());
            System.out.print("CURSO:");
            novoAluno.setCurso(ent.nextLine());
            alunos.add(novoAluno);
            System.out.println("Aluno cadastrado!");
        } else {
            System.out.println("Cadastro de aluno cancelado.");
        }
    }

    public void editarAluno() {
        System.out.print("Digite o número do CPF: ");
        String cpf = ent.nextLine();
        for (Aluno a : alunos) {
            if (a.getCpf().equals(cpf)) {
                System.out.println("Editar dados do(a) aluno(a): " + a.getNome());
                a.editarPessoa();
                System.out.println("MATRÍCULA:");
                a.setMatricula(ent.nextLine());
                System.out.println("CURSO:");
                a.setCurso(ent.nextLine());
                System.out.println("Dados editados com sucesso!");
                return;
            }
        }
        System.out.println("CPF não encontrado!");
    }

    public void buscarAluno() {
        System.out.print("Digite o número do CPF: ");
        String cpf = ent.nextLine();
        boolean alunoEncontrado = false;

        for (Aluno a : alunos) {
            if (a.getCpf().equals(cpf)) {
                alunoEncontrado = true;
                System.out.println("NOME:" + a.getNome());
                System.out.println("IDADE: " + a.getIdade());
                System.out.println("CPF: " + a.getCpf());
                System.out.println("EMAIL: " + a.getEmail());
                System.out.println("MATRÍCULA: " + a.getMatricula());
                System.out.println("CURSO: " + a.getCurso());
                System.out.println("----------------------------------");
                return;
            }
            if (!alunoEncontrado) {
                System.out.println("Aluno não cadastrado!");
            }
        }
    }

    public void listarAlunos() {
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
        } else {
            System.out.println("\n-----------------------------");
            System.out.println("LISTA DE ALUNOS CADASTRADOS");
            System.out.println("-----------------------------");

            for (Aluno a : alunos) {
                System.out.println("NOME:" + a.getNome());
                System.out.println("IDADE: " + a.getIdade());
                System.out.println("CPF: " + a.getCpf());
                System.out.println("EMAIL: " + a.getEmail());
                System.out.println("MATRÍCULA: " + a.getMatricula());
                System.out.println("CURSO: " + a.getCurso());
                System.out.println("----------------------------------");
            }
        }
    }

    public void visualizarAluno() {
        if (alunos.isEmpty()) {
            System.out.println("Não há alunos para visualizar.");
            return;
        }
        System.out.print("Digite o CPF do aluno que deseja visualizar: ");
        String cpf = ent.nextLine();
        boolean alunoEncontrado = false;

        for (Aluno a : alunos) {
            if (a.getCpf().equals(cpf)) {
                alunoEncontrado = true;
                System.out.println("Detalhes do Aluno: ");
                System.out.println("NOME: " + a.getNome());
                System.out.println("IDADE: " + a.getIdade());
                System.out.println("CPF: " + a.getCpf());
                System.out.println("EMAIL: " + a.getEmail());
                System.out.println("MATRÍCULA: " + a.getMatricula());
                System.out.println("CURSO: " + a.getCurso());
                System.out.println("----------------------------------");
                return;
            }
        }
        if (!alunoEncontrado) {
            System.out.println("CPF inválido.");
        }
    }

    public void removerAluno() {
        System.out.print("Digite o número do CPF: ");
        String cpf = ent.nextLine();
        boolean alunoEncontrado = false;

        for (Aluno a : alunos) {
            if (a.getCpf().equals(cpf)) {
                alunoEncontrado = true;
                System.out.println("Aluno(a): " + a.getNome());
                System.out.println("Deseja remove-lo? (sim/não)");
                String resposta = ent.nextLine();
                if (resposta.equalsIgnoreCase("sim")) {
                    alunos.remove(a);
                    System.out.println("Aluno removido.");
                    return;
                } else {
                    System.out.println("Remoção de aluno cancelada.");
                }
            }
        }
        if (!alunoEncontrado) {
            System.out.println("Aluno não cadastrado!");
        }
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

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
}
