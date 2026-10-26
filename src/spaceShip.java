import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.lang.reflect.Field;
import java.util.*;

public class spaceShip {
    class npcs {
        public static Demolitionist darel = new Demolitionist("darel");
        public static Hacker harold = new Hacker("harold");

    }

    static ArrayList<human> npcsList = new ArrayList<human>();
    static Field[] npcFields = npcs.class.getFields();

    static boolean finished = false;

    class spaceShipLevels {
        static int food = 50;
        static int oxygenProcent = 50;
        static Boolean reactorFixed = false;
        static Boolean oxygenFixed = false;
        static int reactorPressureProcent = 1;

    }

    // actionstaken keeps track of how many actions are taken, does nothing now but
    // might be used in future.
    static int actionstaken = 0;

    // static int currentSceneId = 0;

    public static void main(String[] args) throws IllegalAccessException {
        for (Field npcField : npcFields) {

            // null because they are static, idk dude
            human npc = (human) npcField.get(null);
            npcsList.add(npc);

        }

        // for (human x : npcsList) {
        // System.out.println(x.health);
        // }
        // for (human human : folks) {
        // System.out.println(human.name);
        // }

        // System.out.println(response);

        prolougeScene();
        // lockedDoorScene();
        // reactorRoomScene(0, 0);

    }

    // static void nextScene(){
    // currentSceneId++;

    // switch (currentSceneId) {
    // case 1:
    // lockedDoorScene();
    // break;

    // default:
    // break;
    // }
    // }

    static void lockedDoorScene() {

        System.out.println("""

                    there is a locked door that youd like to not be that anymore,
                    who do you send to un-lock the door?
                    1. the hacker (takes longer, but is safer)
                    2. explosion guy (might kill you all, but he is faster)
                """);
        String response = consoleInput(Arrays.asList("1", "2"));

        if (response.equals("1")) {
            for (int i = 0; i < 100; i++) {
                System.out.println(
                        "\n".repeat(10) + "Progress: [" + "#".repeat(i) + "_".repeat(100 - i) + "] " + i + "%");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            for (int i = 1; i < 2; i++) {
                perRoundCheck();
            }
            hallwayRoomScene();
        }

        if (response.equals("2")) {

            int success = npcs.darel.work();

            if (success == 0) {
                youLoseScene();
            }
            if (success == 1 || success == 2) {
                hallwayRoomScene();
            }

        }
    }

    static void vaultScene() {
        System.out.println("""
                You notice that the vault is analog and can not be hacked,
                Do you want to:
                1. Try and guess it
                 """);
        if (npcs.darel.hurt != hurtScale.dead) {
            System.out.println("2. Let bombguy have a crack at it");
        } else {

            System.out.println("2. Try and blow it up yourself (RIP bombguy)");
        }

        String response = consoleInput(Arrays.asList("1", "2"));
        if (response.equals("1")) {

            String guess = "000";

            while (true) {
                guess = System.console().readLine("guess 00 -> 99: ");
                if (guess.equals("42")) {
                    break;
                } else {
                    System.out.println("Wrong, try again");
                }
            }

            System.out.println("jippie! you get some tressure");
            youWinScene();

        }
        if (response.equals("2")) {
            if (npcs.darel.hurt == hurtScale.dead) {
                System.out.println("You're not the demolitionist, you blow everything and everyone up");
                youLoseScene();
            }

            if (npcs.darel.hurt != hurtScale.dead) {

                int success = npcs.darel.work();

                if (success == 0) {
                    youLoseScene();
                }
                if (success == 1 || success == 2) {
                    System.out.println("jippie! you get some tressure");
                    youWinScene();
                }

            }

        }
    }

    static void hallwayRoomScene() {
        if (spaceShipLevels.reactorFixed && spaceShipLevels.oxygenFixed) {
            System.out.println("""
                you fixed all the things and get to look around a bit closer,
                you gather some supplies lying around and find a locked vault,
                the vault is analog and therefore cant be hacked,
                do you:
                1. go home without it
                2. try and break into the vault
                """);

            String response = consoleInput(Arrays.asList("1", "2"));

            if (response.equals("1")){
                System.out.println("you quit while youre ahead, and leave");
                youWinScene();
            }
            if (response.equals("2")){
                vaultScene();
            }

        } else if (spaceShipLevels.reactorFixed) {
            System.out.println("you go towards the other room (oxygen)");
            oxygenRoomScene();
        } else if (spaceShipLevels.oxygenFixed) {
            System.out.println("you go towards the other room (reactor)");
            reactorRoomScene(0, 0);
        } else {

            System.out.println("""
                    To the left is the oxygen-machine room,
                    to the right is the machine-machine room (reactor room).
                    Where do you go first?
                    1. left
                    2. right
                    """);

            String response = consoleInput(Arrays.asList("1", "2"));

            if (response.equals("1")) {
                System.out.println("you go towards the oxygen room");
                oxygenRoomScene();
            }
            if (response.equals("2")) {
                System.out.println("you go towards the reactor room");
                reactorRoomScene(0, 0);
            }
        }
    }

    static void oxygenRoomScene() {
        String response = "[RESPONSE GOES HERE]";

        // System.out.println("you go towards the");

        // if (spaceShipLevels.reactorFixed) {
        // System.out.println("""
        // The oxygen runs out before you make it inside, everyone dies
        // """);
        // youLoseScene();
        // } else {

        System.out.println("""

                Turns out someone left a space-window ajar in the oxygen room
                fix it?
                1. Close the window""");

        if (!spaceShipLevels.reactorFixed) {
            System.out.println("2. Check out reactor room first");
            response = consoleInput(Arrays.asList("1", "2"));
            if (response.contains("2")) {
                reactorRoomScene(0, 0);
            }
        } else {
            response = consoleInput(Arrays.asList("1"));
        }

        if (response.contains("1")) {
            System.out.println("you close the window, why do they even have that?");
            spaceShipLevels.oxygenFixed = true;
            hallwayRoomScene();
        }

        // }
    }

    static void reactorRoomScene(int bonkLevel, int c) {
        c++;

        String countingWord;

        if (c == 1) {
            countingWord = "a";
        } else {
            countingWord = "another";
            // countingWord = String.format("a %g nd", c);
        }

        if (bonkLevel == 4) {
            spaceShipLevels.reactorFixed = true;
            System.out.println("""
                        your technical expertise forces the reactor back online
                    """);
            hallwayRoomScene();
        }

        if (bonkLevel > 4) {
            System.out.println("the reactor has been over-bonked and explodes killing everyone");
            youLoseScene();
        }

        if (bonkLevel < 4) {

            System.out.println(
                    String.format("""

                                The reactor clearly needs %s bonking, how hard do you bonk it?
                                on a scale from 0 to 9 how hard do you bonk it?
                            """, countingWord));
            String response = consoleInput(Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9"));

            if (response != "No return") {

                bonkLevel += Integer.parseInt(response);
                reactorRoomScene(bonkLevel, c);
            }
        }

        // else if (Arrays.asList("0","1","2","3").contains(response)){}
    }

    static void prolougeScene() {
        // String response = "[RESPONSE GOES HERE]";
        System.out.println("""
                    Your crew has picked up a distress signal from a space station
                    maybe they give you a reward if you help them? what do you do?
                    1. go check it out
                    2. dont
                    (type only the corresponding number or in the console and then press enter)
                """);

        String response = consoleInput(Arrays.asList("1", "2", "0"));

        if (response.equals("1")) {
            lockedDoorScene();
        }
        if (response.equals("2")) {
            System.err.println("""
                    ...okay?
                    you ignore the call to adventure
                    coward.
                    """);

        }
        // Dev tool to take a bunch of turns quickly.
        if (response.equals("0")) {
            prolougeScene();

        }
    }

    static void youLoseScene() {
        finished = true;
        System.out.println("YOU LOSE, GOOD DAY SIR -William the wonky");
        for (human npc : npcsList) {
            npc.health = 0;
            npc.hurt = hurtScale.dead;

        }
    }

    static void youWinScene() {
        finished = true;
        System.out.println("YOU WIN, GOOD NIGHT MA'AM -bill the normal");
    }

    static boolean perRoundCheck() {
        boolean proceed = true;

        if (!finished) {

            if (!spaceShipLevels.oxygenFixed) {

                spaceShipLevels.oxygenProcent -= 10;

                if (spaceShipLevels.oxygenProcent <= 0) {
                    System.out.println("you run out of oxygen and die");
                    youLoseScene();
                    proceed = false;
                }

                System.out.println("Oxygen level: " + spaceShipLevels.oxygenProcent + "%");
                System.out.println("[" + "#".repeat(spaceShipLevels.oxygenProcent)
                        + "_".repeat(100 - spaceShipLevels.oxygenProcent) + "]");
                System.out.println();

            }

            if (!spaceShipLevels.reactorFixed) {
                spaceShipLevels.reactorPressureProcent *= 2;
                if (spaceShipLevels.reactorPressureProcent > 100) {
                    spaceShipLevels.reactorPressureProcent = 100;
                }

                if (spaceShipLevels.reactorPressureProcent >= 100) {
                    System.out.println("the reactor explodes");
                    youLoseScene();
                    proceed = false;
                }

                System.out.println("Reactor pressure level: " + spaceShipLevels.reactorPressureProcent + "%");
                System.out.println("[" + "#".repeat(spaceShipLevels.reactorPressureProcent)
                        + "_".repeat(100 - spaceShipLevels.reactorPressureProcent) + "]");
                System.out.println();

            }
        }

        return proceed;
    }

    static String consoleInput(List<String> options) {
        String input = "[RESPONSE GOES HERE]";

        while (true) {
            input = System.console().readLine("input: ");

            if (options.contains(input)) {
                break;
            } else {
                System.out.println("wrong input: " + input);
            }
        }
        System.out.println("\n".repeat(10));

        // Might make actions taken increase by custom value, but for now all actions
        // take the same amount of time.
        actionstaken += 1;
        boolean proceed = perRoundCheck();
        if (proceed) {
            return input;

        } else {
            return "No return";
        }
    }

    // static ArrayList<human> PopulateCrew() {
    // ArrayList<human> folks = new ArrayList<>();

    // Hacker Jebediah = new Hacker("jeb1");
    // folks.add(Jebediah);

    // Demolitionist darel = new Demolitionist("darel");
    // folks.add(darel);

    // // System.out.println(Jebediah.hunger);
    // // // eatFood(Jebediah);
    // // System.out.println(Jebediah.hunger);

    // return folks;
    // }

    enum hurtScale {
        dead,
        lots,
        some,
        hardly,
        none;
    }

    static abstract class human {
        // boolean isHuman;

        String name;

        human(String name) {
            this.name = name;
        }

        hurtScale hurt = hurtScale.none;

        int hunger = 50;
        int oxygen = 50;
        int health = 50;

        abstract int eatFood(human person);

        // boolean death() {
        // if (hunger == 100
        // || oxygen == 0
        // || health == 0) {
        // hurt = hurtScale.dead;
        // return true;
        // } else {
        // return false;
        // }
        // }

    }

    interface worker {
        int work();
    }

    static class Hacker extends human implements worker {

        Hacker(String name) {
            super(name);
        }

        @Override
        int eatFood(human person) {
            hunger += 10;
            return 10;
        }

        @Override
        public int work() {
            hunger -= 10;
            return 0;
        }
    }

    static class Demolitionist extends human implements worker {

        Demolitionist(String name) {
            super(name);
        }

        @Override
        int eatFood(human person) {
            hunger += 15;
            return 15;
        }

        @Override
        public int work() {
            hunger -= 15;

            var coinflip = Math.random();
            if (coinflip < 0.3) {
                System.out.println("bomb guys bomb was to big and everyone dies");
                return 0;
                // youLoseScene();
            } else if (coinflip < 0.6) {
                System.out.println("both it and the bomb guy are reduced to ashes");

                npcs.darel.health = 0;
                npcs.darel.hurt = hurtScale.dead;

                // hallwayRoomScene();
                return 1;
            } else {
                System.out.println("there is now a hole where the door used to be, suprisingly no ones hurt");
                int eaten = npcs.darel.eatFood(npcs.darel);
                spaceShipLevels.food -= eaten;
                if (spaceShipLevels.food < 0) {
                    npcs.darel.hunger += spaceShipLevels.food;
                    spaceShipLevels.food = 0;
                }
                // hallwayRoomScene();
                return 2;
            }
        }
    }

}
