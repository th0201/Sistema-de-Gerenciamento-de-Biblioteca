import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Aluno extends Pessoa {
    private String matricula;
    private String curso;
    private String nomeArquivo = "Alunos.csv";
    private List<Aluno> alunos = new ArrayList<>();

    public Aluno(String nome, int idade, String cpf, String email, String matricula, String curso) {
        super(nome, idade, cpf, email);
        this.matricula = matricula;
        this.curso = curso;
    }

    public Aluno() {
        lerArquivo();
    }

    public void cadastrarAluno() {
        System.out.print("Digite o número do CPF: ");
        String cpf = ent.nextLine();
        for (Aluno a : alunos) {
            if (a.getCpf().equals(cpf)) {
                System.out.println("Aluno já cadastrado!");
                return;
            }
        }
        Funcionario func = new Funcionario();
        for (Funcionario f : func.getFuncionarios()) {
            if (f.getCpf().equals(cpf)) {
                System.out.println("CPF já cadastrado na lista de funcionários!");
                return;
            }
        }
        Aluno novoAluno = new Aluno();
        System.out.println("-----------------------------");
        System.out.println("PÁGINA DE CADASTRO DE ALUNOS");
        System.out.println("-----------------------------");

        novoAluno.cadastrarPessoa();
        System.out.print("MATRÍCULA:");
        novoAluno.setMatricula(ent.nextLine());
        System.out.print("CURSO:");
        novoAluno.setCurso(ent.nextLine());

        alunos.add(novoAluno);
        salvarDadosArquivo();
        System.out.println("Aluno cadastrado!");
    }

    public void salvarDadosArquivo() {
        try {
            boolean arquivoExiste = new File(nomeArquivo).exists();
            FileWriter escritor = new FileWriter(nomeArquivo, StandardCharsets.ISO_8859_1, true);

            if (!arquivoExiste) {
                escritor.write("Nome;Idade;CPF;Email;Senha;Matrícula;Curso\n");
            }

            for (Aluno a : alunos) {
                escritor.write(a.getNome() + ";" + a.getIdade() + ";" + a.getCpf() + ";"
                        + a.getEmail() + ";" + a.getSenha() + ";" + a.getMatricula() + ";"
                        + a.getCurso() + "\n");
            }
            // Escrever todos os dados no arquivo
            escritor.flush();
            escritor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void atualizarArquivo() {
        try (FileWriter escritor = new FileWriter(nomeArquivo, StandardCharsets.ISO_8859_1, false)) {
            escritor.write("Nome;Idade;CPF;Email;Senha;Matrícula;Curso\n");

            for (Aluno a : alunos) {
                escritor.write(a.getNome() + ";" + a.getIdade() + ";" + a.getCpf() + ";"
                        + a.getEmail() + ";" + a.getSenha() + ";" + a.getMatricula() + ";"
                        + a.getCurso() + "\n");
            }
            escritor.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao atualizar o arquivo: " + e.getMessage());
        }
    }

    public void editarAluno() {
        System.out.print("Digite o número do CPF: ");
        String cpf = ent.nextLine();
        for (Aluno a : alunos) {
            if (a.getCpf().equals(cpf)) {
                System.out.println("Editar dados do(a) aluno(a): " + a.getNome());
                System.out.println();
                a.editarPessoa();
                System.out.println("MATRÍCULA:");
                a.setMatricula(ent.nextLine());
                System.out.println("CURSO:");
                a.setCurso(ent.nextLine());
                atualizarArquivo();
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
                System.out.println();
                System.out.println("NOME:" + a.getNome());
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
            System.out.println("Aluno não cadastrado!");
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
                System.out.println("----------------------------------");
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
                    atualizarArquivo();
                    System.out.println("Aluno removido.");
                    return;
                } else {
                    System.out.println("Remoção de aluno cancelada.");
                    return;
                }
            }
        }
        if (!alunoEncontrado) {
            System.out.println("Aluno não cadastrado!");
        }
    }

    public void lerArquivo() {
        File arquivo = new File(nomeArquivo);
        // Verifica se o arquivo existe antes de fazer a leitura
        if (!arquivo.exists()) {
            return;
        }
        try {
            // Abrir o leitor para ler o arquivo
            BufferedReader leitor = new BufferedReader(new FileReader(nomeArquivo));
            String linha;
            boolean primeiraLinha = true;

            // Ler cada linha inteira no arquivo, ignorando a primeira linha
            while ((linha = leitor.readLine()) != null) {
                // Ignora a primeira linha
                if (primeiraLinha) {
                    primeiraLinha = false;
                    continue;
                }

                // Dividir a linha em partes usando o ponto e vírgula como separador
                String[] partes = linha.split(";");
                String nome = partes[0];
                int idade = Integer.parseInt(partes[1]); // converte para string
                String cpf = partes[2];
                String email = partes[3];
                String senha = partes[4];
                String matricula = partes[5];
                String curso = partes[6];

                Aluno a = new Aluno(nome, idade, cpf, email, matricula, curso);
                a.setSenha(senha);
                alunos.add(a);
            }
            leitor.close();
        } catch (IOException e) {
            e.printStackTrace();
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
