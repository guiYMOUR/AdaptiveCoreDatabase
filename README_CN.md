# 自适应核心数据库前置
## [English](README.md)
## 因为V8改了星球科技显示，这个模组可以帮助你将核心数据库重回V7
### 如何使用？
- 如果你是js（json）模组，直接在模组文件夹里添加一份名为`adc.json`的文件，和`mod.json`同级
- 如果你是Java模组，那么你需要在`assets`文件夹下添加名为`adc.json`的文件
### 如何写adc.json?
- 下面是一个实例，未E星添加了铜、铅、石油和尖刀，并将建筑材料只包括铜和铅的建筑迁移到了E星
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
### 原版的物品直接使用他的名字就可以了，而模组的物品需要看下面几条
- 如果你需要添加的星球是模组星球，物品是模组物品，液体是模组液体，单位是模组单位，那么你需要明白你的星球或物品等叫什么，即需要获取它的name，而不是displayName，举个例子，你是一名js或者json模组作者写了一个，物品，如果你是用js写的
```js
const myItem = new Item("item1");
```
- 那么这个物品的name是`item1`，而且需要加上模组的名字，假设这是你模组的物品，再假设你模组的名字叫`modName`，那么这个物品的名字就是`modName-item1`
- 而对于json，文件名字即是他的name，举个例子，`item2.json`，那么这个物品的name就是`item2`，同理需要加上你的模组名字，假设这是你模组的物品，再假设你模组的名字叫`modName`，那么这个物品的名字就是`modName-item2`
- 对于星球也是同理，那么对于模组的`adc.json`就如下，假设你的星球名字是`planet1`，你的物品名字叫`item1`，你的液体名字叫`liquid1`，你的单位名字叫`unit1`，你的模组名字叫`mod1`
```json
{
  "root": [
    {
      "planet":"mod1-planet1",
      //这三个如果没有可以不写，就是[]
      //多个用','隔开，就是["mod1-item1","mod1-item2"]
      "items": ["mod1-item1"],
      "liquids": ["mod1-liquid1"],
      "units": ["mod1-unit1"]
    }
  ]
}
```
