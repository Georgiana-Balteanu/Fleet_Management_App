DROP TABLE IF EXISTS masini;
DROP TABLE IF EXISTS utilizatori;

CREATE TABLE utilizatori (
                             id_utilizator BIGINT PRIMARY KEY AUTO_INCREMENT,
                             nume VARCHAR(100) NOT NULL,
                             utilizator VARCHAR(100) NOT NULL UNIQUE,
                             parola VARCHAR(255) NOT NULL,
                             rol VARCHAR(30) NOT NULL
);

CREATE TABLE masini (
                        nr_inmatriculare VARCHAR(20) PRIMARY KEY,
                        id_utilizator BIGINT NOT NULL,
                        marca VARCHAR(100) NOT NULL,
                        model VARCHAR(100) NOT NULL,
                        culoare VARCHAR(50) NOT NULL,
                        anul_fabricatiei INT NOT NULL,
                        capacitatea_cilindrica INT NOT NULL,
                        tipul_de_combustibil VARCHAR(50) NOT NULL,
                        puterea INT NOT NULL,
                        cuplul INT NOT NULL,
                        volumul_portbagajului INT NOT NULL,
                        pretul DOUBLE NOT NULL,
                        CONSTRAINT fk_masini_utilizatori
                            FOREIGN KEY (id_utilizator) REFERENCES utilizatori(id_utilizator)
);