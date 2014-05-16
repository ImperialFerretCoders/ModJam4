package imperialferretcoders.minecraft.based.genetics

import collection.mutable.Map
import collection.mutable.HashMap

class OrganismState {
  //A mapping of (unlocalized) internal resource names to amounts. Arbitrary unit.
  protected var chemistry: Map[String, Int] = new HashMap()

  def getResourceAmount(name: String): Int = {
    if (chemistry.contains(name)) {
      return chemistry(name)
    }
    else {
      return 0
    }
  }

  def setResourceAmount(name: String, amt: Int) = {
    chemistry(name) = amt
  }

  def alterResourceAmount(name: String, amt: Int) = {
    if (chemistry.contains(name)) {
      chemistry(name) += amt
    }
  }
}
