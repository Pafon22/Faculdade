/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import classes.Aluno;
import classes.Curso;
import classes.Matricula;

/**
 *
 * @author PauloAfonso
 */
public class Universidade {

    private ArrayList<Aluno> aluno;
    private ArrayList<Curso> curso;
    private ArrayList<Matricula> matricula;
    private int codigoAluno;
    private int codigoCurso;
    private int codigoMatricula;
    private int codigo;

    public Universidade() {
        this.aluno = new ArrayList();
        this.curso = new ArrayList();
        this.matricula = new ArrayList();
        this.codigo = 0;
        this.codigoAluno = 0;
        this.codigoCurso = 0;
        this.codigoMatricula = 0;
    }

    public Universidade(ArrayList<Aluno> aluno, ArrayList<Curso> curso, ArrayList<Matricula> matricula, int codigoAluno, int codigoCurso, int codigoMatricula, int codigo) {
        this.aluno = aluno;
        this.curso = curso;
        this.matricula = matricula;
        this.codigoAluno = codigoAluno;
        this.codigoCurso = codigoCurso;
        this.codigoMatricula = codigoMatricula;
        this.codigo = codigo;

    }

    public void cadastrarAluno() {
        Aluno a = new Aluno();
        a.preencher();
        getAluno().add(a);
        a.inserirAlunoBD();
    }

    public void cadastrarCurso() {
        Curso c = new Curso();
        c.preencher(codigoCurso++);
        getCurso().add(c);
        c.inserirCursoBD();
    }

    public void cadastrarMatricula() {
        Scanner ler = new Scanner(System.in);
        Matricula m = new Matricula();
        for (Curso c : getCurso()) {
            System.out.println(c.getCodigoCurso() + "  " + c.getNomeCurso());
        }
        System.out.println("Digite ID do curso: ");
        int idCurso = ler.nextInt();
        for (Curso c : getCurso()) {
            if (c.getCodigoCurso() == idCurso) {
                System.out.println("Digite ID do aluno");
                int idAluno = ler.nextInt();
                for (Aluno a : getAluno()) {
                    if (a.getCodigoAluno() == idAluno) {
                        System.out.println("Digite a data: ");
                        String data = ler.next();
                        m.preencherMatriculaPeloBanco(codigoMatricula++, idAluno, idCurso, data);
                        getMatricula().add(m);
                        m.inserirMatriculaBD();
                    }
                }
            }
        }
    }

    public Aluno buscarAluno(int cod) {
        for (Aluno a : aluno) {
            if (a.getCodigoAluno() == cod) {
                return a;
            }
        }
        return null;
    }

    public Curso buscarCurso(int cod) {
        for (Curso c : curso) {
            if (c.getCodigoCurso() == cod) {
                return c;
            }
        }
        return null;
    }

    public Matricula buscarMatricula(int cod) {
        for (Matricula m : matricula) {
            if (m.getCodigoMatricula() == cod) {
                return m;
            }
        }
        return null;
    }

    public void imprimir() {
        for (Matricula m : matricula) {
            System.out.println("Nome: " + buscarAluno(m.getCodigoAluno()).getNomeAluno());
            System.out.println("Curso: " + buscarCurso(m.getCodigoAluno()).getNomeCurso());
            System.out.println("Matrícula: " + m.getCodigoMatricula());
        }
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

    public void menuPrincipal() throws InterruptedException {
        Scanner ler = new Scanner(System.in);
        int op = 0;
        do {
            System.out.println("\t|*******************************|");
            System.out.println("\t|\t1 - Cadastrar Aluno \t|");
            System.out.println("\t|\t2 - Cadastrar Curso \t|");
            System.out.println("\t|\t3 - Matricular \t\t|");
            System.out.println("\t|\t4 - Imprimir \t\t|");
            System.out.println("\t|\t5 - Carregar dados \t|");
            System.out.println("\t|\t6 - Buscar Pelo Código \t|");
            System.out.println("\t|\t7 - Remover \t\t|");
            System.out.println("\t|\t8 - Salvar \t\t|");
            System.out.println("\t|\t0 - Sair Arquivo\t|");
            System.out.println("\t|*******************************|");
            op = ler.nextInt();

            switch (op) {

                case 1:
                    cadastrarAluno();
                    break;
                case 2:
                    this.cadastrarCurso();
                    break;

                case 3:
                    this.cadastrarMatricula();
                    break;

                case 4:
                    imprimir();
                    break;

                case 5:
                    aluno.get(0).carregarAlunoBD();
                    curso.get(0).carregarCursoBD();
                    matricula.get(0).carregarMatriculaBD();
                    break;

                case 6:
                    String aux;
                    int op4,
                     op5,
                     cod,
                     aux2;
                    System.out.println("\t1 - Buscar Aluno");
                    System.out.println("\t2 - Buscar Curso");
                    System.out.println("\t3 - Buscar Matricula");
                    op4 = ler.nextInt();
                    switch (op4) {
                        case 1:
                            System.out.println("Digite o código do Aluno");
                            cod = ler.nextInt();
                            System.out.println(carregarAlunoBD(cod).getCodigoAluno());
                            System.out.println(carregarAlunoBD(cod).getNomeAluno());
                            System.out.println(carregarAlunoBD(cod).getCpfAluno());

                            do {
                                System.out.println("Deseja fazer alteração no Aluno?");
                                System.out.println("1 - Sim");
                                System.out.println("2 - Não");
                                op5 = ler.nextInt();
                                if (op5 != 2 && op5 != 1) {
                                    System.out.println("Opção Inválida!");
                                }
                            } while (op5 != 1 && op5 != 2);
                            if (op5 == 1) {
                                System.out.println("Digite o Nome:");
                                aux = ler.next();
                                carregarAlunoBD(cod).setNomeAluno(aux);
                                System.out.println("Digite o CPF:");
                                aux = ler.next();
                                carregarAlunoBD(cod).setCpfAluno(aux);
                            }

                            break;

                        case 2:
                            System.out.println("Digite o codigo do Curso");
                            cod = ler.nextInt();
                            System.out.println(carregarCursoBD(cod).getCodigoCurso());
                            System.out.println(carregarCursoBD(cod).getNomeCurso());
                            System.out.println(carregarCursoBD(cod).getCargaHorariaCurso());

                            do {
                                System.out.println("Deseja fazer alteração no Curso?");
                                System.out.println("1 - Sim");
                                System.out.println("2 - Não");
                                op5 = ler.nextInt();
                                if (op5 != 2 && op5 != 1) {
                                    System.out.println("Opção Inválida!");
                                }
                            } while (op5 != 1 && op5 != 2);
                            if (op5 == 1) {
                                System.out.println("Digite o Nome:");
                                aux = ler.next();
                                carregarCursoBD(cod).setNomeCurso(aux);
                                System.out.println("Digite a Carga Horária:");
                                aux2 = ler.nextInt();
                                carregarCursoBD(cod).setCargaHorariaCurso(aux2);
                            }
                            break;

                        case 3:
                            System.out.println("Digite o codigo da Matrícula");
                            cod = ler.nextInt();
                            System.out.println(carregarMatriculaBD(cod).getCodigoCurso());
                            System.out.println(carregarMatriculaBD(cod).getCodigoAluno());
                            System.out.println(carregarMatriculaBD(cod).getCodigoCurso());
                            System.out.println(carregarMatriculaBD(cod).getData());

                            do {
                                System.out.println("Deseja fazer alteração na Matrícula?");
                                System.out.println("1 - Sim");
                                System.out.println("2 - Não");
                                op5 = ler.nextInt();
                                if (op5 != 2 && op5 != 1) {
                                    System.out.println("Opção Inválida!");
                                }
                            } while (op5 != 1 && op5 != 2);
                            if (op5 == 1) {
                                System.out.println("Digite o Código do Aluno:");
                                aux2 = ler.nextInt();
                                carregarMatriculaBD(cod).setCodigoAluno(aux2);
                                System.out.println("Digite o Código do Curso:");
                                aux2 = ler.nextInt();
                                carregarMatriculaBD(cod).setCodigoCurso(aux2);
                                System.out.println("Digite a Data:");
                                aux = ler.next();
                                carregarMatriculaBD(cod).setData(aux);
                            }
                            break;
                    }

                    break;

                case 7:
                    int op3,
                     cod2,
                     verifica;
                    System.out.println("\t1 - Remover Aluno");
                    System.out.println("\t2 - Remover Curso");
                    System.out.println("\t1 - Remover Matrícula");
                    op3 = ler.nextInt();
                    switch (op3) {

                        case 1:
                            System.out.println("Digite o código do Aluno");
                            cod2 = ler.nextInt();
                            carregarAlunoBD(cod2);
                            System.out.println("\tTem certeza que deseja remover?");
                            System.out.println("\t1 - Sim");
                            System.out.println("\t2 - Não");

                            verifica = ler.nextInt();
                            if (verifica == 1) {
                                deleteAluno(cod2);
                            } else {
                                System.out.println("Operação cancelada pelo usuário!");
                            }
                            break;

                        case 2:
                            System.out.println("Digite o código do Curso");
                            cod2 = ler.nextInt();
                            carregarCursoBD(cod2);
                            System.out.println("\tTem certeza que deseja remover?");
                            System.out.println("\t1 - Sim");
                            System.out.println("\t2 - Não");

                            verifica = ler.nextInt();
                            if (verifica == 1) {
                                deleteCurso(cod2);
                            } else {
                                System.out.println("Operação cancelada pelo usuário!");
                            }
                            break;

                        case 3:
                            System.out.println("Digite o código da Matrícula");
                            cod2 = ler.nextInt();
                            carregarMatriculaBD(cod2);
                            System.out.println("\tTem certeza que deseja remover?");
                            System.out.println("\t1 - Sim");
                            System.out.println("\t2 - Não");

                            verifica = ler.nextInt();
                            if (verifica == 1) {
                                deleteMatricula(cod2);
                            } else {
                                System.out.println("Operação cancelada pelo usuário!");
                            }
                            break;

                    }
                    break;
                case 8:
                    salvarArq("aluno.txt", "curso.txt", "matricula.txt");
                    break;
                case 0:
                    autor();
                    break;
                default:
                    System.out.println("\tOpção Inválida!");
                    System.out.println("\t----------------");
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

    public int getCod() {
        return codigo;
    }

    public void setCod(int codigo) {
        this.codigo = codigo;
    }

    public void carregarCursoBD() {
        try {
            ResultSet rs = null;
            PreparedStatement ps = Persistencia.conexao().prepareStatement("Select * from curso");
            rs = ps.executeQuery();

            this.curso = new ArrayList();
            while (rs.next()) {
                Curso c = new Curso();
                c.preencherCursoPeloBanco(rs.getInt("codigoCurso"), rs.getString("nomeCurso"), rs.getInt("cargaHorariaCurso"));
                this.curso.add(c);
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Não foi possivel carregar o comando SQL");
        }
    }

    public void carregarMatriculaBD() {

        try {
            ResultSet rs = null;
            PreparedStatement ps = Persistencia.conexao().prepareStatement("Select * from matricula");
            rs = ps.executeQuery();

            matricula = new ArrayList();
            while (rs.next()) {
                Matricula m = new Matricula();
                m.preencherMatriculaPeloBanco(rs.getInt("codigoMatricula"), rs.getInt("codigoAluno"), rs.getInt("codigoCurso"), rs.getString("data"));
                matricula.add(m);
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Não foi possivel carregar o comando SQL");
        }
    }

    public Aluno carregarAlunoBD(int codigo) {
        try {
            ResultSet rs = null;
            String sql = "Select * from alunos where codigoAluno = " + codigo;
            PreparedStatement ps = Persistencia.conexao().prepareStatement(sql);
            rs = ps.executeQuery();

            Aluno a = new Aluno();
            rs.next();
            a.preencherAlunoPeloBanco(rs.getInt("codigoAluno"), rs.getString("nomeAluno"), rs.getString("cpfoAluno"));
            ps.close();
            return a;
        } catch (SQLException e) {
            System.out.println("Não foi possivel carregar o comando SQL");
        }
        return null;
    }

    public Curso carregarCursoBD(int codigo) {
        try {
            ResultSet rs = null;
            String sql = "Select * from cursos where codigo = " + codigo;
            PreparedStatement ps = Persistencia.conexao().prepareStatement(sql);
            rs = ps.executeQuery();

            Curso c = new Curso();
            rs.next();
            c.preencherCursoPeloBanco(rs.getInt("codigoCurso"), rs.getString("nomeCurso"), rs.getInt("cargaHorariaCurso"));
            ps.close();
            return c;

        } catch (SQLException e) {
            System.out.println("Não foi possivel carregar o comando SQL");
        }
        return null;
    }

    public Matricula carregarMatriculaBD(int codigo) {
        try {
            ResultSet rs = null;
            String sql = "Select * from matriculas where numero = " + codigo;
            PreparedStatement ps = Persistencia.conexao().prepareStatement(sql);
            rs = ps.executeQuery();

            Matricula m = new Matricula();
            rs.next();
            m.preencherMatriculaPeloBanco(rs.getInt("codigoMatricula"), rs.getInt("codigoAluno"), rs.getInt("codigoCurso"), rs.getString("data"));
            ps.close();
            return m;

        } catch (SQLException e) {
            System.out.println("Não foi possivel carregar o comando SQL");
        }
        return null;
    }

    public void deleteAluno(int codigo) {
        try {
            String sql = "DELETE from aluno where codigo = " + codigo;
            PreparedStatement ps = Persistencia.conexao().prepareStatement(sql);
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Não foi possivel carregar o comando SQL");
        }
    }

    public void deleteCurso(int codigo) {
        try {
            String sql = "DELETE from curso where codigo = " + codigo;
            PreparedStatement ps = Persistencia.conexao().prepareStatement(sql);
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Não foi possivel carregar o comando SQL");
        }
    }

    public void deleteMatricula(int codigo) {
        try {
            String sql = "DELETE from matricula where numero = " + codigo;
            PreparedStatement ps = Persistencia.conexao().prepareStatement(sql);
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Não foi possivel carregar o comando SQL");
        }
    }

    public static void autor() throws InterruptedException {
        int x = 500;
        System.out.println("*****     *****     *****     *****     *    *");
        Thread.sleep(x);
        System.out.println("*   *     *   *     *         *   *     * *  *");
        Thread.sleep(x);
        System.out.println("*****     *****     ****      *   *     *  * *");
        Thread.sleep(x);
        System.out.println("*         *   *     *         *   *     *   **");
        Thread.sleep(x);
        System.out.println("*         *   *     *         *****     *    *");
        String autor = "Paulo Afonso Cardoso Franco";
        char[] autor2 = autor.toCharArray();
        for (int i = 0; i < autor.length(); i++) {
            Thread.sleep(250);
            System.out.print(autor2[i]);
        }
        System.out.println();
    }

}
