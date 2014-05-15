#Development Plan

##Brainstorming
- Trait system
    - Spatial properties (size, distance, shape/form, wormholes, etc.), structural properties (what it's made of), functional
    properties (growth rate, resource consumption multipliers, regeneration capabilities, materials it can grow
    "through", etc.)
    - Energy/resource consumption as basic properties of traits - more traits == more expensive to "run"
    - "Energy/resource" actually probably be some sort of "food"
- How does it spread, visually? 
    - Thaumcraft has some fun stuff with taint, might want to take some inspiration from there
    - Tendrils?
        - Would these be like redstone, as a separate block placed on top of the existing structre? Downside is they
        take up space
        - Alternatively they could be coded as a flag/metadata on our existing structural blocks
        - Tendrils could spread by deploying "active"/small tendrils into a block, first destroying the existing block
        there (if not air), and then slowly filling up the space inside with tendril-matter, then the outer husk
        falling/breaking away to reveal final block
        - "Active"/small tendrils would need to be connected back to trunk/core/
- Simple vs compound/designed kernels
    - Simple kernels just have one shape trait, e.g. tunnel, cube, pyramid
        - They can have as many other traits as their shape trait supports
        - Should be relatively easy to make / early-game, don't require any complex interface to put together - possibly
        can just be crafted directly
    - Compound kernels can have many (arbitrary amount?) shape/form traits, and can compine them together to form "maps"
    of complex structures
        - Will probably need some sort of design/map interface on a machine, which will let you place shape components
        from a "library" of the types you have access + set direction/size/distance/function/etc. trait settings on them
        - In such a way you can design the final structure produced, then supply the required trait items to the machine
        to produce the compound kernel
- Directionality
    - Compound kernels are designed in an internal 'map', N/E/S/W + up & down are valid directions in this space
    - When you place a kernel but before you catalyze it/start it/initiate growth you can set which in-world direction
    should be treated as the kernel's "internal north", growth will proceed relative to that
    - Simple kernels will just grow in the direction of the set "internal north"

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

1. Add kernel block
2. Make it place wood in a single block line to some distance
3. Make it place wood in a tunnel shape to some distance
4. Tendril mechanics (TBD)

###Agar
1. Add algae block (get art resources from Bandeross - probably not going to be up for a few more hours though)
2. Make it spread/make it a plant
3. Make it craftable in some way to agar
