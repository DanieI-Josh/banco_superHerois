import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

//Classe principal do sistema de cadastro de super heróis

public class Principal {

    public static void main ( String [ ] args ) {

        Scanner scanner = new Scanner ( System.in );

        Conexao conexao = new Conexao ( );
        Connection conn = conexao.getConnection ( );

        if ( conn == null )
            System.out.println ( "A conexão não ocorreu" );
        else
            System.out.println ( "O banco de dados está conectado\n\nEntrando no sistema de cadastro de Super Heróis" );

        try {
            if ( conn != null ) conn.close ( );
        } catch ( Exception e ) { }

        CidadeDAO cidadeDAO = new CidadeDAO ( );
        HeroiDAO heroiDAO = new HeroiDAO ( );

        int opcao = -1;

        do {

            exibirMenu ( );

            try {
                opcao = Integer.parseInt ( scanner.nextLine ( ) );
            } catch ( Exception e ) {
                opcao = -1;
            }

            switch ( opcao ) {

                case 1:  incluirCidade ( scanner, cidadeDAO );   break;
                case 2:  alterarCidade ( scanner, cidadeDAO );   break;
                case 3:  excluirCidade ( scanner, cidadeDAO );   break;
                case 4:  pesquisarCidadePorId ( scanner, cidadeDAO ); break;
                case 5:  listarCidades ( cidadeDAO ); break;

                case 6:  incluirHeroi ( scanner, heroiDAO, cidadeDAO ); break;
                case 7:  alterarHeroi ( scanner, heroiDAO, cidadeDAO ); break;
                case 8:  excluirHeroi ( scanner, heroiDAO ); break;
                case 9:  pesquisarHeroiPorId ( scanner, heroiDAO ); break;
                case 10: pesquisarHeroisPorNome ( scanner, heroiDAO ); break;
                case 11: pesquisarHeroisPorCidade ( scanner, heroiDAO, cidadeDAO ); break;
                case 12: listarHeroisComCidades ( ); break;

                case 0:
                    System.out.println ( "Encerrando o sistema" );
                    break;

                default:
                    System.out.println ( "Opção inválida" );
            }

        } while ( opcao != 0 );

        scanner.close ( );

    }

    //Método para exibir o menu

    private static void exibirMenu ( ) {

        System.out.println ( );
        System.out.println ( "==========================================" );
        System.out.println ( "          SISTEMA SUPERHERÓIS             " );
        System.out.println ( "==========================================" );
        System.out.println ( "        ► MENU PRINCIPAL ◄                " );
        System.out.println ( "------------------------------------------" );
        System.out.println ( "   CIDADES (tabela auxiliar)" );
        System.out.println ( "   01 - Incluir cidade" );
        System.out.println ( "   02 - Alterar cidade" );
        System.out.println ( "   03 - Excluir cidade" );
        System.out.println ( "   04 - Pesquisar cidade por ID" );
        System.out.println ( "   05 - Listar todas as cidades" );
        System.out.println ( "------------------------------------------" );
        System.out.println ( "   HERÓIS (tabela principal)" );
        System.out.println ( "   06 - Incluir herói" );
        System.out.println ( "   07 - Alterar herói" );
        System.out.println ( "   08 - Excluir herói" );
        System.out.println ( "   09 - Pesquisar herói por ID" );
        System.out.println ( "   10 - Pesquisar herói por nome" );
        System.out.println ( "   11 - Pesquisar heróis por cidade" );
        System.out.println ( "   12 - Listar Herói + Cidade " );
        System.out.println ( "------------------------------------------" );
        System.out.println ( "   00 - SAIR" );
        System.out.println ( "==========================================" );
        System.out.print   ( "Escolha a opção: " );
    }


    // Operações com CIDADE

    private static void incluirCidade ( Scanner scanner, CidadeDAO dao ) {

        System.out.println ( );
        System.out.println ( "Cadastro de cidade" );

        System.out.print ( "Nome da cidade: " );
        String nome = scanner.nextLine ( );

        int porte = 0;

        while ( true ) {
            System.out.print ( "Porte da cidade (1 a 5): " );
            try {
                porte = Integer.parseInt ( scanner.nextLine ( ) );
                if ( porte >= 1 && porte <= 5 ) break;
            } catch ( Exception e ) { }
            System.out.println ( "Valor inválido" );
        }

        int criminalidade = 0;

        while ( true ) {
            System.out.print ( "Índice de criminalidade (1 a 10): " );
            try {
                criminalidade = Integer.parseInt ( scanner.nextLine ( ) );
                if ( criminalidade >= 1 && criminalidade <= 10 ) break;
            } catch ( Exception e ) { }
            System.out.println ( "Valor inválido" );
        }

        Cidade c = new Cidade ( 0, nome, porte, criminalidade );

        if ( dao.inserir ( c ) )
            System.out.println ( "Cidade cadastrada" );
        else
            System.out.println ( "Erro ao cadastrar cidade" );
    }

    private static void alterarCidade ( Scanner scanner, CidadeDAO dao ) {

        System.out.print ( "ID da cidade para alteração: " );
        int id = Integer.parseInt ( scanner.nextLine ( ) );

        Cidade c = dao.buscarPorId ( id );

        if ( c == null ) {
            System.out.println ( "Cidade não encontrada" );
            return;
        }

        System.out.println ( "Cidade atual: " + c );

        System.out.print ( "Novo nome (enter mantém " + c.getNome ( ) + "): " );
        String nome = scanner.nextLine ( );
        if ( !nome.isEmpty ( ) ) c.setNome ( nome );

        System.out.print ( "Novo porte (1 a 5, atual " + c.getPorte ( ) + ", enter mantém): " );
        String porteStr = scanner.nextLine ( );
        if ( !porteStr.isEmpty ( ) ) {
            try {
                int porte = Integer.parseInt ( porteStr );
                if ( porte >= 1 && porte <= 5 ) c.setPorte ( porte );
            } catch ( Exception e ) { }
        }

        System.out.print (
            "Nova criminalidade (1 a 10, atual " + c.getCriminalidade ( ) + ", enter mantém): "
        );
        String crimStr = scanner.nextLine ( );
        if ( !crimStr.isEmpty ( ) ) {
            try {
                int crim = Integer.parseInt ( crimStr );
                if ( crim >= 1 && crim <= 10 ) c.setCriminalidade ( crim );
            } catch ( Exception e ) { }
        }

        if ( dao.atualizar ( c ) )
            System.out.println ( "Cidade atualizada" );
        else
            System.out.println ( "Erro ao atualizar cidade" );
    }

    private static void excluirCidade ( Scanner scanner, CidadeDAO dao ) {

        System.out.print ( "ID da cidade para exclusão: " );
        int id = Integer.parseInt ( scanner.nextLine ( ) );

        if ( dao.excluir ( id ) )
            System.out.println ( "Cidade excluída" );
        else
            System.out.println ( "Erro ao excluir cidade" );
    }

    private static void pesquisarCidadePorId ( Scanner scanner, CidadeDAO dao ) {

        System.out.print ( "ID da cidade: " );
        int id = Integer.parseInt ( scanner.nextLine ( ) );

        Cidade c = dao.buscarPorId ( id );

        if ( c == null )
            System.out.println ( "Cidade não encontrada" );
        else
            System.out.println ( c );
    }

    private static void listarCidades ( CidadeDAO dao ) {

        List<Cidade> lista = dao.listarTodas ( );

        if ( lista.isEmpty ( ) ) {
            System.out.println ( "Nenhuma cidade cadastrada" );
            return;
        }

        for ( Cidade c : lista ) {
            System.out.println ( c.getNome ( ) + " - id: " + c.getIdCidade ( ) );
            System.out.println ( );
        }

        System.out.println ( "===============================" );
    }


    // Operações com HERÓI

    private static void incluirHeroi (
        Scanner scanner,
        HeroiDAO heroiDAO,
        CidadeDAO cidadeDAO
    ) {

        System.out.println ( );
        System.out.println ( "Cadastro de herói" );

        System.out.print ( "Nome do herói: " );
        String nome = scanner.nextLine ( );

        System.out.print ( "Símbolo do herói: " );
        String simbolo = scanner.nextLine ( );

        System.out.print ( "Identidade secreta: " );
        String identidadeSecreta = scanner.nextLine ( );

        System.out.print ( "Poder principal: " );
        String poder = scanner.nextLine ( );

        String nivelHeroi = escolherNivelHeroi ( scanner );

        String riscoPublico = escolherRiscoPublico ( scanner );

        System.out.println ( );
        System.out.println ( "Cidades cadastradas" );
        listarCidadesSimples ( cidadeDAO );

        System.out.print ( "Informe o ID da cidade do herói: " );
        int idCidade = Integer.parseInt ( scanner.nextLine ( ) );

        Heroi h = new Heroi (
            0,
            nome,
            simbolo,
            identidadeSecreta,
            poder,
            nivelHeroi,
            riscoPublico,
            idCidade
        );

        if ( heroiDAO.inserir ( h ) )
            System.out.println ( "Herói cadastrado" );
        else
            System.out.println ( "Erro ao cadastrar herói" );
    }

    //Método para listar as cidades no cadastro
    private static void listarCidadesSimples ( CidadeDAO dao ) {

        List<Cidade> lista = dao.listarTodas ( );

        if ( lista.isEmpty ( ) ) {
            System.out.println ( "Nenhuma cidade cadastrada" );
            return;
        }

        for ( Cidade c : lista ) {
            System.out.println ( c.getNome ( ) + " - " + c.getIdCidade ( ) );
        }
    }


    private static void alterarHeroi (
        Scanner scanner,
        HeroiDAO heroiDAO,
        CidadeDAO cidadeDAO
    ) {

        System.out.print ( "ID do herói para alteração: " );
        int id = Integer.parseInt ( scanner.nextLine ( ) );

        Heroi h = heroiDAO.buscarPorId ( id );

        if ( h == null ) {
            System.out.println ( "Herói não encontrado" );
            return;
        }

        System.out.println ( "Herói atual: " + h );

        System.out.print ( "Novo nome (enter mantém " + h.getNome ( ) + "): " );
        String nome = scanner.nextLine ( );
        if ( !nome.isEmpty ( ) ) h.setNome ( nome );

        System.out.print ( "Novo símbolo (enter mantém " + h.getSimbolo ( ) + "): " );
        String simbolo = scanner.nextLine ( );
        if ( !simbolo.isEmpty ( ) ) h.setSimbolo ( simbolo );

        System.out.print (
            "Nova identidade secreta (enter mantém " + h.getIdentidadeSecreta ( ) + "): "
        );
        String ident = scanner.nextLine ( );
        if ( !ident.isEmpty ( ) ) h.setIdentidadeSecreta ( ident );

        System.out.print ( "Novo poder (enter mantém " + h.getPoder ( ) + "): " );
        String poder = scanner.nextLine ( );
        if ( !poder.isEmpty ( ) ) h.setPoder ( poder );

        System.out.println ( "Nível do herói atual: " + h.getNivelHeroi ( ) );
        System.out.print ( "Alterar nível? (s para sim): " );
        String altNivel = scanner.nextLine ( );
        if ( altNivel.equalsIgnoreCase ( "s" ) )
            h.setNivelHeroi ( escolherNivelHeroi ( scanner ) );

        System.out.println ( "Risco público atual: " + h.getRiscoPublico ( ) );
        System.out.print ( "Alterar risco público? (s para sim): " );
        String altRisco = scanner.nextLine ( );
        if ( altRisco.equalsIgnoreCase ( "s" ) )
            h.setRiscoPublico ( escolherRiscoPublico ( scanner ) );

        System.out.print ( "Alterar cidade do herói? (s para sim): " );
        String altCidade = scanner.nextLine ( );
        if ( altCidade.equalsIgnoreCase ( "s" ) ) {
            System.out.println ( "Cidades cadastradas" );
            listarCidades ( cidadeDAO );
            System.out.print ( "Novo ID da cidade: " );
            int idCidade = Integer.parseInt ( scanner.nextLine ( ) );
            h.setIdCidade ( idCidade );
        }

        if ( heroiDAO.atualizar ( h ) )
            System.out.println ( "Herói atualizado" );
        else
            System.out.println ( "Erro ao atualizar herói" );
    }

    private static void excluirHeroi ( Scanner scanner, HeroiDAO dao ) {

        System.out.print ( "ID do herói para exclusão: " );
        int id = Integer.parseInt ( scanner.nextLine ( ) );

        if ( dao.excluir ( id ) )
            System.out.println ( "Herói excluído" );
        else
            System.out.println ( "Erro ao excluir herói" );
    }

    private static void pesquisarHeroiPorId ( Scanner scanner, HeroiDAO dao ) {

        System.out.print ( "ID do herói: " );
        int id = Integer.parseInt ( scanner.nextLine ( ) );

        Heroi h = dao.buscarPorId ( id );

        if ( h == null )
            System.out.println ( "Herói não encontrado" );
        else
            System.out.println ( h );
    }

    private static void pesquisarHeroisPorNome ( Scanner scanner, HeroiDAO dao ) {

        System.out.print ( "Parte do nome do herói: " );
        String parte = scanner.nextLine ( );

        List<Heroi> lista = dao.buscarPorNome ( parte );

        if ( lista.isEmpty ( ) ) {
            System.out.println ( "Nenhum herói encontrado" );
            return;
        }

        for ( Heroi h : lista )
            System.out.println ( h );
    }

    private static void pesquisarHeroisPorCidade (
        Scanner scanner,
        HeroiDAO heroiDAO,
        CidadeDAO cidadeDAO
    ) {

        System.out.println ( "Cidades cadastradas" );
        listarCidades ( cidadeDAO );

        System.out.print ( "Informe o ID da cidade: " );
        int idCidade = Integer.parseInt ( scanner.nextLine ( ) );

        List<Heroi> lista = heroiDAO.buscarPorCidade ( idCidade );

        if ( lista.isEmpty ( ) ) {
            System.out.println ( "Nenhum herói encontrado nessa cidade" );
            return;
        }

        for ( Heroi h : lista )
            System.out.println ( h );
    }

    // JOIN Herói + Cidade

    private static void listarHeroisComCidades ( ) {

        Conexao conexao = new Conexao ( );
        Connection conn = conexao.getConnection ( );

        if ( conn == null ) {
            System.out.println ( "Não foi possível conectar para o JOIN" );
            return;
        }

        String sql =
            "SELECT h.id_heroi, h.nome, h.simbolo, h.nivel_heroi, h.risco_publico, " +
            "c.nome AS nome_cidade " +
            "FROM herois h " +
            "JOIN cidade c ON h.id_cidade = c.id_cidade";

        try ( java.sql.PreparedStatement ps = conn.prepareStatement ( sql );
              java.sql.ResultSet rs = ps.executeQuery ( ) ) {

            System.out.println ( );
            System.out.println ( "Heróis e suas cidades" );

            while ( rs.next ( ) ) {

                int idHeroi = rs.getInt ( "id_heroi" );
                String nomeHeroi = rs.getString ( "nome" );
                String simbolo = rs.getString ( "simbolo" );
                String nivel = rs.getString ( "nivel_heroi" );
                String risco = rs.getString ( "risco_publico" );
                String cidade = rs.getString ( "nome_cidade" );

                System.out.println (
                    "ID " + idHeroi +
                    " | " + nomeHeroi +
                    " (" + simbolo + ")" +
                    " | Nível " + nivel +
                    " | Risco " + risco +
                    " | Cidade " + cidade
                );
            }

        } catch ( Exception e ) {
            System.out.println ( "Erro ao listar heróis com cidades" );
        } finally {
            try { conn.close ( ); } catch ( Exception e ) { }
        }
    }

    // Auxiliares de leitura

    private static String escolherNivelHeroi ( Scanner scanner ) {

        while ( true ) {

            System.out.println ( );
            System.out.println ( "Nível do herói" );
            System.out.println ( "1 - B" );
            System.out.println ( "2 - A" );
            System.out.println ( "3 - S" );
            System.out.println ( "4 - SS" );
            System.out.print ( "Escolha: " );

            String op = scanner.nextLine ( );

            if ( op.equals ( "1" ) ) return "B";
            if ( op.equals ( "2" ) ) return "A";
            if ( op.equals ( "3" ) ) return "S";
            if ( op.equals ( "4" ) ) return "SS";

            System.out.println ( "Opção inválida" );
        }
    }

    private static String escolherRiscoPublico ( Scanner scanner ) {

        while ( true ) {

            System.out.println ( );
            System.out.println ( "Risco público" );
            System.out.println ( "1 - municipal" );
            System.out.println ( "2 - estadual" );
            System.out.println ( "3 - federal" );
            System.out.println ( "4 - global" );
            System.out.print ( "Escolha: " );

            String op = scanner.nextLine ( );

            if ( op.equals ( "1" ) ) return "municipal";
            if ( op.equals ( "2" ) ) return "estadual";
            if ( op.equals ( "3" ) ) return "federal";
            if ( op.equals ( "4" ) ) return "global";

            System.out.println ( "Opção inválida" );
        }
    }
}
