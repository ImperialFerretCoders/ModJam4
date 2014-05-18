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

  // A list of subtraits that this trait has
  val length = 30
  val width = 3
  val wallBlock = GameRegistry.findBlock("minecraft", "log")
  val wallBlockMeta = 0

  //val subTraits = Map(
  //  "length" -> Int,
  //  "width" -> Int,
  //  "wallblock" -> TraitBlock
  //)

  // Blocks used by the tunnel
  val blockAir = GameRegistry.findBlock("minecraft", "air")
  val blockTorch = GameRegistry.findBlock("minecraft", "torch")

  // todo: fix nasty nasty, ie. hard coded data generation
  override protected def writeToNBT(compound: NBTTagCompound, namespace: String) {
    compound.setInteger(namespace + "length", length)
    compound.setInteger(namespace + "width", width)
    compound.setString(namespace + "wallBlock.mod", "minecraft")
    compound.setString(namespace + "wallBlock.name", "log")
  }

  override protected def printFromNBT(compound: NBTTagCompound, namespace: String, list: java.util.List[String]) {
    list.add("Length: " + compound.getInteger(namespace + "length"))
    list.add("Width: " + compound.getInteger(namespace + "width"))

    val block = GameRegistry.findBlock(compound.getString(namespace + "wallBlock.mod"), compound.getString(namespace + "wallBlock.name"))
    if (block != null) {
      list.add("WallBlock: " + block.getLocalizedName)
    }
  }

  // Generate block updates for this trait
  override protected def getBlockUpdates(compound: NBTTagCompound, x: Int, y: Int, z: Int) : Seq[Tuple3[Tuple3[Int, Int, Int], Block, Int]] = {

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
