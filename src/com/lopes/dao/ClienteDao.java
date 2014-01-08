package VideoDaoClube.DataDao;

import VideoDaoClube.BusinessObjects.Cliente;
import VideoDaoClube.Exceptions.DataAccessException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ClienteDao {

    public static int criar(Cliente user)
            throws DataAccessException, SQLException, FileNotFoundException, IOException {
        try {

            Statement stmt = UtilitariosDao.createConnection();

            String query = "insert into cliente ( Nome, BI, Morada, Telefone) ";
            query += " values ('" + user.getNome() + "', '" + user.getBi() + "', '" + user.getMorada() + "', '" + user.getNrTelefone() + "')";

            int rows = stmt.executeUpdate(query);

            UtilitariosDao.closeConnection(stmt);

            return rows;
        } catch (ClassNotFoundException ex) {
            throw new DataAccessException("Impossivel criar ligacao JDBC. Erro no acesso a biblioteca.");
        } catch (SQLException ex) {
            throw new DataAccessException("Erro no acesso a dados. Motivo: " + ex.getMessage());
        }
    }

    public static int apagar(int id) throws DataAccessException, FileNotFoundException, IOException {
        try {
            Statement stmt = UtilitariosDao.createConnection();

            String query = "delete from cliente ";
            query += " where idCliente = " + id;

            int rows = stmt.executeUpdate(query);

            UtilitariosDao.closeConnection(stmt);

            return rows;
        } catch (ClassNotFoundException ex) {
            throw new DataAccessException("Impossivel criar ligacao JDBC. Erro no acesso a base de dados.");
        } catch (SQLException ex) {
            throw new DataAccessException("Erro no acesso a dados. Motivo: " + ex.getMessage());
        }
    }

    public static HashMap<Integer, Cliente> getAll() throws
            DataAccessException, FileNotFoundException, IOException {

        try {
            Statement stmt = UtilitariosDao.createConnection();

            String query = "select *  from cliente";

            ResultSet results = stmt.executeQuery(query);

            HashMap<Integer, Cliente> toReturn = new HashMap<Integer, Cliente>();

            while (results.next()) {
                Cliente user = new Cliente();
                user.setId(results.getInt("idCliente"));
                user.setNome(results.getString("Nome"));
                user.setBi(results.getString("BI"));
                user.setMorada(results.getString("Morada"));
                user.setNrTelefone(results.getString("Telefone"));

                toReturn.put(user.getId(), user);
            }

            UtilitariosDao.closeConnection(stmt);
            return toReturn;

        } catch (ClassNotFoundException ex) {
            throw new DataAccessException("Impossivel criar ligacao JDBC."
                    + " Erro no acesso a biblioteca.");
        } catch (SQLException ex) {
            throw new DataAccessException("Erro no acesso a dados. Motivo: "
                    + ex.getMessage());
        }
    }

    public static List<Cliente> getByName(Cliente utilizador) throws
            DataAccessException, FileNotFoundException, IOException {

        try {
            Statement stmt = UtilitariosDao.createConnection();

            String query = "select *  from cliente";
            query += " where Nome like '%" + utilizador.getNome() + "%'";

            ResultSet results = stmt.executeQuery(query);

            List<Cliente> toReturn = new ArrayList<Cliente>();

            while (results.next()) {
                Cliente user = new Cliente();
                user.setId(results.getInt("idCliente"));
                user.setNome(results.getString("Nome"));
                user.setBi(results.getString("BI"));
                user.setMorada(results.getString("Morada"));
                user.setNrTelefone(results.getString("Telefone"));

                toReturn.add(user);
            }

            UtilitariosDao.closeConnection(stmt);

            return toReturn;
        } catch (ClassNotFoundException ex) {
            throw new DataAccessException("Impossivel criar ligacao JDBC."
                    + " Erro no acesso a biblioteca.");
        } catch (SQLException ex) {
            throw new DataAccessException("Erro no acesso a dados. Motivo: "
                    + ex.getMessage());
        }
    }

    public static int alteraDados(int id, String dado1, String dado2,
            String dado3, String dado4) throws ClassNotFoundException,
            SQLException,
            FileNotFoundException,
            IOException,
            DataAccessException {

        Statement stmt = UtilitariosDao.createConnection();

        String query = "update cliente ";
        query += " set Nome = '" + dado1 + "',";
        query += " BI = '" + dado2 + "',";
        query += " Morada = '" + dado3 + "',";
        query += " Telefone = '" + dado4 + "'";
        query += " where idCliente = " + id;

        int rows = stmt.executeUpdate(query);

        UtilitariosDao.closeConnection(stmt);

        return rows;
    }

    public static List<Cliente> getListaDeClientes() throws
            DataAccessException, FileNotFoundException, IOException {

        try {
            Statement stmt = UtilitariosDao.createConnection();

            String query = "select *  from cliente";

            ResultSet results = stmt.executeQuery(query);

            List<Cliente> toReturn = new ArrayList<Cliente>();

            while (results.next()) {
                Cliente user = new Cliente();
                user.setId(results.getInt("idCliente"));
                user.setNome(results.getString("Nome"));
                user.setBi(results.getString("BI"));
                user.setMorada(results.getString("Morada"));
                user.setNrTelefone(results.getString("Telefone"));

                toReturn.add(user);
            }

            UtilitariosDao.closeConnection(stmt);
            return toReturn;

        } catch (ClassNotFoundException ex) {
            throw new DataAccessException("Impossivel criar ligacao JDBC."
                    + " Erro no acesso a biblioteca.");
        } catch (SQLException ex) {
            throw new DataAccessException("Erro no acesso a dados. Motivo: "
                    + ex.getMessage());
        }
    }
}
