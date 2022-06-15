


INSERT INTO `personnes` (`rfid`, `email`, `image`, `nom`, `prenom`, `poste`, `date_creation`) VALUES ('S7E2DC8', 'ilyas.ghazoui@gmail.com', '/assets/img/uploads/ilyas.jpg', 'Ghazoui', 'Ilyas', 'Professeur', now());
INSERT INTO `personnes` (`rfid`, `email`, `image`, `nom`, `prenom`, `poste`, `date_creation`) VALUES ('B9D3EE7', 'mouad.chakiri@gmail.com', '/assets/img/uploads/mouad.jpg', 'Chakiri', 'Mouad', 'Professeur', now());
INSERT INTO `personnes` (`rfid`, `email`, `image`, `nom`, `prenom`, `poste`, `date_creation`) VALUES ('E459SE1', 'khalid.ait-zi@gmail.com', '/assets/img/uploads/khalid.jpg', 'Ait-Zi', 'Khalid', 'Professeur', now());
INSERT INTO `personnes` (`rfid`, `email`, `image`, `nom`, `prenom`, `poste`, `date_creation`) VALUES ('A8JK90S', 'hajar.el-moudden@email.com', '/assets/img/uploads/hajar.jpeg', 'El Moudden', 'Hajar', 'Secretaire', now());



INSERT INTO `salles` (`id`, `image`, `nom`, `location`) VALUES (NULL, '/assets/img/uploads/amphi1.jpg', 'Amphi Al Khawarizmi', 'Etage 1');
INSERT INTO `salles` (`id`, `image`, `nom`, `location`) VALUES (NULL, '/assets/img/uploads/salle1.jpg', 'Salle A1', 'Etage 2');
INSERT INTO `salles` (`id`, `image`, `nom`, `location`) VALUES (NULL, '/assets/img/uploads/salle2.jpg', 'Salle A2', 'Etage 3');
INSERT INTO `salles` (`id`, `image`, `nom`, `location`) VALUES (NULL, '/assets/img/uploads/amphi2.jpg', 'Amphi Ibn Batouta', 'Etage 2');
INSERT INTO `salles` (`id`, `image`, `nom`, `location`) VALUES (NULL, '/assets/img/uploads/laboMaster.jpg', 'Labo Master', 'Etage 3');



Insert into `personne_salle`(`id`, `date_entree`, `id_pers`, `id_classe`) values (null, now(), 'S7E2DC8', 1);
Insert into `personne_salle`(`id`, `date_entree`, `id_pers`, `id_classe`) values (null, now(), 'B9D3EE7', 1);
Insert into `personne_salle`(`id`, `date_entree`, `id_pers`, `id_classe`) values (null, now(), 'E459SE1', 1);
Insert into `personne_salle`(`id`, `date_entree`, `id_pers`, `id_classe`) values (null, now(), 'S7E2DC8', 1);
Insert into `personne_salle`(`id`, `date_entree`, `id_pers`, `id_classe`) values (null, now(), 'A8JK90S', 1);

Insert into `personne_salle`(`id`, `date_entree`, `id_pers`, `id_classe`) values (null, now(), 'S7E2DC8', 2);
Insert into `personne_salle`(`id`, `date_entree`, `id_pers`, `id_classe`) values (null, now(), 'A8JK90S', 2);
Insert into `personne_salle`(`id`, `date_entree`, `id_pers`, `id_classe`) values (null, now(), 'B9D3EE7', 2);
Insert into `personne_salle`(`id`, `date_entree`, `id_pers`, `id_classe`) values (null, now(), 'E459SE1', 2);
Insert into `personne_salle`(`id`, `date_entree`, `id_pers`, `id_classe`) values (null, now(), 'B9D3EE7', 2);
Insert into `personne_salle`(`id`, `date_entree`, `id_pers`, `id_classe`) values (null, now(), 'A8JK90S', 2);



Insert into `personne_salle`(`id`, `date_entree`, `id_pers`, `id_classe`) values (null, now(), 'A8JK90S', 3);
Insert into `personne_salle`(`id`, `date_entree`, `id_pers`, `id_classe`) values (null, now(), 'E459SE1', 3);
Insert into `personne_salle`(`id`, `date_entree`, `id_pers`, `id_classe`) values (null, now(), 'S7E2DC8', 3);
Insert into `personne_salle`(`id`, `date_entree`, `id_pers`, `id_classe`) values (null, now(), 'B9D3EE7', 3);


Insert into `personne_salle`(`id`, `date_entree`, `id_pers`, `id_classe`) values (null, now(), 'S7E2DC8', 4);
Insert into `personne_salle`(`id`, `date_entree`, `id_pers`, `id_classe`) values (null, now(), 'B9D3EE7', 4);
Insert into `personne_salle`(`id`, `date_entree`, `id_pers`, `id_classe`) values (null, now(), 'E459SE1', 4);
Insert into `personne_salle`(`id`, `date_entree`, `id_pers`, `id_classe`) values (null, now(), 'A8JK90S', 4);

Insert into `personne_salle`(`id`, `date_entree`, `id_pers`, `id_classe`) values (null, now(), 'S7E2DC8', 5);
Insert into `personne_salle`(`id`, `date_entree`, `id_pers`, `id_classe`) values (null, now(), 'E459SE1', 5);
Insert into `personne_salle`(`id`, `date_entree`, `id_pers`, `id_classe`) values (null, now(), 'B9D3EE7', 5);
Insert into `personne_salle`(`id`, `date_entree`, `id_pers`, `id_classe`) values (null, now(), 'A8JK90S', 5);




