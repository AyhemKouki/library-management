package library_management;
import java.sql.*;
import java.util.Scanner;
public class main{

	public static void main(String[] args){
		System.out.println("choisir 1 ou 2:\n 1) Login\n 2) Sign up");
		Scanner sc = new Scanner(System.in);
		int choix = sc.nextInt();
		if(choix == 1) {
			String role = User.Login();
			if (role.equals("etudiant")){
				System.out.println("   *** bienvenue ***\n");
				boolean test = true;
				while(test) {
					System.out.println("voici les différents choix que vous pouvez effectuer\n");
					System.out.println("   ******menu******");
					System.out.println("1) consulter catalogue\n2)afficher les details d'un livre\n3) afficher profile\n4) emprunter livre\n00) quitter");
					int choix_menu = sc.nextInt();
					switch (choix_menu) {
					case 1:
						User.Consulter_catalogue();
						break;
					case 2:
						User.afficher_details_livre();
						break;
					case 3:
						User.profile_details();
						break;
					case 4:
						System.out.println("donner votre id:");
						int id_user = sc.nextInt();
						System.out.println("donner l'id de livre que vous voulez emprunter:");
						int id_livre = sc.nextInt();
						User.emprunter_livre(id_livre,id_user);
						break;
					case 00:
						System.out.println("à bientot");
						test=false;
						break;
					default:
						System.out.println("essayer un autre choix\n");
						break;
					}
				}
			}else if(role.equals("admin")){
				System.out.println("   *** bienvenue admin***\n");
				boolean test = true;
				while(test) {
					System.out.println("\nvoici les différents choix que vous pouvez effectuer\n");
					System.out.println("   ******menu******");
					System.out.println("1) afficher les utilisateurs\n2)ajouter un livre\n00) quitter");
					int choix_menu = sc.nextInt();
					switch (choix_menu) {
					case 1:
						User.afficher_users();
						break;
					case 2:
						User.ajouter_livre();
						break;
					case 00:
						System.out.println("à bientot admin");
						test=false;
						break;
					default:
						System.out.println("essayer un autre choix\n");
						break;
					}
				}
			}else {
				Scanner scc = new Scanner(System.in);
				System.out.println("tu dois t'inscrire");
				System.out.println("voulez-vous créer un compte?(Y/N)");
				String test_inscrire = scc.nextLine();
				if(test_inscrire.equals("y")|| test_inscrire.equals("Y")|| test_inscrire.equals("yes")|| test_inscrire.equals("YES")){
					User.ajouter_user();
				}else  {
					System.out.println("à bientot");
				}
			}
		}
		else if(choix == 2) {
			User.ajouter_user();}
		else {
			System.out.println("vous devez entrer un nombre valid ( 1 ou 2)");
		}
	}
}