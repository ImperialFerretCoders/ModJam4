package imperialferretcoders.minecraft.based.genetics

import net.minecraft.world.World
import net.minecraft.block.Block

class BlockGene extends CodingGene {
  def BlockTickLogic(state: OrganismState, world: World, x: Int, y: Int, z: Int, metadata: Int) = {
    TickLogic(state)
  }

  /*
    Priority for conflicting genes suggesting a block for appearance goes to the first active such in the genome.
    Null value means this gene has no "opinion" on what the block should be.
   */
  def BlockIs(state: OrganismState): Block = {
    return null
  }

  def BlockDifferentiatesTo(state: OrganismState): Block = {
    return null
  }
}
