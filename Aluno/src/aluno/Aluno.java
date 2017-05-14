public class ArvoreBinaria {
    private No raiz;
    private ArvoreBinaria arvoreEsquerda;
    private ArvoreBinaria arvoreDireita;

    public ArvoreBinaria() { }

    public ArvoreBinaria getArvoreDireita() {
        return arvoreDireita;
    }

    public void setArvoreDireita(ArvoreBinaria arvoreDireita) {
        this.arvoreDireita = arvoreDireita;
    }

    public ArvoreBinaria getArvoreEsquerda() {
        return arvoreEsquerda;
    }

    public void setArvoreEsquerda(ArvoreBinaria arvoreEsquerda) {
        this.arvoreEsquerda = arvoreEsquerda;
    }

    public No getRaiz() {
        return raiz;
    }

    public void setRaiz(No raiz) {
        this.raiz = raiz;
    }

    public void insereAluno(int matricula, String nome) {
        Aluno aluno = new Aluno(matricula, nome);
        No no = new No(aluno);
        inserir(no);
    }

    public void inserir(No no) {
        if (this.raiz == null) {
            this.raiz = no;
        } else {
            if (no.getAluno().getMatricula() > this.raiz.getAluno().getMatricula()) {
                if (this.arvoreDireita == null) {
                    this.arvoreDireita = new ArvoreBinaria();
                }
                this.arvoreDireita.inserir(no);
            } else if (no.getAluno().getMatricula() < this.raiz.getAluno().getMatricula()) {
                if (this.arvoreEsquerda == null) {
                    this.arvoreEsquerda = new ArvoreBinaria();
                }
                this.arvoreEsquerda.inserir(no);
            }
        }
    }

    public void percorrerInOrder() {
        if (this.raiz == null) {
           return;
        }

        if (this.arvoreEsquerda != null) {
            this.arvoreEsquerda.percorrerInOrder();
        }

        System.out.println("Matrícula: " + this.raiz.getAluno().getMatricula());
        System.out.println("Nome: " + this.raiz.getAluno().getNome());
        
        if (this.arvoreDireita != null) {
            this.arvoreDireita.percorrerInOrder();
        }
    }

    public void percorrerPreOrder() {
        if (this.raiz == null) {
           return;
        }

        System.out.println("Matrícula: " + this.raiz.getAluno().getMatricula());
        System.out.println("Nome: " + this.raiz.getAluno().getNome());

        if (this.arvoreEsquerda != null) {
            this.arvoreEsquerda.percorrerPreOrder();
        }

        if (this.arvoreDireita != null) {
            this.arvoreDireita.percorrerPreOrder();
        }
    }

    public void percorrerPostOrder() {
        if (this.raiz == null) {
           return;
        }

        if (this.arvoreEsquerda != null) {
            this.arvoreEsquerda.percorrerPostOrder();
        }

        if (this.arvoreDireita != null) {
            this.arvoreDireita.percorrerPostOrder();
        }

        System.out.println("Matrícula: " + this.raiz.getAluno().getMatricula());
        System.out.println("Nome: " + this.raiz.getAluno().getNome());
    }

    public Aluno busca(int matricula) {
        if (this.raiz == null) {
            return null;
        } else {
            if (matricula == this.raiz.getAluno().getMatricula()) {
                return this.raiz.getAluno();
            } else {
                if (matricula > this.raiz.getAluno().getMatricula()) {
                    if (this.arvoreDireita == null) {
                        return null;
                    }
                    return this.arvoreDireita.busca(matricula);
                } else {
                    if (this.arvoreEsquerda == null) {
                        return null;
                    }
                    return this.arvoreEsquerda.busca(matricula);
                }
            }
        }
    }

    public class No {
        private Aluno aluno;

        public No(Aluno aluno) {
            this.aluno = aluno;
        }

        public Aluno getAluno() {
            return aluno;
        }

        public void setAluno(Aluno aluno) {
            this.aluno = aluno;
        }
    }

    public class Aluno {
        private int matricula;
        private String nome;

        public Aluno(int mat, String nome) {
            this.matricula = mat;
            this.nome = nome;
        }

        public int getMatricula() {
            return matricula;
        }

        public void setMatricula(int mat) {
            this.matricula = mat;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }
    }
}
