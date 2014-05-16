package imperialferretcoders.minecraft.based.genetics

class Gene {

  protected var unlocalizedName: String = ""
  protected var sequence: String = ""

  /*
    Returns a string consisting of the gene's information, as the base pairs A, C, T, and G.
    May or may not be used depending on how badly we want to avoid writing GUI code.
   */
  def getCodingSequence(): String = {
    return sequence
  }

  /*
    Returns the internal name of the gene, to be localized via .lang files.
   */
  def getUnlocalizedName(): String = {
    return unlocalizedName
  }

  def setCodingSequence(seq: String) = {
    sequence = seq
  }

  def setUnlocalizedName(name: String) = {
    unlocalizedName = name
  }
}
