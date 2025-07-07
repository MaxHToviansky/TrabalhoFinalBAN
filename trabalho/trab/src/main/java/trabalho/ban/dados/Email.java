package trabalho.ban.dados;

public class Email {
    private String email;
    private int id_local=-1, id_pessoa=-1;

    public String getEmail() {
        return email;
    }

    public int getId_local() {
        return id_local;
    }

    public void setId_local(int id_local) {
        this.id_local = id_local;
    }

    public int getId_pessoa() {
        return id_pessoa;
    }

    public void setId_pessoa(int id_pessoa) {
        this.id_pessoa = id_pessoa;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String toString() {
        if(this.getId_local() != -1)
            return "Email: " + this.getEmail() + ", ID Local: " + this.getId_local();
        else if(this.getId_pessoa() != -1)
            return "Email: " + this.getEmail() + ", ID Pessoa: " + this.getId_pessoa();
        return "Email: " + this.getEmail();
    }
    

}
