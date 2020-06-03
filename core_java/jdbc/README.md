# ***Java JDBC App***
 
 ## ***Introduction***
 Accessing Database, retrieving data through performing queries and manipulating data as needed is  
 necessary to all Web-Applications or any kind of large-scale systems in general. In this app, 
 we aim to interact with a PostGreSQL database instance using Java, more precisely, using JDBC driver.
 We will utilize DAO access pattern and Perform CRUD operations and also perform complex queries on 
 data. Furthermore, we will also parse the response from the database and populate our generic 
 data object. 

## ***USAGE***:

The app entry-point will be the `main` function in `JDBCExecutor` class. This function will
provide us with a `DatabaseConnectionManager` object which will provide us with a connection to
`PostGreSQL` instance that we will leverage to interact with the database. The 
`DatabaseConnectionManager` instantiation follows,

````java
DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost", "hplussport",
        "postgres", "password");
````

As we are using `DataObjectAccess(DAO)` pattern, we will create our different `DAO` to perform
CRUD or perform queries on the database.

## ***Database Tables***:
Our database consists of following tables:
* `customer`
* `orders`
* `product`
* `salesperson`
* `order_item`

The fields inside each table and relationship between can be understood in the E-R
Diagram

## ***E-R Diagram***:
![E-R Diagram](./assets/jdbc_er.png_)

## ***Dao and Repositories Pattern*** :
`Dao` and `Repositories` are two different ways to implement `Data Access Layer (DAL)`. Applications
need some kind of logic to interact with database. The layer which handles the database access 
and retrieval is `DAL`. It keeps the data access separate from rest of the application layers
and abstract away the data access and persistence of data. in `DAO` pattern, each domain
entity(To simplify, each database table) has a separate `DAO` class which deals with the access,
retrieval and query on that respective domain. In `DAO` pattern, joins are done in database and
sometimes, this pattern is termed as database-centric as well. We chose `DAO` pattern as our tables
are in `3NF`, so the joins will not be very complex. `Repositories` pattern adds another 
abstraction layer on top. `Repositories` are nothing but a bunch of methods which can be 
applied to collection of objects. In `Repositories` pattern, joins are handles in Code not in 
Database. As our database is not very big, keeping the structure simple was our choice. However,
for large databases, `Repositories` pattern might be a suitable choice. 

## ***Methods, Classes and Interfaces***

Our app consists of following parts:

  * *`DataAccessObject`*: This is an abstract class which specifies CRUD methods
 * *`DataTransferObject`*: This is an interface which has to be implemented by data
 models
 * *`Customer`*: Customer class which will implement `DataTransferObject` and will
 have all the instance variables corresponding to the `Customer` table fields
 * *`Order`*: Customer class which will implement `DataTransferObject` and will
  have all the instance variables corresponding to the `Order` table fields
  * *`CustomerDAO`*: CustomerDAO class will extend `DataAccessObject` and will
  implement all abstract methods to perform CRUD operations
  * *`OrderDAO`*: OrderDAO class will extend `DataAccessObject` and will
    implement all abstract methods to perform CRUD operations 
      
We have used `self4j` for logging purpose and handled all the required exceptions.

## ***`process` method pseudocode***:
````
matchedlines = []
for file in listFilesRecursively(rootDir)
    for line in readLines(file)
        if containsPattern(line)
            matchedLines.add(line)
writeToFile(matchedLines)                
````

## ***Performance Issues***:

There are a couple of performance issues in this app:

* *Database size issue*: As we discussed, our solution is not scalabale to database size
because of using `DAO` pattern.

* *Memory consumption issue*: Our queries are not time or memory optimized, utilizing 
brute-force approach of combining multiple joins which can result in a huge table after 
joins. Then we are searching through that table which can result in huge time and memory 
consumption if database size is huge.

## ***Improvements***:

* As this is an MVP (Most Viable Product) soluion, we have implemented a solution which can be
safely called a "brute-force" implementation. We will try to write optimized query to reduce
space and time complexity.

* We already talked about the `repository` pattern. We will try to use `Repository` pattern.
Although it will not give us any performance boost but it will provide us with more 
abstraction and better flexibility and make our solution scalable to database size.

* We will try to make our methods as `stored procedure` and analyze the performance. 

