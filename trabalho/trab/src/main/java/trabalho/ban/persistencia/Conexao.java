package trabalho.ban.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static Connection conexao = null;
    private static String senha;

    private Conexao(){
    }

    public static Connection getConexao(){
        return conexao;
    }
    public static void createConnection() throws ClassNotFoundException, SQLException{
        if(conexao==null){
            String url = "jdbc:postgresql://localhost:5432/TrabalhoBAN";
            String usuario = "postgres";
            Class.forName("org.postgresql.Driver");
            conexao = DriverManager.getConnection(url, usuario, senha);
        }
        
    }

    public static void setSenha(String word) {
        senha = word;
    }

}
