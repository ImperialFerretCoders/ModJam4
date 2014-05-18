package imperialferretcoders.minecraft.based.genetics.traits

import net.minecraft.util.StatCollector

/**
 *  Represents a generic trait - the parent of all trait classes
 */
class Trait {
  // Internal name for describing the trait, used to select a localized name from lang files.
  protected var unlocalizedName:String = ""

  // Localisation
  def setUnlocalizedName(x:String) = { unlocalizedName = x }
  def getUnlocalizedName = "gene." + unlocalizedName + ".name"
  def getLocalizedName = StatCollector.translateToLocal( getUnlocalizedName)

}
