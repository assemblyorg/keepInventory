# Introducing keepInventory!
**keepInventory** is a modern Minecraft plugin for PaperMC servers designed to toggle inventory retention rules for individual players.

### Available commands:
- /keepinv reload -> Reload plugin configs
- /keepinv on -> Enable keepInventory for yourself
- /keepinv on [player] -> Enable keepInventory for the player
- /keepinv off -> Disable keepInventory for yourself
- /keepinv off [player] -> Disable keepInventory for the player
- /keepinv check -> Check if keepInventory is enabled for yourself
- /keepinv check [player] -> Check if keepInventory is enabled for the player
### Permissions:
- keepinventory.reload -> Permission to use /keepinv reload
- keepinventory.command.use -> Permission to see /keepinv
- keepinventory.command.other -> Permission to use /keepinv <action> [player]
- keepinventory.command.state.on -> Permission to use /keepinv on
- keepinventory.command.state.off -> Permission to use /keepinv off
- keepinventory.command.state.check -> Permission to use /keepinv check

---

# For developers (currently unavailable)
### Add repository:
```kotlin
repositories {
    maven("https://jitpack.io")
}
```
### Add dependency:
```kotlin
dependencies {
    implementation("com.github.assemblyDir.keepInventory:core:{version}")
}
```