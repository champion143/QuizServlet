import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class start extends HttpServlet
{
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{
     response.setContentType("text/html");	
	 PrintWriter pw = response.getWriter(); 
     String name=request.getParameter("name");  	
	 String sql="insert into CONTESTANT values(?)";
	 PreparedStatement ps;
      try
	  {  
      Class.forName("oracle.jdbc.driver.OracleDriver"); 
      System.out.println("driver loaded");	 
      Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","tiger");  
      System.out.println("connection established");	
	  ps = con.prepareStatement(sql);
      ps.setString(1,name);
      int count = ps.executeUpdate();
      if(count!=0)
      {
        	response.sendRedirect("question1.html");
      }
     else
      {
       pw.println("its not working ! try again......");
      }
        
       }
	  catch (ClassNotFoundException e2) {System.out.println(e2);} 
	  catch (SQLException e2) {System.out.println(e2);}  
      finally
	   {	   
	    
        pw.close(); 
	   }	  
	}
}