package imperialferretcoders.minecraft.based.genetics

class Gene {
  //Internal name for describing the gene, used to select a localized name from lang files.
  protected var unlocalizedName:String = ""
  //A unique string of the characters A, C, T, and G, identifying this gene from others.
  protected var sequence:String = ""

  def IsValidChar(c:Character):Boolean = c match {
    case 'A' => true
    case 'T' => true
    case 'C' => true
    case 'G' => true
    case _ => false
  }
  //Make sure the sequence consists of A, C, T, and G.
  def ValidateSequence(seq:String):Boolean = {
    seq.foreach( (c:Char) => {
      if(!IsValidChar(c)) {
        return false
      }
    } )
    return true
  }

  def setUnlocalizedName(x:String) = {unlocalizedName = x}
  def getUnlocalizedName = unlocalizedName
  def setSequence(x:String) = {
    if(ValidateSequence(x)) {
      sequence = x
    }
  }
  def getSequence = sequence
}
