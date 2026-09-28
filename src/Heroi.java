//Representa o herói no banco superherois

public class Heroi {

    private int idHeroi;
    private String nome;
    private String simbolo;
    private String identidadeSecreta;
    private String poder;
    private String nivelHeroi;     // B, A, S, SS
    private String riscoPublico;   // municipal, estadual, federal, global
    private int idCidade;          // chave estrangeira para cidade

    //Construtor padrão

    public Heroi ( ) {
        this.idHeroi = 0;
        this.nome = "nulo";
        this.simbolo = "nulo";
        this.identidadeSecreta = "nulo";
        this.poder = "nulo";
        this.nivelHeroi = "B";
        this.riscoPublico = "municipal";
        this.idCidade = 0;
    }

    //Construtor com parâmetros

    public Heroi ( int idHeroi, String nome, String simbolo, String identidadeSecreta, String poder, String nivelHeroi, String riscoPublico, int idCidade ) {
        this.idHeroi = idHeroi;
        this.nome = nome;
        this.simbolo = simbolo;
        this.identidadeSecreta = identidadeSecreta;
        this.poder = poder;
        this.nivelHeroi = nivelHeroi;
        this.riscoPublico = riscoPublico;
        this.idCidade = idCidade;
    }

    public int getIdHeroi ( ) { return idHeroi; }

    public void setIdHeroi ( int idHeroi ) { this.idHeroi = idHeroi; }

    public String getNome ( ) { return nome; }

    public void setNome ( String nome ) { this.nome = nome; }

    public String getSimbolo ( ) { return simbolo; }

    public void setSimbolo ( String simbolo ) { this.simbolo = simbolo; }

    public String getIdentidadeSecreta ( ) { return identidadeSecreta; }

    public void setIdentidadeSecreta ( String identidadeSecreta ) { this.identidadeSecreta = identidadeSecreta; }

    public String getPoder ( ) { return poder; }

    public void setPoder ( String poder ) { this.poder = poder; }

    public String getNivelHeroi ( ) { return nivelHeroi; }

    public void setNivelHeroi ( String nivelHeroi ) { this.nivelHeroi = nivelHeroi; }

    public String getRiscoPublico ( ) { return riscoPublico; }

    public void setRiscoPublico ( String riscoPublico ) { this.riscoPublico = riscoPublico; }

    public int getIdCidade ( ) { return idCidade; }

    public void setIdCidade ( int idCidade ) { this.idCidade = idCidade; }

    @Override
    public String toString ( ) {
        return "Heroi [id=" + idHeroi +
               ", nome=" + nome +
               ", simbolo=" + simbolo +
               ", identidadeSecreta=" + identidadeSecreta +
               ", poder=" + poder +
               ", nivelHeroi=" + nivelHeroi +
               ", riscoPublico=" + riscoPublico +
               ", idCidade=" + idCidade + "]";
    }
}
