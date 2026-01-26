package edu.io;

import edu.io.player.Player;
import edu.io.token.*;

public class Main {
    public static void main(String[] args) {

        Player player = new Player();
        Game game = new Game();

        System.out.println("=== START ===");
        game.join(player);

        System.out.println("Player joined the game.");
        System.out.println("Hydration: " + player.vitals.hydration());
        System.out.println("Gold: " + player.gold.amount());

        //  GIVE PICKAXE
        System.out.println("\n--- Pickaxe found ---");
        player.interactWithToken(new PickaxeToken());

        PickaxeToken pick = (PickaxeToken) player.shed().getTool();
        System.out.println("Pickaxe durability: " + pick.durability());

        //  COLLECT GOLD
        System.out.println("\n--- Collecting Gold (2.0) ---");
        player.interactWithToken(new GoldToken(2.0));
        System.out.println("Gold: " + player.gold.amount());
        System.out.println("Hydration: " + player.vitals.hydration());

        //  DRINK WATER
        System.out.println("\n--- Drinking Water (+20) ---");
        player.interactWithToken(new WaterToken(20));
        System.out.println("Hydration: " + player.vitals.hydration());

        //  REPAIR PICKAXE
        System.out.println("\n--- Repairing at Anvil ---");
        player.interactWithToken(new AnvilToken());

        pick = (PickaxeToken) player.shed().getTool();
        System.out.println("Pickaxe durability restored: " + pick.durability());
        System.out.println("Hydration: " + player.vitals.hydration());

        //  MOVE
        System.out.println("\n--- Move DOWN ---");
        try {
            player.token().move(PlayerToken.Move.DOWN);
        } catch (Exception e) {
            System.out.println("Cannot move down.");
        }

        var pos = player.token().pos();
        System.out.println("Player position: " + pos.col() + "," + pos.row());
        System.out.println("Hydration: " + player.vitals.hydration());

        System.out.println("\n=== END ===");
    }
}
