<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>


<html>
    <head>
        <title>Book Items</title>
    </head>
    <body>
        <h2>Book Management - Books List</h2>
        <hr />
        
        <c:choose>
            <c:when test="${books eq null }">
                <p><strong>No Books Found!</strong>
            </c:when>
            <c:when test="${books.size() eq 0 }">
                <p><strong>No Books Found!</strong>
            </c:when>
            <c:otherwise>
                
                <table border="1" width="100%">
                    <tr>
                        <th>Book Code</th>
                        <th>Book Name</th>
                        <th>Author</th>
                        <th>Date Of Issue</th>
                        <th>ACTION</th>
                    </tr>
                    <c:forEach items="${books }" var="book">
                        <tr>
                            <td>${book.bcode }</td>
                            <td>${book.bname }</td>
                            <td>${book.author }</td>
                            <td>${book.issueDate }</td>
                            <td><a href="deleteBook ?bcode=${book.bcode }">DELETE</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>
        
        <hr />
        <nav>
            <ul>
                <li><a href="index.jsp">Home</a></li>
            </ul>
        </nav>
    </body>
</html>



