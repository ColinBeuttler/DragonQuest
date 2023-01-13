import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import Dialogue.UponHatch;
import Prints.*;
import models.Dragon;
import models.DragonsList;
import models.Egg;
import models.Dragon.Type;

import java.io.FileInputStream;

public class Main {

    static String DRAGONS_FILE = "dragons.txt";

    static DragonsList dragonList = new DragonsList();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("\n\tWelcome to Dragon Flight");
        System.out.println("\n\tPress ENTER to continue");
        scan.nextLine();
        // initiates the game on enter


        // System.out.print(DragonPrint.dragonPrint());

        // Begin Chapter 1: picking a dragon egg

        System.out.println("\n\tGood Morning");
        wait(1);
        System.out.println("\n\tFinally awake I see....");
        wait(2);
        System.out.println("\n\tNow where were we?");
        wait(1);
        waitMessage(2);
        System.out.println("\n\t...Oh yes that's right!!!");
        wait(2);
        System.out.println("\n\tYou were about to pick a dragon egg...");
        wait(2);
        System.out.println("\n\tDid you make a decision yet?");
        wait(1);
        System.out.println("\n\tY/N?");

        String ans = scan.nextLine();

        // If they aren't ready to a pick an egg, pick for them

        if (!(ans.equalsIgnoreCase("y"))) {
            System.out.println("\n\tNeed more time...");
            wait(2);
            System.out.println("\n\tor should I pick for you?");
            waitMessage(3);
        }

        System.out.println("\n\tThis egg look good?");
        wait(1);
        System.out.println("\n\tYou stare deep into the egg...");

        try {
            ArrayList<Dragon> dragons = readDragons();
            createList(dragons);
            Dragon dragonHatchling = dragonList.getDragon(dragonList.assignDragon());
            wait(1);
            UponHatch.eggMessage(dragonHatchling);
            wait(2);
            System.out.println("\n\tY/N?");
            String res = scan.nextLine();

            // If they want to repick

            while (!(res.equalsIgnoreCase("y"))) {
                System.out.println("\n\tAnother one then?");
                waitMessage(2);
                System.out.println("\n\tHow about this one?");
                dragonHatchling = dragonList.getDragon(dragonList.assignDragon());
                wait(1);
                System.out.println("\n\tYou stare deep into this new egg placed in front of you...");
                wait(2);
                UponHatch.eggMessage(dragonHatchling);
                wait(1);
                System.out.println("\n\tY/N?");
                res = scan.nextLine();
            }
            // Creates new egg object, player must warm the egg in order to hatch it
            Egg unHatchedEgg = new Egg(false, 0);
            wait(1);
            while (unHatchedEgg.isHatched() == false) {
                wait(1);
                unHatchedEgg.careMessage();
                String careRes = scan.nextLine();
                wait(1);
                unHatchedEgg.eggCare(careRes);
                wait(1);
            }
                  
            // Message before the egg hatches
            waitMessage(1);
            System.out.println("\n\tI think it's hatching...");
            wait(3);

            System.out.println(DragonPrint.printBaby());
            // reveals the gender of the newly hatched dragon
            System.out.println(UponHatch.genderMessage(dragonHatchling));
            wait(1);
            System.out.print("\n\tOther than that I couldn't tell you much");
            wait(1);
            System.out.println("\n\tI can scan it for you if you want...");
            wait(1);
            System.out.println("\n\tit should tell us a bit more");
            wait(1);
            System.out.println("\n\tWhat do you say?");
            System.out.println("\n\tY/N?");
            
            String scanres = scan.nextLine();

            if(!(scanres.equalsIgnoreCase("y"))){
                System.out.println("too late already did... ");
            }

            System.out.print(dragonHatchling);
            wait(1);
            System.out.println("\n\tI can also get the estimated adult size of your dragon...");
            wait(1);
            System.out.println("\n\tSays here it will be about " + dragonHatchling.getSize());
            wait(1);
            if (dragonHatchling.getSize() > 4200) {
                System.out.println("\n\tThats little guys gonna be a big one eh?");
            }
            else if (dragonHatchling.getSize() < 2100) {
                System.out.println("\n\tKinda small for a dragon that is...");
                wait(1);
                System.out.println("Don't worry a dragon's size can be deceiving.");
                System.out.println("It's definately not a measure of power.");
            }
            
            waitMessage(3);
            System.out.println("\n\tThere is something else here");
            wait(1);
            System.out.println("\n\tDo you know about the dragon tribes?");
            wait(1);


            

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        scan.close();
    }

    // ///////////////////////////////////Method Section

    // Read the dragon file
    public static ArrayList<Dragon> readDragons() throws FileNotFoundException {
        FileInputStream fis = new FileInputStream(DRAGONS_FILE);
        Scanner scanFile = new Scanner(fis);

        // Adds the lines of the dragon file to the ArrayList Dragons
        ArrayList<Dragon> dragons = new ArrayList<Dragon>();
        while (scanFile.hasNextLine()) {
            String[] list = scanFile.nextLine().split(",");
            dragons.add(new Dragon(list[0], Type.valueOf(list[1]), list[2], list[3]));

        }
        scanFile.close();
        return dragons;
    }

    public static void createList(ArrayList<Dragon> dragons) {
        for (Dragon dragon : dragons) {
            dragonList.addDragon(dragon);
        } 
    }

    // Method that returns dialogue fillers for pauses in the conversation
    public static void waitMessage(int sec) {
        switch (sec) {
            case 1: {
                System.out.println("\n\tWWHHHAAATTTT!!!");
                wait(sec);
                break;
            }
            case 2: {
                System.out.println("\n\thmmmm...\n");
                wait(sec);
                break;
            }
            case 3: {
                wait(1);
                System.out.println("\n\tHold on...\n");
                wait(2);
                break;
            }
            case 5: {
                System.out.println("\n\tHope you don't want an answer before sundown hahahaha.....\n");
                wait(4);
                System.out.println("\tJk ;)\n");
                wait(1);
                break;
            }
            default: {
                System.out.println("\n\tThis may take a few minutes...\n");
                wait(sec);
            }
        }
    }

    // Method that creates the converation pauses

    public static void wait(int sec) {
        try {
            TimeUnit.SECONDS.sleep(sec);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
