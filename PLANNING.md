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

##Implementation of traits 
Need a way to feed information from a genome made up of discrete, hierarchical trait data to the various blocks or
tile entities that compose the organism. This is the part where I wish I had dabbled in demoscene stuff.

* Axes of symmetry absolutely have a place in biology so it wouldn't be against the theme to include something like that.
* Tile entity decides to grow, per-direction, based upon distance from axis of symmetry and genes particular to it?
  * No, bio-staircase would be impossible under this. Too simplistic mechanically.
* Take the concept of stem-cell /tissue differentiation, dumb it down loads, and use that to control growth...?
  * Each stem cell tile entity, on grown, reports its existence to the organism controller
  * The organism controller class, much like any multiblock controller, knows where each tile entity making up 
    the MB is present
  * It also knows where and with which direction the origin block (i.e. seed / kernel) was placed.
  * The organism controller class, then, calls a function to set a boolean on the growing block tile entity, informing
    it that, next growth tick, it should try to grow tissue type x in y direction.
  * Alternatively, it can signal one type of living block to become another type of living block.
  * Things such as "Wait a second, this thing can't grow into stone" or "looks like we have an error rate so fuck you
    we're growing in another direction instead" or "time for this stem cell block to turn into a log block" are then
    written at tile-entity level rather than at controller level.
  * Each shape has its own subclass of the organism controller class, and the internal logic of that class determines
    the shape that is grown through signalling the growing blocks to grow in a direction.
    
###Example: Tunnel
First, the seed becomes the first growing block (type: goo pancake) and the organism controller class is initialized.
Every tick of growth logic, the organism controller has each of its goo pancake blocks try to grow in every direction along
the plane perpendicular to the seed block's original orientation, checking to see if growing in that direction would put
it at the edge of the radius. (The radius is controlled by the "growth control" genes under the "tunnel radius"
trait. "Tunnel Radius" only accepts growth control genes (and won't accept "growth control - infinite") and growth hormone genes.
Growth hormone genes determine the speed of growth of the trait.)

When the next block to be grown is at the edge of the radius, the block grown is a live tunnel wall block (LTWB for this
example) instead of the initial goo pancake. The goo pancake blocks slowly die off. Any LTWBs are informed by the 
organism controller to try to grow in a direction parallel to seed's initial facing direction. If "Tunnel length" has 
"Growth Control - Infinite", this just keeps happening. Otherwise, a length defined by growth control genes sets a limit
beyond which the tunnel wall blocks cannot grow. LTWBs have a death timer, and when they reach the end of that death
timer they become inactive blocks (and thus less laggy).

Which blocks they become is tricky since so far this system has been simple, but in order to have an even remotely
interesting system for making organic structures you'd need to be able to have conditionals so it isn't all one material
all over. Enter, the promoter.

The first active gene or combination of genes under the "tissue" trait is checked at differentiation time and used to
select a block for the growing block to become. For example, having no genes produces a disgusting gelatinous cube
(alternate slime-ball source?). Keratin gives you a skin-covered flesh block. The bone gene gives you a bone block.
Bone + Keratin gives you a horn block. Cellulose gives you a wood block. Luciferase gives you a glowing goo block.
Chlorophyll gives you leaf blocks. Cellulose + Luciferase gives you glowing wood. Etc.

What determines which gene is active is where promoters come back into the picture. A promoter with a gene after it
controls whether that gene is expressed. A gene with no promoter is assumed to be always expressed. More complex stuff
can come later but to start we could, for example, have this: 
Tissue:
    Promoter (random chance 10%)
    Luciferase
    Coral
And then the wall blocks would become glowing goo (or glowing coral?) 10% of the time on becoming inactive and the rest
of the time become coral blocks.

###Future plans
More complex structures could be made later by having an "end block tissue" trait and then having, say, genes that are
referential to entire other genomes, so the end block of a tunnel would be the seed for a dome or something like that.

To have different behaviors for floors, ceilings, etc, there could be promoters that check the Organism Controller's 
data on the seed's position at spawn time, and so on a tunnel
Tissue:
    Promoter (below seed Y)
    Die
would produce a semicircular roof. 

###Regrowth? 
Perhaps, with the organism controller knowing where each block of the organism HAD been, healing mechanics could be
implemented through it?