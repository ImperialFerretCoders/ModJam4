package imperialferretcoders.minecraft.based.tileentities

import net.minecraft.tileentity.TileEntity
import net.minecraft.nbt.NBTTagCompound
import imperialferretcoders.minecraft.based.genetics.traits.Trait

/**
 * Tile entity for storing kernel trait data
 */
class TileEntityKernel extends TileEntity {
  var kernelTrait: String = "tunnel"
  var NBTcompound: NBTTagCompound = null

  override def writeToNBT(compound: NBTTagCompound) {
    super.writeToNBT(compound)

    Trait.writeToNBT(compound, kernelTrait)
  }

  override def readFromNBT(compound: NBTTagCompound) {
    super.readFromNBT(compound)

    NBTcompound = compound
  }
}
