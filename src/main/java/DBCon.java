import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/ethu")
public class DBCon extends HttpServlet{

      protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
	
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        try{
        
        //loading drivers for mysql
        Class.forName("com.mysql.jdbc.Driver");

	//creating connection with the database 
          Connection  con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sampledb","root","");
              ResultSet rs;    

        PreparedStatement ps=con.prepareStatement("insert into reg values(?,?,?)");
             
        ps.setString(1, name);
        ps.setString(2, email);
        ps.setString(3, pass);
        Statement stm = con.createStatement();
        
        int i=ps.executeUpdate();
        rs = stm.executeQuery("select * from reg");
        out.println("You are sucessfully regsddsistered"+rs);
          if(i>0)
          {
            out.println("You are sucessfully registered");
          }
         
        }
        catch(Exception se)
        {
            se.printStackTrace();
        }
	
      }
}