package imperialferretcoders.minecraft.based.genetics.traits

import net.minecraft.util.StatCollector
import net.minecraft.nbt.NBTTagCompound

/**
 *  Represents a generic trait
 */
class Trait {
  // Internal name for describing the trait, used to select a localized name from lang files.
  protected var unlocalizedName:String = ""

  //val subTraits = Map[String, Trait]()

  // Write data to the NBT,
  protected def writeNBT(compound: NBTTagCompound, namespace: String = "") { }

  // Print the NBT
  protected def printNBT(compound: NBTTagCompound, namespace: String = "", list: java.util.List[String]) { }

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
  def writeNBT(compound: NBTTagCompound, traitClass: String) {
    if (compound != null) {
      val namespace = traitClass + "."
      compound.setString("type", traitClass)

      traits(traitClass).writeNBT(compound, namespace)
    }
  }

  // Print out the NBT entries to a list, where each string in the list is a different line
  def printNBT(compound: NBTTagCompound, list: java.util.List[String]) {
    if (compound != null && compound.hasKey("type")) {
      val name = compound.getString("type")
      val t = traits(name)

      list.add("Type: " + t.getLocalizedName)

      t.printNBT(compound, name + ".", list)
    }
  }

  // Get a list of blocks that will require updating from an NBT entry
  def getUpdateBlocks(compound: NBTTagCompound) {
    /*if (compound != null && compound.hasKey("type")) {
      val namespace = compound.getString("type")
      val t = traits(namespace)

      list.add("Type: " + t.getLocalizedName)

      traits(compound.getString("type")).printNBT(compound, namespace)
    }*/
  }

}
