/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author edulumulu
 */
@Entity
@Table(name = "empleados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empleados.findAll", query = "SELECT e FROM Empleados e")
    , @NamedQuery(name = "Empleados.findByIdEmpleado", query = "SELECT e FROM Empleados e WHERE e.idEmpleado = :idEmpleado")
    , @NamedQuery(name = "Empleados.findByNombreUsuario", query = "SELECT e FROM Empleados e WHERE e.nombreUsuario = :nombreUsuario")
    , @NamedQuery(name = "Empleados.findByContrasena", query = "SELECT e FROM Empleados e WHERE e.contrasena = :contrasena")
    , @NamedQuery(name = "Empleados.findByNombreCompleto", query = "SELECT e FROM Empleados e WHERE e.nombreCompleto = :nombreCompleto")
    , @NamedQuery(name = "Empleados.findByTelefonoContacto", query = "SELECT e FROM Empleados e WHERE e.telefonoContacto = :telefonoContacto")})
public class Empleados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_empleado")
    private Integer idEmpleado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre_usuario")
    private String nombreUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "contrasena")
    private String contrasena;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre_completo")
    private String nombreCompleto;
    @Size(max = 15)
    @Column(name = "telefono_contacto")
    private String telefonoContacto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmpleadoOrigen")
    private Collection<Incidencias> incidenciasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmpleadoDestino")
    private Collection<Incidencias> incidenciasCollection1;

    public Empleados() {
    }

    public Empleados(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Empleados(Integer idEmpleado, String nombreUsuario, String contrasena, String nombreCompleto, String telefonoContacto) {
        this.idEmpleado = idEmpleado;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.nombreCompleto = nombreCompleto;
        this.telefonoContacto = telefonoContacto;
    }

    public Empleados(Integer idEmpleado, String contrasena) {
        this.idEmpleado = idEmpleado;
        this.contrasena = contrasena;
    }

    
    public Empleados(Integer idEmpleado, String nombreUsuario, String contrasena, String nombreCompleto) {
        this.idEmpleado = idEmpleado;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.nombreCompleto = nombreCompleto;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    @XmlTransient
    public Collection<Incidencias> getIncidenciasCollection() {
        return incidenciasCollection;
    }

    public void setIncidenciasCollection(Collection<Incidencias> incidenciasCollection) {
        this.incidenciasCollection = incidenciasCollection;
    }

    @XmlTransient
    public Collection<Incidencias> getIncidenciasCollection1() {
        return incidenciasCollection1;
    }

    public void setIncidenciasCollection1(Collection<Incidencias> incidenciasCollection1) {
        this.incidenciasCollection1 = incidenciasCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmpleado != null ? idEmpleado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleados)) {
            return false;
        }
        Empleados other = (Empleados) object;
        if ((this.idEmpleado == null && other.idEmpleado != null) || (this.idEmpleado != null && !this.idEmpleado.equals(other.idEmpleado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "clases.Empleados[ idEmpleado=" + idEmpleado + " ]";
    }
    
}
