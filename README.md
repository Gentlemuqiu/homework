# wanandroid

## App的简要介绍

### App的功能

#### 1. 首页相关

1. 首页文章列表
2. 首页banner
3. 常用网站
4. 搜索热词
5. 置顶文章

#### 2. 体系

1. 体系数据
2. 知识体系下的文章

#### 3.导航

1. 导航数据

#### 4.项目

1. 项目分类
2. 项目数据列表

#### 5.登录与注册

1. 登录
2. 注册
3. 退出

#### 6.收藏

1. 收藏文章列表
2. 收藏站内文章
3. 收藏站外文章
4. 取消收藏

#### 7.搜索

1. 搜索

#### 8.问答

1. 问答
2. 问答评论列表

#### 9.公众号

1. 获取公众号列表
2. 查看某个公众号的历史记录

#### 10.教程

1. 教程列表
2. 单个教程下的所有文件

#### 11.工具

1. 工具栏列表



### 功能的实现思路

#### 1.首页:

顶部采用viewPager ，并用Glide加载获得到的图片，为整个图片设计点击事件监听，跳转到对应的内容。

底部采用RecyclerView，由于首页相关的内容过多，因此在加载网络时利用for循环加载多条记录。在进行置顶文章和普通文章的布局时，利用一个RecyclerView完成，利用getItemViewType ，当position小于置顶文章的数目时令viewType为1，否则为0。进而加载不同的布局。(ps: 搜索热词,常用网站 功能均放置搜索界面)。

#### 2.体系

采用recyclerView的嵌套使用，先在fragment中使用一次recyclerView，然后recyclerView中的每一条目中令含一个RecyclerView。

#### 3.导航

利用两个平行的RecyclerView完成，在构造左边的Adapter是将右边的RecyclerView传入，从而实现当点击左侧对应导航时，出现导航下的分类。点击具体分类后跳转到相应的页面。

#### 4.项目

利用Spinner和RecyclerView的结合来实现，由于异步加载网络数据带来的影响使得Spinner无法正常显示，因而我手动为其增加了第一条（提示条目）。利用Glide加载对应的图片

#### 5.登录与注册

注册时将用户名，密码等利用HashMap存起来，进行网络请求，布局中采用谷歌提供的TextInputLayout实现提示文字到上方。 登录界面时同样采用TextInputLayout实现提示文字到上方，利用其中的passwordTohhleEnabled方法，实现可以切换密码是否可见。在请求网络时采用OKHttpClient来请求网络，声明一个全局变量用来存储Cookie，并采用cookieJar保存cookie。

#### 6.收藏

为文章添加了长按事件监听， 长按后弹出对话框提示是否收藏文章。在收藏的文章列表中同样也设置长按监听事件提示是否取消文章收藏。采用FloatIngActionButton 在收藏界面右下角设置增加按钮。

#### 7.搜索

创造searchView界面，并为之设置事件监听，当搜索框内的内容为空时显示热词和常用网站，当有内容时隐藏，搜索完毕时显示搜索内容。同时在搜索的结果中添加分割线。

#### 8.问答

采用相对布局，左侧为提问，右侧设置评论，分别设置点击事件听。跳转到问答内容和问答评论。

#### 9.公众号

采用网格布局，在getLayoutMananger的setSpanSizeLookup中设置，每5个为一组，前四个一行占两个格子，第五个则占一个格子

#### 10.教程&&11.工具

这两个实现比较简单 RecyclerView而已

### 不同页面的实现思路

总共分为四个页面：启动页，主页面，广场页和次页面

启动页：采用viewPager打开后会出现滑动界面，滑满4个后则进入注册登录界面。同时界面下设置RadioGroup，其中包含四个按钮。前三个页面登录及注册按钮隐藏，第四个界面时显示。

主页面：为自己的页面设置标题并绑定菜单，在菜单中实现更多，日间，夜间，退出登录的功能，将夜间显示其他隐藏，而后创建4个Fragment  将四个Fragment装入底部导航页。同时将ViewPager和Fragment结合在一起实现联动。

广场页：viewPager与Fragment的结合。同时顶部实现TabLayout。及在广场页这个Fragment中包含多个Fragment

次页面：实现侧滑界面，即在Activity中设置DrawerLayout，而在DrawerLayout中设置FragemntContainerView以便收纳多个Fragemnt而后设置Nav导航。并设置当点击某一个Nav弹出相应的Fragement。并收回DrawerLayout

## App功能展示

![1675175288177](C:\Users\Gentle\Documents\Tencent Files\1627812101\FileRecv\MobileFile\1675175288177.gif)
![1675175610448](C:\Users\Gentle\Documents\Tencent Files\1627812101\FileRecv\MobileFile\1675175610448.gif)
![1675175784176](C:\Users\Gentle\Documents\Tencent Files\1627812101\FileRecv\MobileFile\1675175784176.gif)
![1675175916843](C:\Users\Gentle\Documents\Tencent Files\1627812101\FileRecv\MobileFile\1675175916843.gif)
![1675176116188](C:\Users\Gentle\Documents\Tencent Files\1627812101\FileRecv\MobileFile\1675176116188.gif)
![1675176156281](C:\Users\Gentle\Documents\Tencent Files\1627812101\FileRecv\MobileFile\1675176156281.gif)
![1675176326889](C:\Users\Gentle\Documents\Tencent Files\1627812101\FileRecv\MobileFile\1675176326889.gif)
![1675176404914](C:\Users\Gentle\Documents\Tencent Files\1627812101\FileRecv\MobileFile\1675176404914.gif)

## 技术亮点

1. 启动页采用viewPager及RadioGroup组合
2. 注册和登录界面使用TabLayout
3. 主页面利用BottomNavigationView实现底部导航
4. 广场页的顶部导航
5. 使用spinner
6. 实现cookie持久化保存
7. searchView的使用
8. FloatIngActionButton设置界面
9. 顶部切换日光和夜间模式 以及更多和退出设置
10. 次页面使用DrawerLayout，并在其中设置FragemntContainerView来切换页面

## 心得体会

###1. 问题

​          说实话，挺不容易的。遇到的问题也挺多，无数次想要放弃。 其实移动上半部分java的学习还是挺顺利的，也给了我莫大的信心，有着继续完成的动力。可随之而来的就是一个巨大的问题 android开始了，可是我的android studio一直出现问题。（因为我是比较早就学完java了，所以比较心急，在学长android studio下载教程发布之前，就去下载了。）我的android studio一直不能用，等到学长发完下载教程，我又去装，还是下载不了（可能是之前下载后设置有问题）。我当时要崩溃了。看到别人都已经开始进度了，我却还停留在原地，最后，我恨下了决心，重装了一次系统，再次下载才解决了这个问题。

​         你以为这就结束了吗？不，根本不是，这只是下一个噩梦的开始，由于我选的是android，所以需要Android手机运行，或者虚拟机运行。然而，我是苹果用户，没有android手机，好吧，留给我的只有一条路了，虚拟机，可是我这破电脑根本带不动，怎么下载？？？阿西.....心灰意冷了。 好吧，那么现在该怎么呢？

​          对，我有办法了。我用我上大学之前的手机（android的），这样不就行了。可是.....  我太可怜了。 当时刚好我们家乡爆发疫情（我是河南的），大家对那时候河南疫情可能也有些了解吧。 反正就是，手机邮寄不过来！我的天啊！ 天要亡我.....

​          再次陷入了僵局，我心里惶惶不安，心有不甘，我努力了这么长时间，难道都白费了吗？不！我不服 !!!!!最后我下定决心再买个android手机。哈哈哈哈，我打算双十一买，因为那个时候便宜些。结果买到了，但是！ 我tm，都是泪啊！ 又是因为疫情，我们学校封校，快递送不过来。好吧，真的要放弃了。

​         那么是什么支持我继续下去呢？ 我也不知道.....  反正就是想继续下去。

​         最后到了十二月中旬，也就是寒假大作业布置前后，我拿到了我的手机，正式开始android学习！

###2. 收获

​        我觉得首先提升的就是自己的抗击打能力和继续下去的信心。

​        我学到的东西也有一些Okhttp，Glide，gson，网络请求，权限获取，activity生命周期，RecyclerView，Fragement的灵活使用，数据库的使用，Cookie的获取保存，线程，线程池等。

## 待提升的地方

1. 更好看的UI界面
2. 各种框架的使用

3. service 和广播的使用
4. Media
5. Room，Retrofit等
6. 之前学到的东西熟练使用（权限获取等）
7. git 和github的使用
8. 还有好多好多...... 不足的地方太多了，在此便不一 一赘述了

​      
###知乎日报的功能:

1.顶部Banner与日期

2.上拉加载与下拉刷新

3.点击查看新闻详情,左右滑动切换下一篇(上一篇)新闻

4.查看评论与分享功能

5.实现夜间功能

6.协调者布局实现Toolbar和RV联动

<!--第7个我们努力去实现了,也花了好多时间,但是还是没能成功-->

### 主要功能实现思路

1.  顶部banner 使用了ViewPager2来装载图片内容，同时实现了轮播： 在viewPager2底部设置了一个水平水平的Linearlayout 然后 在其中设置小点点  设置小点点的数量为itemCount 然后给viewpager2注册页面选择监听当滑倒对应位置时设置变红。同时利用Handler每隔3秒自动循环跳动到下一次。

2.  下拉刷新和上拉加载：下拉刷新 这个比较简单了 利用一个SwipeRefreshLayout布局便可实现，而上拉加载，这里我的实现是通过addOnScrollListener监听是否滑动到最后一页，如果划到了，那么进行重新进行网罗请求拿到更早的数据。

3.  左右划页：利用viewPager2实现，为之前的各种布局设置有点击事件监听，将数据传递给webActiviy中，由webActivity进行解析放到ViewPager2中
4.  评论的话，直接拿到webActivity传来的id然后解析就行。 分享可以调用系统的功能实现隐式跳转
5.  夜间功能：value/night和value里设置noActionBar,然后在activity里切换。
6.  使用app:layout_scrollFlags="scroll|enterAlways|snap" />属性来实现

###不同页面的实现思路

MainActivity中引用了卡片布局、toolbar、AppbarLayout、和抽屉菜单。

利用toolbar中的layout_crollFlags实现了rv和toolbar的联动。 抽屉菜单和navigation进行了结合使用。

抽屉菜单中设置了两个fragment布局。

mainFragment中设置recyclerview，recyclerviw中嵌套使用recyclerview和viewPager2

againFragment中简单的设置了一个卡片布局，里面有之前的各个条目信息

webActivity中同样设置一个ViewPage2 这样便可实现滑动切换

### 动态GIF图

之前寒假上传的时候便看不了，这次就不上传了吧。

###技术亮点

用到了协程，mvvm框架。 使用Handler自定义了轮播图。实现了较为好看的界面。

###心得体会

之前其实看了挺多书的，但是一直没有进行实操，这次一进行实操，就感觉好多东西自己理解的并非那么透彻，以后还是得多敲代码。

### 待提升的地方

fragment的用法，rxjava3的使用。自定义view的学习，代码命名规范，ListAdapter的应用，paging等的jetBrain的使用，flow流的正确使用，断网处理。