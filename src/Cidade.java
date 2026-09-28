//Representa a cidade no banco superherois


public class Cidade {

    private int idCidade;
    private String nome;
    private int porte;           // classificado de 1 a 5
    private int criminalidade;   // classificado de 1 a 10

    //Construtor padrão

    public Cidade ( ) {
        this.idCidade = 0;
        this.nome = "nulo";
        this.porte = 0;
        this.criminalidade = 0;
    }

    //Construtor com parâmetros

    public Cidade ( int idCidade, String nome, int porte, int criminalidade ) {
        this.idCidade = idCidade;
        this.nome = nome;
        this.porte = porte;
        this.criminalidade = criminalidade;
    }

    public int getIdCidade ( ) { return idCidade; }

    public void setIdCidade( int idCidade ) { this.idCidade = idCidade; }

    public String getNome ( ) { return nome; }

    public void setNome ( String nome ) { this.nome = nome; }

    public int getPorte ( ) { return porte; }

    public void setPorte( int porte ) { this.porte = porte; }

    public int getCriminalidade ( ) { return criminalidade; }

    public void setCriminalidade( int criminalidade ) { this.criminalidade = criminalidade; }

    @Override
    public String toString ( ) {
        return "Cidade [id=" + idCidade +
               ", nome=" + nome +
               ", porte=" + porte +
               ", criminalidade=" + criminalidade + "]";
    }
}
