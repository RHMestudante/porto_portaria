package morais.rh.DAO.ModelosDAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import morais.rh.DAO.ControleBanco;
import morais.rh.Modelo.Tipo;

public class TipoDAO {

    static ControleBanco controle = new ControleBanco();
    private static Connection conexao = controle.getConnection();

    public static void adicionaTipo(Tipo tipo) throws IOException {
        String sql = "INSERT INTO Tipo(TipoCod, TipoDesc) VALUES(?, ?)";
    
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, tipo.getCod());
            stmt.setString(2, tipo.getDesc());
    
            stmt.execute();
            stmt.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao adicionar tipo no banco de dados: " + e.getMessage());
        }
    }

    public static ArrayList<Tipo> buscarUsuario() {
        ResultSet resultSet = null;
        ArrayList<Tipo> tipos = new ArrayList<>();
    
        try {
            String consulta = "SELECT * FROM Tipo";
            PreparedStatement preparedStatement = conexao.prepareStatement(consulta);
            resultSet = preparedStatement.executeQuery();
    
            while (resultSet.next()) {

                int cod = resultSet.getInt("TipoCod");
                String desc = resultSet.getString("TipoDesc");
    
                Tipo tipo = new Tipo(cod, desc);
                tipos.add(tipo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar tipos no banco de dados: " + e.getMessage());
        }
    
        return tipos;
    }
    

    public static void apagarUsuario(int cod) {
        if(cod != 0){
        try {
            String sql = "DELETE FROM Tipo WHERE TipoCod = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, cod);

            int linhasAfetadas = preparedStatement.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Tipo deletado com sucesso.");
            } else {
                System.out.println("Nenhum Tipo foi deletado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao apagar tipo no banco de dados: " + e.getMessage());
        }
        }
    }
}