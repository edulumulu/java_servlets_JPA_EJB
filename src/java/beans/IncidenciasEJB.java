/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import clases.Empleados;
import clases.Incidencias;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

/**
 *
 * @author edulumulu
 */
@Stateless
public class IncidenciasEJB {

    @PersistenceUnit
    EntityManagerFactory emf;

    /**
     * Metodo que retorna una lista de objetos Empleados con todas las filas de
     * la tabla de la BBDD
     *
     * @return
     */
    public List findAll() {
        return emf.createEntityManager().createNamedQuery("Empleados.findAll").getResultList();
    }

    public boolean insertarEmpleado(Empleados e) {
        //if (!existeNombreEmpleado(e)|| !existeUsuarioEmpleado(e)) {
        EntityManager em = emf.createEntityManager();
        em.persist(e);
        em.close();
        return true;
        //}
        //return false;
    }

    /**
     * Devuelve true o false si el nombre de usuario ya existe en la base de
     * datos
     *
     * @param s
     * @return
     */
    public boolean existeUsuarioEmpleado(Empleados s) {
        EntityManager em = emf.createEntityManager();
        try {
            em.createNamedQuery("Empleados.findByNombreUsuario", Empleados.class)
                    .setParameter("nombreUsuario", s.getNombreUsuario())
                    .getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;
        } finally {
            em.close();
        }
    }

    /**
     * Devuelve true o false si el nombre completo ya existe en la base de datos
     *
     * @param s
     * @return
     */
    public boolean existeNombreEmpleado(Empleados s) {
        EntityManager em = emf.createEntityManager();
        try {
            em.createNamedQuery("Empleados.findByNombreCompleto", Empleados.class)
                    .setParameter("nombreCompleto", s.getNombreCompleto())
                    .getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;
        } finally {
            em.close();
        }

    }

    /**
     * Obtiene el último ide de la tabla empleados
     *
     * @return
     */
    public int obtenerUltimoIdEmpleado() {
        EntityManager em = emf.createEntityManager();
        try {
            Integer ultimoId = em.createQuery("SELECT e.idEmpleado FROM Empleados e ORDER BY e.idEmpleado DESC", Integer.class)
                    .setMaxResults(1)
                    .getSingleResult();
            return ultimoId;
        } catch (NoResultException e) {
            return 0; // No hay empleados en la base de datos
        } finally {
            em.close();
        }
    }

    /**
     * Modifica un empleado en la base de datos manteniendo el id
     *
     * @param s
     * @return
     */
    public boolean updateEmpleado(Empleados s) {
        EntityManager em = emf.createEntityManager();
        Empleados aux = em.find(Empleados.class, s.getIdEmpleado());
        if (aux != null) {
            aux.setNombreUsuario(s.getNombreUsuario());
            aux.setContrasena(s.getContrasena());
            aux.setNombreCompleto(s.getNombreCompleto());
            aux.setTelefonoContacto(s.getTelefonoContacto());
            em.persist(aux);
            em.close();
            return true;
        }
        return false;
    }

    /**
     * Modifica la contraseña de un empleado ya existente por la contraseña
     * pasada como parámetro
     *
     * @param e
     * @param pass
     * @return
     */
    public boolean updateContrasena(Empleados e, String pass) {
        EntityManager em = emf.createEntityManager();
        Empleados aux = em.find(Empleados.class, e.getIdEmpleado());
        if (!pass.equalsIgnoreCase(aux.getContrasena())) {
            aux.setContrasena(e.getContrasena());
            em.persist(aux);
            em.close();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Devuelve el objeto empleado que corresponde con el id pasado como
     * atributo
     *
     * @param id
     * @return
     */
    public Empleados empleadoByID(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("Empleados.findByIdEmpleado", Empleados.class)
                    .setParameter("idEmpleado", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    /**
     * Metodo que compara si la contraseña pasada como parámetro corresponde con
     * la del usuario registrado en la base de datos
     *
     * @param user
     * @param pass
     * @return
     */
    public boolean validarEmpleado(String user, String pass) {
        EntityManager em = emf.createEntityManager();
        try {
            Empleados aux = em.createQuery("SELECT e FROM Empleados e WHERE e.nombreUsuario = :user", Empleados.class)
                    .setParameter("user", user)
                    .getSingleResult();

            return pass.equalsIgnoreCase(aux.getContrasena());
        } catch (NoResultException e) {
            return false;
        } finally {
            em.close();
        }
    }
    
    /**
     * Elimina un empleado de la base de datos devolviendo true o false siempre
     * que el empleado no tenga incidencias asociadas
     *
     * @param empleado
     * @return
     */
    public boolean eliminarEmpleado(Empleados empleado) {
        EntityManager em = emf.createEntityManager();
        Empleados aux = em.find(Empleados.class, empleado.getIdEmpleado());
        if (aux != null) {
            List<Incidencias> inncidencias_originadas = findIncidenciaOriginadaByEmpleado(aux);
            List<Incidencias> inncidencias_destinadas = findIncidenciaDestinadaParaEmpleado(aux);
            if (inncidencias_originadas.isEmpty() && inncidencias_destinadas.isEmpty()) {
                em.remove(aux);
                em.close();
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * Devuelve una lista de objetos incidencia de un empleado en concreto
     *
     * @param empleado_origen
     * @return
     */
    public List<Incidencias> findIncidenciaOriginadaByEmpleado(Empleados empleado_origen) {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT i FROM Incidencias i WHERE i.idEmpleadoOrigen = :idEmpleadoOrigen");
        q.setParameter("idEmpleadoOrigen", empleado_origen);
        List<Incidencias> incidencias = q.getResultList();
        em.close();
        return incidencias;
    }

    /**
     * Devuelve una lista de objetos incidencia destinadas a un empleado en
     * concreto
     *
     * @param empleado_destino
     * @return
     */
    public List<Incidencias> findIncidenciaDestinadaParaEmpleado(Empleados empleado_destino) {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT i FROM Incidencias i WHERE i.idEmpleadoDestino = :idEmpleadoDestino");
        q.setParameter("idEmpleadoDestino", empleado_destino);
        List<Incidencias> incidencias = q.getResultList();
        em.close();
        return incidencias;
    }

    /**
     * Devuelve una lista de incidencias de la base datos
     *
     * @return
     */
    public List listarIncidencias() {
        return emf.createEntityManager().createNamedQuery("Incidencias.findAll").getResultList();
    }

    /**
     * Devuelve la incidencia que corresponde con el id pasado como atributo
     *
     * @param id
     * @return
     */
    public Incidencias incidenciaByID(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("Incidencias.findByIdIncidencia", Incidencias.class)
                    .setParameter("idIncidencia", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }

    }

   
    /**
     * Inserta una incidencia en la base de datos
     *
     * @param i
     * @return
     */
    public boolean insertarIncidencia(Incidencias i) {
        EntityManager em = emf.createEntityManager();
        try {
            em.persist(i);
            return true;
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        } finally {
            em.close();
        }
    }

    
//Metodos no utilizados
    public List findIncidenciaByEmpleado(int id) {
        Incidencias s = findInidenciaByEmpleado(id);
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT i FROM Incidencias m WHERE e.idEmpleado = :idEmpleado");
        q.setParameter("", s);
        List matriculas = q.getResultList();
        return matriculas;
    }

    public Incidencias findInidenciaByEmpleado(int numSocio) {
        Query q = emf.createEntityManager().createNamedQuery("Socio.findByNumsocio");
        q.setParameter("numsocio", numSocio);
        List<Incidencias> result = q.getResultList();
        Iterator iter = result.iterator();
        Incidencias a = (Incidencias) iter.next();
        return a;
    }
}
