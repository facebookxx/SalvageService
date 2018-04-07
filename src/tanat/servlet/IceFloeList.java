package tanat.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import tanat.beans.IceFloe;
import tanat.database.DBUtils;
import tanat.database.MySQLConnUtils;



@WebServlet(urlPatterns = { "/icefloelist" })
public class IceFloeList extends HttpServlet {
	
    private static final long serialVersionUID = 1L;
    
    public IceFloeList() {
        super();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
   //     Connection conn = MyUtils.getStoredConnection(request);
	Connection conn = null;
        String errorString = null;
        List<IceFloe> listfloe = null;
        
		conn = MySQLConnUtils.getDBConnection();
     
        try {
        	listfloe = DBUtils.queryIceFloe(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        } 
        // Сохранить информацию в request attribute перед тем как forward к views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("listfloe", listfloe);  
         
        // Forward к /views/productListView.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/iceFloeListView.jsp");
        // и так пробывал
//      RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/iceFloeListView.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
