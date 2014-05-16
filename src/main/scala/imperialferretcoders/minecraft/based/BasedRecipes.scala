package imperialferretcoders.minecraft.based

import cpw.mods.fml.common.registry.GameRegistry

/**
 * Contains all the recipes, did I mention that this was a terrible plan?
 */
object BasedRecipes {

  def init {
    // Grab item stacks for the various crafting inputs/outputs
    val algae           = GameRegistry.findItemStack("based", "tile.algae", 1)
    val genomeSampler   = GameRegistry.findItemStack("based", "item.genomeSampler", 1)
    val petridish       = GameRegistry.findItemStack("based", "item.petridish", 1)

    // Grab vanilla minecraft item stacks
    val diamond         = GameRegistry.findItemStack("minecraft", "diamond", 1)
    val diamondBlock    = GameRegistry.findItemStack("minecraft", "diamond_block", 1)
    val glassPane       = GameRegistry.findItemStack("minecraft", "glass_pane", 1)

    // format is (new ItemToBeCrafted, 9row1 requirements (space for empty, char for item), row2, row3, [char for t, Item]+))
    val shapedRecipes = Array(
      (genomeSampler, ("x  ", " xy", " y ", 'x', diamond, 'y', diamondBlock)),
      (genomeSampler, ("  x", "yx ", " y ", 'x', diamond, 'y', diamondBlock)),
      (petridish, ("xyx", "yyy", "xyx", 'x', glassPane, 'y', algae))
    )

    // format is (new ItemToBeCrafted, ([items]+))
    //val shapelessRecipes = Array(

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
