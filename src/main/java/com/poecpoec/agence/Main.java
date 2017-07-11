/**
 * 
 */
package com.poecpoec.agence;

import java.util.ArrayList;
import java.util.List;

import com.poecpoec.agence.dao.AeroportDao;
import com.poecpoec.agence.dao.ClientDao;
import com.poecpoec.agence.dao.VilleDao;
import com.poecpoec.agence.model.Aeroport;
import com.poecpoec.agence.model.Client;
import com.poecpoec.agence.model.Ville;

/**
 * @author Seme
 */
public class Main
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        /*
         * Tests unitaires itération #1
         */
        System.out.println("---------");
        System.out.println("Test it#1");
        System.out.println("---------");
        Aeroport cdg = new Aeroport("CDG");
        Ville paris = new Ville("Paris", "France");
        Ville londres = new Ville("Londres", "UK");
        System.out.println(cdg);
        System.out.println(paris);

        /*
         * UT it#2
         */
        System.out.println("---------");
        System.out.println("Test it#2");
        System.out.println("---------");
        cdg.ajouterVille(paris);
        cdg.ajouterVille(londres);
        System.out.println(cdg);

        /*
         * UT it#3
         */
        System.out.println("---------");
        System.out.println("Test it#3");
        System.out.println("---------");
        System.out.println(paris);

        /*
         * UT it#4 : test de la connexion à la BDD (modifié par l'it8)
         */
        System.out.println("---------");
        System.out.println("Test it#4");
        System.out.println("---------");
        // déclaration des variables
        List<Aeroport> aeroports = new ArrayList<>();

        // j'ai besoin du DAO, alors je l'instancie
        AeroportDao aeroportDao = new AeroportDao();
        // j'appelle la méthode pour récupérer tous les objets de classe
        // Aéroport de la BDD à l'aide du DAO Aéroport
        aeroports = aeroportDao.findAll();

        // j'affiche mes aéroports
        System.out.println(aeroports);

        /*
         * UT it#6: test de la recherche par identifiant (modifié par l'it8)
         */
        System.out.println("---------");
        System.out.println("Test it#6");
        System.out.println("---------");
        Aeroport aeroport = aeroportDao.findById(654);
        aeroport = aeroportDao.findById(1);
        System.out.println(aeroport);

        /*
         * UT it#7: test de la recherche par identifiant
         */
        System.out.println("---------");
        System.out.println("Test it#7");
        System.out.println("---------");
        // j'instancie du DAO de ville
        VilleDao villeDao = new VilleDao();
        Ville ville = villeDao.findById(1);
        System.out.println(ville);
        // toutes les villes
        List<Ville> villes = villeDao.findAll();
        System.out.println(villes);

        // TEAM MMA
        System.out.println("Test MMA");
        // IT#3 Création client DAO.
        List<Client> clients = new ArrayList<>();
        ClientDao clientDao = new ClientDao();
        clients = clientDao.findAll();
        System.out.println(clients);

    }

}
