package imperialferretcoders.minecraft.based.blocks

import net.minecraft.creativetab.CreativeTabs
import net.minecraft.block.{ITileEntityProvider, Block}
import cpw.mods.fml.relauncher.{Side, SideOnly}
import net.minecraft.client.renderer.texture.IIconRegister
import net.minecraft.util.IIcon
import net.minecraft.world.World
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.block.material.Material
import java.util.Random
import net.minecraft.tileentity.TileEntity
import imperialferretcoders.minecraft.based.tileentities.TileEntityKernel
import imperialferretcoders.minecraft.based.genetics.traits.Trait
import net.minecraft.entity.EntityLivingBase
import net.minecraft.item.ItemStack
import imperialferretcoders.minecraft.based.BASED
import net.minecraft.nbt.NBTTagCompound

/**
 * Block for the cute little kernels that build the various structures.
 */
class BlockKernel extends Block(Material.plants) with ITileEntityProvider {
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

  override def createNewTileEntity(world: World, x: Int): TileEntity = {
    new TileEntityKernel()
  }

  // When a kernel is added to the world, it must retain it's trait data
  override def onBlockPlacedBy(world: World, x: Int, y: Int, z: Int, entity: EntityLivingBase, itemStack: ItemStack) {
    // todo: rewrite this when abstracting tileentitykernel
    val kernel: TileEntityKernel = world.getTileEntity(x, y, z).asInstanceOf[TileEntityKernel]
    kernel.NBTcompound = new NBTTagCompound

    Trait.writeToNBT(kernel.NBTcompound, kernel.kernelTrait)
    world.markBlockForUpdate(x, y, z)
  }

  // Right click activates the kernel, increasing it's size and causing it to send tendrils out in predifined directions
  override def onBlockActivated(world: World, x: Int, y: Int, z: Int, entity: EntityPlayer, t: Int, i: Float, j: Float, k: Float): Boolean = {
    val meta = world.getBlockMetadata(x, y, z)

    // Check the meta value so we only grow the block when it isn't grown
    if (meta == 0) {
      // 2 => notify the client of this change
      world.setBlockMetadataWithNotify(x, y, z, 1, 2)
    }
    // otherwise do a manual update to the structure
    else {
      updateStructure(world, x, y, z)
    }

    // Catch right clicks
    return true
  }

  // Update kernel/structure growth
  override def updateTick(world: World, x: Int, y: Int, z: Int, random: Random): Unit = {
    super.updateTick(world, x, y, z, random)

    val meta = world.getBlockMetadata(x, y, z)

    // Only grow from a grown kernel, and only once!
    if (meta == 1) {
      world.setBlockMetadataWithNotify(x, y, z, 2, 2)

      updateStructure(world, x, y, z)
    }
  }

  // Update the structure generated by this kernel
  private def updateStructure(world: World, x: Int, y: Int, z: Int): Unit = {
    var list = new java.util.ArrayList[String]()
    Trait.printFromNBT(world.getTileEntity(x, y, z).asInstanceOf[TileEntityKernel].NBTcompound, list)
    for (s <- list.toArray) {
      BASED.logger.info(s)
    }

    val blocks = Trait.getBlockUpdates(world.getTileEntity(x, y, z).asInstanceOf[TileEntityKernel].NBTcompound, x, y, z)

    // Update the blocks
    if (blocks != null) {
      for (((x, y, z), blockType, meta) <- blocks) {
        // 2 =>  send block update to clients
        world.setBlock(x, y, z, blockType, meta, 2)
      }
    }
  }
}
