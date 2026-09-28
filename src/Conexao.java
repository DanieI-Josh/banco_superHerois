import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private String login = "root";
    private String senha = "";
    private String host = "localhost";
    private String dbName = "superherois";
    private String url = "jdbc:mysql://" +host+ "/" +dbName;

    public Connection conexao = null;

    public Conexao ( ) { }

    public Connection getConnection ( ) {
        try {
            Class.forName ("com.mysql.jdbc.Driver");
        }
        catch ( ClassNotFoundException e ) {
            return null;
        }
        try {
            this.conexao = ( Connection ) DriverManager.getConnection ( url, login, senha );
        }
        catch ( SQLException ex ) {
            return null;
        }
        return this.conexao;

            
    }
}