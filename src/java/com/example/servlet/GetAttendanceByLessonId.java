package com.example.servlet;

import com.example.beans.Attendance;
import com.example.beans.User;
import com.example.models.AttendanceModelImp;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetAttendanceByLessonId extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            User currentUser = (User) request.getSession().getAttribute("active-user");
            int l_id = Integer.parseInt(request.getParameter("lesson_id"));
            List<Attendance> l = new AttendanceModelImp().getAttendanceByLessonId(l_id);
            out.print("[");

            if (l != null) {
                for (int i = 0; i < l.size(); i++) {
                    out.print("{");
                    out.print("\"id\" : \"" + l.get(i).getId() + "\",");
                    out.print("\"student_id\" : \"" + l.get(i).getStudentId() + "\",");
                    out.print("\"lesson_id\" : \"" + l.get(i).getLessonId() + "\",");
                    out.print("\"attendance_time\" : \"" + l.get(i).getAttendanceTime() + "\",");
                    out.print("\"attendance_date\" : \"" + l.get(i).getAttendanceDate() + "\",");
                    out.print("\"attendance_status\" : \"" + l.get(i).getAttendanceStatus() + "\"");
                    out.print("}");

                    if (i != l.size() - 1) {
                        out.print(",");
                    }
                }
            }
            out.print("]");

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
