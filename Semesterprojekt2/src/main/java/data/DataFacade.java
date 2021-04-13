package data;

import Interfaces.DataLayerInterface;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataFacade implements DataLayerInterface {

    String fileName = "Persistence/productons.tvc";
    File file = new File(fileName);

    @Override
    public void saveProduction(Production prod) {

        FileOutputStream fileOutputStream = new FileOutputStream(fileName, true);

        try (ObjectOutputStream outputStream = new ObjectOutputStream(
                fileOutputStream) {
            @Override
            protected void writeStreamHeader() throws IOException {
                if (file.exists()) {
                } else
                    super.writeStreamHeader();
            }
        }) {
            outputStream.writeObject(prod);
        } catch (IOException e) {
            System.err.println("Error opening output file "
                    + fileName + ": " + e.getMessage());
            System.exit(0);
        }
    }

    @Override
    public List<Production> getProductions() {
        Production readOne;
        List<Production> readProductions = new ArrayList<>();

        try (ObjectInputStream inputStream = new ObjectInputStream(
                new FileInputStream(fileName))) {

            while (true) {  //Reads untill EOF
                readOne = (Production) inputStream.readObject();
                readProductions.add(readOne);
                //System.out.println(readOne);
                //System.out.println();
            }

        } catch (EOFException eof) {
            System.out.println("Reading Done! ");

        } catch (IOException e) {
            System.err.println("Error opening input file "
                    + fileName + ": " + e.getMessage());
            System.exit(0);
        }
        //catch (ClassNotFoundException ex) {
        //    System.err.println(ex);
        //}
        return readProductions;
    }


    public static void main(String[] args) {

        // tester læs & skriv
        Production testProduktion = new Production("Badehotellet sæson 1");
        Production testProduktion2 = new Production("Tilbage til fremtiden 3");

        List<Production> production = new ArrayList<>();
        production.add(testProduktion);
        production.add(testProduktion2);

        // Gemmer produktion
        DataFacade saveData = new DataFacade();

        saveData.saveProduction(testProduktion);
        saveData.saveProduction(testProduktion2);

        // læser produktion
        DataFacade getData = new DataFacade();

        production = getData.getProductions();
        System.out.println(production);


    }

}