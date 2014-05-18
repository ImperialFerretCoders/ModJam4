package imperialferretcoders.minecraft.based.items

import net.minecraft.item.{ItemBlock, ItemStack}
import net.minecraft.util.MovingObjectPosition
import net.minecraft.block.material.Material
import net.minecraft.world.World
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.block.Block

/**
 * Algae item, based off the Lilypad item.
 */
class ItemAlgae(block: Block) extends ItemBlock(block) {

  /**
   * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
   */
  override def onItemRightClick(itemStack: ItemStack, world: World, entity: EntityPlayer): ItemStack = {
    val movingobjectposition: MovingObjectPosition = this.getMovingObjectPositionFromPlayer(world, entity, true)

    if (movingobjectposition == null) {
      return itemStack
    }
    else {
      if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
        val x: Int = movingobjectposition.blockX
        val y: Int = movingobjectposition.blockY
        val z: Int = movingobjectposition.blockZ

        if (!world.canMineBlock(entity, x, y, z)) {
          return itemStack
        }

        if (!entity.canPlayerEdit(x, y, z, movingobjectposition.sideHit, itemStack)) {
          return itemStack
        }

        if (world.getBlock(x, y, z).getMaterial == Material.water && world.getBlockMetadata(x, y, z) == 0 && world.isAirBlock(x, y + 1, z)) {
          world.setBlock(x, y + 1, z, block, 1, 2)

          if (!entity.capabilities.isCreativeMode) {
            itemStack.stackSize -= 1
          }
        }
      }

      return itemStack
    }
  }

}
