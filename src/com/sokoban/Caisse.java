package com.sokoban;

// Importation du package Image d'Awt pour manipuler des images
import java.awt.Image;
// Importation de la classe Acteur du même package
import javax.swing.ImageIcon; // Importation du package ImageIcon pour charger des images

// Classe représentant une caisse, étendue de la classe Acteur 
public class Caisse extends Acteur {

    // Constructeur prenant les coordonnées x et y de la caisse comme paramètres
    public Caisse(int x, int y) {
        super(x, y); // Appel au constructeur de la classe parente Acteur
        initialiserCaisse(); // Appel à une méthode pour initialiser l'objet caisse
    }
    
    // Méthode privée pour initialiser l'image de la caisse
    private void initialiserCaisse() {
        // Chargement de l'image de la caisse depuis le fichier "caisse.png"
        ImageIcon icone = new ImageIcon("src/resources/caisse.png"); 
        Image image = icone.getImage(); // Obtention de l'objet Image à partir de l'ImageIcon
        setImage(image); // Définition de l'image pour l'objet Caisse (méthode de la classe parente Acteur)
    }

    // Méthode pour déplacer la caisse en fonction des coordonnées fournies
    public void deplacer(int x, int y) {
        // Calcul des nouvelles positions x et y de la caisse
        int dx = x() + x;
        int dy = y() + y;
        
        // Mise à jour des positions x et y de la caisse
        setX(dx);
        setY(dy);
    }
}
