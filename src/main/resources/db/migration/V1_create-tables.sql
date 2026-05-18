CREATE TABLE pais(
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    ddi VARCHAR(10)
);

CREATE TABLE cidade(
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    ddd VARCHAR(10),
    pais_id INT,
    FOREIGN KEY (pais_id) references pais(id)
);