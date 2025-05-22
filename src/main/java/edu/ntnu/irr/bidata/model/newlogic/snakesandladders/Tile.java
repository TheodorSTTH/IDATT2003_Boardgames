package edu.ntnu.irr.bidata.model.newlogic.snakesandladders;

import edu.ntnu.irr.bidata.model.newlogic.Player;
import edu.ntnu.irr.bidata.model.newlogic.Space;
import java.util.ArrayList;
import java.util.Optional;

public class Tile extends Space<Integer> {
  private Tile next;
  protected ArrayList<Creature> creaturesOnSpace;

  public Tile(int tileNumber) {
    super(tileNumber);
    this.creaturesOnSpace = new ArrayList<>();
  }

  public Tile getNext() {
    return next;
  }
  public void setNext(Tile next) {
    this.next = next;
  }

  public void addCreatures(ArrayList<Creature> creatures) {
    creaturesOnSpace.addAll(creatures);
  }
  public void addCreature(Creature creature) {
    creaturesOnSpace.add(creature);
  }

  public ArrayList<Creature> getCreaturesOnSpace() {
    return creaturesOnSpace;
  }

  public void removeCreature(Creature creature) {
    this.creaturesOnSpace.remove(creature);
  }

  public boolean doesSpaceHaveCreature(Player player) {
    return getCreaturesOnSpace().stream().anyMatch(creature -> creature.getOwner().equals(player));
  }

  public Creature getCreatureOwnedByPlayer(Player player) {
    Optional<Creature> creatureOptional = getCreaturesOnSpace().stream().filter(creature -> creature.getOwner().equals(player)).findFirst();
    return creatureOptional.orElse(null);
  }
}
