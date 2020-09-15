import java.sql.*;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

class Pousada {
    List<Reserva> reservas;

    public Pousada() {
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public String adicionar(Reserva reserva) {
        try {

            if (reserva.getDataEntrada().compareTo(LocalDate.now()) < 0)
                return "A Data de Entrada não pode ser menor do que a Data Atual";

            Period periodo = Period.between(reserva.getDataEntrada(), reserva.getDataSaida());
            int totalDias = periodo.getDays() + (periodo.getMonths() * 30) + (periodo.getYears() * 365);
            if (totalDias  < 2)
                return "A Data de Saida deve ser no minimo 2 dias após a Data de Entrada";

            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "bancouser",
                    "bancosenha");
            // System.out.println("Conexao realizada com sucesso!");
            Statement stmt = conn.createStatement();

            String dataEntrada = reserva.getDataEntrada().toString();
            String dataSaida = reserva.getDataSaida().toString();

            int resultado = stmt.executeUpdate(
                    "INSERT INTO TB_RESERVAS(NUMERO, QTDE_PESSOAS, NUMERO_QUARTO, DATA_ENTRADA, DATA_SAIDA) "
                            + "VALUES (sqc_reservas.NEXTVAL ," + reserva.getQtdePessoas() + ","
                            + reserva.getNumeroQuarto() + "," + "TO_DATE('" + dataEntrada + "','yyyy-mm-dd'),"
                            + "TO_DATE('" + dataSaida + "','yyyy-mm-dd'))");

            if (resultado > 1) {
                System.out.println("Reserva Adicionada com sucesso");
            }
            conn.close();

        } catch (ClassNotFoundException e) {
            System.err.printf("O driver JDBC nao foi encontrado: %s\n", e.getMessage());
        } catch (SQLException e) {
            System.err.println("Nao foi possavel estabelecer a conexao com o banco de dados");
            System.err.println(e.getMessage());
        }

        return "";
    }

    public ArrayList<Reserva> consulta() {

        try {
            ArrayList<Reserva> reservas = new ArrayList<Reserva>();

            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "bancouser",
                    "bancosenha");
            // System.out.println("Conexao realizada com sucesso!");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM TB_RESERVAS");
            while (rs.next()) {
                int numero = rs.getInt("NUMERO");
                int quarto = rs.getInt("NUMERO_QUARTO");
                int qtdePessoas = rs.getInt("QTDE_PESSOAS");
                LocalDate dataEntrada = rs.getDate("DATA_ENTRADA").toLocalDate();
                LocalDate dataSaida = rs.getDate("DATA_SAIDA").toLocalDate();
                reservas.add(new Reserva(numero, quarto, qtdePessoas, dataEntrada, dataSaida));

                // System.out.println("Tabela : { " + quarto + ", " + qtdePessoas + ", " + ", "
                // + dataEntrada.toString()
                // + ", " + dataSaida.toString() + " }");
            }
            conn.close();
            return reservas;
        } catch (ClassNotFoundException e) {
            System.err.printf("O driver JDBC nao foi encontrado: %s\n", e.getMessage());
        } catch (SQLException e) {
            System.err.println("Nao foi possavel estabelecer a conexao com o banco de dados");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public ArrayList<Quarto> consultaQuartos() {
        try {
            ArrayList<Quarto> quartos = new ArrayList<Quarto>();
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "bancouser",
                    "bancosenha");
            // System.out.println("Conexao realizada com sucesso!");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM TB_QUARTOS");
            while (rs.next()) {
                int numero = rs.getInt("NUMERO");
                int categoria = rs.getInt("CATEGORIA");
                int maxPessoas = rs.getInt("MAX_PESSOAS");
                double valorDiaria = rs.getDouble("VALOR_DIARIA");
                quartos.add(new Quarto(numero, CategoriaEnum.values()[categoria - 1], maxPessoas, valorDiaria));

                // System.out.println("Tabela : { " + quarto + ", " + qtdePessoas + ", " + ", "
                // + dataEntrada.toString()
                // + ", " + dataSaida.toString() + " }");
            }
            conn.close();
            return quartos;
        } catch (ClassNotFoundException e) {
            System.err.printf("O driver JDBC nao foi encontrado: %s\n", e.getMessage());
        } catch (SQLException e) {
            System.err.println("Nao foi possavel estabelecer a conexao com o banco de dados");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public Quarto obterQuartoPorNumero(int num) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "bancouser",
                    "bancosenha");
            // System.out.println("Conexao realizada com sucesso!");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM TB_QUARTOS WHERE NUMERO = " + num);

            Quarto quarto = null;
            while (rs.next()) {
                int numero = rs.getInt("NUMERO");
                int categoria = rs.getInt("CATEGORIA");
                int maxPessoas = rs.getInt("MAX_PESSOAS");
                double valorDiaria = rs.getDouble("VALOR_DIARIA");

                quarto = new Quarto(numero, CategoriaEnum.values()[categoria - 1], maxPessoas, valorDiaria);

                // System.out.println("Tabela : { " + quarto + ", " + qtdePessoas + ", " + ", "
                // + dataEntrada.toString()
                // + ", " + dataSaida.toString() + " }");
            }
            conn.close();
            return quarto;
        } catch (ClassNotFoundException e) {
            System.err.printf("O driver JDBC nao foi encontrado: %s\n", e.getMessage());
        } catch (SQLException e) {
            System.err.println("Nao foi possavel estabelecer a conexao com o banco de dados");
            System.err.println(e.getMessage());
        }
        return null;
    }

    
    public Boolean quartoDisponivel(int num, String dataEntrada, String dataSaida) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "bancouser",
                    "bancosenha");
            // System.out.println("Conexao realizada com sucesso!");
            Statement stmt = conn.createStatement();

            String query = "SELECT COUNT(*) AS QTD_RESERVADO FROM TB_RESERVAS"
            +" WHERE DATA_ENTRADA <= TO_DATE('"+dataEntrada+"', 'DD/MM/YYYY')"
            +" AND TO_DATE('"+dataEntrada+"', 'DD/MM/YYYY')  <= DATA_SAIDA" 
            +" AND DATA_ENTRADA <= TO_DATE('"+dataSaida+"', 'DD/MM/YYYY')"
            +" AND TO_DATE('"+dataSaida+"', 'DD/MM/YYYY')  <= DATA_SAIDA"
            +" AND NUMERO_QUARTO = "+num;

            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            
            //Se for igual a 0 quer dizer que não tem nenhuma reserva dentro do periodo, se for maior que 1 existe reservas no periodo
            Boolean disponivel = rs.getInt("QTD_RESERVADO") == 0; 
            conn.close();

            return disponivel;
        } catch (ClassNotFoundException e) {
            System.err.printf("O driver JDBC nao foi encontrado: %s\n", e.getMessage());
        } catch (SQLException e) {
            System.err.println("Nao foi possavel estabelecer a conexao com o banco de dados");
            System.err.println(e.getMessage());
        }
        return false;
    }
}