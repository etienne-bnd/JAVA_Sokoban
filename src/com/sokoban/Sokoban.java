package com.sokoban;

import java.awt.EventQueue;
import javax.swing.JFrame;

// Classe principale du jeu Sokoban, héritant de JFrame pour l'affichage
public class Sokoban extends JFrame {
    
    // Déclaration des variables de la classe
    private static Plateau plateau;
    private static final int DECALAGE = 30;
    private static int niveauCourant = 0;

    // Constructeur de la classe Sokoban
    public Sokoban() {
        initialiserUI();
    }

    // Initialisation de l'interface utilisateur
    private void initialiserUI() {
        plateau = new Plateau(); // Création d'un nouvel objet Plateau
        add(plateau); // Ajout du plateau à la fenêtre principale

        setTitle("Sokoban");
        setSize(plateau.getLargeurPlateau() + DECALAGE + 10,
                plateau.getHauteurPlateau() + 2 * DECALAGE + 5);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // pour pouvoir fermer la fenêtre
        setLocationRelativeTo(null);
    }

    // Méthode pour changer la taille de la fenêtre en fonction du niveau courant
    public void changerTaille() {
        while (true) {
            while (niveauCourant == Niveau.getNiveauCourant()) {
                try {
                    Thread.sleep(20); // Pause de 10 millisecondes pour éviter que la boucle soit trop gourmande en ram
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            try {
                Thread.sleep(10); // Pause de 100 millisecondes avant de changer la taille de la fenêtre pour eviter qu'on appelle l'ancien plateau encore
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            setSize(plateau.getLargeurPlateau() + DECALAGE + 10,
                    plateau.getHauteurPlateau() + 2 * DECALAGE + 5);
            niveauCourant++;
        }
    }

    // Méthode principale (main) du programme
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Sokoban jeu = new Sokoban();
            jeu.setVisible(true); // Rend la fenêtre principale visible
            new Thread(() -> {
                // Code exécuté dans un nouveau thread pour pouvoir toujours ajuster la taille de la fenêtre en fonction
                jeu.changerTaille(); // Appel de la méthode pour changer la taille de la fenêtre
            }).start();
        });
    }
}
