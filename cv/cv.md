# Serafim Evthikhiev

## Contact information

* Phone: +375297777920
* E-mail: plamen2001@mail.ru
* Telegram: [@sam_sambl4](https://t.me/sam_sambl4)
* Github: [Serafim1337](https://github.com/Serafim1337)

 
## About Me

>I am 20 years old student of BSUIR, studying my third year on the Faculty of Engineering and Economics.
>
>While studying here we have received knowledge in many subjects: from history and sociology to economic sciences and programming. I enjoy studying economics, but i am also keen on programming.
>
>We have studied few programming languages and spheres but i want to do my best at front-end)

## Skills

[*Labs examples in my repo*](https://github.com/Serafim1337/repo/tree/labs)
* Java
    * Java EE (Java Servlets, JSP, Hibernate, TCP/IP&UDP Client-Server) 
    * Java SE (Swing)
    * JDBC 
    * Java FX (Scene Builder)
* HTML
* CSS
* JS (Fundamentals)
* C/C++ (Fundamentals)
* Git/GitHub
* SQL (Fundamentals)
    * Microsoft SQL Server, MySQL Workbench, ERwin Data Modeler
*  Function modeling (IDEF0, IDEF3, UML)
* Visual Studio, VS Code, IntelliJ IDEA
* Adobe Photoshop

## Code example

[Java Servlet](https://github.com/Serafim1337/repo/tree/labs/JavaSE%20Servlet%20lab), implementing simple math actions:
```
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
```

## Education 

* [BSUIR (Faculty of Engineering and Economics)](https://www.bsuir.by/ru/ief)
* [RS School JS / FRONT-END. STAGE 0](https://rs.school/)
* [Code Basics (HTML, CSS, JS)](https://ru.code-basics.com/)
* [LearnJavaScript.ru](https://learn.javascript.ru/)

## Languages

 * Belorussian 
 * Russian
 * English **B1+**([training.by](https://training.by/#!/Home?lang=ru&City=37,38,39,40,41,42,50) test results: **B2**)
     * Courses [100ballov](https://www.100ballov.by/)