-- First set of queries (Workshop)

-- First query
SELECT *
FROM Employee e 
WHERE e.Salary > 3000
AND e.birthDate < '1983-01-01';

-- Second query
SELECT *
FROM children c 
WHERE c.fullName = 'gohan';

-- Third query
SELECT e.firstName, e.lastName, c.coName , c.phoneNumber 
FROM Employee e 
INNER JOIN Company c 
ON e.idCompany = c.idCompany 
WHERE e.firstName = 'Krilin';

-- Fourth query
SELECT *
FROM Company c
WHERE c.idCompany IN (
	SELECT e.idCompany 
	FROM Employee e 
	WHERE e.idEmployee IN (
		SELECT c2.idEmployee 
		FROM children c2 
		WHERE c2.idChild = 3 
	)	
);

-- Fifth query
SELECT *
FROM Employee e 
INNER JOIN children c 
ON e.idEmployee  = c.idEmployee 

-- Second set of queries (Challenge)

USE personas;
-- First query (Use the Join Clause to get the data from the tables institution and employee)

SELECT *
FROM Institution i
INNER JOIN Employee e 
ON i.idInstitution  = e.idInstitution 