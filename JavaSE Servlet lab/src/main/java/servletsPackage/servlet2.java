package servletsPackage;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/2servlet")
public class servlet2 extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();

        String number1 = request.getParameter("number1");
        String number2 = request.getParameter("number2");
String math_action = request.getParameter("math_action");
float num1= Float.parseFloat(number1);
        int num2= Integer.parseInt(number2);
        float res1=num1+num2;
        float res2=num1-num2;
        float res3=num1*num2;
        float res4=num1/num2;

        if (math_action.equals("+"))
        {
            writer.println("<center>"+
                    "<p>"+
                    "<font size=5  color = red face=Arial>" +
                    number1 +" "+ math_action+" "+number2+" = "+res1+
                    "</font"+
                    "</p>"+
                    "</center>");
        }
        if (math_action.equals("-"))
        {
            writer.println("<center>"+
                    "<p>"+
                    "<font size=5  color = red face=Arial>" +
                    number1 +" "+ math_action+" "+number2+" = "+res2+
                    "</font"+
                    "</p>"+
                    "</center>");
        }
        if (math_action.equals("*"))
        {
            writer.println("<center>"+
                    "<p>"+
                    "<font size=5  color = red face=Arial>" +
                    number1 +" "+ math_action+" "+number2+" = "+res3+
                    "</font"+
                    "</p>"+
                    "</center>");
        }
        if (math_action.equals("/"))
        {
            writer.println("<center>"+
                    "<p>"+
                    "<font size=5  color = red face=Arial>" +
                    number1 +" "+ math_action+" "+number2+" = "+res4+
                    "</font"+
                    "</p>"+
                    "</center>");
        }
    }}