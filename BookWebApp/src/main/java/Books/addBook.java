package Books;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/addbooks")
public class addBook extends HttpServlet {
	Connection con = null;
	@Override
	public void init() throws ServletException {
		
	    try 
	    {
	    	Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/1eja8?user=root&password=Ashish@3316");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("bookname");
		double price = Double.parseDouble(req.getParameter("bookprice"));
		String author = req.getParameter("author");
		
	    PreparedStatement pstmt = null;
		String query = "insert into book_data values(?,?,?,?)";
		
		try 
		{
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, 0);
			pstmt.setString(2, name);
			pstmt.setDouble(3, price);
			pstmt.setString(4, author);
			int count = pstmt.executeUpdate();
			PrintWriter pw = resp.getWriter();
			pw.print("<h1>" +count+ " Record Inserted Successfully</h1>");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
