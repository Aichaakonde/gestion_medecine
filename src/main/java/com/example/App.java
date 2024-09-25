package com.example;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.example.datasource.DataSource;
import com.example.datasource.MySQLDataSourceImpl;
import com.example.datasource.PostgreSQLDataSourceImpl;
import com.example.repository.MedecinRepository;
import com.example.services.MedecinService;
import com.example.entities.Medecin;
import com.example.entities.Rv;


public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            // Choisissez votre implémentation MySQL ou PostgreSQL
            //DataSource dataSource = MySQLDataSourceImpl.getInstance(); 
            DataSource dataSource = PostgreSQLDataSourceImpl.getInstance(); 

            // Initialisation du dépôt et du service client
            MedecinRepository medecinRepository = new MedecinRepository(dataSource);
            MedecinService medecinService = new MedecinService(medecinRepository);

            while (true) {
                System.out.println("1. Enregistrer un Medecin");
                System.out.println("2. Enregistrer un Rv");
                System.out.println("3.Lister les RV");
                System.out.println("4. Quitter");
            

                int choix = Integer.parseInt(scanner.nextLine());

                switch (choix) {
                    case 1:
                        System.out.println("Entrez le nom du Medecin:");
                        String nom = scanner.nextLine();
                        System.out.println("Entrez le prenom du medecin:");
                        String prenom = scanner.nextLine();
                        

                        Medecin medecin = new Medecin(null, nom, prenom, null);
                        medecinService.addMedecin(medecin);
                        System.out.println("Medecin enregistré avec succès.");
                        break;

                    case 2:
                        System.out.println("Entrez la date du Rv:");
                        String date = scanner.nextLine();
                        System.out.println("Entrez l'heure du Rv:");
                        String heure = scanner.nextLine();
                        

                        Rv rv = new Rv();
                        medecinService.addRv(rv);
                        System.out.println("Rv enregistré avec succès.");

                    case 3:
                        Object rvService;
                        List<Rv> rvs = rvService.listRvs();
                        rvs.forEach(c -> System.out.println(c.getDate() + " - " + c.getHeure()));
                    break;
                        break;

                    case 4:
                        System.out.println("Au revoir !");
                        System.exit(0);

                    default:
                        System.out.println("Choix invalide !");
                }
        }

        } catch (Exception e) {
            System.err.println("Une erreur est survenue : " + e.getMessage());
            e.printStackTrace();
        }
    }
}