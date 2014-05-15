package imperialferretcoders.minecraft.based.items

import net.minecraft.item.ItemStack
import net.minecraft.world.World
import net.minecraft.entity.player.EntityPlayer

//import net.minecraft.item.crafting.CraftingManager

/**
 * Genome sampler item - used to sample the genome of various blocks/sources.
 */
class GenomeSampler extends BasedItem {
  setUnlocalizedName("genomeSampler")
  setMaxStackSize(1)
  setTextureName("based:genomeSampler")

  // todo: add a crafting recipe for this item
  //val crafting = CraftingManager.getInstance
  // crafting.addRecipe()

  // Attempts to sample the genome of the targeted block.
  override def onItemRightClick(stack:ItemStack, world:World, player:EntityPlayer):ItemStack = {
    // todo
    return stack
  }

}
