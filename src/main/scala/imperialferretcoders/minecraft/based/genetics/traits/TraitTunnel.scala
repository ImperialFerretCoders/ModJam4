package imperialferretcoders.minecraft.based.genetics.traits

import cpw.mods.fml.common.registry.GameRegistry
import scala.collection.mutable.MutableList
import net.minecraft.block.Block

/**
 * A tunnel trait
 */
class TraitTunnel extends Trait {
  setUnlocalizedName("tunnel")

  def getBlockUpdates(x: Int, y: Int, z: Int) : Seq[Tuple3[Tuple3[Int, Int, Int], Block, Int]] = {
    // Grab the blocks types we need for this trait
    val blockAir = GameRegistry.findBlock("minecraft", "air")
    val blockBorder = GameRegistry.findBlock("minecraft", "log")
    val blockTorch = GameRegistry.findBlock("minecraft", "torch")

    // Generate block data for this kernel, currently every kernel produces a 3x3 lit tunnel, surrounded with logs
    val blocks: MutableList[Tuple3[Tuple3[Int, Int, Int], Block, Int]] = new MutableList()
    for (i <- 1 to 29) {
      // Generate the borders, this is done first, because the border blocks need to be updated before torches are placed
      for (j <- -2 to 2) {
        for (k <- -2 to 2) {
          if (k == 2 || k == -2 || j == 2 || j == -2) {
            blocks += (((x + i, y + j, z + k), blockBorder, 0))
          }
        }
      }

      for (j <- -1 to 1) {
        for (k <- -1 to 1) {
          // Default to replacing the block with air
          var newBlockType = blockAir
          var meta = 0

          // Generate torches
          if (j == 0 && (k == 1 || k == -1) && (i % 4 == 3)) {
            newBlockType = blockTorch
          }

          // Add the block for updating
          blocks += (((x + i, y + j, z + k), newBlockType, meta))
        }
      }
    }

    // Generate an end to the tunnel
    for (j <- -2 to 2) {
      for (k <- -2 to 2) {
        blocks += (((x + 30, y + j, z + k), blockBorder, 0))
      }
    }

    return blocks
  }
}
