package trabalho.ban;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.util.Pair;
import trabalho.ban.dados.*;
import trabalho.ban.Queries;
import trabalho.ban.persistencia.Conexao;

public class App {
    private static Scanner sc = new Scanner(System.in);

    public static void printPessoas(ArrayList<Pessoa> list) {
        if (list.isEmpty()) {
            System.out.println("Esta vazia!");
        } else {
            for (Pessoa obj : list) {
                System.out.println(obj.toString());
            }
        }
    }

    public static void printAlunos(ArrayList<Aluno> list) {
        if (list.isEmpty()) {
            System.out.println("Esta vazia!");
        } else {
            for (Aluno obj : list) {
                System.out.println(obj.toString());
            }
        }
    }

    public static void printProfessores(ArrayList<Professor> list) {
        if (list.isEmpty()) {
            System.out.println("Esta vazia!");
        } else {
            for (Professor obj : list) {
                System.out.println(obj.toString());
            }
        }
    }

    public static void printCursos(ArrayList<Curso> list) {
        if (list.isEmpty()) {
            System.out.println("Esta vazia!");
        } else {
            for (Curso obj : list) {
                System.out.println(obj.toString());
            }
        }
    }

    public static void printDepartamentos(ArrayList<Departamento> list) {
        if (list.isEmpty()) {
            System.out.println("Esta vazia!");
        } else {
            for (Departamento obj : list) {
                System.out.println(obj.toString());
            }
        }
    }

    public static void printPalestra(ArrayList<Palestra> list) {
        if (list.isEmpty()) {
            System.out.println("Esta vazia!");
        } else {
            for (Palestra obj : list) {
                System.out.println(obj.toString());
            }
        }
    }

    public static void printMaisFrequentes(ArrayList<Pair<Pessoa, Integer>> list) {
        if (list.isEmpty()) {
            System.out.println("Esta vazia!");
        } else {
            for (Pair<Pessoa, Integer> obj : list) {
                System.out.print(obj.getKey());
                System.out.println(obj.getValue());
            }
        }
    }

    public static void printMenu() {
        System.out.println("1 - Inserir aluno");
        System.out.println("2 - Inserir professor");
        System.out.println("3 - Inserir curso");
        System.out.println("4 - Inserir departamento");
        System.out.println("5 - Inserir palestra");
        System.out.println("6 - Inserir participante");
        System.out.println("7 - Inserir palestrante");
        System.out.println("8 - Deletar aluno");
        System.out.println("9 - Deletar professor");
        System.out.println("10 - Deletar curso");
        System.out.println("11 - Deletar departamento");
        System.out.println("12 - Deletar palestra");
        System.out.println("13 - Deletar participante");
        System.out.println("14 - Deletar palestrante");
        System.out.println("15 - Listar pessoas");
        System.out.println("16 - Listar alunos");
        System.out.println("17 - Listar professores");
        System.out.println("18 - Listar cursos");
        System.out.println("19 - Listar departamentos");
        System.out.println("20 - Listar palestras");
        System.out.println("21 - Listar participantes");
        System.out.println("22 - Listar pessoas mais participativas");
        System.out.println("23 - Sair");
    }

    public static void main(String[] args) {
        Conexao.setSenha("postgres");
        try {
            Conexao.createConnection();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (Conexao.getConexao() != null)
            System.out.println("Conectado ao banco de dados");
        else {
            System.out.println("Falha na conexão!");
            return;
        }
        InputDados.setScanner(sc);
        int matricula = 0, palestra = 0;
        int opcao = 0;
        while (opcao != 23) {
            printMenu();
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();
            try {
                switch (opcao) {
                    case 1:
                        Queries.createAluno(InputDados.getDadosAluno());
                        break;
                    case 2:
                        Queries.createProfessor(InputDados.getDadosProfessor());
                        break;
                    case 3:
                        Queries.createCurso(InputDados.getDadosCurso());
                        break;
                    case 4:
                        Queries.createDepartamento(InputDados.getDadosDepartamento());
                        break;
                    case 5:
                        Queries.createPalestra(InputDados.getDadosPalestra());
                        break;
                    case 6:
                        System.out.println("Informe o id da palestra e o id do participante:");
                        int idPalestra = sc.nextInt();
                        matricula = sc.nextInt();
                        Queries.addParticipante(idPalestra, matricula);
                        break;
                    case 7:
                        System.out.println("Informe o id da palestra e o id do palestrante:");
                        int idPalestra_ = sc.nextInt();
                        matricula = sc.nextInt();
                        Queries.addParticipante(idPalestra_, matricula);
                        break;
                    case 8:
                        System.out.println("Informe o id do aluno a ser deletado:");
                        Queries.deleteAluno(sc.nextInt());
                        break;
                    case 9:
                        System.out.println("Informe o id do professor a ser deletado:");
                        Queries.deleteProfessor(sc.nextInt());
                        break;
                    case 10:
                        System.out.println("Informe o id do curso a ser deletado:");
                        Queries.deleteCurso(sc.nextInt());
                        break;
                    case 11:
                        System.out.println("Informe o id do departamento a ser deletado:");
                        Queries.deleteDepartamento(sc.nextInt());
                        break;
                    case 12:
                        System.out.println("Informe o id da palestra a ser deletada:");
                        Queries.deletePalestra(sc.nextInt());
                        break;
                    case 13:
                        System.out.println("Informe o id do participante a ser deletado e o id da palestra:");
                        matricula = sc.nextInt();
                        palestra = sc.nextInt();
                        Queries.deleteParticipante(matricula, palestra);
                        break;
                    case 14:
                        System.out.println("Informe o id do palestrante a ser deletado e o id da palestra:");
                        matricula = sc.nextInt();
                        palestra = sc.nextInt();
                        Queries.deleteParticipante(matricula, palestra);
                        break;
                    case 15:

                        printPessoas(Queries.getPessoas());
                        break;
                    case 16:
                        printAlunos(Queries.getAlunos());
                        break;
                    case 17:
                        printProfessores(Queries.getProfessores());
                        break;
                    case 18:
                        printCursos(Queries.getCursos());
                        break;
                    case 19:
                        printDepartamentos(Queries.getDepartamentos());
                        break;
                    case 20:
                        printPalestra(Queries.getPalestras());
                        break;
                    case 21:
                        System.out.println("Informe o id da palestra: ");
                        printPessoas(Queries.getParticipantes(sc.nextInt()));
                        break;
                    case 22:
                        printMaisFrequentes(Queries.getMaisParticipantes());
                        break;
                    case 23:
                        break;
                    case 30:
                        matricula = sc.nextInt();
                        Queries.deletePessoa(matricula);
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (SQLException e) {
                System.out.println("Erro de SQL! " + e.getMessage());

            }
        }
    }
}
