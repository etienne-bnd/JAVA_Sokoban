package com.sokoban;
import java.awt.Image;
import javax.swing.ImageIcon;
// Importations nécessaires pour utiliser les fonctionnalités d'Awt et Swing pour les images des murs

// La classe Mur hérite de la classe Acteur
public class Mur extends Acteur {

    private Image image;  // Image représentant le mur

    // Constructeur prenant les coordonnées x et y du mur comme paramètres
    public Mur(int x, int y) {
        super(x, y);  // Appel au constructeur de la classe parente Acteur
        
        initialiserMur();  // Appel à une méthode pour initialiser l'objet mur
    }

    // Méthode privée pour initialiser l'image du mur
    private void initialiserMur() {
        
        // Chargement de l'image du mur depuis le fichier "mur.png"
        ImageIcon icone = new ImageIcon("src/resources/mur.png");
        
        // Obtention de l'objet Image à partir de l'ImageIcon
        image = icone.getImage();
        
        // Définition de l'image pour l'objet Mur (méthode de la classe parente Acteur)
        setImage(image);
    }
}
