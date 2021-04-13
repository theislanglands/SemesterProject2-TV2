package data;

import Interfaces.DataLayerInterface;
import domain.Production;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataFacade implements DataLayerInterface {

    String fileName = "src/main/resources/Persistence/productions.tvc";
    File file = new File(fileName);

    @Override
    public void saveProduction(Production prod) {

    // open file



        try (ObjectOutputStream outputStream = new ObjectOutputStream(
                new FileOutputStream(fileName, true)) {
            @Override
            protected void writeStreamHeader() throws IOException {
                if (file.exists()) {
                } else
                    super.writeStreamHeader();
            }
        }) {
            outputStream.writeObject(prod);
            outputStream.close();
        } catch(FileNotFoundException e) {
            System.out.println("file not found");
        } catch(IOException e) {
            System.out.println("IOException");
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
        catch (ClassNotFoundException ex) {
            System.err.println(ex);
        }
        return readProductions;
    }


    public static void main(String[] args) {

        // tester læs & skriv
        Production testProduktion = new Production("Badehotellet", "sæson1", new Date(5000));
        Production testProduktion2 = new Production("Tilbage til fremtiden", "del 3", new Date(100000));

        // opretter arrays med produktioner
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