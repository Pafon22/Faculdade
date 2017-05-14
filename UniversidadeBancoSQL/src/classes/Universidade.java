package Classes;

import java.util.ArrayList;
import java.util.Scanner;
import view.JFrameAluno;
import view.JFrameCurso;
import view.JFrameMatricula;

/**
 *
 * @author Pafon
 */
public class Universidade {

    private ArrayList<Aluno> aluno;
    private ArrayList<Curso> curso;
    private ArrayList<Matricula> matricula;
    private int codigo;
    private int cdAluno;
    private int cdCurso;
    private int cdMatricula;
    private JFrameAluno bancoAluno;
    private JFrameCurso bancoCurso;
    private JFrameMatricula bancoMatricula;

    public Universidade() {
        this.bancoMatricula = new JFrameMatricula();
        this.bancoCurso = new JFrameCurso();
        this.bancoAluno = new JFrameAluno();
        this.codigo = 0;
        this.aluno = new ArrayList();
        this.curso = new ArrayList();
        this.matricula = new ArrayList();
        this.cdAluno = 0;
        this.cdCurso = 0;
        this.cdMatricula = 0;
    }

    public Universidade(int c) {
        this.bancoMatricula = new JFrameMatricula();
        this.bancoCurso = new JFrameCurso();
        this.bancoAluno = new JFrameAluno();
        this.codigo = c;
    }

    public String alunoCSV() {
        String texto = "Código (Aluno);Nome;CPF\r\n";
        for (Aluno a : aluno) {
            texto += a.CSV();
        }
        return texto;
    }

    public String cursoCSV() {
        String texto = "Código (Curso);Nome;Carga Horária\r\n";
        for (Curso c : curso) {
            texto += c.CSV();
        }
        return texto;
    }

    public String matriculaCSV() {
        String texto = "Matrícula;Código (Aluno);Código (Curso);Data\r\n";
        for (Matricula m : matricula) {
            texto += m.CSV();
        }
        return texto;
    }

    public void salvarArq(String alunotxt, String cursotxt, String matriculatxt) {
        FileManager.escreverArq(alunoCSV(), alunotxt);
        FileManager.escreverArq(cursoCSV(), cursotxt);
        FileManager.escreverArq(matriculaCSV(), matriculatxt);
    }

    public void lerArq(String alunotxt, String cursotxt, String matriculatxt) {
        FileManager.lerArq(alunotxt);
        FileManager.lerArq(cursotxt);
        FileManager.lerArq(matriculatxt);
    }

    public void carregarDadosAluno(String alunotxt) {
        String[] texto = FileManager.getLinhas(alunotxt);
        for (int i = 0; i < texto.length; i++) {
            Aluno a = new Aluno();
            a.carregarCSV(texto[i]);
            aluno.add(a);
        }
    }

    public void carregarDadosCurso(String cursotxt) {
        String texto[] = FileManager.getLinhas(cursotxt);
        for (int i = 0; i < texto.length; i++) {
            Curso c = new Curso();
            c.carregarCSV(texto[i]);
            curso.add(c);
        }
    }

    public void carregarDadosMatricula(String matriculatxt) {
        String[] texto = FileManager.getLinhas(matriculatxt);
        for (int i = 0; i < texto.length; i++) {
            Matricula m = new Matricula();
            m.carregarCSV(texto[i]);
            matricula.add(m);
        }
    }

    public void cadastrarCurso() {
        Curso aux = new Curso();
        aux.preencher(cdCurso++);
        getCurso().add(aux);
        aux.gravarBancoCurso();
    }

    public void cadastrarAluno() {
        Aluno aux = new Aluno();
        aux.preencher(cdAluno++);
        getAluno().add(aux);
        aux.gravarBancoAluno();
    }

    public void cadastrarMatricula() {
        Scanner ler = new Scanner(System.in);
        Matricula mat = new Matricula();
        Aluno a = new Aluno();
        a.imprimirTodosAlunos();
        System.out.println("Digite ID do aluno");
        int idAluno = ler.nextInt();
        Curso c = new Curso();
        c.imprimirTodosCursos();
        System.out.println("Digite ID do curso: ");
        int idCurso = ler.nextInt();
        mat.preencher(cdMatricula, idAluno, idCurso, mat.getDataMatricula());
        getMatricula().add(mat);

        mat.gravarBancoMatricula();
    }

    public String getNameAluno(int id) {
        String name = "";
        for (Aluno a : aluno) {
            if (a.getCodigoAluno() == id) {
                name = a.getNomeAluno();
            }
        }
        return name;
    }

    public String getNameCurso(int id) {
        String name = "";
        for (Curso c : curso) {
            if (c.getCodigo() == id) {
                name = c.getNomeCurso();
            }
        }
        return name;
    }

    /*
    public void imprimir() {
        int cda = 0, cdc = 0, cdm = 0;
        for (Matricula m : matricula) {
            cda = m.getCodigoAluno();
            cdc = m.getCodigoCurso();
            System.out.println("Nome: " + this.getNameAluno(cda));
            System.out.println("Curso: " + this.getNameCurso(cdc));
            System.out.println("Matricula: " + cdm);
            cdm++;
        }

    }
     */
    public void submenuCadastrar() {
        Scanner ler = new Scanner(System.in);
        int op = 0;
        do {
            System.out.println("*****************************************");
            System.out.println("*\t1 - Cadastrar Aluno\t\t*");
            System.out.println("*\t2 - Cadastrar Curso\t\t*");
            System.out.println("*\t3 - Matricular\t\t\t*");
            System.out.println("*\t99 - Sair\t\t\t*");
            System.out.println("*****************************************");
            op = ler.nextInt();
            switch (op) {
                case 1:
                    cadastrarAluno();
                    break;
                case 2:
                    cadastrarCurso();
                    break;
                case 3:
                    System.out.println("-------------------");
                    cadastrarMatricula();
                    break;
                case 4:

                    break;
                case 99:
                    break;
                default:
                    System.out.println("Essa opção não existe");
                    break;
            }
        } while (op != 99);
    }

    public void submenuAlterar() {
        Scanner ler = new Scanner(System.in);
        int op = 0;
        int aux;
        Aluno a = new Aluno();
        Curso c = new Curso();
        Matricula m = new Matricula();
        do {
            System.out.println("*****************************************");
            System.out.println("\t1 - Alterar Aluno");
            System.out.println("\t2 - Alterar Curso");
            System.out.println("\t3 - Alterar Matrícula");
            System.out.println("*\t99 - Sair\t\t\t*");
            System.out.println("*****************************************");
            op = ler.nextInt();
            switch (op) {

                case 1:
                    a.imprimirTodosAlunos();
                    System.out.println("Digite o código do aluno a ser alterado: ");
                    aux = ler.nextInt();
                    a.alterarAluno(aux);
                    break;
                case 2:
                    c.imprimirTodosCursos();
                    System.out.println("Digite o código do curso a ser alterado: ");
                    aux = ler.nextInt();
                    c.alterarCurso(aux);
                    break;
                case 3:
                    m.imprimirTodasMatriculas();
                    System.out.println("Digite o código da matrícula a ser alterada: ");
                    aux = ler.nextInt();
                    m.alterarMatricula(aux);
                    break;
                case 99:
                    break;
                default:
                    System.out.println("Essa opção não existe");
                    break;
            }
        } while (op != 99);
    }

    public void submenuRemover() {
        Scanner ler = new Scanner(System.in);
        int op = 0, aux;
        do {
            System.out.println("*****************************************");
            System.out.println("\t1 - Remover Aluno\t\t\t*");
            System.out.println("\t2 - Remover Curso\t\t\t");
            System.out.println("\t3 - Remover Matrícula\t\t\t");
            System.out.println("*\t99 - Sair\t\t\t*");
            System.out.println("*****************************************");
            op = ler.nextInt();
            switch (op) {
                case 1:
                    Aluno aluno = new Aluno();
                    aluno.imprimirTodosAlunos();
                    System.out.println("Qual o código do aluno a ser removido?");
                    aux = ler.nextInt();
                    aluno.removerAluno(aux);
                    break;
                case 2:
                    Curso curso = new Curso();
                    curso.imprimirTodosCursos();
                    System.out.println("Qual o código do curso a ser removido?");
                    aux = ler.nextInt();
                    curso.removerCurso(aux);
                    break;
                case 3:
                    Matricula matricula = new Matricula();
                    matricula.imprimirTodasMatriculas();
                    System.out.println("Qual o código da matricula a ser removida?");
                    aux = ler.nextInt();
                    matricula.removerMatricula(aux);
                    break;
                case 99:
                    break;
                default:
                    System.out.println("Essa opção não existe");
                    break;
            }
        } while (op != 99);
    }

    public void submenuBuscar() {
        Scanner ler = new Scanner(System.in);
        int op = 0;
        do {
            System.out.println("*****************************************");
            System.out.println("\t1 - Buscar Aluno");
            System.out.println("\t2 - Buscar Curso");
            System.out.println("\t3 - Buscar Matrícula");
            System.out.println("*\t99 - Sair\t\t\t*");
            System.out.println("*****************************************");
            op = ler.nextInt();
            switch (op) {
                case 1:
                    Aluno a = new Aluno();
                    a.buscar();
                    break;
                case 2:
                    Curso c = new Curso();
                    c.buscar();
                    break;
                case 3:
                    Matricula m = new Matricula();
                    m.buscar();
                    break;
                case 99:
                    break;
                default:
                    System.out.println("Essa opção não existe");
                    break;
            }
        } while (op != 99);
    }

    public void submenuImprimir() {
        Scanner ler = new Scanner(System.in);
        int op = 0;
        do {
            System.out.println("*****************************************");
            System.out.println("\t14 - Imprimir Aluno");
            System.out.println("\t14 - Imprimir Curso");
            System.out.println("\t14 - Imprimir Matrícula");
            System.out.println("*\t99 - Sair\t\t\t*");
            System.out.println("*****************************************");
            op = ler.nextInt();
            switch (op) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:
                    break;
                case 99:
                    break;
                default:
                    System.out.println("Essa opção não existe");
                    break;
            }
        } while (op != 99);
    }

    public void menu() {
        int aux = 0;
        Scanner ler = new Scanner(System.in);
        int op = 0;
        do {
            System.out.println("*****************************************");
            System.out.println("*\t 1 - Cadastrar \t\t\t*");
            System.out.println("*\t 2 - Alterar \t\t\t*");
            System.out.println("*\t 3 - Remover\t\t\t*");
            System.out.println("*\t 4 - Buscar\t\t\t*");
            System.out.println("*\t 5 - Imprimir\t\t\t*");
            System.out.println("*\t 6 - Carregar Banco de Dados\t*");
            System.out.println("*\t 7 - Interface Aluno\t\t*");
            System.out.println("*\t 8 - Interface Curso\t\t*");
            System.out.println("*\t 9 - Interface Matrícula\t*");
            //System.out.println("*\t 8 - Carregar Arquivo\t\t*");
            //System.out.println("*\t 9 - Salvar no Arquivo\t\t*");
            System.out.println("*\t 0 - Sair\t\t\t*");
            System.out.println("*****************************************");
            op = ler.nextInt();

            switch (op) {

                case 1:
                    submenuCadastrar();
                    break;

                case 2:
                    submenuAlterar();
                    break;

                case 3:
                    submenuRemover();
                    break;

                case 4:
                    submenuBuscar();
                    break;

                case 5:
                    submenuImprimir();
                    break;

                case 6:
                    Aluno a = new Aluno();
                    a.imprimirTodosAlunos();
                    Curso c = new Curso();
                    c.imprimirTodosCursos();
                    Matricula m = new Matricula();
                    m.imprimirTodasMatriculas();
                    /*
                    Aluno a = new Aluno();
                    a.carregarAlunoBD();
                    Curso c = new Curso();
                    c.carregarCursoBD();
                    Matricula m = new Matricula();
                    m.carregarMatriculaBD();*/
                    break;
                /*
                case 7:
                    carregarDadosAluno("aluno.txt");
                    carregarDadosCurso("curso.txt");
                    carregarDadosMatricula("matricula.txt");
                    break;
                case 8:
                    salvarArq("aluo.txt", "curso.txt", "matricula.txt");
                    break;
                  case 7:
                    bancoAluno.setVisible(true);
                    bancoAluno.imprimirTodosAlunos();
                    break;
                case 8:
                    bancoCurso.setVisible(true);
                    bancoCurso.imprimirTodosCursos();
                    break;
                case 9:
                    bancoMatricula.setVisible(true);
                    bancoMatricula.imprimirTodasMatriculas();
                    break;*/
                case 0:
                    break;
                default:
                    System.out.println("Essa opção não existe!");
                    break;
            }

        } while (op != 0);

    }

    public ArrayList<Aluno> getAluno() {
        return aluno;
    }

    public void setAluno(ArrayList<Aluno> aluno) {
        this.aluno = aluno;
    }

    public ArrayList<Curso> getCurso() {
        return curso;
    }

    public void setCurso(ArrayList<Curso> curso) {
        this.curso = curso;
    }

    public ArrayList<Matricula> getMatricula() {
        return matricula;
    }

    public void setMatricula(ArrayList<Matricula> matricula) {
        this.matricula = matricula;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
}
