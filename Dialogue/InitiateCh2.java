package Dialogue;

import models.Dragon;

public class InitiateCh2 {
    
    public void adventureBegins(Dragon dragon) {
        System.out.println("\n\tLooks like you're headed to " + dragon.getOrigin());
        System.out.println("\n\tLet's hope you get along with the " + dragon.getKeepers());
        System.out.println("\n\tPeace be with you...");
    }
}
