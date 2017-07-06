package com.poecpoec.agence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // Etape 1 :chargement du driver
        Class.forName("com.mysql.jdbc.Driver");
        // Etape 2 : création de la connection
        String dsn = "jdbc:mysql://localhost:3306/agence";
        Connection con = DriverManager.getConnection(dsn, "user", "nicoliojuldas");
        // Etape 3 : Création du statement
        Statement statement = con.createStatement();
        // Etape 4 : Executer la requête SQL
        ResultSet resultats = statement.executeQuery("SELECT * FROM aeroport ");
        // Etape 5 : boucle de parcours des résultas
        while (resultats.next()) {
            System.out.println(resultats.getString("nom"));
        }
        // Etape 6 : fermer le statement
        resultats.close();
        // Etape 7: fermer le statement
        statement.close();
        // Etape 8: fermer la connection
        con.close();
    }

}
