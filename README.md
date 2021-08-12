# vehicle_management

Requirements: 

Implement a small JAVA project for maintenance of entity records in db. It should use Spring boot and Hibernate for connection to a database. 
Schema should contain these 3 entities:
•	'Vehicle' with attributes 'VIN', 'Plate Number', 'Date of Registration' and with linkage to Vehicle Type - only single type can be linked
•	'VehicleType' with attribute 'Name' and linkage to Vehicle Part - more parts can be linked to a type; example records: 'Commercial Vehicle', 'Passenger Vehicle'
•	'Vehicle Part' with attribute 'Name'; example records: 'Tire', 'Headlight', 'Door'

Application should contain at least these endpoints:
•	to retrieve records from the DB, 
•	to create new record
•	to update one record 
•	to delete one record.
Create set of tests validating the implementation.
Voluntary extension: Add simple UI which will call created APIs.

