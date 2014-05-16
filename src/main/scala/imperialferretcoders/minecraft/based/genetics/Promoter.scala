package imperialferretcoders.minecraft.based.genetics

class Promoter extends Gene {
  def canAffect(state: OrganismState): Boolean = {
    //Defaults to true.
    return true;
  }
}
