<%--
  Created by IntelliJ IDEA.
  User: plame
  Date: 18.05.2021
  Time: 22:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Form</title>
</head>
<body>

<form action="hello" method="POST">
  <center>
      Temperature: <input name="username" />
  </center>
    <center>
    <input type="submit" value="Submit" />
    </center>

</form>
================================================================================================================================================================================================================================
<form action="2servlet" method="POST">

    <p>
        1st number: <input name="number1" />
    </p>

    <p>
    Math action: <select name="math_action">
    <option>+</option>
    <option>-</option>
    <option>*</option>
    <option>/</option>
</select>
    </p>

  <p>
      2nd number: <input name="number2" />
  </p>

    <input type="submit" value="Submit" />


</form>
</body>
</html>
