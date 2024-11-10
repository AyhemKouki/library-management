# Library Management System
## Introduction
This is a simple Library Management System implemented in Java and using MySQL for database management . This system allows users to either log in or sign up. Depending on their role (student or admin), users can perform various tasks related to book borrowing, returns, and user account management.

## Table of Contents
- [Features](#features)
- [Technologies](#technologies)
- [Usage](#usage)
- [Dependencies](#dependencies)
- [Contributing](#contributing)

## Features
- User Roles: Two roles are supported:
  - Student: Can consult the catalog, view book details, borrow and return books, and view their profile.
  - Admin: Can manage users, add books, consult borrowing history, view most borrowed books, and check the most active users.
- Login and Signup:
  - Users can either log in if they already have an account, or sign up for a new one.
- Email notifications for book availability:
  - Users receive email notifications when a previously unavailable book becomes available for borrowing.
    
## Technologies
- Java
- JDBC (MySQL Connector)
- MySQL Database
- Email Notifications (using Java Mail API)

## Usage
- Login: User.Login()
- Add a new User: User.ajouter_user()
- View Catalogue: User.Consulter_catalogue()
- Borrow Book: User.emprunter_livre(id_user, id_livre)
- Return Book: User.Retour_Livre(id_utilisateur, id_livre)
- Display all users: Admin.afficher_users()
- Add a new book: Admin.ajouter_livre()
- View the borrowing history: Admin.Consulter_Historique_Emprunts()
- Display the top 3 most borrowed books: Admin.livres_les_plus_empruntés()
- Display the top 3 most active users: Admin.utilisateurs_plus_assidus()
  
## Dependencies
Ensure you have the following installed on your system:
- Java Development Kit (JDK): Required to run the program.
- JDBC: Java Database Connectivity (JDBC) for database interaction. Make sure you configure the database connections appropriately within the code.
- Java Mail API

## Contributing
Feel free to fork the repository and make contributions. Please open an issue or pull request for any feature requests or bug fixes.
