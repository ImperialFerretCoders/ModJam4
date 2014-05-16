package imperialferretcoders.minecraft.based.items

import net.minecraft.item.{ItemStack, Item}
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.crafting.CraftingManager
import net.minecraft.block.Block

/**
 * Petri dish item, for storing bio stuff.
 */
class ItemPetridish extends Item {
  setUnlocalizedName("petridish")
  setMaxStackSize(1)
  setTextureName("based:petridish")
  setCreativeTab(CreativeTabs.tabMisc)

}
