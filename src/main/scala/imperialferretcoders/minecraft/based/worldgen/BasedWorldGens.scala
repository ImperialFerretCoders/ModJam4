package imperialferretcoders.minecraft.based.worldgen

import cpw.mods.fml.common.registry.GameRegistry

/**
 * Initialise the world generators
 */
object BasedWorldGens {
  def init  {
    // All mod world generators need to be listed here
    val worldgens = Array(
      new WorldGenAlgae
    )

    for (worldgen <- worldgens) {
      // 10 = mod weight, means that the generation will happen later than mods with lower weights
      GameRegistry.registerWorldGenerator(worldgen, 10)
    }
  }
}
