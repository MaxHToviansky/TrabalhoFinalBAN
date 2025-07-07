package trabalho.ban.dados;

public class Curso {
    private String nome;
    private int id, id_departamento;
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getId_departamento() {
        return id_departamento;
    }
    public void setId_departamento(int id_departamento) {
        this.id_departamento = id_departamento;
    }
    
    public String toString() {
        return "Curso: " + this.getNome() + ", ID: " + this.getId() + 
               ", Departamento: " + this.getId_departamento();
    }

}
