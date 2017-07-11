/**
 * 
 */
package com.poecpoec.agence.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Seme
 */
public class Ville
{
    /** Liste des aéroports qui desservent la ville */
    private List<Aeroport> aeroports;
    /** Identifiant technique */
    private int            idVille;
    /** Nom de la ville */
    private String         nom;
    /** Pays */
    private String         pays;

    /**
     * Constructeur par défaut
     */
    public Ville()
    {
        this.aeroports = new ArrayList<>();
    }

    /**
     * @param id
     * @param nom
     * @param pays
     */
    public Ville(int id, String nom, String pays)
    {
        super();
        this.idVille = id;
        this.nom = nom;
        this.pays = pays;
        this.aeroports = new ArrayList<>();
    }

    /**
     * @param nom
     * @param pays
     */
    public Ville(String nom, String pays)
    {
        super();
        this.nom = nom;
        this.pays = pays;
        this.aeroports = new ArrayList<>();
    }

    /**
     * @param nom
     * @param pays
     * @param aeroportDesservis
     */
    public Ville(String nom, String pays, List<Aeroport> aeroportDesservis)
    {
        super();
        this.nom = nom;
        this.pays = pays;
        this.aeroports = aeroportDesservis;
    }

    public void ajouterAeroport(Aeroport aeroport)
    {
        this.aeroports.add(aeroport);
    }

    /**
     * @return the aeroportDesservis
     */
    public List<Aeroport> getAeroportDesservis()
    {
        return aeroports;
    }

    /**
     * @return the id
     */
    public int getId()
    {
        return idVille;
    }

    /**
     * @return the nom
     */
    public String getNom()
    {
        return nom;
    }

    /**
     * @return the pays
     */
    public String getPays()
    {
        return pays;
    }

    /**
     * @param aeroportDesservis
     *            the aeroportDesservis to set
     */
    public void setAeroportDesservis(List<Aeroport> aeroportDesservis)
    {
        this.aeroports = aeroportDesservis;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(int id)
    {
        this.idVille = id;
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
     * @param pays
     *            the pays to set
     */
    public void setPays(String pays)
    {
        this.pays = pays;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "Ville [" + (nom != null ? "nom=" + nom + ", " : "")
                + (pays != null ? "pays=" + pays + ", " : "")
                + (aeroports != null ? "aeroportDesservis=" + aeroports : "") + "]";
    }

}
