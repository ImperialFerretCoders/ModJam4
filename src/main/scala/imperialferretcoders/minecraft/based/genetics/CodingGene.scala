package imperialferretcoders.minecraft.based.genetics

//Codes for a protein, has behavior.
class CodingGene extends Gene {
  //Resource name & cost amount list.
  protected var costs: List[(String, Int)] = List()

  def TickLogic(state: OrganismState) = {}

  //On organism spawn
  def GrowLogic(state: OrganismState) = {}

  //Resources required to manufacture this trait in the organism at grow time.
  def getCosts(): List[(String, Int)] = return costs

  def setCosts(setTo: List[(String, Int)]) = {
    costs = setTo;
  }
}
