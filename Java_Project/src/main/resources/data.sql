DELETE FROM masini;
DELETE FROM utilizatori;

INSERT INTO utilizatori (id_utilizator, nume, utilizator, parola, rol) VALUES
                                                                           (1, 'User Test', 'user', '$2a$10$uNVRqTpbne/Og.rQSf1aoujrk.mpxR.topsR7j4PeTN9NYLnpGNrC', 'ROLE_USER'),
                                                                           (2, 'Editor Test', 'administrator', '$2a$10$jxuuFg.IYpvx0mVbP5elSuj48kmyOFYVaQev/PXZl2MQtMuYm5FcO', 'ROLE_EDITOR');

INSERT INTO masini (
    nr_inmatriculare,
    id_utilizator,
    marca,
    model,
    culoare,
    anul_fabricatiei,
    capacitatea_cilindrica,
    tipul_de_combustibil,
    puterea,
    cuplul,
    volumul_portbagajului,
    pretul
) VALUES
      ('TM01AAA', 2, 'Ford', 'Focus', 'Blue', 2019, 1598, 'Benzina', 125, 170, 375, 14500),
      ('TM02BBB', 2, 'Volkswagen', 'Golf', 'White', 2020, 1968, 'Diesel', 150, 340, 380, 17800),
      ('TM03CCC', 1, 'Dacia', 'Logan', 'Gray', 2021, 999, 'Benzina', 90, 160, 510, 11800),
      ('TM04DDD', 1, 'BMW', '320d', 'Black', 2018, 1995, 'Diesel', 190, 400, 480, 22500);