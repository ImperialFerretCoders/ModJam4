package imperialferretcoders.minecraft.based.genetics.traits

import net.minecraft.util.StatCollector
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.block.Block

/**
 *  Represents a generic trait
 */
class Trait {
  // Internal name for describing the trait, used to select a localized name from lang files.
  private var unlocalizedName:String = ""

  //val subTraits = Map[String, Trait]()

  // Write data to the NBT,
  protected def writeToNBT(compound: NBTTagCompound, namespace: String = "") { }

  // Print the NBT
  protected def printFromNBT(compound: NBTTagCompound, namespace: String = "", list: java.util.List[String]) { }

  // Get a list of blocks to update when using this trait for building structures
  protected def getBlockUpdates(compound: NBTTagCompound, x: Int, y: Int, z: Int) : Seq[Tuple3[Tuple3[Int, Int, Int], Block, Int]] = null

  // Localisation
  def setUnlocalizedName(x:String) = { unlocalizedName = x }
  def getUnlocalizedName = "trait." + unlocalizedName + ".name"
  def getLocalizedName = StatCollector.translateToLocal( getUnlocalizedName)

}

object Trait {
  // All the traits
  val traits = Map[String, Trait](
    "tunnel" -> new TraitTunnel
  )

  // Update the given NBT
  def writeToNBT(compound: NBTTagCompound, traitClass: String) {
    if (compound != null) {
      val namespace = traitClass + "."
      compound.setString("type", traitClass)

      traits(traitClass).writeToNBT(compound, namespace)
    }
  }

  // Print out the NBT entries to a list, where each string in the list is a different line
  def printFromNBT(compound: NBTTagCompound, list: java.util.List[String]) {
    if (compound != null && compound.hasKey("type")) {
      val name = compound.getString("type")
      val t = traits(name)

      list.add("Type: " + t.getLocalizedName)

      t.printFromNBT(compound, name + ".", list)
    }
  }

  // Get a list of blocks that will require updating from an NBT entry
  def getBlockUpdates(compound: NBTTagCompound, x: Int, y: Int, z: Int): Seq[Tuple3[Tuple3[Int, Int, Int], Block, Int]] = {
    if (compound != null && compound.hasKey("type")) {
      return traits(compound.getString("type")).getBlockUpdates(compound, x, y, z)
    } else {
      return null
    }
  }
}
