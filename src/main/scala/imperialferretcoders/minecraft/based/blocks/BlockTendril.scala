package imperialferretcoders.minecraft.based.blocks

import net.minecraft.creativetab.CreativeTabs
import net.minecraft.block.Block
import net.minecraft.block.material.Material

/**
 * Tendril block - this is a standalone tendril that is located in the air
 */
class BlockTendril extends Block(Material.rock){
  setBlockName("tendril")
  setBlockTextureName("based:tendril_air")
  setCreativeTab(CreativeTabs.tabMisc)
  setHardness(10.0F)
  setStepSound(Block.soundTypeGrass)

  // Overide the render type with one used for cobwebs, saplings, mushrooms, etc. Gives the tendril a 3D (non blocky) appearance
  override def isOpaqueCube: Boolean = { return false }
  override def renderAsNormalBlock: Boolean = { return false }
  override def getRenderType(): Int = { return 1 }

}
