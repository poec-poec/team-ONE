/**
 * 
 */
package com.poecpoec.agence.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.poecpoec.agence.interfaces.IDataRecovery;
import com.poecpoec.agence.model.Aeroport;
import com.poecpoec.agence.model.Ville;

/**
 * @author Seme
 */
public class AeroportDao implements IDataRecovery<Aeroport>
{

    /**
     * Data Access Object
     */
    public AeroportDao()
    {
        // TODO Auto-generated constructor stub
    }

    /**
     * Retourner tous les aéroports de la BDD
     * 
     * @return Liste d'aéroports
     */
    public List<Aeroport> findAll()
    {
        // DAO utilisés
        VilleDao villeDao = new VilleDao();

        List<Aeroport> aeroports = new ArrayList<>();
        try
        {
            // Etape 1 : chargement du driver
            Class.forName("com.mysql.jdbc.Driver");
            // Etape 2 : création de la connexion
            String dsn = "jdbc:mysql://localhost:3306/agence";
            Connection connexion = DriverManager.getConnection(dsn, "user", "password");
            // Etape 3 : création du statement
            Statement statement = connexion.createStatement();
            // Etape 4 : Exécuter la requête SQL
            ResultSet resultats = statement.executeQuery("SELECT * FROM aeroport");
            // Etape 5 : boucle de parcours des résultats
            while (resultats.next())
            {
                // je crée un aéroport vide
                Aeroport aeroport = new Aeroport();
                aeroport.setNom(resultats.getString("nom"));
                aeroport.setIdAero(resultats.getInt("idAero"));

                // je vais chercher dans la table de lien les id des villes
                // desservies
                Statement statementVilles = connexion.createStatement();
                // requête qui renvoie la liste des villes pour un aéroport
                // donné
                ResultSet idDesVilles = statementVilles.executeQuery("SELECT idVille"
                        + " FROM aeroport_ville" + " WHERE idAero = " + aeroport.getIdAero());
                // on parcours les id
                while (idDesVilles.next())
                {
                    // je récupère juste l'id
                    int idVille = idDesVilles.getInt("idVille");
                    // je récupère le POJO Ville grâce à villeDao par la méthode
                    // findById
                    // qui prend en paramètre l'idVille qu'on vient d'extraire
                    // du ResultSet
                    Ville ville = villeDao.findById(idVille);
                    // j'insère la ville dans la liste des villes desservies
                    aeroport.ajouterVille(ville);
                }

                // je l'ajoute à ma liste
                aeroports.add(aeroport);

                // fermeture du résultat
                idDesVilles.close();
                statementVilles.close();

            } // fin de la boucle de parcours des résultats du SELECT * FROM
              // aeroport

            // Etape 6 : fermer le résultat
            resultats.close();
            // Etape 7 : fermer le statement
            statement.close();
            // Etape 8 : fermer la connexion
            connexion.close();
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Impossible de charger le driver. Vérifier votre classpath.");
        }
        catch (SQLException e)
        {
            System.out.println("Erreur SQL. Voir ci-après.");
            System.out.println(e.getMessage());
        }
        // je retourne la liste des aéroports trouvés dans la BDD
        return aeroports;

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.poecpoec.agence.IAeroportDataRecovery#findById(int)
     */
    @Override
    public Aeroport findById(int id)
    {
        // DAO utilisés
        VilleDao villeDao = new VilleDao();
        Aeroport aeroport = new Aeroport();
        try
        {
            // Etape 1 : chargement du driver
            Class.forName("com.mysql.jdbc.Driver");
            // Etape 2 : création de la connexion
            String dsn = "jdbc:mysql://localhost:3306/agence";
            Connection connexion = DriverManager.getConnection(dsn, "user", "password");
            // Etape 3 : création du statement
            Statement statement = connexion.createStatement();
            // Etape 4 : Exécuter la requête SQL
            ResultSet resultats = statement
                    .executeQuery("SELECT * FROM aeroport WHERE idAero = " + id);
            // Etape 5 : boucle de parcours des résultats
            if (resultats.next())
            {
                // je crée un aéroport vide
                aeroport.setIdAero(id);
                aeroport.setNom(resultats.getString("nom"));
                // je vais chercher dans la table de lien les id des villes
                // desservies
                Statement statementVilles = connexion.createStatement();
                // requête qui renvoie la liste des villes pour un aéroport
                // donné
                ResultSet idDesVilles = statementVilles.executeQuery(
                        "SELECT idVille" + " FROM aeroport_ville" + " WHERE idAero = " + id);
                // on parcours les id
                while (idDesVilles.next())
                {
                    // je récupère juste l'id
                    int idVille = idDesVilles.getInt("idVille");
                    // je récupère le POJO Ville grâce à villeDao par la méthode
                    // findById
                    // qui prend en paramètre l'idVille qu'on vient d'extraire
                    // du ResultSet
                    Ville ville = villeDao.findById(idVille);
                    // j'insère la ville dans la liste des villes desservies
                    aeroport.ajouterVille(ville);
                }

                // fermeture du résultat
                idDesVilles.close();
                statementVilles.close();
            }
            else
            {
                throw new SQLException("Aucun aéroport ne correspond à l'identifiant indiqué.");
            }
            // Etape 6 : fermer le résultat
            resultats.close();
            // Etape 7 : fermer le statement
            statement.close();
            // Etape 8 : fermer la connexion
            connexion.close();
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Impossible de charger le driver. Vérifier votre classpath.");
        }
        catch (SQLException e)
        {
            System.out.println("Erreur SQL. Voir ci-après.");
            System.out.println(e.getMessage());
        }
        // je retourne l'aéroport trouvé dans la BDD
        return aeroport;
    }

}
