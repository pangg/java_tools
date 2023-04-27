package com.xxx.example.Thread2.a6;

public class LivelockExampleTwo {
    static class Spoon {
        private Diner owner;

        public Spoon(Diner d) {
            owner = d;
        }

        public synchronized void use() {
            System.out.printf("%s has eaten!", owner.getName());
            owner.notify();
        }
    }

    static class Diner {
        private String name;
        private boolean isHungry;

        public Diner(String n) {
            name = n;
            isHungry = true;
        }

        public String getName() {
            return name;
        }

        public synchronized void eatWith(Spoon spoon, Diner spouse) {
            while (isHungry) {
                // 等待对方先吃
                try {
                    spoon.owner.notify();
                    spoon.wait();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(name + " was interrupted");
                }
            }
            isHungry = false;
            System.out.println(name + " is eating with " + spouse.getName());
            spoon.use();
            // 交换勺子
            spouse.eatWith(new Spoon(this), this);
            isHungry = true;
        }
    }

    public static void main(String[] args) {
        Diner husband = new Diner("Bob");
        Diner wife = new Diner("Alice");

        Spoon s = new Spoon(husband);

        new Thread(() -> {
            husband.eatWith(s, wife);
        }).start();

        new Thread(() -> {
            wife.eatWith(s, husband);
        }).start();
    }
}
