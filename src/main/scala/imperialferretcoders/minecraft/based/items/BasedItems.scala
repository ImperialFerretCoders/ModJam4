package imperialferretcoders.minecraft.based.items

import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.block.Block

/**
 *  Initialisation for all the items in this mod
 */
object BasedItems {

  def init {
    // All mod items need to be listed here
    val items = Array(
      new ItemAlgae(Block.blockRegistry.getObject("algae").asInstanceOf[Block]),

      new ItemPetridish,
      new ItemGenomeSampler
    )

    for (item <- items) {
      GameRegistry.registerItem(item, item.getUnlocalizedName)
    }
  }

}