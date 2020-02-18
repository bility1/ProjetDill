CREATE TABLE authentification(
	email VARCHAR(255) PRIMARY KEY NOT NULL,
	password VARCHAR(255),
	role VARCHAR(100),
	isAdmin boolean) 

INSERT INTO `authentification`(`email`, `password`, `role`, `isAdmin`) VALUES ("admin@admin.com","admin","ENSEIGNANT",true)
)


