package trabalho.ban;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.util.Pair;
import trabalho.ban.dados.*;
import trabalho.ban.persistencia.*;

public class Queries {
    private static Connection c = Conexao.getConexao();
    
    public static int createPessoa(Pessoa p) throws SQLException{
        PreparedStatement prep = c.prepareStatement("SELECT * FROM Pessoa WHERE CPF = ?");
        prep.setLong(1, p.getCpf());
        ResultSet res = prep.executeQuery();
        if(res.next()) return -1;
        prep = c.prepareStatement("INSERT INTO Pessoa(nome,diaNasc,mesNasc,anoNasc,nacionalidade,sexo,CPF) VALUES (?,?,?,?,?,?,?)");
        prep.setString(1, p.getNome());
        prep.setInt(2, p.getDiaNasc());
        prep.setInt(3, p.getMesNasc());
        prep.setInt(4, p.getAnoNasc());
        prep.setString(5, p.getNacionalidade());
        prep.setString(6, String.valueOf(p.getSexo()));
        prep.setLong(7, p.getCpf());
        prep.executeUpdate();
        prep = c.prepareStatement("SELECT * FROM Pessoa WHERE CPF = ?");
        prep.setLong(1, p.getCpf());
        res = prep.executeQuery();
        
        if(!res.next()) return -1;

        return res.getInt("matricula");
    }

    public static boolean createAluno(Aluno a) throws SQLException{
        int matricula = createPessoa(a);
        if(matricula == -1){
            System.out.println("Erro ao criar aluno: CPF já cadastrado.");
            return false;
        }
        PreparedStatement prep = c.prepareStatement("INSERT INTO Aluno(matricula,id_curso,semestre) VALUES (?,?,?)");
        prep.setInt(1, matricula);
        prep.setInt(2, a.getCurso());
        prep.setInt(3, a.getSemestre());
        prep.executeUpdate();
        return true;
    }    
    public static boolean createProfessor(Professor p) throws SQLException{
        int matricula = createPessoa(p);
        if(matricula == -1){
            System.out.println("Erro ao criar professor: CPF já cadastrado.");
            return false;
        }
        PreparedStatement prep = c.prepareStatement("INSERT INTO Professor(matricula,id_departamento,formacao) VALUES (?,?,?)");
        prep.setInt(1, matricula);
        prep.setInt(2, p.getDepartamento());
        prep.setString(3, p.getFormacao());
        prep.executeUpdate();
        return true;
    }

    public static boolean createLocal(Local l) throws SQLException{
        PreparedStatement prep = c.prepareStatement("INSERT INTO LocalEvento(rua,numero,cidade,pais) VALUES (?,?,?,?)");
        prep.setString(1, l.getRua());
        prep.setString(2, l.getNumero());
        prep.setString(3, l.getCidade());
        prep.setString(4, l.getPais());
        if(l.getComplemento() != null) {
            prep.setString(5, l.getComplemento());
        } else {
            prep.setNull(5, java.sql.Types.VARCHAR);
        }
        prep.executeUpdate();
        return true;
    }
    public static boolean createCurso(Curso C) throws SQLException{
        PreparedStatement prep = c.prepareStatement("INSERT INTO Curso(nome,id_departamento) VALUES (?,?)");

        prep.setString(1, C.getNome());
        prep.setInt(2, C.getId_departamento());
        prep.executeUpdate();
        return true;
    }
    public static boolean createDepartamento(Departamento D) throws SQLException{
        PreparedStatement prep = c.prepareStatement("INSERT INTO Departamento(nome) VALUES (?)");
        prep.setString(1, D.getNome());
        prep.executeUpdate();
        return true;
    }

    public static boolean createPalestra(Palestra p) throws SQLException{
        PreparedStatement prep = c.prepareStatement("INSERT INTO Palestra(titulo,identificadorSala,id_local,id_departamento) VALUES (?,?,?,?)");
        prep.setString(1, p.getTitulo());
        prep.setString(2, p.getIdentificadorsala());
        prep.setInt(3, p.getId_local()); 
        prep.setInt(4, p.getId_departamento());
        // Não está sendo usado dataEvento e hora, pois não consegui implementar a conversao em java
        prep.executeUpdate();
        return true;
    }

    public static boolean createEmail(Email e) throws SQLException{
        PreparedStatement prep = c.prepareStatement("SELECT * FROM Email WHERE email = ?");
        prep.setString(1, e.getEmail());
        ResultSet res = prep.executeQuery();
        if(res.next()) {
            System.out.println("Erro ao criar email: Email já cadastrado.");
            return false;
        }
        if(e.getId_local() != -1){
            prep = c.prepareStatement("INSERT INTO Email(email,id_local) VALUES (?,?)");
            prep.setString(1, e.getEmail());
            prep.setInt(2, e.getId_local());
            prep.executeUpdate();
        } else if(e.getId_pessoa() != -1){
            prep = c.prepareStatement("INSERT INTO Email(email,id_pessoa) VALUES (?,?)");
            prep.setString(1, e.getEmail());
            prep.setInt(2, e.getId_pessoa());
            prep.executeUpdate();
        } else {
            System.out.println("Erro ao criar email: id_local e id_pessoa não podem ser ambos indefinidos.");
            return false;
        }
        return true;
    }

    public static boolean createTelefone(Telefone t) throws SQLException{
        // Verifica se o telefone já existe
        PreparedStatement prep = c.prepareStatement("SELECT * FROM Telefone WHERE numero = ?");
        prep.setString(1, t.getTelefone());
        ResultSet res = prep.executeQuery();
        if(res.next()) {
            System.out.println("Erro ao criar telefone: Telefone já cadastrado.");
            return false;
        }
        if(t.getId_local() != -1){
            prep = c.prepareStatement("INSERT INTO Telefone(telefone,id_local) VALUES (?,?)");
            prep.setString(1, t.getTelefone());
            prep.setInt(2, t.getId_local());
            prep.executeUpdate();
        } else if(t.getId_pessoa() != -1){
            prep = c.prepareStatement("INSERT INTO Telefone(telefone,id_pessoa) VALUES (?,?)");
            prep.setString(1, t.getTelefone());
            prep.setInt(2, t.getId_pessoa());
            prep.executeUpdate();
        } else {
            System.out.println("Erro ao criar telefone: id_local e id_pessoa não podem ser ambos indefinidos.");
            return false;
        }
        return true;
    }

    public static boolean deletePessoa(int matricula) throws SQLException{
        PreparedStatement prep = c.prepareStatement("DELETE FROM Pessoa WHERE matricula = ?");
        prep.setInt(1, matricula);
        int rowsAffected = prep.executeUpdate();
        return rowsAffected > 0;
    }

    public static boolean deleteAluno(int matricula) throws SQLException{
        PreparedStatement prep = c.prepareStatement("DELETE FROM Aluno WHERE matricula = ?");
        prep.setInt(1, matricula);
        int rowsAffected = prep.executeUpdate();
        if(rowsAffected > 0) 
            return deletePessoa(matricula);
        else return false;
    }
    public static boolean deleteProfessor(int matricula) throws SQLException{
        PreparedStatement prep = c.prepareStatement("DELETE FROM Professor WHERE matricula = ?");
        prep.setInt(1, matricula);
        int rowsAffected = prep.executeUpdate();
        if(rowsAffected > 0) 
            return deletePessoa(matricula);
        else return false;
    }
    public static boolean deleteCurso(int id) throws SQLException{
        PreparedStatement prep = c.prepareStatement("DELETE FROM Curso WHERE id_curso = ?");
        prep.setInt(1, id);
        int rowsAffected = prep.executeUpdate();
        return rowsAffected > 0;
    }
    public static boolean deleteLocal(int id) throws SQLException{
        PreparedStatement prep = c.prepareStatement("DELETE FROM LocalEvento WHERE id_local = ?");
        prep.setInt(1, id);
        int rowsAffected = prep.executeUpdate();
        return rowsAffected > 0;
    }

    public static boolean deleteDepartamento(int id) throws SQLException{
        PreparedStatement prep = c.prepareStatement("DELETE FROM Departamento WHERE id_departamento = ?");
        prep.setInt(1, id);
        int rowsAffected = prep.executeUpdate();
        return rowsAffected > 0;
    }
    public static boolean deletePalestra(int id) throws SQLException{
        PreparedStatement prep = c.prepareStatement("DELETE FROM Palestra WHERE id_palestra = ?");
        prep.setInt(1, id);
        int rowsAffected = prep.executeUpdate();
        return rowsAffected > 0;
    }
    public static boolean deleteEmail(String email) throws SQLException{
        PreparedStatement prep = c.prepareStatement("DELETE FROM Email WHERE email = ?");
        prep.setString(1, email);
        int rowsAffected = prep.executeUpdate();
        return rowsAffected > 0;
    }
    public static boolean deleteTelefone(String telefone) throws SQLException{
        PreparedStatement prep = c.prepareStatement("DELETE FROM Telefone WHERE numero = ?");
        prep.setString(1, telefone);
        int rowsAffected = prep.executeUpdate();
        return rowsAffected > 0;
    }
    public static ArrayList<Pessoa> getPessoas() throws SQLException {
        ArrayList<Pessoa> pessoas = new ArrayList<>();
        PreparedStatement prep = c.prepareStatement("SELECT * FROM Pessoa");
        ResultSet res = prep.executeQuery();
        while(res.next()) {
            Pessoa p = new Pessoa();
            p.setMatricula(res.getInt("matricula"));
            p.setNome(res.getString("nome"));
            p.setDiaNasc(res.getInt("diaNasc"));
            p.setMesNasc(res.getInt("mesNasc"));
            p.setAnoNasc(res.getInt("anoNasc"));
            p.setNacionalidade(res.getString("nacionalidade"));
            p.setSexo(res.getString("sexo").charAt(0));
            p.setCpf(res.getLong("CPF"));
            pessoas.add(p);
        }
        return pessoas;
    }
    public static ArrayList<Aluno> getAlunos() throws SQLException {
        ArrayList<Aluno> alunos = new ArrayList<>();
        PreparedStatement prep = c.prepareStatement("SELECT * FROM Aluno");
        ResultSet res = prep.executeQuery();
        while(res.next()) {
            Aluno a = new Aluno();
            a.setMatricula(res.getInt("matricula"));
            a.setCurso(res.getInt("id_curso"));
            a.setSemestre(res.getInt("semestre"));
            alunos.add(a);
        }
        return alunos;
    }
    public static ArrayList<Professor> getProfessores() throws SQLException {
        ArrayList<Professor> professores = new ArrayList<>();
        PreparedStatement prep = c.prepareStatement("SELECT * FROM Professor");
        ResultSet res = prep.executeQuery();
        while(res.next()) {
            Professor p = new Professor();
            p.setMatricula(res.getInt("matricula"));
            p.setDepartamento(res.getInt("id_departamento"));
            p.setFormacao(res.getString("formacao"));
            professores.add(p);
        }
        return professores;
    }
    public static ArrayList<Curso> getCursos() throws SQLException {
        ArrayList<Curso> cursos = new ArrayList<>();
        PreparedStatement prep = c.prepareStatement("SELECT * FROM Curso");
        ResultSet res = prep.executeQuery();
        while(res.next()) {
            Curso c = new Curso();
            c.setId(res.getInt("id_curso"));
            c.setNome(res.getString("nome"));
            c.setId_departamento(res.getInt("id_departamento"));
            cursos.add(c);
        }
        return cursos;
    }
    public static ArrayList<Local> getLocais() throws SQLException {
        ArrayList<Local> locais = new ArrayList<>();
        PreparedStatement prep = c.prepareStatement("SELECT * FROM LocalEvento");
        ResultSet res = prep.executeQuery();
        while(res.next()) {
            Local l = new Local();
            l.setId(res.getInt("id_local"));
            l.setRua(res.getString("rua"));
            l.setNumero(res.getString("numero"));
            l.setCidade(res.getString("cidade"));
            l.setPais(res.getString("pais"));
            l.setComplemento(res.getString("complemento"));
            locais.add(l);
        }
        return locais;
    }
    public static ArrayList<Departamento> getDepartamentos() throws SQLException {
        ArrayList<Departamento> departamentos = new ArrayList<>();
        PreparedStatement prep = c.prepareStatement("SELECT * FROM Departamento");
        ResultSet res = prep.executeQuery();
        while(res.next()) {
            Departamento d = new Departamento();
            d.setId(res.getInt("id_departamento"));
            d.setNome(res.getString("nome"));
            departamentos.add(d);
        }
        return departamentos;
    }
    public static ArrayList<Palestra> getPalestras() throws SQLException {
        ArrayList<Palestra> palestras = new ArrayList<>();
        PreparedStatement prep = c.prepareStatement("SELECT * FROM Palestra");
        ResultSet res = prep.executeQuery();
        while(res.next()) {
            Palestra p = new Palestra();
            p.setId(res.getInt("id_palestra"));
            p.setTitulo(res.getString("titulo"));
            p.setIdentificadorsala(res.getString("identificadorSala"));
            p.setId_local(res.getInt("id_local"));
            p.setId_departamento(res.getInt("id_departamento"));
            
            palestras.add(p);
        }
        return palestras;
    }

    public static ArrayList<Email> getEmails() throws SQLException {
        ArrayList<Email> emails = new ArrayList<>();
        PreparedStatement prep = c.prepareStatement("SELECT * FROM Email");
        ResultSet res = prep.executeQuery();
        while(res.next()) {
            Email e = new Email();
            e.setEmail(res.getString("email"));
            e.setId_local(res.getInt("id_local"));
            e.setId_pessoa(res.getInt("id_pessoa"));
            emails.add(e);
        }
        return emails;
    }

    public static ArrayList<Telefone> getTelefones() throws SQLException {
        ArrayList<Telefone> telefones = new ArrayList<>();
        PreparedStatement prep = c.prepareStatement("SELECT * FROM Telefone");
        ResultSet res = prep.executeQuery();
        while(res.next()) {
            Telefone t = new Telefone();
            t.setTelefone(res.getString("numero"));
            t.setId_local(res.getInt("id_local"));
            t.setId_pessoa(res.getInt("id_pessoa"));
            telefones.add(t);
        }
        return telefones;
    }

    
    public static boolean addParticipante(int id_palestra, int matricula) throws SQLException{
        
        PreparedStatement prep = c.prepareStatement("SELECT * FROM Pessoa WHERE matricula = ?");
        prep.setInt(1, matricula);
        ResultSet res = prep.executeQuery();
        if(!res.next()) {
            System.out.println("Erro ao adicionar participante: Matricula não encontrada.");
            return false;
        }
        
        prep = c.prepareStatement("SELECT * FROM Palestra WHERE id_palestra = ?");
        prep.setInt(1, id_palestra);
        res = prep.executeQuery();
        if(!res.next()) {
            System.out.println("Erro ao adicionar participante: Palestra não encontrada.");
            return false;
        }
        
        prep = c.prepareStatement("INSERT INTO Participante(id_palestra,id_pessoa) VALUES (?,?)");
        prep.setInt(1, id_palestra);
        prep.setInt(2, matricula);
        int rowsAffected = prep.executeUpdate();
        return rowsAffected > 0;
    }

    public static boolean deleteParticipante(int id_palestra, int matricula) throws SQLException{
        
        PreparedStatement prep = c.prepareStatement("SELECT * FROM Participante WHERE id_palestra = ? AND id_pessoa = ?");
        prep.setInt(1, id_palestra);
        prep.setInt(2, matricula);
        ResultSet res = prep.executeQuery();
        if(!res.next()) {
            System.out.println("Erro ao remover participante: Participante não encontrado na palestra.");
            return false;
        }
        
        prep = c.prepareStatement("DELETE FROM Participante WHERE id_palestra = ? AND id_pessoa = ?");
        prep.setInt(1, id_palestra);
        prep.setInt(2, matricula);
        int rowsAffected = prep.executeUpdate();
        return rowsAffected > 0;
    }

    public static boolean deletePalestrante(int id_palestra, int matricula) throws SQLException{
        
        PreparedStatement prep = c.prepareStatement("SELECT * FROM Palestrante WHERE id_palestra = ? AND id_pessoa = ?");
        prep.setInt(1, id_palestra);
        prep.setInt(2, matricula);
        ResultSet res = prep.executeQuery();
        if(!res.next()) {
            System.out.println("Erro ao remover palestrante: Palestrante não encontrado na palestra.");
            return false;
        }
        
        prep = c.prepareStatement("DELETE FROM Palestrante WHERE id_palestra = ? AND id_pessoa = ?");
        prep.setInt(1, id_palestra);
        prep.setInt(2, matricula);
        int rowsAffected = prep.executeUpdate();
        return rowsAffected > 0;
    }

    public static boolean addPalestrante(int id_palestra, int matricula) throws SQLException{
        
        PreparedStatement prep = c.prepareStatement("SELECT * FROM Pessoa WHERE matricula = ?");
        prep.setInt(1, matricula);
        ResultSet res = prep.executeQuery();
        if(!res.next()) {
            System.out.println("Erro ao adicionar palestrante: Matricula não encontrada.");
            return false;
        }
        
        prep = c.prepareStatement("SELECT * FROM Palestra WHERE id_palestra = ?");
        prep.setInt(1, id_palestra);
        res = prep.executeQuery();
        if(!res.next()) {
            System.out.println("Erro ao adicionar palestrante: Palestra não encontrada.");
            return false;
        }

        prep = c.prepareStatement("INSERT INTO Palestrante(id_palestra,id_pessoa) VALUES (?,?)");
        prep.setInt(1, id_palestra);
        prep.setInt(2, matricula);
        int rowsAffected = prep.executeUpdate();
        return rowsAffected > 0;
    }

    public static ArrayList<Pessoa> getParticipantes(int id_palestra) throws SQLException{
        ArrayList<Pessoa> participantes = new ArrayList<>();
        PreparedStatement prep = c.prepareStatement("SELECT Pessoa.* FROM Pessoa " +
            "JOIN Participante ON Pessoa.matricula = Participante.id_pessoa " +
            "WHERE Participante.id_palestra = ?");
        prep.setInt(1, id_palestra);
        ResultSet res = prep.executeQuery();
        while(res.next()) {
            Pessoa p = new Pessoa();
            p.setMatricula(res.getInt("matricula"));
            p.setNome(res.getString("nome"));
            p.setDiaNasc(res.getInt("diaNasc"));
            p.setMesNasc(res.getInt("mesNasc"));
            p.setAnoNasc(res.getInt("anoNasc"));
            p.setNacionalidade(res.getString("nacionalidade"));
            p.setSexo(res.getString("sexo").charAt(0));
            p.setCpf(res.getLong("CPF"));
            participantes.add(p);
        }
        return participantes;
    }

    public static ArrayList<Pair<Pessoa,Integer>> getMaisParticipantes() throws SQLException {
        ArrayList<Pair<Pessoa,Integer>> pessoas = new ArrayList<>();
        PreparedStatement prep = c.prepareStatement("SELECT Pessoa.*, COUNT(Participante.id_pessoa) AS num_participacoes " +
            "FROM Pessoa JOIN Participante ON Pessoa.matricula = Participante.id_pessoa " +
            "GROUP BY Pessoa.matricula ORDER BY num_participacoes");
        ResultSet res = prep.executeQuery();
        while(res.next()) {
            Pessoa p = new Pessoa();
            p.setMatricula(res.getInt("matricula"));
            p.setNome(res.getString("nome"));
            p.setDiaNasc(res.getInt("diaNasc"));
            p.setMesNasc(res.getInt("mesNasc"));
            p.setAnoNasc(res.getInt("anoNasc"));
            p.setNacionalidade(res.getString("nacionalidade"));
            p.setSexo(res.getString("sexo").charAt(0));
            p.setCpf(res.getLong("CPF"));
            Pair<Pessoa,Integer> pr = new Pair<>(p, res.getInt("num_participacoes"));
            pessoas.add(pr);
        }
        return pessoas;
    }
}
