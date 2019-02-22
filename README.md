# Midas-SDK接入文档

[![Download](https://api.bintray.com/packages/mobisummer/maven/midassdk/images/download.svg?version=1.2.1)](https://bintray.com/mobisummer/maven/midassdk/1.2.1/link)

### 介绍

Midas SDK

### 接入

#### 1.Gradle引用

```groovy
implementation 'com.ms:midassdk:1.2.1'
```

#### 2.初始化

建议在Application中初始化，`APP_ID`请联系商务获取。

请在初始化之后再进行其他Midas相关的操作（如绑定用户，打开商城页等）。

```java
 Midas.init(this, APP_ID, new MidasListener() {
     @Override
     public void onSucceed() {

     }
     @Override
     public void onFailed(String error) {

     }
 });
```

#### 3.绑定用户

> 目前，即使用户不绑定，也是可以访问商城页面，但是无法付款及查看订单。

这个过程中，需要您的服务器与Midas服务器对接，发送你的`userId`以获取Midas的`userToken`及`midasUserId`。

详情请见这里：[服务器对接](https://github.com/mobisummer/MidasSample/wiki/%E6%9C%8D%E5%8A%A1%E5%99%A8%E5%AF%B9%E6%8E%A5)

获取到`userToken`及`midasUserId`后，进行绑定用户的操作。

```java
 MidasUser user = new MidasUser.Builder()
         .midasUserId(midasUserId) //必须
         .userToken(userToken) //必须
         .nickName("Toby") //补充用户信息以便于优化商品的显示
         .age(18)
         .avatar("https://xxx.com/xxx.jpg")
         .gender(MidasUser.MALE)
         .build();
 Midas.bindUser(user);
```

#### 4.解绑用户

当您的app进行切换用户或者注销用户操作时，调用以下方法，以重置Midas关联的用户

```java
 Midas.unbindUser();
```

#### 5.打开商城页

> 目前只支持通过启动Activity的方式来打开商城

请在初始化之后再调用此方法。如果初始化没完成，则不会打开商城

```java
 Midas.show();
```

#### 6.CreativeView（MidasIcon、MidasBanner）
请在初始化之后再调用load(), 否则无法加载。

特性：
1) MidasIcon宽高比例为1:1
2) MidasBanner宽高比例为720:372
3) 如果宽为精准尺寸高为最大尺寸，则会以宽为基准测量高。
4) 如果高为精准尺寸宽为最大尺寸，则会以高为基准测量宽。
5) 如果宽高都为精准尺寸，则不会按照比例测量
6) 点击跳转商场页

使用：
```java
        mMidasIcon = new MidasIcon(this);
        mMidasIcon.setCreativeListener(new CreativeListener() {
            @Override
            public void onCreativeError(View view, int errorCode, String errorMsg) {

            }

            @Override
            public void onCreativeLoaded(View view) {
                mFrameLayout.addView(view);
            }

            @Override
            public void onCreativeShowed(View view) {

            }

            @Override
            public void onCreativeClosed(View view) {

            }

            @Override
            public void onCreativeClicked(View view) {

            }
        });
        mMidasIcon.load();
```






