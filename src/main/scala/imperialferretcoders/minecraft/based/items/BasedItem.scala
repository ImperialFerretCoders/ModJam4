package imperialferretcoders.minecraft.based.items

import net.minecraft.item.Item
import net.minecraft.creativetab.CreativeTabs
import cpw.mods.fml.common.registry.GameRegistry

/**
 *
 */
class BasedItem extends Item {
  setCreativeTab(CreativeTabs.tabMisc)

}

/**
 *
 */
object BasedItems {

  def init {
    // All mod items need to be listed here
    val items = Array(
      new GenomeSampler
    )

    for (item <- items) {
      GameRegistry.registerItem(item, item.getUnlocalizedName)
    }
  }

}