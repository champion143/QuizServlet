import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class view extends HttpServlet
{
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String name=request.getParameter("name"); 
		System.out.println(name);
		out.println("<html><head></head>");
		out.println("<body bgcolor='lightblue'>");
	    out.println("<form action='firstpage.html' method='post'>");
		out.println("<table align='center' border='2'>");
		out.println("<tr><th>name</th><th>attempt</th>");
		out.println("<th>notattempt</th><th>correct</th>");
		out.println("<th>notcorrect</th></tr>");
		String sql="select * from QUIZ where NAME = ? ";
		try
		{
  		    Class.forName("oracle.jdbc.driver.OracleDriver"); 
            System.out.println("driver loaded");	 
            Connection con=DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:XE","system","tiger");  
            System.out.println("connection established");	   
	        PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1,name);
			ResultSet rs=ps.executeQuery();
		    System.out.println("check");
			while(rs.next())
			{  
		        System.out.println(name);
				String nam=rs.getString(1);
				int attempt=rs.getInt(2);
				int notattempt=rs.getInt(3);
				int correct=rs.getInt(4);
				int notcorrect=rs.getInt(5);
				out.println("<tr><th>"+nam+"</th>");
				out.println("<th>"+attempt+"</th>");
				out.println("<th>"+notattempt+"</th>");
				out.println("<th>"+correct+"</th>");
				out.println("<th>"+notcorrect+"</th></tr>");
			}	
		   
		   out.println("<tr><th><input type='submit' value='close'/></th></tr>");
		   out.println("</table>");
           out.println("</form></body>");	
           out.println("</html>");		   
		}
		catch(SQLException e)
		{System.out.println(e);}
		catch(ClassNotFoundException e)
		{System.out.println(e);}
        finally
		 {
                 out.close();		
		 }		
		
	}
}
