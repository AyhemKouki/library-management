package library_management;

import java.sql.*;
import java.util.Scanner;

public class Admin {
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
	
	// Consulter l'historique des emprunts 
    
    public static void Consulter_Historique_Emprunts()
    {
   	 Connection connect = null;
	     PreparedStatement st1 = null;
	     PreparedStatement st2 = null;
	     ResultSet rs1 = null ;
	     ResultSet rs2 = null ;
	     try {
	    	 connect = Database_connection.OpenConnection();
	    	 String query1 = "select * from Emprunt ";
    		 st1 = connect.prepareStatement(query1);
    		 rs1=st1.executeQuery();
       	 System.out.println("      ***** Voici l'historique des Emprunts *****  \n");
            while (rs1.next()) {
                int id_livre = rs1.getInt("id_livre");
                Date date_emprunt = rs1.getDate("date_emprunt");
                Date date_retour = rs1.getDate("date_retour");
                // Récupération du nom du livre 
                String query2 = "Select * from livre where id = ?";
                st2 = connect.prepareStatement(query2);
                st2.setInt(1,id_livre);
                rs2 = st2.executeQuery();
                String titre ="";
                if (rs2.next()) {
                titre = rs2.getString("titre"); }
                System.out.println(" Livre : " + titre);
                System.out.println(" Date Emprunt : " + date_emprunt);
                System.out.println(" Date Retour : " + date_retour);
                System.out.println("\n ***************************** \n");}
          } catch (SQLException e) {
            e.printStackTrace();
          } finally {
        	Database_connection.CloseConnection(connect);
        	Database_connection.ClosePreparedStatement(st1);
        	Database_connection.ClosePreparedStatement(st2);
            Database_connection.CloseResult(rs1);
            Database_connection.CloseResult(rs2);
          }
    }
    public static void livres_les_plus_empruntés() {
        Connection connect = null;
        PreparedStatement st = null;
        PreparedStatement stTitre = null;
        ResultSet rsTitre = null;
        ResultSet rs = null;
        try {
            connect = Database_connection.OpenConnection();
            String query = "SELECT id_livre, COUNT(*) AS nombre_emprunts " +
                           "FROM Emprunt " +
                           "GROUP BY id_livre " +
                           "ORDER BY nombre_emprunts DESC";
            
            st = connect.prepareStatement(query);
            rs = st.executeQuery();
            
            System.out.println("Livres les plus empruntés :\n");
            // limiter l'affichage aux 3 premiers livres
            int count = 0;
            while (rs.next() && count <3) {
            	count ++;
                int idLivre = rs.getInt("id_livre");
                int nombre_emprunts = rs.getInt("nombre_emprunts");

                // Requête pour récupérer le titre du livre
                String queryTitre = "SELECT titre FROM livre WHERE id = ?";
                stTitre = connect.prepareStatement(queryTitre);
                stTitre.setInt(1, idLivre);
                rsTitre = stTitre.executeQuery();
                if (rsTitre.next()) {
                    String titre = rsTitre.getString("titre");
                    System.out.println("Top "+count+": "+"Titre : " + titre + " | Nombre d'emprunts : " + nombre_emprunts);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Database_connection.CloseConnection(connect);
            Database_connection.ClosePreparedStatement(st);
            Database_connection.ClosePreparedStatement(stTitre);
            Database_connection.CloseResult(rsTitre);
            Database_connection.CloseResult(rs);
        }
    }
    
    public static void utilisateurs_plus_assidus() {
    	Connection connect = null;
        PreparedStatement st = null;
        PreparedStatement st1 = null;
        ResultSet rs = null;
        ResultSet rs1 = null;
        try {
        	connect = Database_connection.OpenConnection();
        	String query = "select id_user , count(*) as nombre_emprunts from Emprunt"
        					+ " GROUP BY id_user ORDER BY nombre_emprunts DESC";
        	st = connect.prepareStatement(query);
        	rs = st.executeQuery();
        	System.out.println("Utilisateurs les plus assidus :\n");
        	// Limiter l'affichage aux 3 utilisateurs les plus assidus
        	int rank = 0;
        	while(rs.next() && rank < 3) {
        		rank++;
        		int idUser = rs.getInt("id_user");
                int nombre_emprunts = rs.getInt("nombre_emprunts");

                // Requête pour récupérer le nom et prénom du user
                String querynom = "SELECT nom , prenom FROM user WHERE id_user = ?";
                st1 = connect.prepareStatement(querynom);
                st1.setInt(1, idUser);
                rs1 = st1.executeQuery();
                if (rs1.next()) {
                    String nom = rs1.getString("nom");
                    String prenom = rs1.getString("prenom");
                    System.out.println("Rank : " + rank +" | Nom : " + nom + " Prenom : " + prenom +
                    					" | Nombre d'emprunts : " + nombre_emprunts);
                }
        	}
        }catch(SQLException e) {
        	e.printStackTrace();;
        }finally {
        	Database_connection.CloseConnection(connect);
            Database_connection.ClosePreparedStatement(st);
            Database_connection.ClosePreparedStatement(st1);
            Database_connection.CloseResult(rs1);
            Database_connection.CloseResult(rs);
        }
    }
}
