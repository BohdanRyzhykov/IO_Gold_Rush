package edu.io;

import edu.io.token.PlayerToken;

public class Player {

    private PlayerToken token;

   public void assingToken(PlayerToken token){
       this.token = token;
   }

   public PlayerToken token(){
       return this.token;
   }

}
