package trabalho.ban.dados;

public class Professor extends Pessoa {
    private int departamento;
    private String formacao;

    
    public String getFormacao() {
        return formacao;
    }
    public int getDepartamento() {
        return departamento;
    }
    public void setDepartamento(int departamento) {
        this.departamento = departamento;
    }

    public void setFormacao(String formacao) {
        this.formacao = formacao;
    }

    public Professor() {
    }
    
    public String toString() {
        return super.toString() + ", Departamento: " + this.getDepartamento() + ", Formação: " + this.getFormacao();
    }
}
