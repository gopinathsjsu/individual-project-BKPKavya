package inventory_management;

import java.io.*;
import java.util.*;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

public class Billing {
    private static final HashMap<String, Instances> inventoryItems = new HashMap<>();
    private static final HashSet<String> cards = new HashSet<>();

    @SuppressWarnings("resource")
    public static void main(String[] args) throws Exception
    {
        new FileOutputStream("Output.csv").close();
        new FileOutputStream("Error.txt").close();

        List<String[]> allRows =fileCSVreader("Inventory.csv");
        List<String[]> allCards =fileCSVreader("Cards.csv");

        Scanner userInput = new Scanner(System.in);
        System.out.print("Enter the file name: ");
        String inputFile = userInput.nextLine();
        List<String[]> inputs =fileCSVreader(inputFile);

        for(String[] row : allRows){
            Instances itemInstance= toItems(Arrays.toString(row));
            if(itemInstance!=null)
                inventoryItems.put(itemInstance.getItem(), itemInstance);

        }

        for(String[] card:allCards)
        {
            cards.add(card[0]);

        }

        CategoryEssentials essentials = new CategoryEssentials();
        CategoryLuxury luxury = new CategoryLuxury();
        CategoryMisc misc = new CategoryMisc();

        essentials.setCartCategory(luxury);
        luxury.setCartCategory(misc);

        Set<String> corrections = new HashSet<>();

        double totalAmount=0;
        Set<String> essentialsSeen = new HashSet<>();
        Set<String> luxurySeen = new HashSet<>();
        Set<String> MissSeen = new HashSet<>();
        for(String[] map : inputs) {
            cards.add(map[2]);

            double amount= essentials.calculateTotal(inventoryItems,map[0],Integer.parseInt(map[1]),corrections,essentialsSeen,luxurySeen,MissSeen);
            totalAmount+=amount;
        }

        if(ItemsCategoryLimitation.getEssentialCount()<0)
            corrections.addAll(essentialsSeen);

        if(ItemsCategoryLimitation.luxurySum()<0)
            corrections.addAll(luxurySeen);

        if(ItemsCategoryLimitation.getMiscSum()<0)
            corrections.addAll(MissSeen);

        List<String[]> updatedCards = new LinkedList<>();
        String[] header= new String[]{"CardNumber"};
        updatedCards.add(header);
        for(String card :cards)
        {
            String[] addCard= new String[]{card};
            updatedCards.add(addCard);
        }

        CSVWriter writer = new CSVWriter(new FileWriter("Cards.csv"), ',');
        writer.writeAll(updatedCards);
        writer.flush();
        writer.close();

        if(corrections.size()==0) {
            List<String[]> writting= new LinkedList<>();
            //writting.add(new String[]{"Order Summary"});

            //String summaryHeader = "Item" + "," + "Quantity" + "," + "Amount deducted";
            writting.add(new String[]{"Item", "Quantity", "Price", "Total Price"});
            boolean firstLine = false;
            for (int i = 0; i < inputs.size(); i++) {
                Double totalPrice = inventoryItems.get(inputs.get(i)[0]).price * Integer.parseInt(inputs.get(i)[1]);
                if (!firstLine) {
                    writting.add(new String[]{inputs.get(i)[0], inputs.get(i)[1], totalPrice.toString(), String.valueOf(totalAmount)});
                    firstLine = true;
                }
                else{
                        writting.add(new String[]{inputs.get(i)[0], inputs.get(i)[1], totalPrice.toString()});
                }
            }

            writer = new CSVWriter(new FileWriter("Output.csv"), ',');
            writer.writeAll(writting);
            writer.flush();
            writer.close();
        }

        else
        {
            System.out.println("Find the error message in Error.txt");
            System.out.println("Note: Permissible quantities allowed to input for essential items are 3, luxury items are 4 and miscellaneous items are 6");
            File file = new File("Error.txt");

            try (BufferedWriter bf = new BufferedWriter(new FileWriter(file))) {

                bf.write("Please correct quantities.");
                bf.newLine();

                for ( String input : corrections) {
                    bf.write(input);
                    bf.newLine();
                }

                bf.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    private static Instances toItems(String entries) {
        entries = entries.substring(1,entries.length()-1);
        String[] entrie = entries.split(",");
        if(entrie.length<4)
            return null;

        Instances itemInstance = new Instances();
        itemInstance.setCategory(entrie[0].trim());
        itemInstance.setItem(entrie[1].trim());
        itemInstance.setQuantity(Integer.valueOf(entrie[2].trim()));
        itemInstance.setPrice(Double.valueOf(entrie[3].trim()));
        return itemInstance;
    }

    private static List<String[]> fileCSVreader(String filePath) throws IOException {
        CSVReader reader = new CSVReader(new FileReader(filePath), ',', '"', 1);
        return  reader.readAll();
    }
}
