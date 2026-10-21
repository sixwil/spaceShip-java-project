import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class spaceShip {

    static int currentSceneId = 0;
    static Boolean reactorFixed = false;
    static Boolean oxygenFixed = false;

    public static void main(String[] args) {

        ArrayList<human> folks = PopulateCrew();

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
                        "\n".repeat(10) + "Progress: [" + "#".repeat(i) + " ".repeat(100 - i) + "] " + i + "%");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            hallwayRoomScene();
        }

        if (response.equals("2")) {
            var coinflip = Math.random();
            if (coinflip < 0.3) {
                System.out.println("bomb guys bomb was to big and everyone dies");
                youLoseScene();
            } else if (coinflip < 0.6) {
                System.out.println("both the door and the bomb guy are reduced to ashes");
                hallwayRoomScene();
            } else {
                System.out.println("there is now a hole where the door used to be, suprisingly no ones hurt");
                hallwayRoomScene();
            }
        }
    }

    static void hallwayRoomScene() {
        if (reactorFixed && oxygenFixed) {
            System.out.println("you fixed all the things and now you get to go home");
            youWinScene();
        } else if (reactorFixed) {
            System.out.println("you go towards the other room (oxygen)");
            oxygenRoomScene();
        } else if (oxygenFixed) {
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

        if (reactorFixed) {
            System.out.println("""
                    The oxygen runs out before you make it inside, everyone dies
                    """);
            youLoseScene();
        } else {

            System.out.println("""

                Turns out someone left a space-window ajar in the oxygen room
                fix it?
                1. Close the window""");

            if (!reactorFixed) {
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
                oxygenFixed = true;
                hallwayRoomScene();
            }

        }
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
            reactorFixed = true;
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

            bonkLevel += Integer.parseInt(response);
            reactorRoomScene(bonkLevel, c);
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

        String response = consoleInput(Arrays.asList("1", "2"));

        if (response.equals("1")) {
            lockedDoorScene();
        } else {
            System.err.println("""
                    ...okay?
                    you ignore the call to adventure
                    coward.
                    """);
        }

    }

    static void youLoseScene() {
        System.out.println("YOU LOSE, GOOD DAY SIR -William the wonky");
    }

    static void youWinScene() {
        System.out.println("YOU WIN, GOOD NIGHT MA'AM -bill the normal");
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
        return input;
    }

    static ArrayList<human> PopulateCrew() {
        ArrayList<human> folks = new ArrayList<>();

        Hacker Jebediah = new Hacker("jeb1");
        folks.add(Jebediah);

        Demolitionist Jebediah2 = new Demolitionist("jeb2");
        folks.add(Jebediah2);

        // System.out.println(Jebediah.hunger);
        // // eatFood(Jebediah);
        // System.out.println(Jebediah.hunger);

        return folks;
    }

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

        abstract void eatFood(human person);

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
        void work();
    }

    static class Hacker extends human implements worker {

        Hacker(String name) {
            super(name);
        }

        @Override
        void eatFood(human person) {
            hunger += 10;
        }

        @Override
        public void work() {
            hunger -= 10;
        }
    }

    static class Demolitionist extends human implements worker {

        Demolitionist(String name) {
            super(name);
        }

        @Override
        void eatFood(human person) {
            hunger += 15;
        }

        @Override
        public void work() {
            hunger -= 15;
        }
    }

}
