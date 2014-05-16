package imperialferretcoders.minecraft.based.tileentities

import net.minecraft.tileentity.TileEntity

import imperialferretcoders.minecraft.based.genetics.{BlockGenome, OrganismState}

class TileEntityStem extends TileEntity {
  protected var orgState: OrganismState = OrganismState()
  //Must be assigned later.
  protected var genome: BlockGenome = null();
}
