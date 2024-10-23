# Library Management System
This is a simple Library Management System implemented in Java that allows users to either log in or sign up. Depending on their role (student or admin), users can perform various tasks related to book borrowing, returns, and user account management.

## Features
User Roles: Two roles are supported:
Student: Can consult the catalog, view book details, borrow and return books, and view their profile.
Admin: Can manage users, add books, consult borrowing history, view most borrowed books, and check the most active users.
Login and Signup:
Users can either log in if they already have an account, or sign up for a new one.
## Functionality Overview
Student Menu
Consult Catalog: Browse through available books.
View Book Details: Get detailed information on a specific book.
View Profile: Display personal profile details.
Borrow a Book: Borrow a book by providing user and book IDs.
Return a Book: Return a borrowed book by providing user and book IDs.
Exit: Log out from the system.
Admin Menu
View Users: Display all registered users.
Add a Book: Add a new book to the catalog.
View Borrowing History: Review the borrowing history of users.
View Most Borrowed Books: List books that have been borrowed the most.
View Active Users: Display users with the highest borrowing activity.
Exit: Log out from the system.
## Dependencies
Java Development Kit (JDK): Required to run the program.
JDBC: Java Database Connectivity (JDBC) for database interaction. Make sure you configure the database connections appropriately within the code.
