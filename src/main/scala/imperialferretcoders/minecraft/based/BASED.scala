package imperialferretcoders.minecraft.based


import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import cpw.mods.fml.common.event.FMLPostInitializationEvent
import cpw.mods.fml.common.event.FMLInitializationEvent
import imperialferretcoders.minecraft.based.items.BasedItems
import imperialferretcoders.minecraft.based.blocks.BasedBlocks

import net.minecraftforge.common.config.Configuration

@Mod(modid = "based", name = "Bio-Adaptive Synthetic Environment Deployment", version = "0.0.0", modLanguage = "scala")
object BASED {
  var logger = null
  //LogManager.getLogger("BASED")
  var config: Configuration = null

  @Mod.EventHandler
  def PreInit(event: FMLPreInitializationEvent) {
    logger = event.getModLog()
    this.logger.info("Loading BASED")
    config = new Configuration(event.getSuggestedConfigurationFile)
  }

  @Mod.EventHandler
  def init(event: FMLInitializationEvent) {
    this.logger.info("Initializing blocks...")
    BasedBlocks.init
    this.logger.info("Initializing items...")
    BasedItems.init
    this.logger.info("Initializing recipes...")
    BasedRecipes.init

  }

  @Mod.EventHandler
  def postInit(event: FMLPostInitializationEvent) {

  }
}