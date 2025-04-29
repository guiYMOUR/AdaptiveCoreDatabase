# 自适应核心数据库前置
## [English ver.](README.md)
### 这是一个前置库
- 它可以帮助模组作者们调整自己星球的物品和建筑放置规则，辅助模组作者用V7的思路来写V8的星球
### 如何使用？
- 如果你是js（json）模组，直接在模组文件夹里添加一份名为`adc.json`的文件，和`mod.json`同级
- 如果你是Java模组，那么你需要在`assets`文件夹下添加名为`adc.json`的文件
- 下面是`adc.json`的模板，你可以直接复制后修改
```json
{
  "root": [
    {
      "planet":"",//字符串类型  "aaa"
      "items": [],//字符串数组  ["aaa","bbb",...]
      "liquids": [],//字符串数组  ["aaa","bbb",...]
      "units": []//字符串数组  ["aaa","bbb",...]
    },//如果不需要第二个root请参考json格式删除，参考下面的"如何写adc.json"
    {
      //第二个root（按需求可选）
    }
  ]
}
```
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
- 那么这个物品的name是`item1`，可以不用加上模组的名字
- 但如果你的物品和原版物品重名，举个例子，你的物品也叫`copper`，那么你需要加上你模组的名字来区别，假设这是你模组的物品，再假设你模组的名字叫`modName`，那么这个物品的名字就是`modName-copper`
- 而对于json，文件名字即是他的name，举个例子，`item2.json`，那么这个物品的name就是`item2`，如果物品名字和原版重复，请参考上一条
- 对于星球也是同理，那么对于模组的`adc.json`就如下，假设你的星球名字是`planet1`，你的物品名字叫`item1`，你的液体名字叫`liquid1`，你的单位名字叫`unit1`，你的模组名字叫`mod1`
```json
{
  "root": [
    {
      "planet":"planet1",
      //这三个如果没有可以不写，就是[]
      //多个用','隔开，就是["item1","item2"]
      "items": ["item1"],
      "liquids": ["liquid1"],
      "units": ["unit1"]
    }
  ]
}
```