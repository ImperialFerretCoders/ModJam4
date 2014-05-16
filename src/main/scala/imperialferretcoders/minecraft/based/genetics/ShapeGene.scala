package imperialferretcoders.minecraft.based.genetics

/*
 * A gene to help define the shape of a multi-voxel structure.
 */
class ShapeGene extends Gene {
  //Contribution to end-value radius, length, etc. of the shape promoter feature our gene is under.
  protected var growthMagnitude: Int = 0

  def getGrowthMagnitude(): Int = growthRateContrib

  def setGrowthMagnitude(x: Int) = {
    growthRateContrib = x
  }

  //Contribution to total growth rate of organism
  protected var growthRate: Int = 0

  def getGrowthRate(): Int = growthRateContrib

  def setGrowthRate(x: Int) = {
    growthRateContrib = x
  }
}
