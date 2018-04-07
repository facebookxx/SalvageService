package tanat.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import tanat.beans.IceFloe;
import tanat.database.DBUtils;
import tanat.database.MySQLConnUtils;
 
@WebServlet(urlPatterns = { "/editice" })
public class EditIceFloe extends HttpServlet {
	public int id;
    private static final long serialVersionUID = 1L;
 
    public EditIceFloe() {
        super();
    }
 
    // Отобразить страницу создания продукта.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = null;
        
		try {
			conn = MySQLConnUtils.getMySQLConnection();
		} catch (ClassNotFoundException e1) {
			// TODO Автоматически созданный блок catch
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Автоматически созданный блок catch
			e1.printStackTrace();
		}
 
        id = Integer.parseInt(request.getParameter("id"));
 
        IceFloe iceFloe = null;
 
        String errorString = null;
 
        try {
        	iceFloe = DBUtils.findOneIceFloe(conn, id);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
 
        // Ошибки не имеются.
        // Продукт не существует для редактирования (edit).
        // Redirect sang trang danh sách sản phẩm.
        if (errorString != null) {
            response.sendRedirect(request.getServletPath() + "/icefloelist");
            return;
        }
 
        // Сохранить информацию в request attribute перед тем как forward к views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("iceFloe", iceFloe);
 
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/views/editicefloe.jsp");
        dispatcher.forward(request, response);
 
    }
 
    // Когда пользователь вводит информацию продукта, и нажимает Submit.
    // Этот метод будет вызван.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = null;
        
		try {
			conn = MySQLConnUtils.getMySQLConnection();
		} catch (ClassNotFoundException e1) {
			// TODO Автоматически созданный блок catch
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Автоматически созданный блок catch
			e1.printStackTrace();
		}
        
        String name = (String) request.getParameter("name");
        int size = Integer.parseInt((request.getParameter("size")));
        int numberOfFishermen = Integer.parseInt((request.getParameter("numberOfFishermen")));
	
        IceFloe iceFloe = new IceFloe(id, name, size, numberOfFishermen);
 
        String errorString = null;
 
            try {
                DBUtils.updateIceFloe(conn, iceFloe);
            } catch (SQLException e) {
                e.printStackTrace();
                errorString = e.getMessage();
            }
 
        // Сохранить информацию в request attribute перед тем как forward к views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("iceFloe", iceFloe);
 
        // Если имеется ошибка forward (перенаправления) к странице 'edit'.
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/views/editicefloe.jsp");
            dispatcher.forward(request, response);
        }
        // Если все хорошо.
        // Redirect (перенаправить) к странице со списком продуктов.
        else {
            response.sendRedirect(request.getContextPath() + "/icefloelist");
        }
    }
 
}