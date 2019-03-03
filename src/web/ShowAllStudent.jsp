<%@ page import="entity.Student" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dao.DatabaseDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>

<html>
<head>
    <meta charset="utf-8">
    <title>fSchool</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="css/ShowAllStudent.css">
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="css/menu.css">
    <script src="js/jquery1111.min.js" type="text/javascript"></script>
    <script src="js/script.js"></script>
</head>

<body class="home-page">
<div class="wrap-body">
    <header>
        <div id="cssmenu">
            <ul>
                <li class="active"><a href="index.html"><span>FSchool</span></a></li>
                <li class="has-sub"><a href="#"><span>Students</span></a>
                    <ul>
                        <li class="has-sub"><a href="#"><span>Show All Students</span></a>
                            <ul>
                                <li><a href="#"><span>Add</span></a></li>
                                <li class="last"><a href="#"><span>Remove</span></a></li>
                            </ul>
                        </li>
                    </ul>
                </li>
                <li><a href="archive.html"><span>Media</span></a></li>
                <li><a href="single.html"><span>Skills</span></a></li>
                <li class="last"><a href="contact.html"><span>About</span></a></li>
            </ul>
        </div>
    </header>

    <div class="ShowAllStudent">
        <h1>All Students</h1>
        <table>
            <%
                DatabaseDAO dao = new DatabaseDAO();
                ArrayList<Student> students = dao.readAllStudentsFromDB();
                for (Student student : students) {
            %>
            <tr>
                <td><%=student.getSurname()%></td>
                <td><%=student.getName()%></td>
                <td>
                    <form action="servlet.MyServlet" method="Post">
                        <input type="hidden" name="id" value="<%=student.getId()%>"><br>
                        <input type="hidden" name="actionname" value="RemoveStudent">
                        <input type="submit" value="X"><br>
                    </form>
                </td>
            </tr>
            <%
                }
            %>
        </table>
    </div>
</div>
</body>
</html>
