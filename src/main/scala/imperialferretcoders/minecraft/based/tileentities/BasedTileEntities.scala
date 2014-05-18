package imperialferretcoders.minecraft.based.tileentities

import imperialferretcoders.minecraft.based.items.{ItemGenomeSampler, ItemPetridish}
import cpw.mods.fml.common.registry.GameRegistry

/**
 * Initialisation for all the tile entities in this mod
 */
object BasedTileEntities {

  def init  {
    // All mod tile entities need to be listed here
    val tileentities = Array(
      (classOf[TileEntityKernel], "based_kernel")
    )

    for ((tileClass, id) <- tileentities) {
      GameRegistry.registerTileEntity(tileClass, id)
    }
  }

}
