package controllers;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Tasks;
import utils.DBUtil;

/**
 * Servlet implementation class DereteServlet
 */
@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Tasks m = em.find(Tasks.class, (Integer)(request.getSession().getAttribute("tasks_id")));

            em.getTransaction().begin();
            em.remove(m);
            em.getTransaction().commit();
            request.getSession().setAttribute("flush", "タスクを削除しました。");
            em.close();

            request.getSession().removeAttribute("tasks_id");

            response.sendRedirect(request.getContextPath() + "/index");
        }
    }

}