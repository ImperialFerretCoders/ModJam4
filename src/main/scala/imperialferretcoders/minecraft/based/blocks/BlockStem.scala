package imperialferretcoders.minecraft.based.blocks

import net.minecraft.block.BlockContainer
import net.minecraft.block.material.Material
import net.minecraft.world.World
import net.minecraft.tileentity.TileEntity
import imperialferretcoders.minecraft.based.tileentities.TileEntityStem

class BlockStem(mat: Material) extends BlockContainer(mat) {
  override def createNewTileEntity(world: World, meta: Int): TileEntity = {
    return new TileEntityStem()
  }
}
