-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  ven. 28 fév. 2020 à 15:23
-- Version du serveur :  10.4.11-MariaDB
-- Version de PHP :  7.4.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `base_batch`
--
CREATE DATABASE IF NOT EXISTS `base_batch` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `base_batch`;

-- --------------------------------------------------------

--
-- Structure de la table `batch_job_execution`
--

CREATE TABLE `batch_job_execution` (
  `JOB_EXECUTION_ID` bigint(20) NOT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  `JOB_INSTANCE_ID` bigint(20) NOT NULL,
  `CREATE_TIME` datetime NOT NULL,
  `START_TIME` datetime DEFAULT NULL,
  `END_TIME` datetime DEFAULT NULL,
  `STATUS` varchar(10) DEFAULT NULL,
  `EXIT_CODE` varchar(2500) DEFAULT NULL,
  `EXIT_MESSAGE` varchar(2500) DEFAULT NULL,
  `LAST_UPDATED` datetime DEFAULT NULL,
  `JOB_CONFIGURATION_LOCATION` varchar(2500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `batch_job_execution`
--

INSERT INTO `batch_job_execution` (`JOB_EXECUTION_ID`, `VERSION`, `JOB_INSTANCE_ID`, `CREATE_TIME`, `START_TIME`, `END_TIME`, `STATUS`, `EXIT_CODE`, `EXIT_MESSAGE`, `LAST_UPDATED`, `JOB_CONFIGURATION_LOCATION`) VALUES
(1, 2, 1, '2020-02-27 16:49:13', '2020-02-27 16:49:13', '2020-02-27 16:49:13', 'COMPLETED', 'COMPLETED', '', '2020-02-27 16:49:13', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `batch_job_execution_context`
--

CREATE TABLE `batch_job_execution_context` (
  `JOB_EXECUTION_ID` bigint(20) NOT NULL,
  `SHORT_CONTEXT` varchar(2500) NOT NULL,
  `SERIALIZED_CONTEXT` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `batch_job_execution_context`
--

INSERT INTO `batch_job_execution_context` (`JOB_EXECUTION_ID`, `SHORT_CONTEXT`, `SERIALIZED_CONTEXT`) VALUES
(1, '{}', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `batch_job_execution_params`
--

CREATE TABLE `batch_job_execution_params` (
  `JOB_EXECUTION_ID` bigint(20) NOT NULL,
  `TYPE_CD` varchar(6) NOT NULL,
  `KEY_NAME` varchar(100) NOT NULL,
  `STRING_VAL` varchar(250) DEFAULT NULL,
  `DATE_VAL` datetime DEFAULT NULL,
  `LONG_VAL` bigint(20) DEFAULT NULL,
  `DOUBLE_VAL` double DEFAULT NULL,
  `IDENTIFYING` char(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `batch_job_execution_params`
--

INSERT INTO `batch_job_execution_params` (`JOB_EXECUTION_ID`, `TYPE_CD`, `KEY_NAME`, `STRING_VAL`, `DATE_VAL`, `LONG_VAL`, `DOUBLE_VAL`, `IDENTIFYING`) VALUES
(1, 'STRING', '-spring.output.ansi.enabled', 'always', '1970-01-01 01:00:00', 0, 0, 'N');

-- --------------------------------------------------------

--
-- Structure de la table `batch_job_execution_seq`
--

CREATE TABLE `batch_job_execution_seq` (
  `ID` bigint(20) NOT NULL,
  `UNIQUE_KEY` char(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `batch_job_execution_seq`
--

INSERT INTO `batch_job_execution_seq` (`ID`, `UNIQUE_KEY`) VALUES
(1, '0');

-- --------------------------------------------------------

--
-- Structure de la table `batch_job_instance`
--

CREATE TABLE `batch_job_instance` (
  `JOB_INSTANCE_ID` bigint(20) NOT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  `JOB_NAME` varchar(100) NOT NULL,
  `JOB_KEY` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `batch_job_instance`
--

INSERT INTO `batch_job_instance` (`JOB_INSTANCE_ID`, `VERSION`, `JOB_NAME`, `JOB_KEY`) VALUES
(1, 0, 'importuserJob', 'd41d8cd98f00b204e9800998ecf8427e');

-- --------------------------------------------------------

--
-- Structure de la table `batch_job_seq`
--

CREATE TABLE `batch_job_seq` (
  `ID` bigint(20) NOT NULL,
  `UNIQUE_KEY` char(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `batch_job_seq`
--

INSERT INTO `batch_job_seq` (`ID`, `UNIQUE_KEY`) VALUES
(1, '0');

-- --------------------------------------------------------

--
-- Structure de la table `batch_step_execution`
--

CREATE TABLE `batch_step_execution` (
  `STEP_EXECUTION_ID` bigint(20) NOT NULL,
  `VERSION` bigint(20) NOT NULL,
  `STEP_NAME` varchar(100) NOT NULL,
  `JOB_EXECUTION_ID` bigint(20) NOT NULL,
  `START_TIME` datetime NOT NULL,
  `END_TIME` datetime DEFAULT NULL,
  `STATUS` varchar(10) DEFAULT NULL,
  `COMMIT_COUNT` bigint(20) DEFAULT NULL,
  `READ_COUNT` bigint(20) DEFAULT NULL,
  `FILTER_COUNT` bigint(20) DEFAULT NULL,
  `WRITE_COUNT` bigint(20) DEFAULT NULL,
  `READ_SKIP_COUNT` bigint(20) DEFAULT NULL,
  `WRITE_SKIP_COUNT` bigint(20) DEFAULT NULL,
  `PROCESS_SKIP_COUNT` bigint(20) DEFAULT NULL,
  `ROLLBACK_COUNT` bigint(20) DEFAULT NULL,
  `EXIT_CODE` varchar(2500) DEFAULT NULL,
  `EXIT_MESSAGE` varchar(2500) DEFAULT NULL,
  `LAST_UPDATED` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `batch_step_execution`
--

INSERT INTO `batch_step_execution` (`STEP_EXECUTION_ID`, `VERSION`, `STEP_NAME`, `JOB_EXECUTION_ID`, `START_TIME`, `END_TIME`, `STATUS`, `COMMIT_COUNT`, `READ_COUNT`, `FILTER_COUNT`, `WRITE_COUNT`, `READ_SKIP_COUNT`, `WRITE_SKIP_COUNT`, `PROCESS_SKIP_COUNT`, `ROLLBACK_COUNT`, `EXIT_CODE`, `EXIT_MESSAGE`, `LAST_UPDATED`) VALUES
(1, 5, 'step1', 1, '2020-02-27 16:49:13', '2020-02-27 16:49:13', 'COMPLETED', 3, 10, 0, 10, 0, 0, 0, 0, 'COMPLETED', '', '2020-02-27 16:49:13');

-- --------------------------------------------------------

--
-- Structure de la table `batch_step_execution_context`
--

CREATE TABLE `batch_step_execution_context` (
  `STEP_EXECUTION_ID` bigint(20) NOT NULL,
  `SHORT_CONTEXT` varchar(2500) NOT NULL,
  `SERIALIZED_CONTEXT` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `batch_step_execution_context`
--

INSERT INTO `batch_step_execution_context` (`STEP_EXECUTION_ID`, `SHORT_CONTEXT`, `SERIALIZED_CONTEXT`) VALUES
(1, '{\"contactItemreader.read.count\":11,\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `batch_step_execution_seq`
--

CREATE TABLE `batch_step_execution_seq` (
  `ID` bigint(20) NOT NULL,
  `UNIQUE_KEY` char(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `batch_step_execution_seq`
--

INSERT INTO `batch_step_execution_seq` (`ID`, `UNIQUE_KEY`) VALUES
(1, '0');

-- --------------------------------------------------------

--
-- Structure de la table `contacts`
--

CREATE TABLE `contacts` (
  `id` int(11) NOT NULL,
  `nom` varchar(100) NOT NULL,
  `prenom` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `contacts`
--

INSERT INTO `contacts` (`id`, `nom`, `prenom`, `email`) VALUES
(1, 'EPONGE', 'bob', 'bob@eponge.com'),
(2, 'ETOILE', 'patrick', 'patrick@etoile.com'),
(3, 'STARK', 'tony', 'tony@stark.com'),
(4, 'PARKER', 'tony1', 'tony.parker@nba.com'),
(5, 'PARKER', 'tony2', 'tony.parker@nba.com'),
(6, 'PARKER', 'tony3', 'tony.parker@nba.com'),
(7, 'PARKER', 'tony4', 'tony.parker@nba.com'),
(8, 'PARKER', 'tony5', 'tony.parker@nba.com'),
(9, 'PARKER', 'tony6', 'tony.parker@nba.com'),
(10, 'PARKER', 'tony7', 'tony.parker@nba.com');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `batch_job_execution`
--
ALTER TABLE `batch_job_execution`
  ADD PRIMARY KEY (`JOB_EXECUTION_ID`),
  ADD KEY `JOB_INST_EXEC_FK` (`JOB_INSTANCE_ID`);

--
-- Index pour la table `batch_job_execution_context`
--
ALTER TABLE `batch_job_execution_context`
  ADD PRIMARY KEY (`JOB_EXECUTION_ID`);

--
-- Index pour la table `batch_job_execution_params`
--
ALTER TABLE `batch_job_execution_params`
  ADD KEY `JOB_EXEC_PARAMS_FK` (`JOB_EXECUTION_ID`);

--
-- Index pour la table `batch_job_execution_seq`
--
ALTER TABLE `batch_job_execution_seq`
  ADD UNIQUE KEY `UNIQUE_KEY_UN` (`UNIQUE_KEY`);

--
-- Index pour la table `batch_job_instance`
--
ALTER TABLE `batch_job_instance`
  ADD PRIMARY KEY (`JOB_INSTANCE_ID`),
  ADD UNIQUE KEY `JOB_INST_UN` (`JOB_NAME`,`JOB_KEY`);

--
-- Index pour la table `batch_job_seq`
--
ALTER TABLE `batch_job_seq`
  ADD UNIQUE KEY `UNIQUE_KEY_UN` (`UNIQUE_KEY`);

--
-- Index pour la table `batch_step_execution`
--
ALTER TABLE `batch_step_execution`
  ADD PRIMARY KEY (`STEP_EXECUTION_ID`),
  ADD KEY `JOB_EXEC_STEP_FK` (`JOB_EXECUTION_ID`);

--
-- Index pour la table `batch_step_execution_context`
--
ALTER TABLE `batch_step_execution_context`
  ADD PRIMARY KEY (`STEP_EXECUTION_ID`);

--
-- Index pour la table `batch_step_execution_seq`
--
ALTER TABLE `batch_step_execution_seq`
  ADD UNIQUE KEY `UNIQUE_KEY_UN` (`UNIQUE_KEY`);

--
-- Index pour la table `contacts`
--
ALTER TABLE `contacts`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `contacts`
--
ALTER TABLE `contacts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `batch_job_execution`
--
ALTER TABLE `batch_job_execution`
  ADD CONSTRAINT `JOB_INST_EXEC_FK` FOREIGN KEY (`JOB_INSTANCE_ID`) REFERENCES `batch_job_instance` (`JOB_INSTANCE_ID`);

--
-- Contraintes pour la table `batch_job_execution_context`
--
ALTER TABLE `batch_job_execution_context`
  ADD CONSTRAINT `JOB_EXEC_CTX_FK` FOREIGN KEY (`JOB_EXECUTION_ID`) REFERENCES `batch_job_execution` (`JOB_EXECUTION_ID`);

--
-- Contraintes pour la table `batch_job_execution_params`
--
ALTER TABLE `batch_job_execution_params`
  ADD CONSTRAINT `JOB_EXEC_PARAMS_FK` FOREIGN KEY (`JOB_EXECUTION_ID`) REFERENCES `batch_job_execution` (`JOB_EXECUTION_ID`);

--
-- Contraintes pour la table `batch_step_execution`
--
ALTER TABLE `batch_step_execution`
  ADD CONSTRAINT `JOB_EXEC_STEP_FK` FOREIGN KEY (`JOB_EXECUTION_ID`) REFERENCES `batch_job_execution` (`JOB_EXECUTION_ID`);

--
-- Contraintes pour la table `batch_step_execution_context`
--
ALTER TABLE `batch_step_execution_context`
  ADD CONSTRAINT `STEP_EXEC_CTX_FK` FOREIGN KEY (`STEP_EXECUTION_ID`) REFERENCES `batch_step_execution` (`STEP_EXECUTION_ID`);
--
-- Base de données :  `base_firstgridfsapp`
--
CREATE DATABASE IF NOT EXISTS `base_firstgridfsapp` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `base_firstgridfsapp`;

-- --------------------------------------------------------

--
-- Structure de la table `image`
--

CREATE TABLE `image` (
  `id` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `storage_id` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `image`
--

INSERT INTO `image` (`id`, `description`, `storage_id`) VALUES
(1, 'une capture de la gpl', '5e456ee94d73d52c95760fcc');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `image`
--
ALTER TABLE `image`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `image`
--
ALTER TABLE `image`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- Base de données :  `base_firstsecurity`
--
CREATE DATABASE IF NOT EXISTS `base_firstsecurity` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `base_firstsecurity`;

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `role_name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `role`
--

INSERT INTO `role` (`id`, `role_name`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER'),
(3, 'ROLE_VISITEUR');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `id` int(11) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `login` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `enabled`, `login`, `password`) VALUES
(1, b'1', 'admin', '$2a$10$53Z9eLijuY1NRzddnDCDDev19Ab8j487pi7BAl3xXdzvMV3kRtEcC'),
(2, b'1', 'vincent', '$2a$10$c2LnxYdw8gupJPKu/foEZ.S4YTQn7Y2aRzEJIZ49nnrHaaCjRYINy');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur_roles`
--

CREATE TABLE `utilisateur_roles` (
  `utilisateurs_id` int(11) NOT NULL,
  `roles_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `utilisateur_roles`
--

INSERT INTO `utilisateur_roles` (`utilisateurs_id`, `roles_id`) VALUES
(1, 1),
(1, 2),
(2, 2),
(2, 3);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_iubw515ff0ugtm28p8g3myt0h` (`role_name`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_18vwp4resqussqmlpqnymfqxk` (`login`);

--
-- Index pour la table `utilisateur_roles`
--
ALTER TABLE `utilisateur_roles`
  ADD PRIMARY KEY (`utilisateurs_id`,`roles_id`),
  ADD KEY `FKgvjufa2i7moss3i9eh2i9yoaq` (`roles_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `role`
--
ALTER TABLE `role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `utilisateur_roles`
--
ALTER TABLE `utilisateur_roles`
  ADD CONSTRAINT `FKgvjufa2i7moss3i9eh2i9yoaq` FOREIGN KEY (`roles_id`) REFERENCES `role` (`id`),
  ADD CONSTRAINT `FKirehnt4bumoo8qvpb6rvtom54` FOREIGN KEY (`utilisateurs_id`) REFERENCES `utilisateur` (`id`);
--
-- Base de données :  `base_hotelsinfo`
--
CREATE DATABASE IF NOT EXISTS `base_hotelsinfo` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `base_hotelsinfo`;

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
--
-- Base de données :  `base_livreinfos`
--
CREATE DATABASE IF NOT EXISTS `base_livreinfos` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `base_livreinfos`;

-- --------------------------------------------------------

--
-- Structure de la table `livre`
--

CREATE TABLE `livre` (
  `id` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `isbn` varchar(20) NOT NULL,
  `nb_pages` int(11) NOT NULL,
  `titre` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `livre`
--

INSERT INTO `livre` (`id`, `description`, `isbn`, `nb_pages`, `titre`) VALUES
(1, 'peche a la baleine', '123456789123', 345, 'moby dick'),
(2, 'une aventure autour du monde', '789123456789', 425, 'le tour du monde en 80 jours'),
(3, 'apprendre le java en toute circonstances', '456789123456', 750, 'java commando');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `livre`
--
ALTER TABLE `livre`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_9lix5phlv9fnqt4gsyu0qgb5` (`isbn`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `livre`
--
ALTER TABLE `livre`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- Base de données :  `base_mybooks`
--
CREATE DATABASE IF NOT EXISTS `base_mybooks` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `base_mybooks`;

-- --------------------------------------------------------

--
-- Structure de la table `lecteur`
--

CREATE TABLE `lecteur` (
  `id` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `password` varchar(80) NOT NULL,
  `username` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `lecteur`
--

INSERT INTO `lecteur` (`id`, `email`, `enabled`, `password`, `username`) VALUES
(1, 'moi@moi.com', b'1', '$2y$10$PrUUiTW0PxIc0N6jkHo01.PnDpkGg2hV/4um1MeBmtL/ckOu1KKrO', 'admin'),
(2, 'moi@moi.com\', lecteur.passsword=\'$2y$10$PrUUiTW0PxIc0N6jkHo01.PnDpkGg2hV/4um1MeBmtL/ckOu1KKrO\' WHERE lecteur.id=1 -- ', b'1', '$2a$10$YbtLo8FznsyQtjIh6kkGPezj1r4GVpjReDmC2zrAEwpCxE52fthw.', 'vincent');

-- --------------------------------------------------------

--
-- Structure de la table `lecteur_livres`
--

CREATE TABLE `lecteur_livres` (
  `lecteurs_id` int(11) NOT NULL,
  `livres_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `lecteur_livres`
--

INSERT INTO `lecteur_livres` (`lecteurs_id`, `livres_id`) VALUES
(1, 1),
(1, 2),
(1, 4),
(2, 2),
(2, 3);

-- --------------------------------------------------------

--
-- Structure de la table `lecteur_roles`
--

CREATE TABLE `lecteur_roles` (
  `lecteurs_id` int(11) NOT NULL,
  `roles_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `lecteur_roles`
--

INSERT INTO `lecteur_roles` (`lecteurs_id`, `roles_id`) VALUES
(1, 1),
(1, 2),
(2, 2);

-- --------------------------------------------------------

--
-- Structure de la table `livre`
--

CREATE TABLE `livre` (
  `id` int(11) NOT NULL,
  `isbn` varchar(20) NOT NULL,
  `titre` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `livre`
--

INSERT INTO `livre` (`id`, `isbn`, `titre`) VALUES
(1, '35431324354', '20000 lieux sous les mers'),
(2, '876213431', 'java acrobatique'),
(3, '64312131321', 'angular de combat'),
(4, '399432187', 'l\'ile flottante au trésors');

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `rolename` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `role`
--

INSERT INTO `role` (`id`, `rolename`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `lecteur`
--
ALTER TABLE `lecteur`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_a7nv7h6s1ehkc6s3gyp8dot9s` (`username`);

--
-- Index pour la table `lecteur_livres`
--
ALTER TABLE `lecteur_livres`
  ADD PRIMARY KEY (`lecteurs_id`,`livres_id`),
  ADD KEY `FKpaonvfl7hpge9d6wowevnoqa2` (`livres_id`);

--
-- Index pour la table `lecteur_roles`
--
ALTER TABLE `lecteur_roles`
  ADD PRIMARY KEY (`lecteurs_id`,`roles_id`),
  ADD KEY `FK5gf8rpb9mx39wvygtqrhrbs3o` (`roles_id`);

--
-- Index pour la table `livre`
--
ALTER TABLE `livre`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_9lix5phlv9fnqt4gsyu0qgb5` (`isbn`);

--
-- Index pour la table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_nctmxadhieiw7aduxjy4dfglt` (`rolename`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `lecteur`
--
ALTER TABLE `lecteur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `livre`
--
ALTER TABLE `livre`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `role`
--
ALTER TABLE `role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `lecteur_livres`
--
ALTER TABLE `lecteur_livres`
  ADD CONSTRAINT `FKhp2lijiabd4i27vqc3tntkqm6` FOREIGN KEY (`lecteurs_id`) REFERENCES `lecteur` (`id`),
  ADD CONSTRAINT `FKpaonvfl7hpge9d6wowevnoqa2` FOREIGN KEY (`livres_id`) REFERENCES `livre` (`id`);

--
-- Contraintes pour la table `lecteur_roles`
--
ALTER TABLE `lecteur_roles`
  ADD CONSTRAINT `FK2uqu5rlxj53h4vwqyjrmgb8dw` FOREIGN KEY (`lecteurs_id`) REFERENCES `lecteur` (`id`),
  ADD CONSTRAINT `FK5gf8rpb9mx39wvygtqrhrbs3o` FOREIGN KEY (`roles_id`) REFERENCES `role` (`id`);
--
-- Base de données :  `base_mymovies`
--
CREATE DATABASE IF NOT EXISTS `base_mymovies` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `base_mymovies`;

-- --------------------------------------------------------

--
-- Structure de la table `movie`
--

CREATE TABLE `movie` (
  `id` int(11) NOT NULL,
  `date_sortie` date DEFAULT NULL,
  `duree_minutes` int(11) NOT NULL,
  `titre` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `movie`
--

INSERT INTO `movie` (`id`, `date_sortie`, `duree_minutes`, `titre`) VALUES
(1, '2015-01-15', 145, 'expert java'),
(2, '2009-01-01', 130, 'java contre c#, la bataille'),
(3, '2002-01-15', 125, 'dragon ball evolution'),
(4, '2020-01-03', 115, 'tintin a hongkong'),
(5, '2018-01-22', 95, 'james bond hack oauth');

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `role_name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `role`
--

INSERT INTO `role` (`id`, `role_name`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER'),
(3, 'ROLE_VISITEUR');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `id` int(11) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `login` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `enabled`, `login`, `password`) VALUES
(1, b'1', 'admin', '$2a$10$Yt2UwdgZz7gch7IDMCg5bOzIPWTrzcRhYQ/66oIi86yjpwVWAr6RK'),
(2, b'1', 'vincent', '$2a$10$hCZjawYdqFBwplI79QqS5.Mw/ydlqMemseKuOoAJIUnRIATSYsz6q');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur_roles`
--

CREATE TABLE `utilisateur_roles` (
  `utilisateurs_id` int(11) NOT NULL,
  `roles_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `utilisateur_roles`
--

INSERT INTO `utilisateur_roles` (`utilisateurs_id`, `roles_id`) VALUES
(1, 1),
(1, 2),
(2, 2),
(2, 3);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `movie`
--
ALTER TABLE `movie`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_iubw515ff0ugtm28p8g3myt0h` (`role_name`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_18vwp4resqussqmlpqnymfqxk` (`login`);

--
-- Index pour la table `utilisateur_roles`
--
ALTER TABLE `utilisateur_roles`
  ADD PRIMARY KEY (`utilisateurs_id`,`roles_id`),
  ADD KEY `FKgvjufa2i7moss3i9eh2i9yoaq` (`roles_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `movie`
--
ALTER TABLE `movie`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `role`
--
ALTER TABLE `role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `utilisateur_roles`
--
ALTER TABLE `utilisateur_roles`
  ADD CONSTRAINT `FKgvjufa2i7moss3i9eh2i9yoaq` FOREIGN KEY (`roles_id`) REFERENCES `role` (`id`),
  ADD CONSTRAINT `FKirehnt4bumoo8qvpb6rvtom54` FOREIGN KEY (`utilisateurs_id`) REFERENCES `utilisateur` (`id`);
--
-- Base de données :  `base_myxss`
--
CREATE DATABASE IF NOT EXISTS `base_myxss` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `base_myxss`;

-- --------------------------------------------------------

--
-- Structure de la table `message`
--

CREATE TABLE `message` (
  `id` int(11) NOT NULL,
  `corps` varchar(255) DEFAULT NULL,
  `titre` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `message`
--

INSERT INTO `message` (`id`, `corps`, `titre`) VALUES
(1, 'il fait froid aujourd\'hui', 'bonjour'),
(5, '<b>titre</b><div class=\"haha\">hello</div>un autre', 'pawned3');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `message`
--
ALTER TABLE `message`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `message`
--
ALTER TABLE `message`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- Base de données :  `base_nanomania`
--
CREATE DATABASE IF NOT EXISTS `base_nanomania` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `base_nanomania`;

-- --------------------------------------------------------

--
-- Structure de la table `editeur`
--

CREATE TABLE `editeur` (
  `id` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `editeur`
--

INSERT INTO `editeur` (`id`, `email`, `nom`) VALUES
(1, 'ubi@soft.com', 'ubisoft'),
(2, 'rockstar@rockstar.com', 'rockstar');

-- --------------------------------------------------------

--
-- Structure de la table `genre`
--

CREATE TABLE `genre` (
  `id` int(11) NOT NULL,
  `libelle` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `genre`
--

INSERT INTO `genre` (`id`, `libelle`) VALUES
(1, 'aventure'),
(2, 'rpg'),
(3, 'sport');

-- --------------------------------------------------------

--
-- Structure de la table `jeux_video`
--

CREATE TABLE `jeux_video` (
  `id` int(11) NOT NULL,
  `date_sortie` date DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `editeur_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `jeux_video`
--

INSERT INTO `jeux_video` (`id`, `date_sortie`, `nom`, `editeur_id`) VALUES
(1, '2015-10-12', 'gta4', 2),
(2, '2016-10-12', 'monster hunter world', 1);

-- --------------------------------------------------------

--
-- Structure de la table `jeux_video_genres`
--

CREATE TABLE `jeux_video_genres` (
  `jeux_videos_id` int(11) NOT NULL,
  `genres_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `jeux_video_genres`
--

INSERT INTO `jeux_video_genres` (`jeux_videos_id`, `genres_id`) VALUES
(1, 1),
(1, 2),
(2, 1),
(2, 2);

-- --------------------------------------------------------

--
-- Structure de la table `jeux_video_plate_formes`
--

CREATE TABLE `jeux_video_plate_formes` (
  `jeux_videos_id` int(11) NOT NULL,
  `plate_formes_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `jeux_video_plate_formes`
--

INSERT INTO `jeux_video_plate_formes` (`jeux_videos_id`, `plate_formes_id`) VALUES
(1, 1),
(1, 2),
(2, 2),
(2, 3);

-- --------------------------------------------------------

--
-- Structure de la table `plate_forme`
--

CREATE TABLE `plate_forme` (
  `id` int(11) NOT NULL,
  `marque` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `plate_forme`
--

INSERT INTO `plate_forme` (`id`, `marque`, `nom`) VALUES
(1, 'microsoft', 'xbox one'),
(2, 'sony', 'ps4'),
(3, 'nintendo', 'switch');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `editeur`
--
ALTER TABLE `editeur`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `genre`
--
ALTER TABLE `genre`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `jeux_video`
--
ALTER TABLE `jeux_video`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKeq5slw6kvum6vxw5v2gj0bq9i` (`editeur_id`);

--
-- Index pour la table `jeux_video_genres`
--
ALTER TABLE `jeux_video_genres`
  ADD PRIMARY KEY (`jeux_videos_id`,`genres_id`),
  ADD KEY `FKlk9jgt1t89iwdb3xi0e2qdlh9` (`genres_id`);

--
-- Index pour la table `jeux_video_plate_formes`
--
ALTER TABLE `jeux_video_plate_formes`
  ADD PRIMARY KEY (`jeux_videos_id`,`plate_formes_id`),
  ADD KEY `FKmomym8p67sffky71n2tt1n62b` (`plate_formes_id`);

--
-- Index pour la table `plate_forme`
--
ALTER TABLE `plate_forme`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `editeur`
--
ALTER TABLE `editeur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `genre`
--
ALTER TABLE `genre`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `jeux_video`
--
ALTER TABLE `jeux_video`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `plate_forme`
--
ALTER TABLE `plate_forme`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `jeux_video`
--
ALTER TABLE `jeux_video`
  ADD CONSTRAINT `FKeq5slw6kvum6vxw5v2gj0bq9i` FOREIGN KEY (`editeur_id`) REFERENCES `editeur` (`id`);

--
-- Contraintes pour la table `jeux_video_genres`
--
ALTER TABLE `jeux_video_genres`
  ADD CONSTRAINT `FKlk9jgt1t89iwdb3xi0e2qdlh9` FOREIGN KEY (`genres_id`) REFERENCES `genre` (`id`),
  ADD CONSTRAINT `FKp6oqdy1i1xex5uf26fv0gkoqk` FOREIGN KEY (`jeux_videos_id`) REFERENCES `jeux_video` (`id`);

--
-- Contraintes pour la table `jeux_video_plate_formes`
--
ALTER TABLE `jeux_video_plate_formes`
  ADD CONSTRAINT `FK8qlhdn1ptghgpcyx9nbicmsvj` FOREIGN KEY (`jeux_videos_id`) REFERENCES `jeux_video` (`id`),
  ADD CONSTRAINT `FKmomym8p67sffky71n2tt1n62b` FOREIGN KEY (`plate_formes_id`) REFERENCES `plate_forme` (`id`);
--
-- Base de données :  `base_ratings`
--
CREATE DATABASE IF NOT EXISTS `base_ratings` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `base_ratings`;

-- --------------------------------------------------------

--
-- Structure de la table `rating`
--

CREATE TABLE `rating` (
  `id` int(11) NOT NULL,
  `date_created` date DEFAULT NULL,
  `isbn` varchar(20) NOT NULL,
  `note` double NOT NULL,
  `user_id` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `rating`
--

INSERT INTO `rating` (`id`, `date_created`, `isbn`, `note`, `user_id`) VALUES
(1, '2020-01-01', '123456789123', 9, 'bob1234'),
(2, '2018-01-01', '789123456789', 8, 'bob1234'),
(3, '2020-01-13', '789123456789', 7.5, 'joe1234'),
(4, '2020-01-21', '456789123456', 9.5, 'joe1234');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `rating`
--
ALTER TABLE `rating`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `rating`
--
ALTER TABLE `rating`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- Base de données :  `base_revision1`
--
CREATE DATABASE IF NOT EXISTS `base_revision1` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `base_revision1`;

-- --------------------------------------------------------

--
-- Structure de la table `soda`
--

CREATE TABLE `soda` (
  `id` int(11) NOT NULL,
  `marque` varchar(255) DEFAULT NULL,
  `denomination` varchar(100) NOT NULL,
  `prix` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `soda`
--

INSERT INTO `soda` (`id`, `marque`, `denomination`, `prix`) VALUES
(1, 'coca-cola', 'coca-cola', 1.1),
(2, 'coca-cola', 'orangina', 1.5),
(3, 'coca-cola', 'schweps', 1.3);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `soda`
--
ALTER TABLE `soda`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `soda`
--
ALTER TABLE `soda`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- Base de données :  `base_revision2`
--
CREATE DATABASE IF NOT EXISTS `base_revision2` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `base_revision2`;

-- --------------------------------------------------------

--
-- Structure de la table `caracteristique`
--

CREATE TABLE `caracteristique` (
  `id` int(11) NOT NULL,
  `libelle` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `caracteristique`
--

INSERT INTO `caracteristique` (`id`, `libelle`) VALUES
(1, 'petillant'),
(2, 'rouge'),
(3, 'blanc'),
(5, 'fruité'),
(6, 'boisé'),
(7, 'liquoreux'),
(8, 'dessert');

-- --------------------------------------------------------

--
-- Structure de la table `terroir`
--

CREATE TABLE `terroir` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `terroir`
--

INSERT INTO `terroir` (`id`, `nom`) VALUES
(1, 'bourgogne'),
(2, 'champagne');

-- --------------------------------------------------------

--
-- Structure de la table `vin`
--

CREATE TABLE `vin` (
  `id` int(11) NOT NULL,
  `annee` int(11) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `terroir_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `vin`
--

INSERT INTO `vin` (`id`, `annee`, `nom`, `terroir_id`) VALUES
(1, 2001, 'chateau tokyo', 1),
(3, 2016, 'ruinart', 2),
(4, 2006, 'dom perignon', 2);

-- --------------------------------------------------------

--
-- Structure de la table `vin_caracteristiques`
--

CREATE TABLE `vin_caracteristiques` (
  `vins_id` int(11) NOT NULL,
  `caracteristiques_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `vin_caracteristiques`
--

INSERT INTO `vin_caracteristiques` (`vins_id`, `caracteristiques_id`) VALUES
(1, 2),
(1, 6),
(1, 7),
(4, 1),
(4, 3);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `caracteristique`
--
ALTER TABLE `caracteristique`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `terroir`
--
ALTER TABLE `terroir`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `vin`
--
ALTER TABLE `vin`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKh00xwhvoxifbwhg9e4wpcc5k0` (`terroir_id`);

--
-- Index pour la table `vin_caracteristiques`
--
ALTER TABLE `vin_caracteristiques`
  ADD PRIMARY KEY (`vins_id`,`caracteristiques_id`),
  ADD KEY `FK68rk89gom5eda5nh6mb0xk5uf` (`caracteristiques_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `caracteristique`
--
ALTER TABLE `caracteristique`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT pour la table `terroir`
--
ALTER TABLE `terroir`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `vin`
--
ALTER TABLE `vin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `vin`
--
ALTER TABLE `vin`
  ADD CONSTRAINT `FKh00xwhvoxifbwhg9e4wpcc5k0` FOREIGN KEY (`terroir_id`) REFERENCES `terroir` (`id`);

--
-- Contraintes pour la table `vin_caracteristiques`
--
ALTER TABLE `vin_caracteristiques`
  ADD CONSTRAINT `FK68rk89gom5eda5nh6mb0xk5uf` FOREIGN KEY (`caracteristiques_id`) REFERENCES `caracteristique` (`id`),
  ADD CONSTRAINT `FKfrqyy6j3nos58m4ekc5og2d1n` FOREIGN KEY (`vins_id`) REFERENCES `vin` (`id`);
--
-- Base de données :  `base_revision_prod2`
--
CREATE DATABASE IF NOT EXISTS `base_revision_prod2` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `base_revision_prod2`;

-- --------------------------------------------------------

--
-- Structure de la table `caracteristique`
--

CREATE TABLE `caracteristique` (
  `id` int(11) NOT NULL,
  `libelle` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `terroir`
--

CREATE TABLE `terroir` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `vin`
--

CREATE TABLE `vin` (
  `id` int(11) NOT NULL,
  `annee` int(11) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `terroir_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `vin_caracteristiques`
--

CREATE TABLE `vin_caracteristiques` (
  `vins_id` int(11) NOT NULL,
  `caracteristiques_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `caracteristique`
--
ALTER TABLE `caracteristique`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `terroir`
--
ALTER TABLE `terroir`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `vin`
--
ALTER TABLE `vin`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKh00xwhvoxifbwhg9e4wpcc5k0` (`terroir_id`);

--
-- Index pour la table `vin_caracteristiques`
--
ALTER TABLE `vin_caracteristiques`
  ADD PRIMARY KEY (`vins_id`,`caracteristiques_id`),
  ADD KEY `FK68rk89gom5eda5nh6mb0xk5uf` (`caracteristiques_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `caracteristique`
--
ALTER TABLE `caracteristique`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `terroir`
--
ALTER TABLE `terroir`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `vin`
--
ALTER TABLE `vin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `vin`
--
ALTER TABLE `vin`
  ADD CONSTRAINT `FKh00xwhvoxifbwhg9e4wpcc5k0` FOREIGN KEY (`terroir_id`) REFERENCES `terroir` (`id`);

--
-- Contraintes pour la table `vin_caracteristiques`
--
ALTER TABLE `vin_caracteristiques`
  ADD CONSTRAINT `FK68rk89gom5eda5nh6mb0xk5uf` FOREIGN KEY (`caracteristiques_id`) REFERENCES `caracteristique` (`id`),
  ADD CONSTRAINT `FKfrqyy6j3nos58m4ekc5og2d1n` FOREIGN KEY (`vins_id`) REFERENCES `vin` (`id`);
--
-- Base de données :  `base_security_oauth2`
--
CREATE DATABASE IF NOT EXISTS `base_security_oauth2` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `base_security_oauth2`;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `user_type` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `email`, `image_url`, `name`, `user_type`) VALUES
(1, 'vincent.courtalon@gmail.com', 'https://lh4.googleusercontent.com/-oNGRVk4-wH8/AAAAAAAAAAI/AAAAAAAAAAA/ACHi3re3ADKQSkY9pV2Lizyc1CFDziH0KA/s96-c/photo.jpg', 'vincent courtalon', 'google');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- Base de données :  `base_springboutique`
--
CREATE DATABASE IF NOT EXISTS `base_springboutique` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `base_springboutique`;

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

CREATE TABLE `produit` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `poids` double NOT NULL,
  `prix` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `produit`
--

INSERT INTO `produit` (`id`, `nom`, `poids`, `prix`) VALUES
(1, 'brochette boeuf fromage', 0.2, 12.99),
(2, 'sushi saumon', 0.15, 13.99),
(3, 'riz nature', 0.3, 4.99),
(4, 'chirashi saumon avocat', 0.5, 11.99),
(5, 'mochi glace noisette', 0.2, 3.99);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `produit`
--
ALTER TABLE `produit`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `produit`
--
ALTER TABLE `produit`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- Base de données :  `base_springplanification`
--
CREATE DATABASE IF NOT EXISTS `base_springplanification` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `base_springplanification`;

-- --------------------------------------------------------

--
-- Structure de la table `intervenant`
--

CREATE TABLE `intervenant` (
  `id` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `intervenant`
--

INSERT INTO `intervenant` (`id`, `email`, `nom`) VALUES
(1, 'bobo@eponge.com', 'bob'),
(2, 'mario@plombier.com', 'mario');

-- --------------------------------------------------------

--
-- Structure de la table `intervention`
--

CREATE TABLE `intervention` (
  `id` int(11) NOT NULL,
  `date_intervention` date DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `heure_debut` int(11) NOT NULL,
  `heure_fin` int(11) NOT NULL,
  `lieu` varchar(255) DEFAULT NULL,
  `intervenant_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `intervention`
--

INSERT INTO `intervention` (`id`, `date_intervention`, `description`, `heure_debut`, `heure_fin`, `lieu`, `intervenant_id`) VALUES
(1, '2020-02-03', 'reparer porte des toilettes', 14, 15, 'edugroupe', 2),
(2, '2020-02-05', 'peindre le mur gauche', 14, 18, 'elysee', 1),
(3, '2020-02-05', 'peindre le mur droit', 9, 12, 'elysee', 1);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `intervenant`
--
ALTER TABLE `intervenant`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `intervention`
--
ALTER TABLE `intervention`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK1gq45xrtd7j688juiuto7vs3x` (`intervenant_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `intervenant`
--
ALTER TABLE `intervenant`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `intervention`
--
ALTER TABLE `intervention`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `intervention`
--
ALTER TABLE `intervention`
  ADD CONSTRAINT `FK1gq45xrtd7j688juiuto7vs3x` FOREIGN KEY (`intervenant_id`) REFERENCES `intervenant` (`id`);
--
-- Base de données :  `base_springzoo`
--
CREATE DATABASE IF NOT EXISTS `base_springzoo` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `base_springzoo`;

-- --------------------------------------------------------

--
-- Structure de la table `animal`
--

CREATE TABLE `animal` (
  `id` int(11) NOT NULL,
  `espece` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `zoo_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `animal`
--

INSERT INTO `animal` (`id`, `espece`, `nom`, `zoo_id`) VALUES
(1, 'kangourou', 'jumpy', 1),
(2, 'bison', 'le futé', 1),
(3, 'panda', 'pandi', 2),
(4, 'chimpanzee', 'bobo2', 1),
(5, 'renard', 'chippeur', 2),
(6, 'renard', 'maitre', NULL),
(7, 'dauphin', 'flipper', 4),
(8, 'gorille', 'koko', 4),
(9, 'zebre', 'mada', 4),
(10, 'pingouin', 'tux', 4),
(11, 'lion', 'mufassa', 2),
(12, 'lion', 'scar', 4),
(13, 'tigre', 'baguerra', 1);

-- --------------------------------------------------------

--
-- Structure de la table `zoo`
--

CREATE TABLE `zoo` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `ville` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `zoo`
--

INSERT INTO `zoo` (`id`, `nom`, `ville`) VALUES
(1, 'zoo de beauval', 'beauval'),
(2, 'la menagerie des plantes', 'paris'),
(4, 'zoo de bruxelle', 'bruxelle');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `animal`
--
ALTER TABLE `animal`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKs26gik9b4u7e2iu4hpruc3vyw` (`zoo_id`);

--
-- Index pour la table `zoo`
--
ALTER TABLE `zoo`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `animal`
--
ALTER TABLE `animal`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT pour la table `zoo`
--
ALTER TABLE `zoo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `animal`
--
ALTER TABLE `animal`
  ADD CONSTRAINT `FKs26gik9b4u7e2iu4hpruc3vyw` FOREIGN KEY (`zoo_id`) REFERENCES `zoo` (`id`);
--
-- Base de données :  `exemple_ville`
--
CREATE DATABASE IF NOT EXISTS `exemple_ville` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `exemple_ville`;

-- --------------------------------------------------------

--
-- Structure de la table `ville`
--

CREATE TABLE `ville` (
  `id` int(11) NOT NULL,
  `nom` varchar(100) NOT NULL,
  `coordonnees` point NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `ville`
--

INSERT INTO `ville` (`id`, `nom`, `coordonnees`) VALUES
(0, 'paris', 0x000000000101000000280af4893c6d484027c286a757ca0240),
(0, 'lyon', 0x0000000001010000009d2e8b89cddf4640dec83cf207631340);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
