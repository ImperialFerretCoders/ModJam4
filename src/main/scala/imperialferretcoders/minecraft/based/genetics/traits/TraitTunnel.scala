package imperialferretcoders.minecraft.based.genetics.traits

import cpw.mods.fml.common.registry.GameRegistry
import scala.collection.mutable.MutableList
import net.minecraft.block.Block
import net.minecraft.nbt.NBTTagCompound

/**
 * A tunnel trait
 */
class TraitTunnel extends Trait {
  setUnlocalizedName("tunnel")

  protected override val subTraits = Map[String, Any](
    "length" -> 30,
    "width" -> 3,
    "wallblock" -> GameRegistry.findBlock("minecraft", "log")
  )

  // Blocks used by the tunnel
  val blockAir = GameRegistry.findBlock("minecraft", "air")
  val blockTorch = GameRegistry.findBlock("minecraft", "torch")

  // Generate block updates for this trait
  override protected def getBlockUpdates(compound: NBTTagCompound, x: Int, y: Int, z: Int) : Seq[Tuple3[Tuple3[Int, Int, Int], Block, Int]] = {

    //val wallBlock = GameRegistry.findBlock(compound.getString("tunnel.wallBlock.mod"), compound.getString("tunnel.wallBlock.name"))
    val wallBlock = GameRegistry.findBlock("minecraft", "log")
    // todo: allow changing of block metadata
    val wallBlockMeta = 0
    val length = subTraits("length").asInstanceOf[Int]
    val width = subTraits("width").asInstanceOf[Int]

    // Generate block data for this kernel, currently every kernel produces a 3x3 lit tunnel, surrounded with logs
    val blocks: MutableList[Tuple3[Tuple3[Int, Int, Int], Block, Int]] = new MutableList()
    for (i <- 1 to length) {
      // Generate the borders, this is done first, because the border blocks need to be updated before torches are placed
      for (j <- -1 * width to width) {
        for (k <- -1 * width to width) {
          if (k == width || k == -1 * width || j == width || j == -1 * width) {
            blocks += (((x + i, y + j, z + k), wallBlock, 0))
          }
        }
      }

      for (j <- -1 * width + 1 to width - 1) {
        for (k <- -1 * width + 1 to width - 1) {
          // Default to replacing the block with air
          var newBlockType = blockAir

          // Generate torches
          if (j == 0 && (k == width - 1 || k == -1 * width + 1) && (i % 4 == 3)) {
            newBlockType = blockTorch
          }

          // Add the block for updating
          blocks += (((x + i, y + j, z + k), newBlockType, 0))
        }
      }
    }

    // Generate an end to the tunnel
    for (j <- -1 * width to width) {
      for (k <- -1 * width to width) {
        blocks += (((x + length + 1, y + j, z + k), wallBlock, wallBlockMeta))
      }
    }

    return blocks
  }
}
