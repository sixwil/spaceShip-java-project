import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class spaceShip {

    public static void main(String[] args) {

        ArrayList<human> folks = Populate();

        for (human human : folks) {
            System.out.println(human.name);
        }


        String response = consoleInput(Arrays.asList("aa","bb"));
        System.out.println(response);
        
    }

    static String consoleInput(List<String> options){
        String input = "[RESPONSE GOES HERE]";

        while (true) {
            input = System.console().readLine("input: ");

            if (options.contains(input)){break;}
            else { System.out.println("Error: " + input); }
        }
        return input;
    }

    static ArrayList<human> Populate() {
        ArrayList<human> folks = new ArrayList<>();

        engineer Jebediah = new engineer("jeb1");
        folks.add(Jebediah);

        engineer Jebediah2 = new engineer("jeb2");
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

    static class engineer extends human implements worker {

        engineer(String name) {
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

}
