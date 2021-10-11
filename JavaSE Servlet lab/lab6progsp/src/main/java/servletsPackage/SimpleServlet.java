package servletsPackage;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/hello")
public class SimpleServlet extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();

        String name = request.getParameter("username");
       int temperature = Integer.parseInt(name);


        try {
            if (temperature>0) {
                writer.println("<center>" +
                        "<p>" +
                        "<font size=5  color = red face=Arial>" +
                        "Temperature: " + temperature + "" +
                        "</font>" +
                        "</p>" +
                        "</center>");
            }
            else {
                writer.println("<center>" +
                        "<p>" +
                        "<font size=5  color = blue face=Arial>" +
                        "Temperature: " + temperature + "" +
                        "</font>" +
                        "</p>" +
                        "</center>");



            }

        } finally {
            writer.close();
        }
    }

}
