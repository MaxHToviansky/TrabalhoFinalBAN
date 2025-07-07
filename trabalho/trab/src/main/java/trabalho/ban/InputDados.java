package trabalho.ban;

import java.util.Scanner;

import trabalho.ban.dados.*;

public class InputDados {
    private static Scanner scanner = null;

    public static void setScanner(Scanner sc){
        scanner = sc;
    }
    public static Pessoa getDadosPessoa(Pessoa p){
        System.out.println("Informe, respectivamente, o nome, nacionalidade, sexo e CPF da pessoa:");        
        if(p==null) p = new Pessoa();
        p.setNome(scanner.nextLine());
        p.setNacionalidade(scanner.nextLine());
        p.setSexo(scanner.nextLine().charAt(0));
        p.setCpf(scanner.nextLong());
        System.out.println("Informe, respectivamente, o dia, mes e ano de aniversario:");        
        p.setDiaNasc(scanner.nextInt());
        p.setMesNasc(scanner.nextInt());
        p.setAnoNasc(scanner.nextInt());
        return p;
    }
    public static Aluno getDadosAluno(){
        Aluno a = (Aluno) getDadosPessoa(new Aluno());
        System.out.println("Informe o semestre e o id do curso do aluno:");
        a.setSemestre(scanner.nextInt());
        a.setCurso(scanner.nextInt());
        return a;
    }
    public static Professor getDadosProfessor(){
        Professor p = (Professor) getDadosPessoa( new Professor());
        System.out.println("Informe a formação e o id do departamento do professor:");
        p.setFormacao(scanner.nextLine());
        p.setDepartamento(scanner.nextInt());
        return p;
    }
    public static Departamento getDadosDepartamento(){
        Departamento d = new Departamento();
        System.out.println("Informe o nome do departamento:");
        d.setNome(scanner.nextLine());
        return d;
    }
    public static Curso getDadosCurso(){
        Curso c = new Curso();
        System.out.println("Informe o nome do curso e o id do departamento:");
        c.setNome(scanner.nextLine());
        c.setId_departamento(scanner.nextInt());
        return c;
    }
    public static Email getDadosEmail(boolean type){
        Email e = new Email();
        if(type){
            System.out.println("Informe o email, o id do local:");
            e.setEmail(scanner.nextLine());
            e.setId_local(scanner.nextInt());
        }else{
            System.out.println("Informe o email, o id da pessoa:");
            e.setEmail(scanner.nextLine());
            e.setId_pessoa(scanner.nextInt());
        }
        return e;
    }
    public static Local getDadosLocal(){
        Local l = new Local();
        System.out.println("Informe a rua, numero, cidade, pais do local");
        l.setRua(scanner.nextLine());
        l.setNumero(scanner.nextLine());
        l.setCidade(scanner.nextLine());
        l.setPais(scanner.nextLine());
        System.out.println("Informe o complemento do local (caso não tenha, digite 'nulo'):");
        String complemento = scanner.nextLine();
        if(!complemento.equals("nulo"))
            l.setComplemento(complemento);
        else
            l.setComplemento(null);
        return l;
    }
    public static Palestra getDadosPalestra(){
        Palestra p = new Palestra();
        System.out.println("Informe o título, o identificador da sala, o id do local e o id do departamento da palestra:");
        p.setTitulo(scanner.nextLine());
        p.setIdentificadorsala(scanner.nextLine());
        p.setId_local(scanner.nextInt());
        p.setId_departamento(scanner.nextInt());
        return p;
    }   
    public static Telefone getDadosTelefone(boolean type){
        Telefone t = new Telefone();
        if(type){
            System.out.println("Informe o número do telefone e o id do local:");
            t.setTelefone(scanner.nextLine());
            t.setId_local(scanner.nextInt());
        }else{
            System.out.println("Informe o número do telefone e o id da pessoa:");
            t.setTelefone(scanner.nextLine());
            t.setId_pessoa(scanner.nextInt());
        }
        return t;
    }

}
