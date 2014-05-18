package imperialferretcoders.minecraft.based.genetics

import net.minecraft.util.StatCollector

class GeneticTrait {
  // Internal name for describing the trait, used to select a localized name from lang files.
  protected var unlocalizedName:String = ""
  // Whitelisted by necessity
  var validGenes:List[Gene] = List()
  // Whitelisted by necessity
  var validPromoters:List[Promoter] = List()

  def AddValidGene(g:Gene) = {
    validGenes = validGenes ::: List(g)
  }
  def AddValidPromoter(p:Promoter) = {
    validPromoters = validPromoters ::: List(p)
  }

  def IsPromoterValid(p:Promoter):Boolean = validPromoters.contains(p)
  def IsGeneValid(g:Gene):Boolean = validGenes.contains(g)

  // Localisation
  def setUnlocalizedName(x:String) = { unlocalizedName = x }
  def getUnlocalizedName = "gene." + unlocalizedName + ".name"
  def getLocalizedName = StatCollector.translateToLocal( getUnlocalizedName)

}
