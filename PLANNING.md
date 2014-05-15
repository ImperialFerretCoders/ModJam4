#Development Plan

##Brainstorming
- Trait system - what the fuck do these guys even mean by this?
    - Spatial properties (size, distance, shape, wormholes, etc.), structural properties (what it's made of), functional
    properties (growth rate, resource consumption multipliers, regeneration capabilities, materials it can grow
    "through", etc.)
    - Energy/resource consumption as basic properties of traits - more traits == more expensive to "run"
    - "Energy/resource" actually probably be some sort of "food" - agar slurry :P?
        - Need algae added to craft this with
- How does it spread, visually? 
    - Thaumcraft has some fun stuff with taint, might want to take some inspiration from there
    - Tendrils?
        - Would these be like redstone, as a separate block placed on top of the existing structre? Downside is they
        take up space
        - Alternatively they could be coded as a flag/metadata on our existing structural blocks
        
##Traits
- Distance (spatial)
- Lighting (functional), not sure on details for this exactly

###Shape Traits
- Tunnel/Corridor - hollow cylindrical shape, interacts with/accepts various other traits:
    - Distance -> Length (0-length specifies "just keep growing until you are out of resources"?)
    - Distance -> Outer width
    - Distance -> Inner width
    - Lighting (possibly both inner & outer wall options)
    - Structural modifiers (for gaps or windows? for extra-strong materials etc.)
    
    
##Timeline/Incremental Steps

###Tunnel/Corridor Trait
Want to start this separately to the trait system because working on individual traits/effects is more interesting than
working on trait system itself to start with

1. Add core/seed/kernel block
2. 

###Agar
1. Add algae block (get art resources from Bandeross - probably not going to be up for a few more hours though)
2. Make it spread/make it a plant
3. Make it craftable in some way to agar
