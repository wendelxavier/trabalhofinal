import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import java.sql.SQLException;

public class Teste3 {
    static Connection conexao = null;
    static Scanner ler = new Scanner(System.in);
    private static Garcom gDadosAtualizados;


    public static void main(String[] args) throws Exception {
        String nomeGarcom, dataNasc, email, sexo, cpf, telefone;
        float salarioFixo;


        System.out.println("Digite os dados do Garçom");
        System.out.println("Digite o nome");
        nomeGarcom = ler.nextLine();
        System.out.println("Digite o sexo");
        sexo = ler.nextLine();
        System.out.println("Digite a data de nascimento");
        dataNasc = ler.nextLine();
        System.out.println("Digite o Email");
        email = ler.nextLine();
        System.out.println("Digite o seu CPF");
        cpf = ler.nextLine();
        System.out.println("Digite o telefone de contato");
        telefone = ler.nextLine();
        System.out.println("Digite o salário fixo");
        salarioFixo = Float.parseFloat(ler.next());

        //INSERINDO UM GARÇOM
        Garcom g = new Garcom(nomeGarcom, cpf, dataNasc, email, telefone, sexo, salarioFixo);
        inserirGarcom(g);

        //REMOVENDO UM GARÇOM
        System.out.println("\n Digite o email do garçom que deseja remover:");
        String emailGarcomRemover = ler.next();
        try {
            removerGarcomPeloEmail(emailGarcomRemover);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        //BUSCANDO UM GARÇOM
        System.out.println("\nDigite o email do garçom que deseja buscar:");
        String emailGarcomBuscado = ler.next();
        Garcom gEncontrado;
        try {
            gEncontrado = buscarGarcomPeloEmail(emailGarcomBuscado);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (gEncontrado == null) {
            System.out.println("Garçom não encontrado");
        } else {
            System.out.println("Nome: " + gEncontrado.getNomeGarcom());
            System.out.println("Telefone: " + gEncontrado.getTelefone());
            System.out.println("Data de Nascimento: " + gEncontrado.getDataNasc());
            System.out.println("Email: " + gEncontrado.getEmail());
            System.out.println("CPF: " + gEncontrado.getCpf());
            System.out.println("Sexo: " + gEncontrado.getSexo());
            System.out.println("Salário Fixo: " + gEncontrado.getSalarioFixo());
            System.out.println("\n");
        }

        //ALTERANDO UM GARÇOM
//        System.out.println("Digite os dados atuais do garçom que deseja alterar");
//        System.out.println("Nome: ");
//        nomeGarcom = ler.next();
//        System.out.println("Sexo: ");
//        sexo = ler.next();
//        System.out.println("Data de Nascimento: ");
//        dataNasc = ler.next();
//        System.out.println("Email: ");
//        email = ler.next();
//        System.out.println("CPF: ");
//        cpf = ler.next();
//        System.out.println("Telefone: ");
//        telefone = ler.next();
//        System.out.println("Salário Fixo: ");
//        salarioFixo = Float.parseFloat(ler.next());
//
//        Garcom gDadosAtualizados = new Garcom(nomeGarcom, cpf, dataNasc, email, telefone, sexo, salarioFixo);
//        alterarGarcom(gDadosAtualizados);

        System.out.println("Digite o email do Garçom que deseja atualizar:");
                emailGarcomBuscado = ler.next();
                Garcom garcomExistente = buscarGarcomPeloEmail(emailGarcomBuscado);

        if (garcomExistente != null) {
                    boolean sair = false;

                    while (!sair) {
                        System.out.println("Digite os novos dados do Garçom");
                        System.out.println("Digite o Nome:");
                        nomeGarcom = ler.next();
                        System.out.println("Digite a Data de Nascimento:");
                        dataNasc = ler.next();
                        System.out.println("Digite o Sexo:");
                        sexo = ler.next();
                        System.out.println("Digite o CPF:");
                        cpf = ler.next();
                        System.out.println("Digite o Telefone:");
                        telefone = ler.next();
                        System.out.println("Digite o Email:");
                        email = ler.next();
                        System.out.println("Digite o Salário:");
                        salarioFixo = Float.parseFloat(ler.next());

                        Garcom gDadosAtualizados = new Garcom(nomeGarcom, dataNasc, sexo, cpf, telefone, email, salarioFixo);
                        alterarGarcom(gDadosAtualizados);
                        System.out.println("Deseja atualizar mais informações do garçom? (S/N)");
                        String opcaoSair = ler.next();
                        if (opcaoSair.equalsIgnoreCase("N")) {
                            sair = true;
                        }

                    }
        }
                else {
                    System.out.println("Nenhum garçom encontrado com o email informado.");
                }
//        try {
//            alterarGarcom(gDadosAtualizados);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
    }

    private static void inserirGarcom(Garcom glida) throws Exception {
            conexao = ConexaoBD.getInstance();
            String sql = "insert into garcom (nomeGarcom, cpf, dataNasc, email, telefone, sexo, salarioFixo)" +
                    "values (?,?,?,?,?,?,?)";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, glida.getNomeGarcom());
            stmt.setString(2, glida.getCpf());
            stmt.setString(3, glida.getDataNasc());
            stmt.setString(4, glida.getEmail());
            stmt.setString(5, glida.getTelefone());
            stmt.setString(6, glida.getSexo());
            stmt.setFloat(7, glida.getSalarioFixo());

            stmt.execute();
            stmt.close();
    }

    public static void removerGarcomPeloEmail (String emailGarcomRemovido) throws Exception {
        conexao = ConexaoBD.getInstance();
        String sql = "delete from garcom where email like ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setString(1, emailGarcomRemovido);

        stmt.execute();
        stmt.close();
    }

    public static Garcom buscarGarcomPeloEmail(String emailBuscado) throws Exception {
        conexao = ConexaoBD.getInstance();
        String sql = "select * from garcom where email like ?";

        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setString(1, emailBuscado);
        ResultSet resultado; /*importar a classe ResulSet para recuperar
                                                        os dados que o select selecionou*/
        resultado = stmt.executeQuery();
        Garcom g = null;
        if (resultado.next()) {
            g = new Garcom(resultado.getString("nomeGarcom"),
                    resultado.getString("dataNasc"),
                    resultado.getString("cpf"),
                    resultado.getString("email"),
                    resultado.getString("telefone"),
                    resultado.getNString("sexo"),
                    resultado.getFloat("salarioFixo"));
        }
        resultado.close();
        stmt.close();
        return g;
    }
    public static void alterarGarcom (Garcom gSendoAlterado) throws Exception {
        try {
            conexao = ConexaoBD.getInstance();
            String sql = "UPDATE garcom SET nomeGarcom = ?, dataNasc = ?, sexo = ?, cpf = ?, telefone = ?, " +
                    "email = ?, salarioFixo = ? " + "WHERE nomeGarcom = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, gSendoAlterado.getNomeGarcom());
            stmt.setString(2, gSendoAlterado.getDataNasc());
            stmt.setString(3, gSendoAlterado.getSexo());
            stmt.setString(4, gSendoAlterado.getCpf());
            stmt.setString(5, gSendoAlterado.getTelefone());
            stmt.setString(6, gSendoAlterado.getEmail());
            stmt.setFloat(7, gSendoAlterado.getSalarioFixo());
            stmt.setString(8, gSendoAlterado.getNomeGarcom());

            int linhasAfetadas = stmt.executeUpdate();
            System.out.println("Linhas afetadas: " + linhasAfetadas);
        } catch (SQLException e) {
        e.printStackTrace();
    }

//        stmt.execute();
//        stmt.close();

            /* não feche a conexão com o banco de dados para que possa continuar cadastranho
            conexao.close();
           */
}

}






//private static void exibirMenu(Garçom garcom) throws SQLException, ClassNotFoundException {
//        int opcao = -1;
//
//        while (opcao != 0) {
//            System.out.println("\n-- Menu --");
//            System.out.println("1. Inserir Garçom");
//            System.out.println("2. Atualizar Garçom");
//            System.out.println("3. Deletar Garçom");
//            System.out.println("4. Buscar Garçom");
//            System.out.println("0. Sair");
//            System.out.println("Escolha uma opção:");
//
//            opcao = ler.nextInt();
//
//            switch (opcao) {
//                case 1:
//                    inserirGarçom(garcom);
//                    break;
//                case 2:
//                    atualizarGarcom(garcom);
//                    break;
//                case 3:
//                    deletarGarcom(garcom.getId_garcom());
//                    break;
//                case 4:
//                    buscarGarcom(garcom.getId_garcom());
//                    break;
//                case 0:
//                    System.out.println("Saindo...");
//                    break;
//                default:
//                    System.out.println("Opção inválida");
//                    break;
//            }
//        }
//    }
//
//    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//
//        int id_garcom, Telefone;
//        String nome, Sexo, Turno, Email, Data_de_Nascimento, CPF;
//        Float Salario;
//
//        System.out.println("-- Menu Principal --");
//        System.out.println("1. Inserir Garçom");
//        System.out.println("2. Atualizar Garçom");
//        System.out.println("3. Deletar Garçom");
//        System.out.println("4. Buscar Garçom");
//        System.out.println("0. Sair");
//        System.out.println("Escolha uma opção:");
//
//        int opcao = ler.nextInt();
//        ler.nextLine();
//
//        switch (opcao) {
//
//            case 1:
//                System.out.println("Digite os dados do Garçom");
//                System.out.println("Digite o ID:");
//                id_garcom = ler.nextInt();
//                System.out.println("Digite o Nome:");
//                nome = nomeScanner.nextLine();
//                System.out.println("Digite a Data de Nascimento:");
//                Data_de_Nascimento = ler.next();
//                System.out.println("Digite o Sexo:");
//                Sexo = ler.next();
//                System.out.println("Digite o CPF:");
//                CPF = ler.next();
//                System.out.println("Digite o Telefone:");
//                Telefone = ler.nextInt();
//                System.out.println("Digite o Turno:");
//                Turno = ler.next();
//                System.out.println("Digite o Email:");
//                Email = ler.next();
//                System.out.println("Digite o Salário:");
//                Salario = Float.parseFloat(ler.next());
//
//                Garçom g = new Garçom(id_garcom, nome, Data_de_Nascimento, Sexo, Salario, Turno, Telefone, Email, CPF);
//                inserirGarçom(g);
//                break;
//
//
//
//            case 2:
//                System.out.println("Digite o ID do Garçom que deseja atualizar:");
//                id_garcom = ler.nextInt();
//
//                Garçom garcomExistente = buscarGarcom(id_garcom);
//                if (garcomExistente == null) {
//                    boolean sair = false;
//
//                    while (!sair){
//                    System.out.println("Digite os novos dados do Garçom");
//                    System.out.println("Digite o Nome:");
//                    nome = nomeScanner.nextLine();
//                    System.out.println("Digite a Data de Nascimento:");
//                    Data_de_Nascimento = ler.next();
//                    System.out.println("Digite o Sexo:");
//                    Sexo = ler.next();
//                    System.out.println("Digite o CPF:");
//                    CPF = ler.next();
//                    System.out.println("Digite o Telefone:");
//                    Telefone = ler.nextInt();
//                    System.out.println("Digite o Turno:");
//                    Turno = ler.next();
//                    System.out.println("Digite o Email:");
//                    Email = ler.next();
//                    System.out.println("Digite o Salário:");
//                    Salario = Float.parseFloat(ler.next());
//
//                    Garçom gAtualizado = new Garçom(id_garcom, nome, Data_de_Nascimento, Sexo, Salario, Turno, Telefone, Email, CPF);
//                    atualizarGarcom(gAtualizado);
//                    System.out.println("Deseja atualizar mais informações do garçom? (S/N)");
//                    String opcaoSair = ler.next();
//                    if (opcaoSair.equalsIgnoreCase("N")){
//                        sair = true;
//                    }
//                    }
//                }
//                else {
//                    System.out.println("Nenhum garçom encontrado com o ID informado.");
//                }
//                break;
//
//
//
//            case 3:
//                System.out.println("Digite o ID do Garçom que deseja deletar:");
//                id_garcom = ler.nextInt();
//
//                deletarGarcom(id_garcom);
//                break;
//
//
//
//            case 4:
//                System.out.println("Digite o ID do Garçom que deseja buscar:");
//                id_garcom = ler.nextInt();
//
//                Garçom garcomBuscado = buscarGarcom(id_garcom);
//                if (garcomBuscado != null) {
//                    System.out.println("Garçom encontrado:");
//                    System.out.println("Nome: " + garcomBuscado.getNome());
//                    // ... exibir outros dados do garçom ...
//                } else {
//                    System.out.println("Nenhum garçom encontrado com o ID informado.");
//                }
//                break;
//
//
//
//            case 0:
//                System.out.println("Saindo...");
//                break;
//            default:
//                System.out.println("Opção inválida");
//                break;
//        }
//    }