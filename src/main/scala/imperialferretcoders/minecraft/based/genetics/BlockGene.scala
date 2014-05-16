package imperialferretcoders.minecraft.based.genetics

import net.minecraft.world.World
import net.minecraft.block.Block

class BlockGene extends CodingGene {
  def BlockTickLogic(world: World, x: Int, y: Int, z: Int, metadata: Int) = {}

  /*
    Priority for conflicting genes suggesting a block for appearance goes to the first in the genome.
    Null value means this gene has no "opinion" on what the block should be.
   */
  def BlockIs(): Block = {
    return null
  }

  def BlockDiesTo(): Block = {
    return null
  }
}
