import java.util.ArrayList;

public class spaceShip {

    public static void main(String[] args) {

        ArrayList<human> folks = new ArrayList<>();

        engineer Jebediah = new engineer("jeb1");
        engineer Jebediah2 = new engineer("jeb2");

        folks.add(Jebediah);
        folks.add(Jebediah2);

        for (human human : folks) {
            System.out.println(human.name);
        }

        System.out.println(Jebediah.hunger);
        // eatFood(Jebediah);
        System.out.println(Jebediah.hunger);

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
