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

    // todo: make this so it doesn't overwrite the block meta data
    Trait.writeToNBT(compound, kernelTrait, Map())
  }

  override def readFromNBT(compound: NBTTagCompound) {
    super.readFromNBT(compound)

    NBTcompound = compound
  }
}
