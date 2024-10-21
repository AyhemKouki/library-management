package library_management;

import java.sql.*;
import java.util.Scanner;
import java.sql.Date;
import java.time.LocalDate;
public class User {
	
    public static String Login(){
    	Connection connect = null;
    	PreparedStatement st = null;
    	ResultSet rs = null ;
    	Scanner sc = new Scanner(System.in);
    	String role = "";
    	try {
    		System.out.println("saisir votre username");
    		String username = sc.nextLine();
    		System.out.println("saisir votre password");
    		String password = sc.nextLine();
    		connect = Database_connection.OpenConnection();
    		String query = "select role , id_user from user where username = ? and pwd = ?";
    		st = connect.prepareStatement(query);
    		st.setString(1, username);
    		st.setString(2, password);
    		rs = st.executeQuery();
    		if (rs.next()){
    			role = rs.getString("role");
    		}
    		
    	}catch(SQLException  e) {
    		e.getMessage();
    	}finally {
    		Database_connection.CloseConnection(connect);
    		Database_connection.ClosePreparedStatement(st);
    		Database_connection.CloseResult(rs);
    	}
		return role;
    	
    }
    public static void profile_details(){
    	Connection connect = null;
    	PreparedStatement st = null;
    	ResultSet rs = null ;
    	Scanner sc = new Scanner(System.in);
    	try {
    		System.out.println("saisir votre username");
    		String username = sc.nextLine();
    		System.out.println("saisir votre password");
    		String password = sc.nextLine();
    		connect = Database_connection.OpenConnection();
    		String query = "select  * from user where username = ? and pwd = ?";
    		st = connect.prepareStatement(query);
    		st.setString(1, username);
    		st.setString(2, password);
    		rs = st.executeQuery();
    		if (rs.next()){
    			System.out.println("votre id: "+rs.getInt("id_user"));
    			System.out.println("votre nom: "+rs.getString("nom"));
    			System.out.println("votre prenom: "+rs.getString("prenom"));
    			System.out.println("votre username: "+rs.getString("username"));
    		}
    		
    	}catch(SQLException  e) {
    		e.getMessage();
    	}finally {
    		Database_connection.CloseConnection(connect);
    		Database_connection.ClosePreparedStatement(st);
    		Database_connection.CloseResult(rs);
    	}
    	
    }
    
    public static void ajouter_user() {
    	Connection connect = null;
    	PreparedStatement st = null;
    	Scanner sc = new Scanner(System.in);
    	
    	try {
    		System.out.println("saisir votre nom");
    		String nom = sc.nextLine();
    		System.out.println("saisir votre prenom");
    		String prenom = sc.nextLine();
    		System.out.println("saisir votre username");
    		String username = sc.nextLine();
    		System.out.println("saisir votre password");
    		String pwd = sc.nextLine();
    		
    		connect = Database_connection.OpenConnection();
    		String query = "insert into user (nom , prenom , username , pwd) values(?,?,?,?)";
    		st = connect.prepareStatement(query);
    		st.setString(1, nom);
    		st.setString(2, prenom);
    		st.setString(3, username);
    		st.setString(4, pwd);
    		st.executeUpdate();
    		System.out.println( " Utilisateur ajouté avec succès ! \n ");
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}finally {
    		Database_connection.CloseConnection(connect);
    		Database_connection.ClosePreparedStatement(st);
    	}

    }
    public static void Consulter_catalogue()
    {
        Connection connect = null;
        PreparedStatement st = null;
        ResultSet rs = null ;
        int i = 0;
        try {
        	System.out.println("   *****  Voici notre catalogue  ***** ");
            connect = Database_connection.OpenConnection();
            String query = "select titre from livre " ;
            st = connect.prepareStatement(query);
            rs = st.executeQuery();
            while (rs.next())
            {
            	i++;
            	String titre = rs.getString("titre");
                System.out.println("livre "+i+": "+titre );
                
            }
           } catch (SQLException e) {
            e.getMessage();
           } finally {
        	   Database_connection.CloseConnection(connect);
        	   Database_connection.ClosePreparedStatement(st);
        	   Database_connection.CloseResult(rs); }
    }
    
    public static void afficher_details_livre() {
    	Connection connect = null;
    	PreparedStatement st = null;
    	ResultSet rs = null;
    	Scanner sc = new Scanner(System.in);
    	try {
    		System.out.println("donner le titre du livre");
    		String titre = sc.nextLine();
    		connect = Database_connection.OpenConnection();
    		String query = "select * from livre where titre = ?";
    		st = connect.prepareStatement(query);
    		st.setString(1,titre);
    		rs = st.executeQuery();
    		if (rs.next()) {
    			System.out.println("   ***** details de livre *****");
    			System.out.println("id_livre: "+rs.getInt("id"));
    			System.out.println("titre: "+rs.getString("titre"));
    			System.out.println("genre: "+rs.getString("genre"));
    			System.out.println("auteur: "+rs.getString("auteur"));
    			System.out.println("disponibilité: "+rs.getString("disponibilité"));
    		}else {
    			System.out.println("il n y a pas de livre");
    		}
    	}catch(SQLException e) {
    		e.getMessage();
    	}finally {
    		Database_connection.CloseConnection(connect);
    		Database_connection.ClosePreparedStatement(st);
    		Database_connection.CloseResult(rs);
    	}
    }
    
    public static void emprunter_livre(int id_user, int id_livre) {
    	Connection connect = null;
    	PreparedStatement st = null;
    	PreparedStatement st1 = null;
    	PreparedStatement st2 = null;
    	ResultSet rs = null;
    	LocalDate date_actuelle = LocalDate.now();
    	try {
    		connect = Database_connection.OpenConnection();
    		String query = "select * from livre where id=?";
    		st = connect.prepareStatement(query);
    		st.setInt(1, id_livre);
    		rs=st.executeQuery();
    		if(rs.next()) {
    			String dispo = rs.getString("disponibilité");
    			if(dispo.equals("disponible")) {
    				String query2 = "update livre set disponibilité=? where id=?";
    				st1 = connect.prepareStatement(query2);
    				st1.setString(1, "non disponible");
    				st1.setInt(2, id_livre);
    				st1.executeUpdate();
    				
    				String query3 = "insert into Emprunt (id_livre,id_user,date_emprunt,date_retour) values (?,?,?,?)";
    				st2 = connect.prepareStatement(query3);
    				st2.setInt(1, id_livre);
    				st2.setInt(2, id_user);
    				st2.setDate(3, java.sql.Date.valueOf(date_actuelle));
    				st2.setDate(4, null);
    				st2.executeUpdate();
    				System.out.println("l'emprunt a été réalisé avec succés");
    			}else {
    				System.out.println("désolé le livre est non disponible pour le moment , vous pouvez le réserver");
    			}
    		}else {
    			System.out.println("il n ya aucun livre avec cet id"+ id_livre);
    		}
    	}catch(SQLException e) {
    		e.getMessage();
    	}finally {
    		Database_connection.CloseConnection(connect);
    		Database_connection.ClosePreparedStatement(st);
    		Database_connection.ClosePreparedStatement(st1);
    		Database_connection.ClosePreparedStatement(st2);
    		Database_connection.CloseResult(rs);
    	}
    }
    
    public static void afficher_users() {
    	Connection connect = null;
    	PreparedStatement st = null;
    	ResultSet rs = null;
    	try {
    		connect = Database_connection.OpenConnection();
    		String query = "select nom , prenom from user where role=?";
    		st = connect.prepareStatement(query);
    		st.setString(1,"etudiant");
    		rs = st.executeQuery();
    		System.out.println("nom et prenom des utilisateurs:");
    		while(rs.next()) {
    			System.out.println(rs.getString("nom")+" "+rs.getString("prenom"));
    		}
    	}catch(SQLException e) {
    		e.getMessage();
    	}finally {
    		Database_connection.CloseConnection(connect);
    		Database_connection.ClosePreparedStatement(st);
    		Database_connection.CloseResult(rs);
    	}
    }
    public static void ajouter_livre() {
    	Connection connect = null;
    	PreparedStatement st = null;
    	Scanner sc = new Scanner(System.in);
    	try {
    		System.out.println("donner le titre du livre\n");
    		String titre = sc.nextLine();
    		System.out.println("donner le genre du livre\n");
    		String genre = sc.nextLine();
    		System.out.println("donner l'auteur du livre\n");
    		String auteur = sc.nextLine();
    		System.out.println("donner la disponibilité du livre\n");
    		String disponibilité = sc.nextLine();
    		
    		connect = Database_connection.OpenConnection();
    		String query = "insert into livre (titre , genre , auteur , disponibilité) values(?,?,?,?)";
    		st = connect.prepareStatement(query);
    		st.setString(1, titre);
    		st.setString(2, genre);
    		st.setString(3, auteur);
    		st.setString(4, disponibilité);
    		st.executeUpdate();
    		System.out.println( " livre ajouté avec succès ! \n ");
    	}catch(SQLException e) {
    		e.getMessage();
    	}finally {
    		Database_connection.CloseConnection(connect);
    		Database_connection.ClosePreparedStatement(st);
    	}
    }
}