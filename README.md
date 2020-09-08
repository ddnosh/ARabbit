# ARabbit
[![Download](https://api.bintray.com/packages/ddnosh/maven/androidquick/images/download.svg) ](https://bintray.com/ddnosh/maven/androidquick/_latestVersion)  
欢迎加入QQ群：
<a target="_blank" href="//shang.qq.com/wpa/qunwpa?idkey=5867e988b85eecbb8c50bedab9810624fc017ce71098ae9394e7c935a4125281"><img border="0" src="http://pub.idqqimg.com/wpa/images/group.png" alt="Android开发技术交流" title="Android开发技术交流"></a>

# 项目目的
提供App开发的接口，使开发者只关心App的业务实现，不用再关心具体功能实现，比如网络请求、对话框、缓存等，也不用再为内存泄漏担心

# 项目结构
ARabbit包含两个项目，一个是app，一个是sdk。  
sdk：顾名思义，就是用来提供快速开发app的库，封装了常用的功能；     
app：作为ARabbit的演示工程，采用MVVM模式，整合RxJava+Retrofit+OkHttp+Glide等主流模块，使用ViewBinding技术。  
## SDK功能
1. constant：存放常量；  
2. module: 功能模块；  
2.1 asynchronize：异步模块，包含eventbus和handler；  
2.2 exception: 异常处理；  
2.3 glide：图片模块glide的封装；  
2.4 retrofit：网络模块retrofit的功能封装，包括exception和ssl；  
2.5 rxjava：rxjava部分功能封装；  
3. mvvm  
3.1 livedata：增强版livedata
4. ui：UI模块  
4.1 adapter：单一布局和多布局；  
4.2 base：提供activity和fragment基类；  
4.3 dialog：提供基于dialog和dialogfragment的对话框；  
4.4 multipleviewstatus：不同页面状态支持；  
4.5 receiver：receiver支持；  
4.6 view：常用view控件支持；  
4.7 webview：提供一个加载webview的activity；  
5. util：提供一些常用的Util工具类；  
5.1 immersion：沉浸式状态栏；  
5.2 manager：存放管理类；  
## App功能
1. MVVM架构(简化版，只有View + ViewModel，通过CompositeDisposable解决RxJava内存泄漏问题)；  
2. 改进型LiveData，保证LiveData不丢失，LiveData在激活时回调，没有内存泄漏；  
3. ViewBinding视图绑定；  
4. 网络连接（retrofit + okhttp，rxjava通过rxlifecycle绑定生命周期，RxJava异常处理）
5. 图片处理：Glide
6. 自定义各种Dialog
7. Kotlin协程
# 主要版本修订日志
* 3.1.0版本改名，完善App工程实例；
* 3.0.0版本改用Kotlin；  
* 2.2.0版本支持AndroidX；  
* 2.1.0版本剥离强制引用的一些第三方库；  
* 2.0.0版本更改包名为com.androidwind.androidquick；  
* 1.0.0版本初次提交；  

# 引用的第三方库
``` xml
api rootProject.ext.dependencies.values()
//eventbus
api 'org.greenrobot:eventbus:3.2.0'
//butterknife
api "com.jakewharton:butterknife:10.0.0"
annotationProcessor "com.jakewharton:butterknife-compiler:10.0.0"
//rxjava
api "io.reactivex.rxjava2:rxjava:2.2.10"
api "io.reactivex.rxjava2:rxandroid:2.1.1"
//retrofit
api 'com.squareup.retrofit2:retrofit:2.6.2'
//okhttp
api 'com.squareup.okhttp3:okhttp:4.2.2'
api 'com.squareup.okhttp3:logging-interceptor:3.12.1'
//gson
api 'com.google.code.gson:gson:2.8.6'
//glide
api 'com.github.bumptech.glide:glide:4.10.0'
api 'jp.wasabeef:glide-transformations:4.0.0@aar'
```

# SDK引用的一些开源框架
- [ButterKnife](https://github.com/JakeWharton/butterknife)
- [Glide](https://github.com/bumptech/glide)
- [EventBus](https://github.com/greenrobot/EventBus)
- [RxJava](https://github.com/ReactiveX/RxJava)
- [Retrofit](https://github.com/square/retrofit)
- [OkHttp](https://github.com/square/okhttp)
- [Gson](https://github.com/google/gson)

# 使用QuickBase的项目
- [AndroidQuick](https://github.com/ddnosh/AndroidQuick)
- [QuickGank](https://github.com/ddnosh/QuickGank)
- [QuickGank-Kotlin](https://github.com/ddnosh/QuickGank-Kotlin)
