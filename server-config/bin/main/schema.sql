CREATE TABLE IF NOT EXISTS peliculas (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  titulo VARCHAR(255),
  genero VARCHAR(50),
  estudio VARCHAR(70),
  puntuacion INT;
  estreno INT;
  PRIMARY KEY id
);
