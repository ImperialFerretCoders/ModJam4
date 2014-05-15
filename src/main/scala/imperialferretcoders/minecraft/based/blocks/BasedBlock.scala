package imperialferretcoders.minecraft.based.blocks

import net.minecraft.block.Block
import net.minecraft.creativetab.CreativeTabs
import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.block.material.Material

/**
 *
 */
class BasedBlock(material:Material) extends Block(material:Material) {
  setCreativeTab(CreativeTabs.tabMisc)

}

/**
 *
 */
object BasedBlocks {

  def init {
    // All blocks need to be listed here.
    val blocks = Array(
      new RandomBlock
    )

    for (block <- blocks) {
      GameRegistry.registerBlock(block, block.getUnlocalizedName)
    }
  }

}