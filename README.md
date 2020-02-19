# QuickBase
[![Download](https://api.bintray.com/packages/ddnosh/maven/androidquick/images/download.svg) ](https://bintray.com/ddnosh/maven/androidquick/_latestVersion)  
欢迎加入QQ群：
<a target="_blank" href="//shang.qq.com/wpa/qunwpa?idkey=5867e988b85eecbb8c50bedab9810624fc017ce71098ae9394e7c935a4125281"><img border="0" src="http://pub.idqqimg.com/wpa/images/group.png" alt="Android开发技术交流" title="Android开发技术交流"></a>

QuickBase是一个用于Android App快速开发的SDK库。   
因为是SDK，所以QuickBase提供基本功能库，尽量剥离强制性引用第三方库。  

# 主要版本修订日志
* 3.0.0版本改用Kotlin；  
* 2.2.0版本支持AndroidX；  
* 2.1.0版本剥离强制引用的一些第三方库；  
* 2.0.0版本更改包名为com.androidwind.androidquick；  
* 1.0.0版本初次提交；  

# SDK功能
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

# 引用的第三方库
``` xml
api rootProject.ext.dependencies.values()
//eventbus
api 'org.greenrobot:eventbus:3.1.1'
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

# 开源框架
- [ButterKnife](https://github.com/JakeWharton/butterknife)
- [Glide](https://github.com/bumptech/glide)
- [EventBus](https://github.com/greenrobot/EventBus)
- [Retrofit](https://github.com/square/retrofit)
- [Gson](https://github.com/google/gson)

# 使用QuickBase的项目
- [AndroidQuick](https://github.com/ddnosh/AndroidQuick)
- [QuickGank](https://github.com/ddnosh/QuickGank)
- [QuickGank-Kotlin](https://github.com/ddnosh/QuickGank-Kotlin)
