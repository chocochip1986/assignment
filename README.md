# *Spring Boot Application Assignment*
### This application does mainly these following things
### 1. Establishes a database connection to an oracle db
### 2. Inserts entries into the database using a file data.csv under the resouces subdirectory upon application start up
### 3. Exposes 3 routes:
    one for displaying all valid users
    one for displaying one user
    one for inserting a user
#
#
#
## *Database details (What you need to do to setup the database)*
### Setting up an oracle database
##### 1. Download and install the 12c oracle database
#####    - hostname = localhost, port = 1521, service name = orclpdb 
##### 2. Download ojdbc7.jar. This is a oracle db driver which is required for the application to talk to the database
##### 3. Connect to the oracle database as a sysdba (Can use the default sys user)
##### 4. create user `development_owner` with password `development_owner` 
#####    - i.e. create user development_owner identified by development_owner;
##### 5. Create a table named tbl_test under the owner `development_owner`
#####    - i.e create table development_owner.tbl_test (id number(10) NOT NULL, "NAME" varchar(50) NOT NULL, salary number(8,2) NOT NULL, constraint tbl_test_pk primary key(id) );
##### 6. grant privileges on table space to development_owner
#####    - i.e. grant unlimited tablespace to development_owner;
##### 7. grant privileges to development_owner to connect, insert, select table tbl_test
#####    - i.e. grant connect to `development_owner`
#####    - i.e. grant insert, select, update on tbl_test to `development_owner`
##### 8. Create a sequence called `tbl_test_seq`. This is to aid us when inserting records. The primary key will be using this sequence so that it'll be forever unique.
#####    - i.e. create sequence tbl_test_seq minvalue 10000 maxvalue 1000000 start with 10000 increment by 1;
##### 9. Grant `development_owner` privilege to use the tbl_test_seq
#####    - i.e. grant select on development_owner.tbl_test_seq to development_owner;

### Configuring Spring Boot Application to connect to the oracle database
##### 1. The datbase configuration details are in the `src/main/resouces/application.properties`
##### 2. Set spring.jpa.hibernate.ddl-auto=update will not allow Spring to drop and recreate the database schema everytime it comes up.
##### 3. spring.datasource.url=jdbc:oracle:thin:@localhost:1521/orclpdb. The rest of the details are in the application.properties


### Download all dependencies according to POM file
##### 1. run mvn dependency:tree

### Run the Spring application
##### 1. mvn spring-boot:run

### Spring Boot Application file explanation
##### 1. Application.java is where the SpringApplication will start running
##### 2. I placed all classes related to the User model into the subdirectory of User
##### 3. The User.java entity defines the table tbl_test's columns and their model validations.
##### 4. The UserDao.java serves as the model for the entity User. You can define all your queries here. 
##### 5. The UserController.java class controls routing and what information to give to the View. 
##### 4. The UserService.java class serves as a layer that calls the UserDao's functions.
##### 6. The ApplicationBeforeStartupBean.java class implements the CommandLineRunner interface. This is meant for the application to run functionalities that has to be ran just before the application starts up. In this case, the data will be loaded from the CSV file and inserted into the database before the application starts up.
##### 7. DataSetup.java job is to read the data brought in by the CsvFileReader and insert into the database.
##### 8. CsvFileReader.java class is meants to process the data.csv file residing in the src/main/java/resouces/ subdirectory

