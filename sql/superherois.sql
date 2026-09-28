-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 28/09/2026 às 19:18
-- Versão do servidor: 10.4.32-MariaDB
-- Versão do PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `superherois`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `cidade`
--

CREATE TABLE `cidade` (
  `id_cidade` int(11) NOT NULL,
  `nome` varchar(50) DEFAULT NULL,
  `porte` int(11) NOT NULL DEFAULT 1,
  `criminalidade` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `cidade`
--

INSERT INTO `cidade` (`id_cidade`, `nome`, `porte`, `criminalidade`) VALUES
(1, 'Gotham City', 4, 9),
(2, 'Central City', 2, 6),
(5, 'Star City', 3, 6),
(6, 'Metropolis', 5, 4),
(7, 'Coast City', 2, 3),
(8, 'Gateway City', 3, 3),
(9, 'GOIANIA CITY', 3, 1);

-- --------------------------------------------------------

--
-- Estrutura para tabela `herois`
--

CREATE TABLE `herois` (
  `id_heroi` int(11) NOT NULL,
  `nome` varchar(50) DEFAULT NULL,
  `simbolo` varchar(50) DEFAULT NULL,
  `identidadeSecreta` varchar(50) DEFAULT NULL,
  `id_cidade` int(11) DEFAULT NULL,
  `poder` varchar(120) NOT NULL,
  `nivel_heroi` varchar(2) NOT NULL,
  `risco_publico` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `herois`
--

INSERT INTO `herois` (`id_heroi`, `nome`, `simbolo`, `identidadeSecreta`, `id_cidade`, `poder`, `nivel_heroi`, `risco_publico`) VALUES
(1, 'Batman', 'Morcego', 'Bruce Wayne', 1, 'Detetive e rico e ninja', 'SS', 'global'),
(2, 'Asa noturna', 'P�ssaro', 'Dick Grayson', 1, 'Acorbacias / ninja / detetive', 'S', 'estadual'),
(3, 'Batgirl', 'Morcego', 'Barbara Gordon', 1, 'Ninja / detetive / Hacker', 'A', 'estadual'),
(4, 'The Flash', 'Raio', 'Barry Allen', 2, 'Supervelocidade / for�a de acelera��o', 'SS', 'global'),
(5, 'Kid Flash', 'Raio', 'Wally West', 2, 'Supervelocidade / for�a de acelera��o', 'S', 'global'),
(6, 'Flash', 'Raio', 'Jay Garrick', 2, 'Supervelocidade / for�a de acelera��o', 'SS', 'global'),
(7, 'Arqueiro verde', 'Ponta de flecha', 'Oliver Queen', 5, 'Ninja / habilidade com arco', 'S', 'federal'),
(8, 'Arsenal', 'flecha', 'Roy Harper', 5, 'Bra�o mec�nico / habilidade com arco', 'A', 'estadual'),
(9, 'Can�rio negro', 'Can�rio', 'Dinah Drake', 5, 'Grito supers�nico / artes marciais', 'S', 'estadual'),
(10, 'Superman', 'S no peito', 'Clark Kent', 6, 'Superfor�a / vis�o de calor / poder de voar / supervelocidade / super resist�ncia / vis�o de raio-x', 'SS', 'global'),
(11, 'Supergirl', 'S no peito', 'Kara Denvers', 6, 'Superfor�a / vis�o de calor / poder de voar / supervelocidade / super resist�ncia / vis�o de raio-x', 'S', 'global'),
(12, 'Superboy', 'S no peito', 'Conner Kent', 6, 'Super for�a / Super durabilidade ', 'A', 'federal'),
(13, 'Lanterna verde', 'lanterna no peito', 'Hal Jordan', 7, 'Gerar construtos atrav�s da imagina��o', 'S', 'global'),
(14, 'Lanterna verde', 'Lanterna no peito', 'Guy Gardner', 7, 'Gerar construtos com a imagina��o', 'S', 'global'),
(15, 'Mulher Maravilha', 'Asas douradas no peito', 'Diana Prince', 8, 'Poder de voar / super durabilidade / super for�a / QI de batalha / artef�tos m�gicos', 'S', 'global'),
(17, 'Robin', 'R', 'Jason Todd', 1, 'ninja / mec�nica avan�ada ', 'A', 'estadual'),
(18, 'Super Saymito', 'Nen�m', 'Andr�', 9, 'Fazer beber mijar', 'SS', 'global');

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `cidade`
--
ALTER TABLE `cidade`
  ADD PRIMARY KEY (`id_cidade`);

--
-- Índices de tabela `herois`
--
ALTER TABLE `herois`
  ADD PRIMARY KEY (`id_heroi`),
  ADD KEY `fk_heroi_cidade` (`id_cidade`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `cidade`
--
ALTER TABLE `cidade`
  MODIFY `id_cidade` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de tabela `herois`
--
ALTER TABLE `herois`
  MODIFY `id_heroi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `herois`
--
ALTER TABLE `herois`
  ADD CONSTRAINT `fk_heroi_cidade` FOREIGN KEY (`id_cidade`) REFERENCES `cidade` (`id_cidade`),
  ADD CONSTRAINT `heroi_cidade` FOREIGN KEY (`id_cidade`) REFERENCES `cidade` (`id_cidade`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
