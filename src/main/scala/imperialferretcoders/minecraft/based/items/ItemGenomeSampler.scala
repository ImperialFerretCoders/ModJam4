package imperialferretcoders.minecraft.based.items

import net.minecraft.item.{ItemDoor, Item, ItemStack}
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

  // todo: replace temp recipe
  val crafting = CraftingManager.getInstance
  val sampler = new ItemStack(this)
  val diamond = new ItemStack(Item.getItemById(264))
  val diamondBlock = new ItemStack(Block.getBlockFromName("diamond_block"))
  crafting.addRecipe(sampler, "x  ", " xy", " y ", 'x'.asInstanceOf[Object], diamond, 'y'.asInstanceOf[Object], diamondBlock)
  crafting.addRecipe(sampler, "  x", "yx ", " y ", 'x'.asInstanceOf[Object], diamond, 'y'.asInstanceOf[Object], diamondBlock)

  // Attempts to sample the genome of the targeted block.
  override def onItemRightClick(stack:ItemStack, world:World, player:EntityPlayer):ItemStack = {
    // todo
    return stack
  }

}
