package imperialferretcoders.minecraft.based.blocks

import net.minecraft.block.{Block, BlockBush}
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.world.{IBlockAccess, World}
import net.minecraft.init.Blocks
import net.minecraft.block.material.Material
import net.minecraftforge.common.EnumPlantType

/**
 * Algae block, based off the vanilla lily pad.
 */
class BlockAlgae extends BlockBush {
  setBlockName("algae")
  setBlockTextureName("based:algae")
  setCreativeTab(CreativeTabs.tabMisc)
  setHardness(0.0F)
  setStepSound(Block.soundTypeGrass)

  // Bounding box for harvesting
  setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.015625F, 1.0F)

  // Sets the render type of the block to the same as lilypad, so that the texture is rendered as sitting on the surface of the block below
  override def getRenderType: Int = { return 23 }

  // Only allow algae to be placed on water
  override def canPlaceBlockOn(block: Block): Boolean = { block == Blocks.water }

  // Harvest algae if the water below is removed
  override def canBlockStay(world: World, x : Int, y : Int, z : Int): Boolean = {
    // bad constants
    y >= 0 && y < 256 && world.getBlock(x, y - 1, z).getMaterial == Material.water && world.getBlockMetadata(x, y - 1, z) == 0
  }

  // Set algae to have the same plant type as lilypads
  override def getPlantType(world: IBlockAccess, x: Int, y: Int, z: Int): EnumPlantType = { EnumPlantType.Water }
}
