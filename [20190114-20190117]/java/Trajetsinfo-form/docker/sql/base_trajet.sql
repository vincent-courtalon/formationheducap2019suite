

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";

--
-- Base de données : `base_trajet`
--

-- --------------------------------------------------------

--
-- Structure de la table `ville`
--

CREATE TABLE `ville` (
  `id` int(11) NOT NULL,
  `nom` varchar(100) NOT NULL,
  `coordonnee` point NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `ville`
--

INSERT INTO `ville` (`id`, `nom`, `coordonnee`) VALUES
(1, 'lyon', 0x0000000001010000009d2e8b89cddf4640dec83cf207631340),
(2, 'paris', 0x000000000101000000280af4893c6d484027c286a757ca0240),
(3, 'marseille', 0x0000000001010000000b46257502a6454067f2cd3637861540),
(4, 'bordeaux', 0x0000000001010000000820b589936b4640fa7e6abc7493e2bf),
(5, 'toulouse', 0x000000000101000000f623456458cd45400c76c3b64519f73f),
(6, 'nantes', 0x0000000001010000002b8716d9ce9b47400aa2ee0390daf8bf);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `ville`
--
ALTER TABLE `ville`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nom` (`nom`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `ville`
--
ALTER TABLE `ville`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

