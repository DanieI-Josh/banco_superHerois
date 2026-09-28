import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//Gerencia operações da tabela herois no banco superherois

public class HeroiDAO {

    private Conexao conexao;

    public HeroiDAO ( ) { this.conexao = new Conexao ( ); }

    private Connection getConnection ( ) { return conexao.getConnection ( ); }

    //Inserir novo herói
    public boolean inserir ( Heroi h ) {

        String sql =
            "INSERT INTO herois " +
            "(nome, simbolo, identidadeSecreta, poder, nivel_heroi, risco_publico, id_cidade) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try ( Connection conn = getConnection ( );
              PreparedStatement ps = conn.prepareStatement ( sql ) ) {

            ps.setString ( 1, h.getNome ( ) );
            ps.setString ( 2, h.getSimbolo ( ) );
            ps.setString ( 3, h.getIdentidadeSecreta ( ) );
            ps.setString ( 4, h.getPoder ( ) );
            ps.setString ( 5, h.getNivelHeroi ( ) );
            ps.setString ( 6, h.getRiscoPublico ( ) );
            ps.setInt ( 7, h.getIdCidade ( ) );

            ps.executeUpdate ( );
            return true;

        } catch ( SQLException e ) {
            System.out.println ( "Erro ao inserir herói" );
            return false;
        }
    }

    //Atualizar herói existente
    public boolean atualizar ( Heroi h ) {

        String sql =
            "UPDATE herois SET " +
            "nome = ?, simbolo = ?, identidadeSecreta = ?, poder = ?, " +
            "nivel_heroi = ?, risco_publico = ?, id_cidade = ? " +
            "WHERE id_heroi = ?";

        try ( Connection conn = getConnection ( );
              PreparedStatement ps = conn.prepareStatement ( sql ) ) {

            ps.setString ( 1, h.getNome ( ) );
            ps.setString ( 2, h.getSimbolo ( ) );
            ps.setString ( 3, h.getIdentidadeSecreta ( ) );
            ps.setString ( 4, h.getPoder ( ) );
            ps.setString ( 5, h.getNivelHeroi ( ) );
            ps.setString ( 6, h.getRiscoPublico ( ) );
            ps.setInt ( 7, h.getIdCidade ( ) );
            ps.setInt ( 8, h.getIdHeroi ( ) );

            int linhas = ps.executeUpdate ( );
            return linhas > 0;

        } catch ( SQLException e ) {
            System.out.println ( "Erro ao atualizar herói" );
            return false;
        }
    }

    //Excluir herói pelo id
    public boolean excluir ( int idHeroi ) {

        String sql = "DELETE FROM herois WHERE id_heroi = ?";

        try ( Connection conn = getConnection ( );
              PreparedStatement ps = conn.prepareStatement ( sql ) ) {

            ps.setInt ( 1, idHeroi );
            int linhas = ps.executeUpdate ( );
            return linhas > 0;

        } catch ( SQLException e ) {
            System.out.println ( "Erro ao excluir herói" );
            return false;
        }
    }

    //Buscar herói por ID
    public Heroi buscarPorId ( int idHeroi ) {

        String sql = "SELECT * FROM herois WHERE id_heroi = ?";

        try ( Connection conn = getConnection ( );
              PreparedStatement ps = conn.prepareStatement ( sql ) ) {

            ps.setInt ( 1, idHeroi );
            ResultSet rs = ps.executeQuery ( );

            if ( rs.next ( ) ) {

                Heroi h = new Heroi ( );
                h.setIdHeroi ( rs.getInt ( "id_heroi" ) );
                h.setNome ( rs.getString ( "nome" ) );
                h.setSimbolo ( rs.getString ( "simbolo" ) );
                h.setIdentidadeSecreta ( rs.getString ( "identidadeSecreta" ) );
                h.setPoder ( rs.getString ( "poder" ) );
                h.setNivelHeroi ( rs.getString ( "nivel_heroi" ) );
                h.setRiscoPublico ( rs.getString ( "risco_publico" ) );
                h.setIdCidade ( rs.getInt ( "id_cidade" ) );

                return h;
            }

        } catch ( SQLException e ) {
            System.out.println ( "Erro ao buscar herói" );
        }

        return null;
    }

    //Listar todos os heróis
    public List<Heroi> listarTodos ( ) {

        List<Heroi> lista = new ArrayList<Heroi> ( );

        String sql = "SELECT * FROM herois";

        try ( Connection conn = getConnection ( );
              PreparedStatement ps = conn.prepareStatement ( sql );
              ResultSet rs = ps.executeQuery ( ) ) {

            while ( rs.next ( ) ) {

                Heroi h = new Heroi ( );
                h.setIdHeroi ( rs.getInt ( "id_heroi" ) );
                h.setNome ( rs.getString ( "nome" ) );
                h.setSimbolo ( rs.getString ( "simbolo" ) );
                h.setIdentidadeSecreta ( rs.getString ( "identidadeSecreta" ) );
                h.setPoder ( rs.getString ( "poder" ) );
                h.setNivelHeroi ( rs.getString ( "nivel_heroi" ) );
                h.setRiscoPublico ( rs.getString ( "risco_publico" ) );
                h.setIdCidade ( rs.getInt ( "id_cidade" ) );

                lista.add ( h );
            }

        } catch ( SQLException e ) {
            System.out.println ( "Erro ao listar heróis" );
        }

        return lista;
    }

    //Buscar heróis por parte do nome
    public List<Heroi> buscarPorNome ( String parteNome ) {

        List<Heroi> lista = new ArrayList<Heroi> ( );

        String sql = "SELECT * FROM herois WHERE nome LIKE ?";

        try ( Connection conn = getConnection ( );
              PreparedStatement ps = conn.prepareStatement ( sql ) ) {

            ps.setString ( 1, "%" + parteNome + "%" );
            ResultSet rs = ps.executeQuery ( );

            while ( rs.next ( ) ) {

                Heroi h = new Heroi ( );
                h.setIdHeroi ( rs.getInt ( "id_heroi" ) );
                h.setNome ( rs.getString ( "nome" ) );
                h.setSimbolo ( rs.getString ( "simbolo" ) );
                h.setIdentidadeSecreta ( rs.getString ( "identidadeSecreta" ) );
                h.setPoder ( rs.getString ( "poder" ) );
                h.setNivelHeroi ( rs.getString ( "nivel_heroi" ) );
                h.setRiscoPublico ( rs.getString ( "risco_publico" ) );
                h.setIdCidade ( rs.getInt ( "id_cidade" ) );

                lista.add ( h );
            }

        } catch ( SQLException e ) {
            System.out.println ( "Erro ao buscar heróis por nome" );
        }

        return lista;
    }

    //Buscar heróis por cidade
    public List<Heroi> buscarPorCidade ( int idCidade ) {

        List<Heroi> lista = new ArrayList<Heroi> ( );

        String sql = "SELECT * FROM herois WHERE id_cidade = ?";

        try ( Connection conn = getConnection ( );
              PreparedStatement ps = conn.prepareStatement ( sql ) ) {

            ps.setInt ( 1, idCidade );
            ResultSet rs = ps.executeQuery ( );

            while ( rs.next ( ) ) {

                Heroi h = new Heroi ( );
                h.setIdHeroi ( rs.getInt ( "id_heroi" ) );
                h.setNome ( rs.getString ( "nome" ) );
                h.setSimbolo ( rs.getString ( "simbolo" ) );
                h.setIdentidadeSecreta ( rs.getString ( "identidadeSecreta" ) );
                h.setPoder ( rs.getString ( "poder" ) );
                h.setNivelHeroi ( rs.getString ( "nivel_heroi" ) );
                h.setRiscoPublico ( rs.getString ( "risco_publico" ) );
                h.setIdCidade ( rs.getInt ( "id_cidade" ) );

                lista.add ( h );
            }

        } catch ( SQLException e ) {
            System.out.println ( "Erro ao buscar heróis por cidade" );
        }

        return lista;
    }
}
