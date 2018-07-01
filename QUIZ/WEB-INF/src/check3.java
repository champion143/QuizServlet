import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class check3 extends HttpServlet
{
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{
     response.setContentType("text/html");	
	 PrintWriter pw = response.getWriter(); 
     String n=request.getParameter("q5");  
     String i=request.getParameter("q6"); 
	 String sql="insert into QUIZ values(?,?,?,?,?)";
	// pw.println("<html><head></head><body>");
	 int attempt=0;
	 int notattempt=0;
	 int correct=0;
	 int notcorrect=0;
	 int total=2;
     Statement stmt;
	 PreparedStatement ps;
      try
	  {  
      Class.forName("oracle.jdbc.driver.OracleDriver"); 
      System.out.println("driver loaded");	 
      Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","tiger");  
      System.out.println("connection established");	
	  	 stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
     ResultSet rs = stmt.executeQuery("select NAME from CONTESTANT");
     System.out.println("just checking purpose3");
     rs.last();
     String name=rs.getString(1);
     System.out.println(name);
	  ps = con.prepareStatement(sql);
	  if(n.equals("a")||n.equals("b"))
	  {
		   attempt++; 
	  }
	  if(i.equals("a")||i.equals("b"))
	  {
		   attempt++; 
	  }
      if(n.equals("a"))
      { 
		  correct++;
	  }	
      else if(n.equals("b"))		  
	  {
		  notcorrect++;
	  }	
	  if(i.equals("a"))
      {
		  notcorrect++; 
	  }	
      else if(i.equals("b"))
	  {
		  correct++;
	  }	  
	  notattempt=total-attempt;
	  ps.setString(1,name);
      ps.setInt(2,attempt);
      ps.setInt(3,notattempt);
	  ps.setInt(4,correct);
      ps.setInt(5,notcorrect);
      int count = ps.executeUpdate();
     // pw.println(count);
      if(count!=0)
      {
    //   pw.println("Your Data has been successfully stored in database");
        	response.sendRedirect("question4.html");
      }
     else
      {
       pw.println("sorry failed to store ! try again......");
      }
	//  pw.println("</body></html>");
        
       }
	  catch (ClassNotFoundException e2) {System.out.println(e2);} 
	  catch (SQLException e2) {System.out.println(e2);}  
      finally
	   {	   
	    
        pw.close(); 
	   }	  
	}
}
