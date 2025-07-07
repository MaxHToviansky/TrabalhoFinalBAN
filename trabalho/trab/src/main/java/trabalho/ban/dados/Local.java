package trabalho.ban.dados;

public class Local {
    private int id;
    private String rua, numero, cidade, pais, complemento;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getRua() {
        return rua;
    }
    public void setRua(String rua) {
        this.rua = rua;
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    public String getPais() {
        return pais;
    }
    public void setPais(String pais) {
        this.pais = pais;
    }
    public String getComplemento() {
        return complemento;
    }
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
    
    public String toString() {
        if(complemento!=null)
            return "Local: " + this.getRua() + ", " + this.getNumero() + ", " + this.getCidade() + 
               ", " + this.getPais() + ", Complemento: " + this.getComplemento();
        else
            return "Local: " + this.getRua() + ", " + this.getNumero() + ", " + this.getCidade() + 
               ", " + this.getPais();
    }
}

