package imperialferretcoders.minecraft.based.worldgen

import net.minecraft.world.gen.feature.WorldGenerator
import net.minecraft.world.World
import java.util.Random
import cpw.mods.fml.common.registry.GameRegistry
import cpw.mods.fml.common.IWorldGenerator
import net.minecraft.world.chunk.IChunkProvider

/**
 * Generates algae growing on top of water, based on WorldGenWaterlily with algae roughly 10 times less likely to appear
 */
class WorldGenAlgae extends IWorldGenerator {

  def generate(random: Random, chunkX: Int, chunkY: Int, world: World, chunkGen: IChunkProvider, chunkProvider: IChunkProvider) = {
    // 2% chance of an algae block per chunk, assuming that the chosen block is a valid location for an algae block
    if (random.nextInt(50) == 0) {
      val i = chunkX * 16 + random.nextInt(16)
      val j = 63 // just above water level
      val k = chunkY * 16 + random.nextInt(16)

      val blockAlgae = GameRegistry.findBlock("based", "tile.algae")

      if (world.isAirBlock(i, j, k) && blockAlgae.canPlaceBlockAt(world, i, j, k)) {
        // 2 => inform the client
        world.setBlock(i, j, k, blockAlgae, 0, 2)
      }
    }
  }
}
