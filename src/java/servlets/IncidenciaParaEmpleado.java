/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.IncidenciasEJB;
import clases.Empleados;
import clases.Incidencias;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
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
@WebServlet(name = "IncidenciaParaEmpleado", urlPatterns = {"/IncidenciaParaEmpleado"})
public class IncidenciaParaEmpleado extends HttpServlet {

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
            out.println("<title>Servlet IncidenciaDesdeEmpleado</title>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/estiloTablasCSS.css\">");
            out.println("</head>");
            out.println("<body>");

            String id_string = request.getParameter("id");

            try {

                int id_int = Integer.parseInt(id_string);
                //Incidencias inc = iEJB.incidenciaByID(id_int);

                Empleados emp = iEJB.empleadoByID(id_int);
                if (emp != null) {

                    List<Incidencias> lista_incidencias = iEJB.findIncidenciaDestinadaParaEmpleado(emp);

                    if (lista_incidencias.isEmpty()) {
                        out.println("<p>No hay incidencias registradas para este usuario.</p>");
                    } else {
                        out.println("<h1>Incidencias destinadas a: " + emp.getNombreCompleto() + " --> id: " + emp.getIdEmpleado() + "</h1>");
                        out.println("<table>");
                        out.println("<tr>");

                        out.println("<th>ID</th>");

                        out.println("<th>Origen</th>");
                        out.println("<th>Descripción</th>");
                        out.println("<th>Fecha</th>");
                        out.println("<th>Estado</th>");
                        out.println("</tr>");

                        for (int i = 0; i < lista_incidencias.size(); i++) {
                            String estado_string = "";
                            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                            Incidencias inc = lista_incidencias.get(i);

                            if (inc.getEstado() == ('U')) {
                                estado_string = "Urgente";
                            } else if (inc.getEstado() == ('N')) {
                                estado_string = "Normal";
                            }
                            out.println("<tr>");

                            out.println("<td>" + inc.getIdIncidencia() + "</td>");

                            out.println("<td>" + inc.getIdEmpleadoOrigen().getNombreUsuario() + "</td>");
                            out.println("<td>" + inc.getDescripcion() + "</td>");
                            out.println("<td>" + formato.format(inc.getFecha()) + "</td>");
                            out.println("<td>" + estado_string + "</td>");
                            out.println("</tr>");
                        }

                        out.println("</table>");
                    }

                } else {
                    out.println("No existen ningún empleado con ese id.");
                }
            } catch (NumberFormatException e) {
                response.getWriter().println("El campo id debe rellenarse con un numero entero");
            }

            out.println("<form action=\"IncidenciaParaEmpleado.html\" method=\"POST\">"
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
