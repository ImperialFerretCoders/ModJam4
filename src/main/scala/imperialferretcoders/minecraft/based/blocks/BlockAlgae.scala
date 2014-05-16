package imperialferretcoders.minecraft.based.blocks

import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.block.{Block, BlockBush}
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.world.{IBlockAccess, World}
import net.minecraft.init.Blocks
import net.minecraft.block.material.Material
import net.minecraftforge.common.EnumPlantType
import java.util.Random
import imperialferretcoders.minecraft.based.BASED

/**
 * Algae block, based off the vanilla lily pad.
 */
class BlockAlgae extends BlockBush {
  setBlockName("algae")
  setBlockTextureName("based:algae")
  setCreativeTab(CreativeTabs.tabMisc)
  setHardness(0.0F)
  setStepSound(Block.soundTypeGrass)

  // Randomly activate this block for algae growth
  val doesTick = BASED.config.get("Features", "Natural algae spread", true).getBoolean(true)
  setTickRandomly(doesTick)

  // Bounding box for harvesting
  setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.015625F, 1.0F)

  // Sets the render type of the block to the same as lilypad, so that the texture is rendered as sitting on the surface of the block below
  override def getRenderType: Int = {
    return 23
  }

  // Only allow algae to be placed on water
  override def canPlaceBlockOn(block: Block): Boolean = {
    block == Blocks.water
  }

  // Harvest algae if the water below is removed
  override def canBlockStay(world: World, x: Int, y: Int, z: Int): Boolean = {
    // bad constants
    y >= 0 && y < 256 && world.getBlock(x, y - 1, z).getMaterial == Material.water && world.getBlockMetadata(x, y - 1, z) == 0
  }

  // Set algae to have the same plant type as lilypads
  override def getPlantType(world: IBlockAccess, x: Int, y: Int, z: Int): EnumPlantType = {
    EnumPlantType.Water
  }

  // Handle algae growth
  // Currently algae has a chance to expand to an adjacent block every random tick, which occur on average every ~68s.
  // Each block also has growth stages, which determine the texture/colour of the algae block.
  override def updateTick(world: World, x: Int, y: Int, z: Int, random: Random): Unit = {
    super.updateTick(world, x, y, z, random)

    // Ensure that the algae is properly lit - it requires photosynthesis to grow properly
    if (world.getBlockLightValue(x, y + 1, z) >= 9) {
      val growth: Int = world.getBlockMetadata(x, y, z)

      // 20% chance of growth - could do this but meh
      if (true) {
        //random.nextInt(5) == 0) {
        // Update the algae block, informing the client (last arg set to 2)
        if (growth < 5) {
          world.setBlockMetadataWithNotify(x, y, z, growth + 1, 2)
        }

        if (true) {
          //growth > 2) {
          // Choose a random adjacent block to expand into
          val xz = (random.nextInt(4)) match {
            case 0 => (x + 1, z)
            case 1 => (x - 1, z)
            case 2 => (x, z + 1)
            case 3 => (x, z - 1)
          }

          // Update the adjacent block if it's a valid location for algae to grow
          if (world.getBlock(xz._1, y - 1, xz._2).getMaterial == Material.water && world.getBlockMetadata(xz._1, y - 1, xz._2) == 0 && world.isAirBlock(xz._1, y, xz._2)) {
            world.setBlock(xz._1, y, xz._2, GameRegistry.findBlock("based", "tile.algae"))
          }
        }
      }
    }
  }
}
