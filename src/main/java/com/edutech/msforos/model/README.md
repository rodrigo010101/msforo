# ManyToOne

@ManyToOne: muchos mensajes pertenecen a un solo foro.

@JoinColumn: defino la columna en la tabla mensaje_foro que actuará como clave foránea (foro_curso_id) apuntando al ForoCurso.

foroCurso: es la instancia del objeto padre (la entidad a la que este mensaje pertenece).

# OneToMany

@OneToMany: un foro puede tener muchos mensajes.

mappedBy = "foroCurso": indica que la relación está mapeada del otro lado (en MensajeForo) a través del atributo foroCurso. Este lado no tiene la clave foránea.

List<MensajeForo>: representa todos los mensajes relacionados con este foro.

cascade = CascadeType.ALL: las operaciones (persistir, eliminar, etc.) en el foro se propagan a los mensajes.

orphanRemoval = true: si un mensaje es eliminado de la lista, también se elimina de la base de datos.
