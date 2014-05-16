package imperialferretcoders.minecraft.based.items

import net.minecraft.item.{Item, ItemStack}
import net.minecraft.world.World
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.crafting.CraftingManager
import net.minecraft.block.Block

/**
 * Genome sampler item - used to sample the genome of various blocks/sources.
 */
class ItemGenomeSampler extends Item {
  setUnlocalizedName("genomeSampler")
  setMaxStackSize(1)
  setTextureName("based:genomeSampler")
  setCreativeTab(CreativeTabs.tabMisc)

  // Attempts to sample the genome of the targeted block.
  override def onItemRightClick(stack:ItemStack, world:World, player:EntityPlayer):ItemStack = {
    // todo
    return stack
  }

}
