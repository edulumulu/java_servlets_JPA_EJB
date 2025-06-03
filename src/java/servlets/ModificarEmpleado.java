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
@WebServlet(name = "ModificarEmpleado", urlPatterns = {"/ModificarEmpleado"})
public class ModificarEmpleado extends HttpServlet {

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
            out.println("<title>Servlet ModificarEmpleado</title>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/estilos.css\">");
            out.println("</head>");
            out.println("<body>");
            //out.println("<h1>Servlet ModificarEmpleado at " + request.getContextPath() + "</h1>");

            String id = request.getParameter("id");
            String user = request.getParameter("user");
            String pass = request.getParameter("pass");
            String nombre_completo = request.getParameter("nombre_completo");
            String tel = request.getParameter("tlf");

            if (pass.equalsIgnoreCase("") || user.equalsIgnoreCase("") || nombre_completo.equalsIgnoreCase("") || tel.equalsIgnoreCase("")) {
                out.println("Debes rellenar todos los campos.");

            } else if (pass.contains(" ")) {
                out.println("La contraseña no puede tener espacios en blanco.");

            } else if (user.contains(" ")) {
                out.println("El ususario no puede tener espacios en blanco.");

            } else {
                if (!tel.equalsIgnoreCase("")) {

                    try {
                        int tel_int = Integer.parseInt(tel);

                        try {
                            int id_int = Integer.parseInt(id);

                            Empleados empleado = iEJB.empleadoByID(id_int);

                            if (empleado == null) {
                                out.println("No existe ese id de empleado .");
                            } else {
                                Empleados em = new Empleados(id_int, user, pass, nombre_completo, tel);
                                if (iEJB.updateEmpleado(em)) {
                                    out.println("Empleado modificado.");
                                } else {
                                    out.println("No se ha podido modificar el empleado porque ese id de empleado no exite.");
                                }
                            }
                        } catch (NumberFormatException e) {
                            out.println("<p>Error: ID no es un número válido.</p>");
                        }

                    } catch (NumberFormatException f) {
                        out.println("<p>Error: El numero de telefono debe de ser un numero entero .</p>");
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
