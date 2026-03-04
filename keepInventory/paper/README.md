# keepInventory Paper module
This is the plugin's core module. It is not recommended to use it directly in your plugin; it is better to use the dedicated API module. To work with this module, follow these instructions:

#### Add repository:
```kotlin
repositories {
    maven("https://jitpack.io")
}
```
#### Add dependency:
```kotlin
dependencies {
    implementation("com.github.assemblyDir.keepInventory:paper:1.0.0")
}
```