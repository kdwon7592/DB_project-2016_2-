

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import register.courseInfo;
import register.userInfo;
/**
 * Servlet implementation class viewmycourse
 */
@WebServlet("/viewmycourse")
public class viewmycourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//db�κ�
				Context context = null;
				DataSource dataSource = null; 
				Connection conn = null;
				Statement stmt = null;
				try {
					context = new InitialContext();
					dataSource = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
				} catch (NamingException e) {
					e.printStackTrace();
				}
			    try {
					conn = dataSource.getConnection();
					stmt = (Statement)conn.createStatement();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("connect!");
				//db�κ�
				HttpSession session = request.getSession();
				userInfo userinfo = (userInfo) session.getAttribute("userInfo");
				courseInfo[] course = new courseInfo[1000];
				int cnt = 0;
				ResultSet rs = null;
				try {
					stmt = (Statement) conn.createStatement();
					rs = stmt.executeQuery("select cid, cname, room, stime, etime, credit, day from course where pid = "+userinfo.getId());
					while (rs.next()) {
						response.setContentType( "text/html;charset=euc-kr");
						course[cnt] = new courseInfo(rs.getInt("cid"),rs.getString("cname"),rs.getString("room"),rs.getString("stime"),rs.getString("etime"),userinfo.getId(),rs.getInt("credit"),rs.getString("day"));
						cnt++;
					}
					request.setAttribute("cnt", cnt);
					session.setAttribute("course", course);
		            RequestDispatcher view = request.getRequestDispatcher("/viewmycourse.jsp");
		            view.forward(request, response);
					stmt.close();
					conn.close();
					System.out.println("disconnect!");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
}
