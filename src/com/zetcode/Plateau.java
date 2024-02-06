package com.zetcode;

import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyAdapter; // permet de ne pas surcharger en ne prenant que les méthodes nécessaires
import java.awt.event.KeyEvent; // pour récupérer les touches
import java.util.ArrayList;

// Classe représentant le plateau de jeu dans le jeu Sokoban
public class Plateau extends JPanel {
    // variable de la classe
    private final int DECALAGE = 30;
    private final int ESPACE = 20;
    // Constantes pour la gestion des collisions
    private final int COLLISION_GAUCHE = 1;
    private final int COLLISION_DROITE = 2;
    private final int COLLISION_HAUT = 3;
    private final int COLLISION_BAS = 4;

    // Listes d'éléments sur le plateau (murs, caisses, zones) 
    private ArrayList<Mur> murs; 
    private ArrayList<Caisse> caisses;
    private ArrayList<Zone> zones;
    
    private Joueur soko;
    private int largeur = 0;
    private int hauteur = 0;
    
    private boolean estTermine = false; 

    private String niveau   = Niveau.getNiveauActuel(); 

    //constructeur
    public Plateau() {

        initialiserPlateau();
    }
    // Initialisation du plateau
    private void initialiserPlateau() {      

        addKeyListener(new AdaptateurClavier()); // on ajoute un écouteur d'évènement avec en argument la fonction
        // adaptateurClavier créée plus loin
        setFocusable(true); // le composant graphique peut recevoir des entrées claviers 
        initialiserMonde();
        
    }
    // getteur LargeurPlateau
    public int getLargeurPlateau() {
        return this.largeur;
    }
    // getteur hauteurPlateau
    public int getHauteurPlateau() {
        return this.hauteur;
    }
    // initialisation du monde (la partie graphique)
    private void initialiserMonde() {
        
        murs = new ArrayList<>();
        caisses = new ArrayList<>();
        zones = new ArrayList<>();

        int x = DECALAGE;
        int y = DECALAGE;
        this.largeur = 0; // on remet la largeur à 0 si on est au niveau suivant 
        this.hauteur = 0; // on remet la haut à 0 si on est au niveau suivant 

        Mur mur;
        Caisse caisse;
        Zone zone;

        for (int i = 0; i < niveau.length(); i++) {//  on parcourt le String niveau

            char element = niveau.charAt(i);

            switch (element) { 
                case '\n':
                    y += ESPACE;

                    if (this.largeur < x) { // pour mettre à jour la largeur
                        this.largeur = x;
                    }

                    x = DECALAGE; // on recommence au décalage initial
                    break;

                case '#':
                    mur = new Mur(x, y); // on créer un mur
                    murs.add(mur);
                    x += ESPACE; // on décale
                    break;

                case '$':
                    caisse = new Caisse(x, y);
                    caisses.add(caisse); // on ajoute à l'array
                    x += ESPACE;
                    break;

                case '.':
                    zone = new Zone(x, y);
                    zones.add(zone);
                    x += ESPACE;
                    break;

                case '@':
                    soko = new Joueur(x, y);
                    x += ESPACE;
                    break;

                case ' ':
                    x += ESPACE;
                    break;

                default:
                    break;
            }

            hauteur = y;
        }
    }
    // Construction graphique du monde (murs, caisses, zones, joueur)
    private void construireMonde(Graphics g) {
        g.setColor(new Color(255, 182, 193));  

        g.fillRect(0, 0, this.getWidth(), this.getHeight()); // on créer la fenêtre

        ArrayList<Acteur> monde = new ArrayList<>(); // on créer une liste d'acteur

        monde.addAll(murs);
        monde.addAll(zones);
        monde.addAll(caisses);
        monde.add(soko);

        for (int i = 0; i < monde.size(); i++) {

            Acteur element = monde.get(i);
               
            g.drawImage(element.getImage(), element.x(), element.y(), this);
            

            if (estTermine) { 
                if (Niveau.getNiveauCourant() == Niveau.nombreNiveau - 1){
                    g.setColor(new Color(0, 0, 0));
                    g.drawString("Bravo vous avez terminé le jeu", 25, 20);  
     

            }
                niveauSuivant();

            }

        }
    }

    @Override // on override la fonction repaint ! 
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        construireMonde(g);
    }
    // Classe interne pour gérer les événements du clavier
    private class AdaptateurClavier extends KeyAdapter {

        @Override   // on remplace les instances prédéfinie de la class KeyAdapter
        public void keyPressed(KeyEvent e) { // ici on prend en entrée e de la class KeyEvent

            if (estTermine) { 
                return;
            }

            int touche = e.getKeyCode();

            switch (touche) {
                
                case KeyEvent.VK_LEFT:
                    ImageIcon iconeGauche = new ImageIcon("src/resources/joueurGauche.png");
                    Image imageGauche = iconeGauche.getImage();
                    soko.setImage(imageGauche);
                    
                    if (verifierCollisionMur(soko,
                            COLLISION_GAUCHE)) {
                        return;
                    }
                    
                    if (verifierCollisionCaisse(COLLISION_GAUCHE)) {
                        return;
                    }
                    
                    soko.deplacer(-ESPACE, 0);
                    
                    break;
                    
                case KeyEvent.VK_RIGHT:
                    ImageIcon iconeDroite = new ImageIcon("src/resources/joueurDroite.png");
                    Image imageDroite = iconeDroite.getImage();
                    soko.setImage(imageDroite);
                    
                    if (verifierCollisionMur(soko, COLLISION_DROITE)) {
                        return;
                    }
                    
                    if (verifierCollisionCaisse(COLLISION_DROITE)) {
                        return;
                    }
                    
                    soko.deplacer(ESPACE, 0);
                    
                    break;
                    
                case KeyEvent.VK_UP:
                    ImageIcon iconeHaut = new ImageIcon("src/resources/joueurHaut.png");
                    Image imageHaut = iconeHaut.getImage();
                    soko.setImage(imageHaut);
                        
                    if (verifierCollisionMur(soko, COLLISION_HAUT)) {
                        return;
                    }
                    
                    if (verifierCollisionCaisse(COLLISION_HAUT)) {
                        return;
                    }
                    
                    soko.deplacer(0, -ESPACE);
                    
                    break;
                    
                case KeyEvent.VK_DOWN:
                    ImageIcon iconeBas = new ImageIcon("src/resources/joueurBas.png");
                    Image imageBas = iconeBas.getImage();
                    soko.setImage(imageBas);
                    if (verifierCollisionMur(soko, COLLISION_BAS)) {
                        return;
                    }
                    
                    if (verifierCollisionCaisse(COLLISION_BAS)) {
                        return;
                    }
                    
                    soko.deplacer(0, ESPACE);
                    
                    break;
                    
                case KeyEvent.VK_R:
                    
                    recommencerNiveau();
                    
                    break;
                    
                default: // on ne fait rien dans le cas général
                    break;
            }

            repaint(); // on met à jour le plateau
        }
    }
    // Méthode pour vérifier la collision avec les murs
    private boolean verifierCollisionMur(Acteur acteur, int type) {

        switch (type) {
            
            case COLLISION_GAUCHE:
                
                for (int i = 0; i < murs.size(); i++) {
                    
                    Mur mur = murs.get(i);
                    
                    if (acteur.estCollisionGauche(mur)) {
                        
                        return true;
                    }
                }
                
                return false;
                
            case COLLISION_DROITE:
                
                for (int i = 0; i < murs.size(); i++) {
                    
                    Mur mur = murs.get(i);
                    
                    if (acteur.estCollisionDroite(mur)) {
                        return true;
                    }
                }
                
                return false;
                
            case COLLISION_HAUT:
                
                for (int i = 0; i < murs.size(); i++) {
                    
                    Mur mur = murs.get(i);
                    
                    if (acteur.estCollisionHaut(mur)) {
                        
                        return true;
                    }
                }
                
                return false;
                
            case COLLISION_BAS:
                
                for (int i = 0; i < murs.size(); i++) {
                    
                    Mur mur = murs.get(i);
                    
                    if (acteur.estCollisionBas(mur)) {
                        
                        return true;
                    }
                }
                
                return false;
                
            default:
                break;
        }
        
        return false;
    }
    // Méthode pour vérifier la collision avec les caisses
    private boolean verifierCollisionCaisse(int type) {

        switch (type) {
            
            case COLLISION_GAUCHE:
                
                for (int i = 0; i < caisses.size(); i++) {

                    Caisse caisse = caisses.get(i);

                    if (soko.estCollisionGauche(caisse)) {

                        for (int j = 0; j < caisses.size(); j++) {
                            
                            Caisse element = caisses.get(j);
                            
                            if (!caisse.equals(element)) {
                                
                                if (caisse.estCollisionGauche(element)) {
                                    return true;
                                }
                            }
                            
                            if (verifierCollisionMur(caisse, COLLISION_GAUCHE)) {
                                return true;
                            }
                        }
                        
                        caisse.deplacer(-ESPACE, 0);
                        estTermine();
                    }
                }
                
                return false;
                
            case COLLISION_DROITE:
                
                for (int i = 0; i < caisses.size(); i++) {

                    Caisse caisse = caisses.get(i);
                    
                    if (soko.estCollisionDroite(caisse)) {
                        
                        for (int j = 0; j < caisses.size(); j++) {

                            Caisse element = caisses.get(j);
                            
                            if (!caisse.equals(element)) {
                                
                                if (caisse.estCollisionDroite(element)) {
                                    return true;
                                }
                            }
                            
                            if (verifierCollisionMur(caisse, COLLISION_DROITE)) {
                                return true;
                            }
                        }
                        
                        caisse.deplacer(ESPACE, 0);
                        estTermine();
                    }
                }
                return false;
                
            case COLLISION_HAUT:
                
                for (int i = 0; i < caisses.size(); i++) {

                    Caisse caisse = caisses.get(i);
                    
                    if (soko.estCollisionHaut(caisse)) {
                        
                        for (int j = 0; j < caisses.size(); j++) {

                            Caisse element = caisses.get(j);

                            if (!caisse.equals(element)) {
                                
                                if (caisse.estCollisionHaut(element)) {
                                    return true;
                                }
                            }
                            
                            if (verifierCollisionMur(caisse, COLLISION_HAUT)) {
                                return true;
                            }
                        }
                        
                        caisse.deplacer(0, -ESPACE);
                        estTermine();
                    }
                }

                return false;
                
            case COLLISION_BAS:
                
                for (int i = 0; i < caisses.size(); i++) {

                    Caisse caisse = caisses.get(i);
                    
                    if (soko.estCollisionBas(caisse)) {
                        
                        for (int j = 0; j < caisses.size(); j++) {

                            Caisse element = caisses.get(j);
                            
                            if (!caisse.equals(element)) {
                                
                                if (caisse.estCollisionBas(element)) {
                                    return true;
                                }
                            }
                            
                            if (verifierCollisionMur(caisse,COLLISION_BAS)) {
                                
                                return true;
                            }
                        }
                        
                        caisse.deplacer(0, ESPACE);
                        estTermine();
                    }
                }
                
                break;
                
            default:
                break;
        }

        return false;
    }
   // Méthode pour vérifier si le niveau est terminé
    public void estTermine() {

        int nombreCaisse = caisses.size();
        int caissesTerminees = 0;

        for (int i = 0; i < nombreCaisse; i++) { // on parcours toutes les caisses
            
            Caisse caisse = caisses.get(i);
            
            for (int j = 0; j < nombreCaisse; j++) {
                
                Zone zone =  zones.get(j);
                
                if (caisse.x() == zone.x() && caisse.y() == zone.y()) { // si la caisse est exactement à la même position qu'une zone
                    
                    caissesTerminees += 1;
                }
            }
        }

        if (caissesTerminees == nombreCaisse) {
            
            estTermine = true;
            repaint();
        }
    }

    public void recommencerNiveau() {

        zones.clear();
        caisses.clear();
        murs.clear();

        initialiserMonde();

        if (estTermine) {
            estTermine = false;
        }
    }
    public void niveauSuivant() {
        zones.clear();
        caisses.clear();
        murs.clear();

        Niveau.augmenterNiveau();
        this.niveau = Niveau.getNiveauActuel();
        initialiserMonde();

        if (estTermine) {
            estTermine = false;
        }
        repaint(); // le repaint sert à actualiser sans avoir à refaire un tour de boucle

    }
}
