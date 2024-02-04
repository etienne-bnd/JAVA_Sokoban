package com.zetcode;

// Importation du package Image d'Awt pour manipuler des images
import java.awt.Image;

// Classe parentes pour les différents composants du jeu
public class Acteur {

    // Espacement entre les acteurs, ajustable pour une meilleure résolution
    private final int ESPACE = 20;

    // Positions x et y de l'acteur, et l'image associée à l'acteur
    private int x;
    private int y;
    private Image image;

    // Constructeur de la classe Acteur
    public Acteur(int x, int y) {
        // Initialisation des positions x et y de l'acteur
        this.x = x;
        this.y = y;
    }

    // Méthodes d'accès aux valeurs de position et d'image, ainsi que les méthodes de modification
    public Image getImage() {
        return image;
    }

    public void setImage(Image img) {
        image = img;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public void setX(int x) {
        // Modification de la position x de l'acteur
        this.x = x;
    }

    public void setY(int y) {
        // Modification de la position y de l'acteur
        this.y = y;
    }

    // Méthodes de détection de collision avec d'autres acteurs
    public boolean estCollisionGauche(Acteur acteur) {
        // Collision à gauche si les positions sont les mêmes avec un espacement
        return x() - ESPACE == acteur.x() && y() == acteur.y();
    }

    public boolean estCollisionDroite(Acteur acteur) {
        // Collision à droite si les positions sont les mêmes avec un espacement
        return x() + ESPACE == acteur.x() && y() == acteur.y();
    }

    public boolean estCollisionHaut(Acteur acteur) {
        // Collision en haut si les positions sont les mêmes avec un espacement
        return y() - ESPACE == acteur.y() && x() == acteur.x();
    }

    public boolean estCollisionBas(Acteur acteur) {
        // Collision en bas si les positions sont les mêmes avec un espacement
        return y() + ESPACE == acteur.y() && x() == acteur.x();
    }
}
