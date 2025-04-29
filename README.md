# Adaptive Core Database lib
## [中文版本](README_CN.md)
### This is a preload library
- It helps mod authors adjust the item and building placement rules for their planets, assisting modders in using the V7 mindset to create V8 planets.
### How to use it?
- If you are a js (json) mod, simply add a file named `adc.json` to your mod folder, at the same level as `mod.json`.
- If you are a Java mod, you need to add a file named `adc.json` under the `assets` folder.
- Here's the translation of the adc.json template:
```json
{
  "root": [
    {
      "planet":"",//String type  "aaa"
      "items": [],//String array  ["aaa","bbb",...]
      "liquids": [],//String array  ["aaa","bbb",...]
      "units": []//String array  ["aaa","bbb",...]
    },//If you don't need a second root, please delete it according to the JSON format. Refer to "How to write adc.json"
    {
      //Second root (optional based on requirements)
    }
  ]
}
```
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
- The item's name is `item1`, and you do not need to prepend the mod name.
- However, if your item shares the same name as a vanilla item, for example, your item is also named `copper`, then you need to prepend your mod's name to distinguish it. Assuming your mod is named `modName`, the full name of this item becomes `modName-copper`.
- For json files, the file name is the item's name. For example, `item2.json` corresponds to an item named `item2`. If the item's name duplicates a vanilla item, please refer to the previous rule.
- The same applies to planets. Here's an example of what `adc.json` might look like for a mod: if your planet is named `planet1`, your item is named `item1`, your liquid is named `liquid1`, your unit is named `unit1`, and your mod is named `mod1`:
```json
{
  "root": [
    {
      "planet":"planet1",
      // If any of these are not applicable, you can omit them, i.e., use empty arrays []
      // For multiple entries, separate them with commas, like ["item1","item2"]
      "items": ["item1"],
      "liquids": ["liquid1"],
      "units": ["unit1"]
    }
  ]
}
```