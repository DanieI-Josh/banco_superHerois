import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//Gerencia operações da tabela cidade no banco superherois

public class CidadeDAO {

    private Conexao conexao;

    public CidadeDAO ( ) { this.conexao = new Conexao ( ); }

    private Connection getConnection ( ) { return conexao.getConnection ( ); }

    //Inserir nova cidade
    public boolean inserir ( Cidade c ) {

        String sql = "INSERT INTO cidade (nome, porte, criminalidade) VALUES (?, ?, ?)";

        try ( Connection conn = getConnection ( );
              PreparedStatement ps = conn.prepareStatement ( sql ) ) {

            ps.setString ( 1, c.getNome ( ) );
            ps.setInt ( 2, c.getPorte ( ) );
            ps.setInt ( 3, c.getCriminalidade ( ) );

            ps.executeUpdate ( );
            return true;

        } catch ( SQLException e ) {
            System.out.println ( "Erro ao inserir cidade" );
            return false;
        }
    }

    //Atualizar uma cidade existente
    public boolean atualizar ( Cidade c ) {

        String sql = "UPDATE cidade SET nome = ?, porte = ?, criminalidade = ? WHERE id_cidade = ?";

        try ( Connection conn = getConnection ( );
              PreparedStatement ps = conn.prepareStatement ( sql ) ) {

            ps.setString ( 1, c.getNome ( ) );
            ps.setInt ( 2, c.getPorte ( ) );
            ps.setInt ( 3, c.getCriminalidade ( ) );
            ps.setInt ( 4, c.getIdCidade ( ) );

            int linhas = ps.executeUpdate ( );
            return linhas > 0;

        } catch ( SQLException e ) {
            System.out.println ( "Erro ao atualizar cidade" );
            return false;
        }
    }

    //Excluir cidade pelo id
    public boolean excluir ( int idCidade ) {

        String sql = "DELETE FROM cidade WHERE id_cidade = ?";

        try ( Connection conn = getConnection ( );
              PreparedStatement ps = conn.prepareStatement ( sql ) ) {

            ps.setInt ( 1, idCidade );
            int linhas = ps.executeUpdate ( );
            return linhas > 0;

        } catch ( SQLException e ) {
            System.out.println ( "Erro ao excluir cidade" );
            return false;
        }
    }

    //Buscar cidade por ID
    public Cidade buscarPorId ( int idCidade ) {

        String sql = "SELECT * FROM cidade WHERE id_cidade = ?";

        try ( Connection conn = getConnection ( );
              PreparedStatement ps = conn.prepareStatement ( sql ) ) {

            ps.setInt ( 1, idCidade );
            ResultSet rs = ps.executeQuery ( );

            if ( rs.next ( ) ) {

                Cidade c = new Cidade ( );
                c.setIdCidade ( rs.getInt ( "id_cidade" ) );
                c.setNome ( rs.getString ( "nome" ) );
                c.setPorte ( rs.getInt ( "porte" ) );
                c.setCriminalidade ( rs.getInt ( "criminalidade" ) );
                return c;
            }

        } catch ( SQLException e ) {
            System.out.println ( "Erro ao buscar cidade" );
        }

        return null;
    }

    //Listar todas as cidades
    public List<Cidade> listarTodas ( ) {

        List<Cidade> lista = new ArrayList<Cidade> ( );

        String sql = "SELECT * FROM cidade";

        try ( Connection conn = getConnection ( );
              PreparedStatement ps = conn.prepareStatement ( sql );
              ResultSet rs = ps.executeQuery ( ) ) {

            while ( rs.next ( ) ) {

                Cidade c = new Cidade ( );
                c.setIdCidade ( rs.getInt ( "id_cidade" ) );
                c.setNome ( rs.getString ( "nome" ) );
                c.setPorte ( rs.getInt ( "porte" ) );
                c.setCriminalidade ( rs.getInt ( "criminalidade" ) );

                lista.add ( c );
            }

        } catch ( SQLException e ) {
            System.out.println ( "Erro ao listar cidades" );
        }

        return lista;
    }

    //Buscar cidades por parte do nome
    public List<Cidade> buscarPorNome ( String parteNome ) {

        List<Cidade> lista = new ArrayList<Cidade> ( );

        String sql = "SELECT * FROM cidade WHERE nome LIKE ?";

        try ( Connection conn = getConnection ( );
              PreparedStatement ps = conn.prepareStatement ( sql ) ) {

            ps.setString ( 1, "%" + parteNome + "%" );
            ResultSet rs = ps.executeQuery ( );

            while ( rs.next ( ) ) {

                Cidade c = new Cidade ( );
                c.setIdCidade ( rs.getInt ( "id_cidade" ) );
                c.setNome ( rs.getString ( "nome" ) );
                c.setPorte ( rs.getInt ( "porte" ) );
                c.setCriminalidade ( rs.getInt ( "criminalidade" ) );

                lista.add ( c );
            }

        } catch ( SQLException e ) {
            System.out.println ( "Erro ao buscar cidades por nome" );
        }

        return lista;
    }
}
