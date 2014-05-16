package imperialferretcoders.minecraft.based.genetics

import net.minecraft.world.World

class BlockPromoter extends Promoter {
  def canAffectBlock(state: OrganismState, world: World, x: Int, y: Int, z: Int, metadata: Int): Boolean = {
    //Also defaults to true.
    return true;
  }
}
