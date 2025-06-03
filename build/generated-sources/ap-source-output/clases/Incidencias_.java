package clases;

import clases.Empleados;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2025-05-02T10:43:03")
@StaticMetamodel(Incidencias.class)
public class Incidencias_ { 

    public static volatile SingularAttribute<Incidencias, String> descripcion;
    public static volatile SingularAttribute<Incidencias, Integer> idIncidencia;
    public static volatile SingularAttribute<Incidencias, Date> fecha;
    public static volatile SingularAttribute<Incidencias, Character> estado;
    public static volatile SingularAttribute<Incidencias, Empleados> idEmpleadoDestino;
    public static volatile SingularAttribute<Incidencias, Empleados> idEmpleadoOrigen;

}