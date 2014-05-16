package imperialferretcoders.minecraft.based


import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import cpw.mods.fml.common.event.FMLPostInitializationEvent
import cpw.mods.fml.common.event.FMLInitializationEvent
import org.apache.logging.log4j.LogManager
import imperialferretcoders.minecraft.based.items.BasedItems
import imperialferretcoders.minecraft.based.blocks.BasedBlocks


@Mod(modid = "based", name = "Bio-Adaptive Synthetic Environment Deployment", version = "0.0.0", modLanguage = "scala")
object BASED {
  val logger = LogManager.getLogger("BASED")

  @Mod.EventHandler
  def PreInit(event: FMLPreInitializationEvent) {
    this.logger.info("Loading BASED")

  }
  @Mod.EventHandler
  def init(event: FMLInitializationEvent) {
    BasedBlocks.init
    BasedItems.init
    BasedRecipes.init

  }
  @Mod.EventHandler
  def postInit(event: FMLPostInitializationEvent) {

  }
}