# QuickBase
[![Download](https://api.bintray.com/packages/ddnosh/maven/androidquick/images/download.svg) ](https://bintray.com/ddnosh/maven/androidquick/_latestVersion)  

QuickBase一个用于Android快速的SDK库。  
因为是快速开发，所以会集成一些常用的开源框架，以便用于快速开发目的。  
功能简单点的模块比如task、log等可以直接源码引入。

# SDK功能
1. constant：存放常量；
2. manager：存放管理类；
3. module: 功能模块  
3.1 asynchronize：异步模块，包含eventbus和tinytask； 
3.2 glide：图片模块glide的封装；  
3.3 retrofit：网络模块retrofit的功能封装，包括exception和ssl；  
3.4 rxjava：rxjava部分功能封装；  
4. ui：UI模块  
4.1 adapter：单一布局和多布局；  
4.2 base：提供activity和fragment基类；  
4.3 dialog：提供基于dialog和dialogfragment的对话框；  
4.4 mvp：mvp框架支持；  
4.5 permission：提供权限支持；  
4.6 receiver：receiver支持；  
4.7 view：常用view控件支持；  
4.8 viewstatus：不同页面状态支持；  
4.9 webview：提供一个加载webview的activity；  
5. util：提供一些常用的Util工具类；  

# 开源框架
- [ButterKnife](https://github.com/JakeWharton/butterknife)
- [Glide](https://github.com/bumptech/glide)
- [EventBus](https://github.com/greenrobot/EventBus)
- [Retrofit](https://github.com/square/retrofit)
- [gson](https://github.com/google/gson)
- [RxLifecycle](https://github.com/trello/RxLifecycle) - > 同时自动引入[RxJava2](https://github.com/ReactiveX/RxJava)

# 使用QuickBase的项目
- [AndroidQuick](https://github.com/ddnosh/AndroidQuick)
- [QuickGank](https://github.com/ddnosh/QuickGank)
- [QuickGank-Kotlin](https://github.com/ddnosh/QuickGank-Kotlin)
