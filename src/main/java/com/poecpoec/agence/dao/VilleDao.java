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
import com.poecpoec.agence.model.Ville;

/**
 * @author Seme
 */
public class VilleDao implements IDataRecovery<Ville>
{

    /**
     * 
     */
    public VilleDao()
    {
        // TODO Auto-generated constructor stub
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.poecpoec.agence.IDataRecovery#findAll()
     */
    @Override
    public List<Ville> findAll()
    {
        List<Ville> bos = new ArrayList<>();
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
            ResultSet resultats = statement.executeQuery("SELECT * FROM ville");
            // Etape 5 : boucle de parcours des résultats
            while (resultats.next())
            {
                // je crée un BO vide
                Ville bo = new Ville();
                bo.setNom(resultats.getString("nom"));
                bo.setPays(resultats.getString("pays"));
                bo.setId(resultats.getInt("idVille"));
                // je l'ajoute à ma liste
                bos.add(bo);
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
        // je retourne la liste des bos trouvés dans la BDD
        return bos;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.poecpoec.agence.IDataRecovery#findById(int)
     */
    @Override
    public Ville findById(int id)
    {
        Ville bo = new Ville();
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
                    .executeQuery("SELECT * FROM ville WHERE idVille = " + id);
            // Etape 5 : boucle de parcours des résultats
            if (resultats.next())
            {
                // je crée un BO vide
                bo.setId(id);
                bo.setNom(resultats.getString("nom"));
                bo.setPays(resultats.getString("pays"));
            }
            else
            {
                throw new SQLException("Aucune ville ne correspond à l'identifiant indiqué.");
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
        // je retourne le BO trouvé dans la BDD
        return bo;
    }

}
