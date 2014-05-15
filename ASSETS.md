#Notes on Required Assets & Aesthetics/Visuals

- Algae - Block, same physical size ingame as lilypads, similar on-water placement
- Agar - Item, made from algae, used in crafting processes for synthetic organisms
- Tendrils
    - Trunk tendrils that would probably be 50-75% of the size of a normal block, can connect ala cables, would have
    connected textures
    - Active/small tendrils, probably be 10~20% size of a normal block, can connect ala cables (also to trunk tendrils),
    would have connected textures
    - Small tendrils will also need growth states - trunk tendril extends a small tendril into empty space, which then
    gets bigger (first to the size of active tendril, then to size of trunk tendril)
    - Active & trunk tendrils will also need "construction" states - tendrils in empty space will fill the space around
    them with tendril-matter until full, then crack and fall away to reveal final block
    - Will also need de/construction states for tendrils extending into non-empty space, can probably just apply
    decals/overlays on top of the texture of the block that is actually there, at least for simple blocks, so that you
    see tendrils "breaking through" and slowly expanding across the block