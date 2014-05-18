package imperialferretcoders.minecraft.based

import cpw.mods.fml.common.registry.GameRegistry
import imperialferretcoders.minecraft.based.items.ItemKernel

/**
 * Contains all the recipes, did I mention that this was a terrible plan?
 */
object BasedRecipes {

  def init {
    // Grab item stacks for the various crafting inputs/outputs
    val algae             = GameRegistry.findItemStack("based", "tile.algae", 1)
    //val genomeSampler   = GameRegistry.findItemStack("based", "item.genomeSampler", 1)
    //val petridish       = GameRegistry.findItemStack("based", "item.petridish", 1)
    val kernel_oak        = GameRegistry.findItemStack("based", "tile.kernel", 1)
    val kernel_spruce     = GameRegistry.findItemStack("based", "tile.kernel", 1)
    val kernel_birch      = GameRegistry.findItemStack("based", "tile.kernel", 1)
    val kernel_jungle     = GameRegistry.findItemStack("based", "tile.kernel", 1)
    val kernel_acacia     = GameRegistry.findItemStack("based", "tile.kernel", 1)
    val kernel_big_oak    = GameRegistry.findItemStack("based", "tile.kernel", 1)

    kernel_oak.setItemDamage(0)
    kernel_spruce.setItemDamage(1)
    kernel_birch.setItemDamage(2)
    kernel_jungle.setItemDamage(3)
    kernel_acacia.setItemDamage(4)
    kernel_big_oak.setItemDamage(5)

    // Grab vanilla minecraft item stacks
    //val diamond         = GameRegistry.findItemStack("minecraft", "diamond", 1)
    //val diamondBlock    = GameRegistry.findItemStack("minecraft", "diamond_block", 1)
    //val glassPane       = GameRegistry.findItemStack("minecraft", "glass_pane", 1)
    val log_oak           = GameRegistry.findItemStack("minecraft", "log", 1)
    val log_spruce        = GameRegistry.findItemStack("minecraft", "log", 1)
    val log_birch         = GameRegistry.findItemStack("minecraft", "log", 1)
    val log_jungle        = GameRegistry.findItemStack("minecraft", "log", 1)
    val log_acacia        = GameRegistry.findItemStack("minecraft", "log", 1)
    val log_big_oak       = GameRegistry.findItemStack("minecraft", "log", 1)

    log_oak.setItemDamage(0)
    log_spruce.setItemDamage(1)
    log_birch.setItemDamage(2)
    log_jungle.setItemDamage(3)
    log_acacia.setItemDamage(4)
    log_big_oak.setItemDamage(5)

    // format is (ItemToBeCrafted, 9row1 requirements (space for empty, char for item), row2, row3, [char for t, Item]+))
    val shapedRecipes = Array(
      //(genomeSampler, ("x  ", " xy", " y ", 'x', diamond, 'y', diamondBlock)),
      //(genomeSampler, ("  x", "yx ", " y ", 'x', diamond, 'y', diamondBlock)),
      //(petridish, ("xyx", "yyy", "xyx", 'x', glassPane, 'y', algae))
      (kernel_oak, ("xxx", "xyx", "xxx", 'x', algae, 'y', log_oak)),
      (kernel_spruce, ("xxx", "xyx", "xxx", 'x', algae, 'y', log_spruce)),
      (kernel_birch, ("xxx", "xyx", "xxx", 'x', algae, 'y', log_birch)),
      (kernel_jungle, ("xxx", "xyx", "xxx", 'x', algae, 'y', log_jungle)),
      (kernel_acacia, ("xxx", "xyx", "xxx", 'x', algae, 'y', log_acacia)),
      (kernel_big_oak, ("xxx", "xyx", "xxx", 'x', algae, 'y', log_big_oak))
    )

    // format is (ItemToBeCrafted, ([items]+))
    //val shapelessRecipes = Array(
    //  (kernel, (algae, algae, algae, algae, algae, algae, algae, algae, algae))
    //)

    // Register the recipes. Input crafting vars are forcefully passed as objects - for language compat reasons.
    for (recipe <- shapedRecipes) {
      GameRegistry.addShapedRecipe(recipe._1, recipe._2.productIterator.toSeq.toArray.asInstanceOf[Array[Object]]: _*)
    }

    //for (recipe <- shapelessRecipes) {
    //  GameRegistry.addShapelessRecipe(recipe._1, recipe._2.productIterator.toSeq.toArray.asInstanceOf[Array[Object]]: _*)
    //}
  }

}
