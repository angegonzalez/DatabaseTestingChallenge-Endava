## Database testing Challenge
Solved by: Ángel Mateo González Bejarano

<small>Testing intern | Endava 2023.</small>

In this repo you'll find the solution for the database challenge proposed by the testing discipline

In the `/sql_scripts` folder you'll find the scripts to create the database (includes the new table called `Institution`)
`Create.sql` and the script for the queries `Queries.sql` that include both set of queries: first part from the workshop and the
second one about the challenge queries proposal.

This challenge is about the following points:

1. Create the table `Institution` that will have a relationship with the table employee
    (use foreign key), to show the studies from an employee. Take a screenshot from the
   new Entity-Relationship Diagram.

   <img alt="ER Diagram" height="500" src="./resources/Diagrama%20ER.png" width="500"/>

2. Use the Join Clause to get the data from the tables `Institution` and `Employee`

   _Note: this is solved in the `Queries.sql`_ file

3. Use Sub-Queries to get the data from the children whose parents work in PriceSmart
   (`idCompany = 5`). Note: Get the data only from the table `Children`.
    
   _Note: this is solved in the `Queries.sql`_ file

4. Create a Java program using JPA+Hibernate to create a database connection, where
   the user should be able to perform the following actions:

    _Note: this is solved in the `java` project located inside this repo_ 
    
    <br/>

    1. [X] Get all records from the table employee    
    2. [X] Get employees by last name from the table employee   
    3. [X] Insert a new record in the table employee 
    4. [X] Update a record in the table employee
    5. [X] Delete a record in the table employee
