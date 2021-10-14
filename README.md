## Project Title "mybank"
In mybank, Admin and registered Customer can login. Admin can register customer, can see list of customers and Customer can transfer money,can see customer details and 
transaction History.Admin can also activate or deactivate any customer's account in the list of customers.If Customer's account is activated then statusId of customer will
be 1 and if customer's account is deactivated then statusId of customer will be 2.

## Screenshots
## Login Page
![Screenshot (5)](https://user-images.githubusercontent.com/92373972/137255285-4dec027e-893d-49a3-bcde-4cc08cf9c113.png)

## Admin DashBoard
![image](https://user-images.githubusercontent.com/92373972/137255407-cfe09093-ed77-4807-a731-54f4b4e00700.png)

## List of Customers Page
![image](https://user-images.githubusercontent.com/92373972/137255995-a7262f38-8743-4589-88ee-57e8a0337fc6.png)

## Registration Page
![image](https://user-images.githubusercontent.com/92373972/137256062-7e034d6b-b888-4b32-95d8-8cecb2052c8a.png)

## Customer DashBoard
![image](https://user-images.githubusercontent.com/92373972/137256127-b72b9ba9-810f-474f-8ed2-d5036956928d.png)

## TransferMoney Page
![image](https://user-images.githubusercontent.com/92373972/137256193-9de8a4f1-1602-4a6e-b74c-a68d1d9743b7.png)

## TransactionHistory Page
![image](https://user-images.githubusercontent.com/92373972/137256261-491e60af-4856-4bf4-be2d-ce7607926958.png)

## Technologies and Installation
1. JDK 1.8: Download and install JDK. Need to set environment variable (copy the path of bin folder of JRE and paste in system varibale).
2. Apache Tomcat 9.0.53 : Download and install this server. Set environment variable (copy the path of bin and paste in system varibale) .
3. SBT 1.3.13: Download and install sbt.
4. Intellij Idea : Download and install this IDE. Also add plugins of Scala (works as play framework and have server, Netty).
5. SQL : Install workbench sql.

##  WorkBench Sql
1. Create schema with the name "my_bank".<br>
2. Create tables in mybank schema.<br>
   a) user -- it contains id, fname, lname, dob, aadhar_no, email, status_id, role_id, created_on, phone_no COLUMNS.<br>
   b) transaction -- it contains id, user_id(foreign key of "user" table), amount, transaction_type_id(foreign key of "transaction_type" table), 
   description, acttion_type_id(foreign key of "transaction_action" table), bank_details_id(foreign key of "bank" table) COLUMNS.<br>
   c) transaction_type -- it contains id, name(for debit and credit) COLUMNS.<br>
   d) transaction_action -- it contains id, name(for transfer and withdraw) COLUMNS.<br>
   e) bank -- it contains id, name, ifsc, account_no, description, amount_to_transact(this is the amount when customer transfer money),
   transaction_type_id(foreign key of "transaction_type" table), user_id(foreign key of "user" table) COLUMNS.<br>
   f) password -- it contains id, user_id(foreign key of "user" table), password, created_on COLUMNS.<br>
   g) role_type -- it contains id, name(for admin and customer) COLUMNS.<br>
   h) status -- it contains id, name(foe active and inactive) COLUMNS.<br>

## How To Execute
1. Clone the project and save in separate folder.<br>
2. Create tables in workbench sql.<br>
3. Import project in Intellij Idea.<br>
4. Build the project using sbt.<br>
5. Run the project using sbt command <br>
sbt run<br>
6. On the otherhand, save the other folder(folder name in git project: MyApp)in the webapps of Apache Tomcat.<br>
7. Run the HTML pages on Tomcat, starting from myBank.html page.















