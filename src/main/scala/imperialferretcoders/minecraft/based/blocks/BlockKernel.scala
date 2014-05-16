package imperialferretcoders.minecraft.based.blocks

import net.minecraft.creativetab.CreativeTabs
import net.minecraft.block.Block
import cpw.mods.fml.relauncher.{Side, SideOnly}
import net.minecraft.client.renderer.texture.IIconRegister
import net.minecraft.util.IIcon
import net.minecraft.world.World
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.block.material.Material
import java.util.Random

/**
 * Block for the cute little kernel's that build the various structures.
 */
class BlockKernel extends Block(Material.plants) {
  setBlockName("kernel")
  setCreativeTab(CreativeTabs.tabMisc)
  setHardness(10.0F)
  setStepSound(Block.soundTypeGrass)
  setTickRandomly(true)

  var firstStageIcon: IIcon = null
  var secondStageIcon: IIcon = null

  // Get the icon for the relevant growth state
  @SideOnly(Side.CLIENT)
  override def getIcon(side: Int, meta: Int): IIcon = {
    if (meta == 0) firstStageIcon else secondStageIcon
  }

  // Register the two kernel textures
  @SideOnly(Side.CLIENT)
  override def registerBlockIcons(iconRegister: IIconRegister): Unit = {
    firstStageIcon = iconRegister.registerIcon("based:kernel_seed")
    secondStageIcon = iconRegister.registerIcon("based:kernel_grown")
    this.blockIcon = firstStageIcon
  }

  // Overide the render type with one used for cobwebs, saplings, mushrooms, etc. Gives the kernel a 3D (non blocky) appearance
  override def isOpaqueCube: Boolean = { return false }
  override def renderAsNormalBlock: Boolean = { return false }
  override def getRenderType(): Int = { return 1 }

  // Right click activates the kernel, increasing it's size and causing it to send tendrils out in predifined directions
  override def onBlockActivated(world: World, x: Int, y: Int, z: Int, entity: EntityPlayer, t: Int, i: Float, j: Float, k: Float): Boolean = {
    val meta = world.getBlockMetadata(x, y, z)

    // Check the meta value so we only grow the block when it isn't grown, also catches right clicks only when growing the block
    if (meta == 0) {
      // notify the client of this change
      world.setBlockMetadataWithNotify(x, y, z, 1, 2)
      return true
    }
    else {
      return false
    }
  }

  // Update tendril growth
  override def updateTick(world: World, x: Int, y: Int, z: Int, random: Random): Unit = {
    super.updateTick(world, x, y, z, random)

    val meta = world.getBlockMetadata(x, y, z)

    // Only grow from a grown kernel, and only once!
    if (meta == 1) {
      world.setBlockMetadataWithNotify(x, y, z, 2, 2)

      // Remove a bunch of blocks, for some reason!
      for (i <- 1 to 50) {
        for (j <- -2 to 2) {
          for (k <- -2 to 2) {
            world.setBlock(x + i, y + j, z + k, Block.getBlockFromName("air"))
          }
        }
      }
    }
  }

}
