# Introducing keepInventory!
**keepInventory** is a modern Minecraft plugin for PaperMC servers designed to toggle inventory retention rules for individual players.

### Available commands:
- keepInventory on -> Enable keepInventory for yourself
- keepInventory on [player] -> Enable keepInventory for the player
- keepInventory off -> Disable keepInventory for yourself
- keepInventory off [player] -> Disable keepInventory for the player
- keepInventory check -> Check if keepInventory is enabled for yourself
- keepInventory check [player] -> Check if keepInventory is enabled for the player
### Permissions:
- keepinventory.command.self -> Permission to use /keepInventory
- keepinventory.command.other -> Permission to use /keepInventory on others

---

# For developers
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