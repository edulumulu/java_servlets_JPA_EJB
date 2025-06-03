/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author edulumulu
 */
@Entity
@Table(name = "incidencias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Incidencias.findAll", query = "SELECT i FROM Incidencias i")
    , @NamedQuery(name = "Incidencias.findByIdIncidencia", query = "SELECT i FROM Incidencias i WHERE i.idIncidencia = :idIncidencia")
    , @NamedQuery(name = "Incidencias.findByFecha", query = "SELECT i FROM Incidencias i WHERE i.fecha = :fecha")
    , @NamedQuery(name = "Incidencias.findByEstado", query = "SELECT i FROM Incidencias i WHERE i.estado = :estado")})
public class Incidencias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_incidencia")
    private Integer idIncidencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private Character estado;
    @JoinColumn(name = "id_empleado_origen", referencedColumnName = "id_empleado")
    @ManyToOne(optional = false)
    private Empleados idEmpleadoOrigen;
    @JoinColumn(name = "id_empleado_destino", referencedColumnName = "id_empleado")
    @ManyToOne(optional = false)
    private Empleados idEmpleadoDestino;

    public Incidencias() {
    }

    public Incidencias(Integer idIncidencia) {
        this.idIncidencia = idIncidencia;
    }

    
    public Incidencias(Integer idIncidencia, Date fecha, String descripcion, Character estado, Empleados idEmpleadoOrigen, Empleados idEmpleadoDestino) {
        this.idIncidencia = idIncidencia;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.estado = estado;
        this.idEmpleadoOrigen = idEmpleadoOrigen;
        this.idEmpleadoDestino = idEmpleadoDestino;
    }
    public Incidencias(Integer idIncidencia, Date fecha, String descripcion, Character estado) {
        this.idIncidencia = idIncidencia;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public Incidencias(Date fecha, String descripcion, Character estado, Empleados idEmpleadoOrigen, Empleados idEmpleadoDestino) {
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.estado = estado;
        this.idEmpleadoOrigen = idEmpleadoOrigen;
        this.idEmpleadoDestino = idEmpleadoDestino;
    }

    
    public Integer getIdIncidencia() {
        return idIncidencia;
    }

    public void setIdIncidencia(Integer idIncidencia) {
        this.idIncidencia = idIncidencia;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public Empleados getIdEmpleadoOrigen() {
        return idEmpleadoOrigen;
    }

    public void setIdEmpleadoOrigen(Empleados idEmpleadoOrigen) {
        this.idEmpleadoOrigen = idEmpleadoOrigen;
    }

    public Empleados getIdEmpleadoDestino() {
        return idEmpleadoDestino;
    }

    public void setIdEmpleadoDestino(Empleados idEmpleadoDestino) {
        this.idEmpleadoDestino = idEmpleadoDestino;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idIncidencia != null ? idIncidencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Incidencias)) {
            return false;
        }
        Incidencias other = (Incidencias) object;
        if ((this.idIncidencia == null && other.idIncidencia != null) || (this.idIncidencia != null && !this.idIncidencia.equals(other.idIncidencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "clases.Incidencias[ idIncidencia=" + idIncidencia + " ]";
    }
    
}
