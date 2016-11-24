[![Gitter](https://badges.gitter.im/Join Chat.svg)](https://gitter.im/HiveLayoutManager/Lobby)

# HiveLayoutManager

这是一个蜂巢布局管理器，它可以在RecyclerView中实现像蜂巢一样的布局。

## 1 效果展示

**横向布局：**

![](https://chacojack.github.io/2016/09/29/RecyclerView的一个马蜂窝布局/horizontal.png)

**纵向布局：**

![](https://chacojack.github.io/2016/09/29/RecyclerView的一个马蜂窝布局/vertical.png)

**随机添加：**

![](https://chacojack.github.io/2016/09/29/RecyclerView的一个马蜂窝布局/add.gif)

**随机删除：**

![](https://chacojack.github.io/2016/09/29/RecyclerView的一个马蜂窝布局/remove.gif)

**随机移动：**

![](https://chacojack.github.io/2016/09/29/RecyclerView的一个马蜂窝布局/move.gif)

**滚动：**

![](https://chacojack.github.io/2016/09/29/RecyclerView的一个马蜂窝布局/scroll.gif)

## 2 使用方法

### 2.1 加入依赖

```
compile 'com.github.chacojack:hivelayoutmanager:1.0.1'
```

### 2.2 使用HiveLayoutManager


为RecyclerView设置HiveLayoutManager即可。其中包含横向和纵向两种方向。暂时只支持在初始化的时候设置方向，不支持后期改变。

```
recyclerView.setLayoutManager(new HiveLayoutManager(HiveLayoutManager.VERTICAL));
```


### 2.3 正六边形ViewHolder

ViewHolder使用的时候建议使用固定边长的正方形，这样比较好看。可以通过提供的`HiveDrawable`，将图片裁切为正六边形。`HiveDrawable`继承自`Drawable`，我们使用的所有视图都是`View`，使用`View`中的`setBackground(Drawable background)`即可为一个`View`设置正六边形背景。但是这种方法会让图片保持原有的大小，不会根据`View`的大小自动调整。所以建议使用一个`ImageView`来显示图片。

```
HiveDrawable drawable = new HiveDrawable(HiveLayoutManager.VERTICAL,bitmap);
imageView.setImageDrawable(drawable);
```

ViewHolder显示纯色背景：

```
drawable.setColor(resources.getColor(getRandomColor()));
```

纯色配文字的一个简单示例：

![](https://chacojack.github.io/2016/09/29/RecyclerView的一个马蜂窝布局/color.png)
