# Introducing keepInventory!
**keepInventory** is a modern Minecraft plugin for PaperMC servers designed to toggle inventory retention rules for individual players.

### Available commands:
- keepInventory on -> Enable keepInventory for yourself
- keepInventory on [player] -> Enable keepInventory for the player
- keepInventory off -> Disable keepInventory for yourself
- keepInventory off [player] -> Disable keepInventory for the player
- keepInventory check -> Check if keepInventory is enabled for yourself
- keepInventory check <player> -> Check if keepInventory is enabled for the player

### Permissions:
- keepinventory.command.self -> Permission to use /keepInventory
- keepinventory.command.other -> Permission to use /keepInventory for others

---

### For developers
The plugin has its own API that you can use to handle player deaths with keepInventory enabled and to manage player inventory toggle.