# ARabbit
[![Download](https://api.bintray.com/packages/ddnosh/maven/arabbit/images/download.svg) ](https://bintray.com/ddnosh/maven/arabbit/_latestVersion)  
欢迎加入QQ群：
<a target="_blank" href="//shang.qq.com/wpa/qunwpa?idkey=5867e988b85eecbb8c50bedab9810624fc017ce71098ae9394e7c935a4125281"><img border="0" src="http://pub.idqqimg.com/wpa/images/group.png" alt="Android开发技术交流" title="Android开发技术交流"></a>

# 项目目的
提供App开发的接口，使开发者只关心App的业务实现，不用再关心具体功能实现，比如网络请求、图片加载、对话框、缓存等，也不用再为内存泄漏担心

# 项目结构
4.0.0版本进一步精简代码，淘汰了一些老旧的技术栈，全面向kotlin技术栈和jetpack技术栈靠齐;  

## SDK功能 
1. module: 功能模块；   
1.1 exception: 异常处理；  
1.2 glide：图片模块glide的封装；  
1.3 retrofit：网络模块retrofit的功能封装，包括exception和ssl；  
1.4 rxbus：使用rxbus替换eventbus；  
1.5 rxjava：rxjava部分功能封装；  
2. mvvm  
2.1 livedata：增强版livedata
3. ui：UI模块  
3.1 adapter：单一布局和多布局；  
3.2 base：提供activity和fragment基类；  
3.3 dialog：提供基于dialog和dialogfragment的对话框；  
3.5 multipleviewstatus：不同页面状态支持；    
3.5 view：常用view控件支持；  
3.6 webview：提供一个加载webview的activity；  
4. util：提供一些常用的Util工具类；  
4.1 handler：防泄漏Handler；  
4.2 immersion：沉浸式状态栏；  
4.3 manager：存放管理类；  
## App功能
1. MVVM架构(简化版，只有View + ViewModel，通过CompositeDisposable解决RxJava内存泄漏问题)；  
2. 改进型LiveData，保证LiveData不丢失，LiveData在激活时回调，没有内存泄漏，SingleLiveData保证只被调用一次；  
3. Coroutine，Kotlin协程代码实例； 
4. 网络连接（retrofit + okhttp，rxjava通过rxlifecycle绑定生命周期，RxJava异常处理）
5. 加载图片：Glide
6. 自定义各种Dialog
7. viewBinding的使用方法  

# 主要版本修订日志
* 4.0.0
  1. 去除butterknife(使用kotlin自带)、去除viewbinding(放在app工程中)、去除eventbus(使用rxbus替换)、去除老旧的屏幕适配方案(可使用头条的)
  2. 使用jetpack的viewmodels和coroutines等
  3. 进一步明确rxjava+rxlifecycle+rxbus搭配、viewmodel+livedata搭配的技术栈
  4. 更改包名
* 3.1.0版本改名，完善App工程实例；
* 3.0.0版本改用Kotlin；  
* 2.2.0版本支持AndroidX；  
* 2.1.0版本剥离强制引用的一些第三方库；  
* 2.0.0版本更改包名为com.androidwind.androidquick；  
* 1.0.0版本初次提交；  

# 引用的第三方库
``` xml
    implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version"
    api rootProject.ext.dependencies.values()
    //rxjava2
    api "io.reactivex.rxjava2:rxjava:2.2.21"
    api "io.reactivex.rxjava2:rxandroid:2.1.1"
    //rxlifecycle
    api 'com.trello.rxlifecycle3:rxlifecycle:3.0.0'
    api 'com.trello.rxlifecycle3:rxlifecycle-android:3.0.0'
    api 'com.trello.rxlifecycle3:rxlifecycle-android-lifecycle:3.0.0'
    api 'com.trello.rxlifecycle3:rxlifecycle-kotlin:3.0.0'
    //retrofit2
    api 'com.squareup.retrofit2:retrofit:2.9.0'
    //okhttp3
    api 'com.squareup.okhttp3:okhttp:4.9.0'
    api 'com.squareup.okhttp3:logging-interceptor:4.9.0'
    //gson
    api 'com.google.code.gson:gson:2.8.6'
    //glide
    api 'com.github.bumptech.glide:glide:4.12.0'
    api 'jp.wasabeef:glide-transformations:4.1.0@aar'
    //ktx
    api 'androidx.activity:activity-ktx:1.2.2'
    api 'androidx.fragment:fragment-ktx:1.3.2'
```

# SDK引用的一些开源框架
- [ButterKnife](https://github.com/JakeWharton/butterknife)
- [Glide](https://github.com/bumptech/glide)
- [EventBus](https://github.com/greenrobot/EventBus)
- [RxJava](https://github.com/ReactiveX/RxJava)
- [Retrofit](https://github.com/square/retrofit)
- [OkHttp](https://github.com/square/okhttp)
- [Gson](https://github.com/google/gson)

# 使用ARabbit的项目
- [AndroidQuick](https://github.com/ddnosh/AndroidQuick)
- [QuickGank](https://github.com/ddnosh/QuickGank)
- [QuickGank-Kotlin](https://github.com/ddnosh/QuickGank-Kotlin)

# 微信公众号：国民程序员
<p align="center">
  <img src="https://img-blog.csdnimg.cn/20200909075440310.jpg" alt="公众号：国民程序员"/>
</p>
