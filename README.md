# Adaptive Core Database lib
## [中文](README_CN.md)
## Because V8 changed the way Planet Tech is displayed, this mod helps bring the core database back to V7
### How to use it?
- If you are a js (json) mod, simply add a file named `adc.json` to your mod folder, at the same level as `mod.json`
- If you are a Java mod, you need to add a file named `adc.json` under the `assets` folder
### How to write adc.json?
- Here is an example that adds copper, lead, oil and dagger to Erekir, and moves buildings that only use copper and lead as construction materials to Erekir:
```json
{
  "root": [
    {
      "planet":"erekir",
      "items": ["copper","lead"],
      "liquids": ["oil"],
      "units": ["dagger"]
    }
  ]
}
```
### For original items, you can directly use their names. However, for mod items, please follow the guidelines below:
- If the planet, items, liquids, or units you want to add are from a mod, you need to know their actual names (not display names). For example, if you are a js or json mod author and you created an item like this in js:
```js
const myItem = new Item("item1");
```
- Then the name of this item is `item1`, and you need to prepend your mod's name to it. Assuming your mod is named `modName`, the full name of this item becomes `modName-item1`.
- For json files, the file name is the item's name. For example, `item2.json` corresponds to an item named `item2`. Similarly, you need to prepend your mod's name. So, if this item is part of your mod `modName`, its full name is `modName-item2`.
- The same applies to planets. Here's an example of what `adc.json` might look like for a mod: if your planet is named `planet1`, your item is named `item1`, your liquid is named `liquid1`, your unit is named `unit1`, and your mod is named `mod1`:
```json
{
  "root": [
    {
      "planet":"mod1-planet1",
      // If any of these are not applicable, you can omit them, i.e., use empty arrays []
      // For multiple entries, separate them with commas, like ["mod1-item1","mod1-item2"]
      "items": ["mod1-item1"],
      "liquids": ["mod1-liquid1"],
      "units": ["mod1-unit1"]
    }
  ]
}
```
