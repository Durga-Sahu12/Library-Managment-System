<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>


<html>
    <head>
        <title>Library NEW Items</title>
    </head>
    <body>
        <h2>Library Management - New Books List</h2>
        <hr />
        <form action="confirmAddBook" method="POST">
        <div>
              <label>Book Code: </label>
              <input type="number" name="bcode" required/>
        
        </div>
         <div>
              <label>Book Name: </label>
              <input type="text" name="bname" required/>
        
        </div>
         <div>
              <label>Author Name: </label>
              <input type="text" name="author" required/>
        
        </div>
         <div>
              <label>Issue Date: </label>
              <input type="date" name="issueDate" required/>
        
        </div>
        <div>
             <button>Add Book</button>
        </div>
      
       </from>
    </body>
</html>



