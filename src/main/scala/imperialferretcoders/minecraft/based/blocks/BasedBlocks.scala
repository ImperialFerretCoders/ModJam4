package imperialferretcoders.minecraft.based.blocks

import cpw.mods.fml.common.registry.GameRegistry

/**
 *  Initialisation for all the blocks in this mod.
 */
object BasedBlocks {

  def init {
    // All blocks need to be listed here.
    val blocks = Array(
      new BlockTemplate
    )

    for (block <- blocks) {
      GameRegistry.registerBlock(block, block.getUnlocalizedName)
    }
  }

}