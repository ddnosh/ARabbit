# QuickBase
[![Download](https://api.bintray.com/packages/ddnosh/maven/androidquick/images/download.svg) ](https://bintray.com/ddnosh/maven/androidquick/_latestVersion)  

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
2. manager：存放管理类；
3. module: 功能模块；  
3.1 asynchronize：异步模块，包含eventbus和handler；  
3.2 glide：图片模块glide的封装；  
3.3 retrofit：网络模块retrofit的功能封装，包括exception和ssl；  
3.4 rxjava：rxjava部分功能封装；  
3.5 exception: 异常处理
4. ui：UI模块  
4.1 adapter：单一布局和多布局；  
4.2 base：提供activity和fragment基类；  
4.3 dialog：提供基于dialog和dialogfragment的对话框；  
4.4 receiver：receiver支持；  
4.5 view：常用view控件支持；  
4.6 viewstatus：不同页面状态支持；  
4.7 webview：提供一个加载webview的activity；  
5. util：提供一些常用的Util工具类；  

# 引用的第三方库
``` xml
//androidx  
api : 'androidx.appcompat:appcompat:1.1.0'
api : 'com.google.android.material:material:1.2.0-alpha03'
api : 'androidx.multidex:multidex:2.0.0'
api : 'androidx.constraintlayout:constraintlayout:1.1.3'
//异步分发：eventbus  
api 'org.greenrobot:eventbus:3.1.1'  
//IOC：butterknife  
api "com.jakewharton:butterknife:10.0.0"  
annotationProcessor "com.jakewharton:butterknife-compiler:10.0.0"  
//网络：retrofit+okhttp  
api 'com.squareup.retrofit2:retrofit:2.5.0'  
api 'com.squareup.retrofit2:adapter-rxjava2:2.5.0'  
api 'com.squareup.okhttp3:okhttp:3.12.1'  
api 'com.squareup.okhttp3:logging-interceptor:3.12.1'  
//解析：gson  
api 'com.google.code.gson:gson:2.4'  
//图片：glide  
api 'com.github.bumptech.glide:glide:4.9.0'  
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
