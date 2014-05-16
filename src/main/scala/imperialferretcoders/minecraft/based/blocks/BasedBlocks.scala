package imperialferretcoders.minecraft.based.blocks

import cpw.mods.fml.common.registry.GameRegistry
import imperialferretcoders.minecraft.based.items.ItemAlgae

/**
 *  Initialisation for all the blocks in this mod.
 */
object BasedBlocks {

  def init {
    // All blocks need to be listed here, format is (new BlockType, classOf[{subclass of ItemBlock}])
    val blocks = Array(
      (new BlockAlgae, classOf[ItemAlgae])
    )

    for ((block, item) <- blocks) {
      GameRegistry.registerBlock(block, item, block.getUnlocalizedName)
    }
  }

}