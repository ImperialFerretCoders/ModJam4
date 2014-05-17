package imperialferretcoders.minecraft.based.genetics

class Promoter extends Gene {
  //Determines if the gene coming after this one in the trait is active. Defaults to true.
  def IsGeneActive(state:OrganismState):Boolean = true
}
