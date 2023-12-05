CREATE TABLE IF NOT EXISTS Filme (
                                     idFilme BIGINT PRIMARY KEY AUTO_INCREMENT,
                                     titulo VARCHAR(255) NOT NULL,
                                     diretor VARCHAR(255) NOT NULL,
                                     genero VARCHAR(255) NOT NULL,
                                     sinopse VARCHAR(280) NOT NULL,
                                     lancamento DATE,
                                     duracao INT NOT NULL,
                                     classificacao INT NOT NULL,
                                     distribuidora VARCHAR(255) NOT NULL,
                                     personagens VARCHAR(255) NOT NULL

);