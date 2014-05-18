package imperialferretcoders.minecraft.based.genetics.traits

import net.minecraft.util.StatCollector
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.block.Block
import imperialferretcoders.minecraft.based.BASED
import cpw.mods.fml.common.registry.GameRegistry

/**
 *  Represents a generic trait
 */
abstract class Trait {
  // Internal name for describing the trait, used to select a localized name from lang files.
  private var unlocalizedName:String = ""

  // todo: abstract subTraits in a more type strict manner
  //val subTraitTypes = Map[String, Class[Any]]()
  protected val subTraits: Map[String, Any]

  // Write data to the NBT,
  protected def writeToNBT(compound: NBTTagCompound, namespace: String = "") {
    // Set the trait type type
    compound.setString(namespace + "type", unlocalizedName)
    // Update the namespace for this trait type
    val subNamespace = namespace + unlocalizedName + "."

    for ((subTraitName, subTrait) <- subTraits.toSeq) {
      // Handle basic subtraits
      // todo: abstract this properly
      // todo: add all nbt supported basic types
      if (subTrait.isInstanceOf[String]) {
        compound.setString(subNamespace + subTraitName, subTraits(subTraitName).asInstanceOf[String])
      } else if (subTrait.isInstanceOf[Int]) {
        compound.setInteger(subNamespace + subTraitName, subTraits(subTraitName).asInstanceOf[Int])
      } else if (subTrait.isInstanceOf[Float]) {
        compound.setFloat(subNamespace + subTraitName, subTraits(subTraitName).asInstanceOf[Float])
      } else if (subTrait.isInstanceOf[Double]) {
        compound.setDouble(subNamespace + subTraitName, subTraits(subTraitName).asInstanceOf[Double])
      }
      // Useful complex types, ie. block types
      // todo: support metadata and mod blocks
      else if (subTrait.isInstanceOf[Block]) {
        compound.setString(subNamespace + subTraitName + ".mod", "minecraft")
        // removes the tile. that is added to most blocks.
        compound.setString(subNamespace + subTraitName + ".name", subTraits(subTraitName).asInstanceOf[Block].getUnlocalizedName.substring(5))
      }
      // Finally recursively handle complex subtraits
      else if (subTrait.isInstanceOf[Trait]) {
        subTrait.asInstanceOf[Trait].writeToNBT(compound, subNamespace + subTraitName + ".")
      }
      // Warn on any unsupported subtrait classes
      else {
        BASED.logger.warn("Trait.writeToNBT: Ignoring unsupported class used by " + subNamespace + subTraitName)
      }
    }
  }

  // Print the NBT
  // todo: proper localisation
  // todo: change colours, indent, limit number of lines, etc. Something to make the item info work for large numbers of traits
  protected def printFromNBT(compound: NBTTagCompound, list: java.util.List[String], namespace: String = "") {
    // Print the type
    list.add(namespace + "type:" + unlocalizedName)
    // Update the namespace for this trait type
    val subNamespace = namespace + unlocalizedName + "."

    for ((subTraitName, subTrait) <- subTraits.toSeq) {
      // Handle basic subtraits
      // todo: abstract this properly
      // todo: add all nbt supported basic types
      if (subTrait.isInstanceOf[String]) {
        list.add(subNamespace + subTraitName + ": " + compound.getString(subNamespace + subTraitName))
      } else if (subTrait.isInstanceOf[Int]) {
        list.add(subNamespace + subTraitName + ": " + compound.getInteger(subNamespace + subTraitName))
      } else if (subTrait.isInstanceOf[Float]) {
        list.add(subNamespace + subTraitName + ": " + compound.getFloat(subNamespace + subTraitName))
      } else if (subTrait.isInstanceOf[Double]) {
        list.add(subNamespace + subTraitName + ": " + compound.getDouble(subNamespace + subTraitName))
      }
      // Useful complex types, ie. block types
      // todo: support metadata and mod blocks
      else if (subTrait.isInstanceOf[Block]) {
        val block = GameRegistry.findBlock(compound.getString(subNamespace + subTraitName + ".mod"), compound.getString(subNamespace + subTraitName + ".name"))
        if (block != null) {
          list.add(subNamespace + subTraitName + ": " + block.getLocalizedName)
        }
      }
      // Finally recursively handle complex subtraits
      else if (subTrait.isInstanceOf[Trait]) {
        subTrait.asInstanceOf[Trait].printFromNBT(compound, list, subNamespace + subTraitName + ".")
      }
      // Warn on any unsupported subtrait classes
      else {
        BASED.logger.warn("Trait.printFromNBT: Ignoring unsupported class used by " + subNamespace + subTraitName)
      }
    }
  }

  // Get a list of blocks to update when using this trait for building structures
  protected def getBlockUpdates(compound: NBTTagCompound, x: Int, y: Int, z: Int) : Seq[Tuple3[Tuple3[Int, Int, Int], Block, Int]] = null

  // Localisation
  def setUnlocalizedName(x:String) = { unlocalizedName = x }
  def getUnlocalizedName = "trait." + unlocalizedName + ".name"
  def getLocalizedName = StatCollector.translateToLocal( getUnlocalizedName)

}

object Trait {
  // All the main traits - the ones that can be used as main kernel traits
  val traits = Map[String, Trait](
    "tunnel" -> new TraitTunnel
  )

  // Update the given NBT
  def writeToNBT(compound: NBTTagCompound, traitClass: String, extraMetaData: Map[String, Int]) {
    // write any extra data - this is hacky!!! used for hardcoding meta data
    for ((key, meta) <- extraMetaData) {
      compound.setInteger(key, meta)
    }

    if (compound != null && traits.contains(traitClass)) {
      traits(traitClass).writeToNBT(compound)
    }
  }

  // Print out the NBT entries to a list, where each string in the list is a different line
  def printFromNBT(compound: NBTTagCompound, list: java.util.List[String]) {
    if (compound != null && compound.hasKey("type")) {

      val traitClass = compound.getString("type")
      if (traits.contains(traitClass)) {
        traits(traitClass).printFromNBT(compound, list)
      }
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
