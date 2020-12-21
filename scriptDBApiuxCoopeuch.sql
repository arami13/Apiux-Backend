CREATE TABLE dbo.Tareas (
    identificador int IDENTITY(1,1) NOT NULL,
    descripcion  varchar(255) NOT NULL,
    fechacreacion Datetime NOT NULL,
    vigente TINYINT NOT NULL
    CONSTRAINT PK_Person PRIMARY KEY (identificador)
);

insert into tareas (descripcion,vigente,fechaCreacion) VALUES('Tarea1',1,convert(datetime,'2020-12-12 08:10:10'))
insert into tareas (descripcion,vigente,fechaCreacion) VALUES('Tarea2',1,convert(datetime,'2020-12-13 08:20:10'))
insert into tareas (descripcion,vigente,fechaCreacion) VALUES('Tarea3',1,convert(datetime,'2020-12-14 08:30:10'))
insert into tareas (descripcion,vigente,fechaCreacion) VALUES('Tarea4',1,convert(datetime,'2020-12-15 08:40:10'))
insert into tareas (descripcion,vigente,fechaCreacion) VALUES('Tarea5',1,convert(datetime,'2020-12-16 08:50:10'))
insert into tareas (descripcion,vigente,fechaCreacion) VALUES('Tarea6',1,convert(datetime,'2020-12-17 08:11:10'))
insert into tareas (descripcion,vigente,fechaCreacion) VALUES('Tarea7',1,convert(datetime,'2020-12-18 08:12:10'))
insert into tareas (descripcion,vigente,fechaCreacion) VALUES('Tarea8',1,convert(datetime,'2020-12-19 08:13:10'))
insert into tareas (descripcion,vigente,fechaCreacion) VALUES('Tarea9',1,convert(datetime,'2020-12-20 08:14:10'))
GO