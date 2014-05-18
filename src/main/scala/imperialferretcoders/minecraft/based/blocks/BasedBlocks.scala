package imperialferretcoders.minecraft.based.blocks

import cpw.mods.fml.common.registry.GameRegistry
import imperialferretcoders.minecraft.based.items.{ItemKernel, ItemAlgae}
import net.minecraft.item.ItemBlock

/**
 *  Initialisation for all the blocks in this mod.
 */
object BasedBlocks {

  def init {
    // All blocks need to be listed here, format is (new BlockType, classOf[{subclass of ItemBlock}])
    val blocks = Array(
      (new BlockAlgae, classOf[ItemAlgae]),
      //(new BlockBasedFactory, classOf[ItemBlock]),
      (new BlockKernel, classOf[ItemKernel])
      //(new BlockTendril, classOf[ItemBlock])
    )

    for ((block, item) <- blocks) {
      GameRegistry.registerBlock(block, item, block.getUnlocalizedName)
    }
  }

}