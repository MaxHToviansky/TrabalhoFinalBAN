package trabalho.ban.dados;

public class Aluno extends Pessoa{
    private int semestre, curso;

    public int getSemestre() {
        return semestre;
    }

    public int getCurso() {
        return curso;
    }

    public void setCurso(int curso) {
        this.curso = curso;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public Aluno() {
        
    }
    public String toString(){
        return super.toString()+ ", Curso: " + this.getCurso() + ", Semestre: " + this.getSemestre();
    }

    
    
}
