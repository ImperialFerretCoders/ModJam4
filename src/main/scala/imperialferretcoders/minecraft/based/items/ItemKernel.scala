package imperialferretcoders.minecraft.based.items

import net.minecraft.item.{ItemStack, ItemBlock}
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.world.World
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.block.Block
import imperialferretcoders.minecraft.based.genetics.traits.{TraitTunnel, Trait}

/**
 * Kernel item, contains information regarding the traits of this kernel
 */
class ItemKernel(block: Block) extends ItemBlock(block) {
  setMaxStackSize(1)

  // Setup the trait data when the kernel is smelted/crafted
  override def onCreated(itemStack: ItemStack, world: World, player: EntityPlayer) {
    itemStack.stackTagCompound = new NBTTagCompound

    Trait.writeNBT(itemStack.stackTagCompound, "tunnel")
  }

  // Display trait data when hovering over a kernel
  override def addInformation(itemStack: ItemStack, player: EntityPlayer, list: java.util.List[_], flag: Boolean) {
    val info = list.asInstanceOf[java.util.List[String]]

    Trait.printNBT(itemStack.stackTagCompound, info)
  }
}
