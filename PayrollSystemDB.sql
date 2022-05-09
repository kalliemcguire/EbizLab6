drop table Employee;
drop table Hourly_Employee;
drop table Salary_Employee;
drop table Timecard;
drop table Withholding_Type;
drop table Payroll;
drop table UserRole;

CREATE TABLE UserRole (
 Role_ID INT NOT NULL GENERATED ALWAYS AS IDENTITY,
 RoleDescription VARCHAR(50),
 AllTC BOOLEAN,
 AllRoles BOOLEAN,
 CalculatePayroll BOOLEAN,
 OwnTC BOOLEAN,
 OwnPayroll BOOLEAN,

 PRIMARY KEY(Role_ID)
);

INSERT INTO UserRole
  (RoleDescription, AllTC, AllRoles, CalculatePayroll, OwnTC, OwnPayroll)
VALUES
  ('Admin', TRUE, TRUE, TRUE, TRUE, TRUE),
  ('Mgt', TRUE, FALSE, TRUE, TRUE, TRUE),
  ('Employee', FALSE, FALSE, FALSE, TRUE, TRUE);

CREATE TABLE Employee (
  Employee_ID INT NOT NULL,
  Employee_Type INT,
  First_Name VARCHAR(50),
  Last_Name VARCHAR(50),
  SSN BIGINT,
  User_ID VARCHAR(20),
  Password VARCHAR(20),
  UserRole INT,
  
  PRIMARY KEY(Employee_ID) 
);

INSERT INTO Employee 
  (Employee_ID, Employee_Type, First_Name, Last_Name, SSN, User_ID, Password, UserRole)
VALUES 
  (1001, 1, 'Bob', 'Smith', 555121234, 'User1', 'user1', 3),
  (1002, 1, 'Jane', 'Doe', 555124321, 'User2', 'user2', 3),
  (1003, 2, 'John', 'Doe', 333125678, 'User3', 'user3', 3),
  (1004, 2, 'James', 'Wilson', 444342345, 'User4', 'user4', 3),
  (1005, 2, 'Administrator', null, null, 'Admin', 'admin', 1),
  (1006, 2, 'Management', null, null, 'Mgt1', 'mgt1', 2);

CREATE TABLE Hourly_Employee (
  Employee_ID INT NOT NULL,
  Hourly_Rate DOUBLE,
  Overtime_Rate DOUBLE
);

INSERT INTO Hourly_Employee
  (Employee_ID, Hourly_Rate, Overtime_Rate)
VALUES
  (1001, 12.75, 1.5),
  (1002, 18.75, 1.5);

CREATE TABLE Salary_Employee (
  Employee_ID INT NOT NULL,
  Salary DOUBLE
);

INSERT INTO Salary_Employee 
  (Employee_ID, Salary)
VALUES
  (1003, 55000),
  (1004, 60000),
  (1005, 80000),
  (1006, 70000);

--generated always as identity automatically assigns value to Timecard_ID
CREATE TABLE Timecard (
  Timecard_ID INT NOT NULL GENERATED ALWAYS AS IDENTITY,
  Timecard_Date DATE,
  Employee_ID INT,
  Hours_Worked DOUBLE,
  Overtime_Hours DOUBLE,
  
  PRIMARY KEY(Timecard_ID)
);

INSERT INTO Timecard
  (Timecard_Date, Employee_ID, Hours_Worked, Overtime_Hours)
VALUES
  ('04/06/2022', 1001, 8, 3.75),
  ('04/07/2022', 1001, 8, 3.75),
  ('04/11/2022', 1001, 8, 3.75),
  ('04/12/2022', 1001, 8, 2.5),
  ('04/13/2022', 1001, 8, 1.0),
  ('04/14/2022', 1001, 8, 3.75),
  ('04/18/2022', 1001, 8, 2.0),
  ('04/06/2022', 1002, 8, 3.75),
  ('04/07/2022', 1002, 5, 0.0),
  ('04/11/2022', 1002, 8, 3.75),
  ('04/12/2022', 1002, 8, 2.75),
  ('04/14/2022', 1002, 8, 1.75),
  ('04/18/2022', 1002, 7, 0.0);

CREATE TABLE Withholding_Type (
   Withholding_ID INT NOT NULL,
   Withholding_Description VARCHAR(50),
   Withholding_Amount DOUBLE,
   Withholding_Rate DOUBLE,

   PRIMARY KEY(Withholding_ID)
);

INSERT INTO Withholding_Type
   (Withholding_ID, Withholding_Description, Withholding_Amount, Withholding_Rate)
VALUES
   (1, 'Social security', 0.0, 5.0),
   (2, 'Health insurance', 75.75, 0.0);

CREATE TABLE Payroll (
   Payroll_ID INT NOT NULL GENERATED ALWAYS AS IDENTITY,
   Payroll_Date DATE,
   Employee_ID INT,
   Gross_Pay DOUBLE,
   Total_Deductions DOUBLE,
   Net_Pay DOUBLE,

   PRIMARY KEY(Payroll_ID)
);