/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

public class Player {

    private static String name;

    /**
     * Creates a new Player
     *
     * @param name - name of the player
     */
    public Player(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the Player
     *
     * @return player name
     */
    public String getName() {
        return name;
    }

    /**
     * Overrides method toString()
     *
     * @return "Player: " + this.name
     */
    @Override
    public String toString() {
        return "Player: " + this.name;
    }
}
