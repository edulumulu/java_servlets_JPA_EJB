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
import java.util.Date;
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
@WebServlet(name = "InsertarIncidencia", urlPatterns = {"/InsertarIncidencia"})
public class InsertarIncidencia extends HttpServlet {

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
            out.println("<title>Servlet InsertarIncidencia</title>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/estilos.css\">");
            out.println("</head>");
            out.println("<body>");
            //out.println("<h1>Servlet InsertarIncidencia at " + request.getContextPath() + "</h1>");

            String id_origen_string = request.getParameter("id_origen");
            String id_destin_stringo = request.getParameter("id_destino");
            String detalle = request.getParameter("detalle");
            String urgencia = request.getParameter("urgencia");

            if (id_destin_stringo.equalsIgnoreCase("") || id_origen_string.equalsIgnoreCase("") || detalle.equalsIgnoreCase("")) {
                out.println("Debes rellenar todos los campos.");
            } else {
                int id_origen = Integer.parseInt(id_origen_string);
                int id_destino = Integer.parseInt(id_destin_stringo);

                Empleados empleado_origen = iEJB.empleadoByID(id_origen);
                Empleados empleado_destino = iEJB.empleadoByID(id_destino);

                // L—gica para convertir la urgencia a un char
                char urgenciaChar;
                if ("Urgente".equalsIgnoreCase(urgencia)) {
                    urgenciaChar = 'U';
                } else {
                    urgenciaChar = 'N';
                }

                Date fechahoy = new Date();

                if (empleado_origen == null) {
                    out.println("Error: El empleado origen no existe escoge otro id.");
                    return;
                }

                if (empleado_destino == null) {
                    out.println("Error: El empleado destino no existe escoge otro id.");
                    return;
                }

                int id_incidencia = iEJB.listarIncidencias().size() + 1;
                Incidencias in = new Incidencias(id_incidencia, fechahoy, detalle, urgenciaChar, empleado_origen, empleado_destino);

                if (iEJB.insertarIncidencia(in)) {
                    out.println("Incidencia dada de alta.");
                } else {
                    out.println("No se pudo insertar la incidencia.");
                }

            }
            out.println("<form action=\"index2.html\" method=\"POST\">"
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
