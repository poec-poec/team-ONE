/**
 * 
 */
package com.poecpoec.agence.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Seme
 */
public class Aeroport
{
    /** Identifiant technique */
    private int         idAero;

    /**
     * Nom de l'aéroport
     */
    private String      nom;

    /** Villes desservies par l'aéroport */
    private List<Ville> villes;

    /**
     * 
     */
    public Aeroport()
    {
        super();
        this.villes = new ArrayList<>();
    }

    /**
     * @param idAero
     * @param nom
     */
    public Aeroport(int idAero, String nom)
    {
        super();
        this.idAero = idAero;
        this.nom = nom;
        this.villes = new ArrayList<>();
    }

    /**
     * @param villes
     * @param nom
     */
    public Aeroport(List<Ville> villes, String nom)
    {
        super();
        this.villes = villes;
        this.nom = nom;
    }

    /**
     * @param nom
     */
    public Aeroport(String nom)
    {
        super();
        this.nom = nom;
        this.villes = new ArrayList<>();
    }

    /**
     * Ajoute une ville desservie par l'aéroport
     * 
     * @param ville
     */
    public void ajouterVille(Ville ville)
    {
        this.villes.add(ville);
        // je fais le lien dans l'autre direction ville->aéroport
        ville.ajouterAeroport(this);
    }

    /**
     * @return the idAero
     */
    public int getIdAero()
    {
        return idAero;
    }

    /**
     * @return the nom
     */
    public String getNom()
    {
        return nom;
    }

    /**
     * @return the villes
     */
    public List<Ville> getVilles()
    {
        return villes;
    }

    /**
     * @param idAero
     *            the idAero to set
     */
    public void setIdAero(int idAero)
    {
        this.idAero = idAero;
    }

    /**
     * @param nom
     *            the nom to set
     */
    public void setNom(String nom)
    {
        this.nom = nom;
    }

    /**
     * @param villes
     *            the villes to set
     */
    public void setVilles(List<Ville> villes)
    {
        this.villes = villes;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        StringBuffer sBuffer = new StringBuffer("Aeroport [nom= " + this.nom + ", villes=");
        // boucle de parcours des villes desservies
        for (Ville ville : villes)
        {
            sBuffer.append("[Ville nom=" + ville.getNom() + ", pays=" + ville.getPays() + "],");
        }
        sBuffer = new StringBuffer(sBuffer.substring(0, sBuffer.length() - 1));
        sBuffer.append("]");

        return sBuffer.toString();
    }

}
