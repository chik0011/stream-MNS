import models.Etudiant;
import models.Note;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class Application {
    public static void main(String[] args) {

        Etudiant etudiant1 = new Etudiant("bansept", "franck");
        etudiant1.getListeNote().add(new Note("JAVA", 15));
        etudiant1.getListeNote().add(new Note("PHP", 10));
        etudiant1.getListeNote().add(new Note("UML", 5));

        Etudiant etudiant2 = new Etudiant("doe", "simon");
        etudiant2.getListeNote().add(new Note("JAVA", 5));
        etudiant2.getListeNote().add(new Note("PHP", 19));

        Etudiant etudiant3 = new Etudiant("stark", "sansa");
        etudiant3.getListeNote().add(new Note("C#", 5));
        etudiant3.getListeNote().add(new Note("AVA", 19));


        List<Etudiant> listeEtudiant = new ArrayList<>();
        listeEtudiant.add(etudiant1);
        listeEtudiant.add(etudiant2);
        listeEtudiant.add(etudiant3);


        // Exercice 1
        String mail = listeEtudiant.stream()
                .map(utilisateur -> utilisateur.getNom().toLowerCase() + "." + utilisateur.getPrenom().toLowerCase() + "@cesi.com")
                .collect(joining(" - "));

        System.out.println(mail);

        // Exercice 2

        StringBuilder sb = new StringBuilder();
        String bestEtudiant = null;
        for (Etudiant utilisateur : listeEtudiant) {
            String charAtNom = String.valueOf(utilisateur.getNom().charAt(0));
            String charAtPrenom = String.valueOf(utilisateur.getPrenom().charAt(0));


            if (charAtNom.toLowerCase().equals("s") || charAtPrenom.toLowerCase().equals("s")) {
                sb.append(utilisateur.getNom() + " " + utilisateur.getPrenom() + " ");

                List<Note> notes = utilisateur.getListeNote();
                for (Note note : notes) {
                    Integer maxNote = 0;
                    bestEtudiant = "";
                    if (note.getNote() > maxNote) {
                        bestEtudiant = utilisateur.getNom() + " " + utilisateur.getPrenom();
                    }
                }
            }
        }
        String personne = (String) sb.toString();

        // Etudiants qui commence par S
        System.out.println(personne);

        // Meilleure note des etudiants qui commence par S
        System.out.println((String) bestEtudiant);

        //Exercice 3
        List<Note> newNote = listeEtudiant.stream()
                .flatMap(utilisateur -> utilisateur.getListeNote().stream())
                .collect(Collectors.toCollection(ArrayList::new));
        System.out.println(newNote);

        // Exercice 4
        System.out.println(
                listeEtudiant.stream()
                        .sorted(Comparator.comparing(utilisateur -> utilisateur.getListeNote().stream()
                                .mapToInt(note -> note.getNote()).average().getAsDouble()))
                        .map(utilisateur ->
                                utilisateur.getNom().charAt(0) + " " +
                                        utilisateur.getPrenom().charAt(0) + " (" +
                                        utilisateur.getListeNote().stream()
                                                .mapToInt(note -> note.getNote()).max().getAsInt() + ")"
                        )
                        .collect(Collectors.joining(" > ")));

    }
}
