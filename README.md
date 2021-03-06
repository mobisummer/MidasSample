# Midas-SDK接入文档

[![Download](https://api.bintray.com/packages/mobisummer/maven/midassdk/images/download.svg?version=1.3.6)](https://bintray.com/mobisummer/maven/midassdk/1.2.7/link)

### 介绍

Midas SDK

### 接入

#### 1.Gradle引用

```groovy
implementation 'com.ms:midassdk:1.3.6'
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

#### 3.CreativeView（MidasIcon、MidasBanner）
请在初始化之后再调用load(), 否则无法加载。

特性：
1) MidasIcon宽高比例为1:1
2) MidasBanner宽高比例为720:372
3) 如果宽为精准尺寸高为最大尺寸，则会以宽为基准测量高。
4) 如果高为精准尺寸宽为最大尺寸，则会以高为基准测量宽。
5) 如果宽高都为精准尺寸，则不会按照比例测量
6) 如果宽高都为未指定尺寸，则会按照原本图片大小测量
7) 点击跳转商场页

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

##### 3.1 可配置项

1. 自动加载图片

```java
//默认为false
creativeView.setEnableAutoLoad(true);
```

2. 自动回收

```java
//默认为true
creativeView.setEnableAutoRecycle(false);
//手动回收
creativeView.recycle()
```


