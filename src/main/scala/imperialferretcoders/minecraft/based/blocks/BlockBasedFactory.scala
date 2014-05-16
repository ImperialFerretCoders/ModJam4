package imperialferretcoders.minecraft.based.blocks

import cpw.mods.fml.relauncher.{Side, SideOnly}
import net.minecraft.util.{MathHelper, IIcon}
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.client.renderer.texture.IIconRegister
import net.minecraft.world.World
import net.minecraft.entity.EntityLivingBase
import net.minecraft.item.ItemStack

/**
 * This is in fact a bio factory of sorts, as opposed to a factory based on blocks - wait what?
 */
class BlockBasedFactory extends Block(Material.rock) {
  setBlockName("basedFactory")
  setCreativeTab(CreativeTabs.tabMisc)
  setHardness(20.0F)
  setStepSound(Block.soundTypePiston)

  val textureNames = List("based:basedFactory_bottom", "based:basedFactory_top", "based:basedFactory_front", "based:basedFactory_side")
  val textures = Array[IIcon](null, null, null, null)

  // Get the icon for the relevant side.
  // todo: work out why the item icon isn't rendered correctly, front is facing the wrong way! :<
  // 0 = bottom, 1 = top, (2, 3, 4, 5) = ?
  @SideOnly(Side.CLIENT)
  override def getIcon(side: Int, meta: Int): IIcon = {
    // Bottom and top textures map directly
    if (side < 2) {
      return textures{side}
    }
    // Determine direction of front from metadata
    else if (side == meta) {
      return textures{2}
    } else {
      return textures{3}
    }
  }

  // Register the face textures
  @SideOnly(Side.CLIENT)
  override def registerBlockIcons(iconRegister: IIconRegister) = {
    textures{0} = iconRegister.registerIcon(textureNames{0})
    textures{1} = iconRegister.registerIcon(textureNames{1})
    textures{2} = iconRegister.registerIcon(textureNames{2})
    textures{3} = iconRegister.registerIcon(textureNames{3})

    // set the block icon to be the same texture as the front of the block
    this.blockIcon = textures{2}
  }

  // Handle block placement, setting the metadata directions to match the internal
  override def onBlockPlacedBy(world: World, x: Int, y : Int, z : Int, entity : EntityLivingBase, itemStack : ItemStack): Unit = {
    val meta = (MathHelper.floor_double((entity.rotationYaw * 4.0F / 360.0F).asInstanceOf[Double] + 0.5D) & 3) match {
      case 0 => 2
      case 1 => 5
      case 2 => 3
      case 3 => 4
    }

    // Update the block, notifying the client (flag=2)
    world.setBlockMetadataWithNotify(x, y, z, meta, 2)
  }

}
