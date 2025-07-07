package trabalho.ban.dados;


public class Palestra {
    private int id, id_local, id_departamento;
    private String titulo, identificadorsala;
    
    public int getId() {
        return id;
    }
    public int getId_local() {
        return id_local;
    }
    public void setId_local(int id_local) {
        this.id_local = id_local;
    }
    public int getId_departamento() {
        return id_departamento;
    }
    public void setId_departamento(int id_departamento) {
        this.id_departamento = id_departamento;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getIdentificadorsala() {
        return identificadorsala;
    }
    public void setIdentificadorsala(String identificadorsala) {
        this.identificadorsala = identificadorsala;
    }
    
    public String toString() {
        return "Palestra: " + this.getTitulo() + ", ID: " + this.getId() + 
               ", identificador da sala: " + this.getIdentificadorsala() +", Local: "+ this.getId_local()+ ", Departamento: " + this.getId_departamento();
    }
}
