/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.IncidenciasEJB;
import clases.Empleados;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author edulumulu
 */
@WebServlet(name = "InsertarEmpleado2", urlPatterns = {"/InsertarEmpleado2"})
public class InsertarEmpleado2 extends HttpServlet {

    @EJB
    IncidenciasEJB iEJB;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet InsertarEmpleado2</title>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/estilos.css\">");
            out.println("</head>");
            out.println("<body>");

            String user = request.getParameter("user");
            String pass = request.getParameter("pass");
            String nombre_completo = request.getParameter("nombre_completo");
            String tel = request.getParameter("tlf");

            List<Empleados> lista_empleados = iEJB.findAll();
            int id_empleado = iEJB.obtenerUltimoIdEmpleado() + 1;

            if (pass.equalsIgnoreCase("") || user.equalsIgnoreCase("") || nombre_completo.equalsIgnoreCase("") || tel.equalsIgnoreCase("")) {
                out.println("Debes rellenar todos los campos.");

            } else if (pass.contains(" ")) {
                out.println("La contraseña no puede tener espacios en blanco.");

            } else if (user.contains(" ")) {
                out.println("El ususario no puede tener espacios en blanco.");

            } else {
                if (!tel.equalsIgnoreCase("")) {
                    try {
                        int num_tel = Integer.parseInt(tel);

                        Empleados em = new Empleados(id_empleado, user, pass, nombre_completo, tel);

                        if (iEJB.existeNombreEmpleado(em)) {
                            out.println("Ese Nombre ya existe en la base de datos.");
                        } else if (iEJB.existeUsuarioEmpleado(em)) {
                            out.println("Ese Usuario ya existe en la base de datos .");
                        } else {
                            if (iEJB.insertarEmpleado(em)) {
                                out.println("Empleado dado de alta.");
                            } else {
                                out.println("No se pudo insertar.");
                            }
                        }
                    } catch (NumberFormatException e) {
                        out.println("El telefono deve estar compuesto de numeros enteros");
                    }
                }

            }

            out.println("<form action=\"index.html\" method=\"POST\">"
                    + "<input type=\"submit\" name=\"volver\" value=\"Volver\" />"
                    + "</form>");
            out.println("</body>");
            out.println("</html>");

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
