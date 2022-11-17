INSERT INTO regiones (name) VALUES ('Europa')
INSERT INTO regiones (name) VALUES ('Asia')
INSERT INTO regiones (name) VALUES ('Africa')
INSERT INTO regiones (name) VALUES ('Oceania')
INSERT INTO regiones (name) VALUES ('Antartida')
INSERT INTO regiones (name) VALUES ('Sudamerica')
INSERT INTO regiones (name) VALUES ('Norteamerica')
INSERT INTO regiones (name) VALUES ('Centroamerica')

INSERT INTO clientes (name,last_name,email,phone,create_at,id_regiones) VALUES('Jose','Perez','jp@email.com',534541,'2022-11-14',1);
INSERT INTO clientes (name,last_name,email,phone,create_at,id_regiones) VALUES('Maria','Ruiz','mr@email.com',534541,'2022-11-14',2);
INSERT INTO clientes (name,last_name,email,phone,create_at,id_regiones) VALUES('Carlos','Leon','cl@email.com',534541,'2022-11-14',3);
INSERT INTO clientes (name,last_name,email,phone,create_at,id_regiones) VALUES('Pedro','Sanchez','ps@email.com',534541,'2022-11-14',4);
INSERT INTO clientes (name,last_name,email,phone,create_at,id_regiones) VALUES('Juana','Arco','ja@email.com',534541,'2022-11-14',5);
INSERT INTO clientes (name,last_name,email,phone,create_at,id_regiones) VALUES('Luis','Yaris','ly@email.com',534541,'2022-11-14',6);
INSERT INTO clientes (name,last_name,email,phone,create_at,id_regiones) VALUES('Tito','Ramos','tr@email.com',534541,'2022-11-14',7);
INSERT INTO clientes (name,last_name,email,phone,create_at,id_regiones) VALUES('Juan','Casillas','jc@email.com',534541,'2022-11-14',8);
INSERT INTO clientes (name,last_name,email,phone,create_at,id_regiones) VALUES('Josefa','Raz','jr@email.com',534541,'2022-11-14',1);


INSERT INTO usuarios (username,password,enabled) VALUES ("rolando",'$2a$10$TkBmmuIoDWOLQlbJjC0ibuexzEeKp/VMovdCsXCD3E2.YpmKMWAKq',1);
INSERT INTO usuarios (username,password,enabled) VALUES ("admin",'$2a$10$YDm2LbKff5quS3a8JsczjucdUSUhPrhmUrb..cMGbjyBxWjNLYzRG',1);

INSERT INTO roles (name) VALUES('ROLE_USER');
INSERT INTO roles (name) VALUES('ROLE_ADMIN');

INSERT INTO usuarios_roles (usuario_id,role_id) VALUES(1,1);
INSERT INTO usuarios_roles (usuario_id,role_id) VALUES(2,2);



