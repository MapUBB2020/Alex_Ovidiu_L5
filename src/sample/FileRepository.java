package sample;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FileRepository {
    public List<Frage> alleFragen;

    public FileRepository() throws java.io.FileNotFoundException {
        alleFragen = new ArrayList<>();
        FileInputStream citire = new FileInputStream("files/fragen");
        Scanner scanner = new Scanner(citire);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] splituit = line.split(";");
            String[] splituitRaspunsuri = splituit[7].split(",");
            List<String> raspunsuricorecte = new ArrayList<>(Arrays.asList(splituitRaspunsuri));
            Frage k = new Frage(splituit[0], splituit[1], splituit[2], splituit[3], splituit[4], Integer.parseInt(splituit[5]), splituit[6], raspunsuricorecte);
            alleFragen.add(k);
        }
    }

    public List<Frage> getAll() {
        return alleFragen;
    }

    public Frage findFrageById(int id) {
        for (Frage f : alleFragen) {
            if (f.getID() == id)
                return f;
        }
        return null;
    }
}





