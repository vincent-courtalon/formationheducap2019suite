
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


--
-- Base de données :  `base_hotelsinfo`
--

-- --------------------------------------------------------

--
-- Structure de la table `hotel`
--

CREATE TABLE `hotel` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `tarif_jour` double NOT NULL,
  `ville` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `hotel`
--

INSERT INTO `hotel` (`id`, `nom`, `tarif_jour`, `ville`) VALUES
(1, 'hotel du faubourg', 135, 'paris'),
(2, 'htel du rhone', 115, 'lyon'),
(3, 'hotel du port', 85, 'marseille'),
(4, 'hotel des aviateurs', 185, 'toulouse'),
(5, 'hotel des vignes', 165, 'bordeaux'),
(6, 'hotel de bretagne', 95, 'nantes');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `hotel`
--
ALTER TABLE `hotel`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_97ee430atx1b60hvajmu6yi0s` (`ville`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `hotel`
--
ALTER TABLE `hotel`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

