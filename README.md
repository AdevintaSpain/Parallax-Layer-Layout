# Parallax Layer Layout

<img src="art/ovni.gif" align="left" width="204px" height="168px"/>
<img align="left" width="0" height="168px" hspace="10"/>

[![Bintray](https://img.shields.io/bintray/v/schibstedspain/maven/parallax-layer-layout.svg?maxAge=2592000)](https://bintray.com/schibstedspain/maven/parallax-layer-layout/) [![Build Status](https://travis-ci.org/SchibstedSpain/Parallax-Layer-Layout.svg?branch=master)](https://travis-ci.org/SchibstedSpain/Parallax-Layer-Layout)

Let's you add layered parallax effect to your Android views or images based on things like device rotation.

We use it at [InfoJobs](https://play.google.com/store/apps/details?id=net.infojobs.mobile.android) for our error and empty states.

<br/>
<br/>
<p align="center">
<b><a href="#usage">Usage</a></b>
|
<b><a href="#download">Download</a></b>
|
<b><a href="#how-it-works">How it works</a></b>
|
<b><a href="#customize">Customize</a></b>
|
<b><a href="#contribute">Contribute</a></b>
</p>

# Usage

In your layout wrap your layers inside a **ParallaxLayerLayout**:

```xml
<com.schibsted.spain.parallaxlayerlayout.ParallaxLayerLayout
    android:id="@+id/parallax"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
    <View
        android:id="@+id/layer_3"
        android:layout_width="180dp"
        android:layout_height="180dp"
        android:background="@color/colorPrimaryDark" />
    <View
        android:id="@+id/layer_2"
        android:layout_width="120dp"
        android:layout_height="120dp"
        android:background="@color/colorPrimary" />
    <View
        android:id="@+id/layer_1"
        android:layout_width="80dp"
        android:layout_height="80dp"
        android:background="@color/colorAccent" />
            
    </com.schibsted.spain.parallaxlayerlayout.ParallaxLayerLayout>
```

In your code create a **TranslationUpdater** and link it with the *ParallaxLayerLayout*:

```java
sensorTranslationUpdater = new SensorTranslationUpdater(this);
parallaxLayout.setTranslationUpdater(sensorTranslationUpdater);

// onResume
sensorTranslationUpdater.registerSensorManager();

// onPause
sensorTranslationUpdater.unregisterSensorManager();
```
**Note:** Just for the sensor updater you'll need to register and unregister it in your onResume/onPause.

# Download
Grab the latest version from jCenter:

```groovy
dependencies {
  compile 'com.schibsted.spain:parallax-layer-layout:1.0.0'
}
```


# How it works
The layout applies a maximum offset to each of its views, starting with a **base offset** and adding one **offset increment** for each layer. 

The higher view in the Z index is the closest one from the user perspective so it will only move with the base offset. The further the view from the user, the more it will move.

The parallax effect is applied based on an horizontal and vertical translations, ranging from -1.0 to 1.0. This translation is provided by an external TranslationUpdater. The library includes some:

- **SensorTranslationUpdater:** Updates the translation based on the orientation sensors (roll and pitch).
- **AnimatedTranslationUpdater:** Applies the translation automatically as a continuous animation. 

# Customize
## Parallax offset

You can change the default offset for the effect within your xml with the attributes **parallaxOffsetBase** and **parallaxOffsetIncrement**:

```xml
<com.schibsted.spain.parallaxlayerlayout.ParallaxLayerLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:parallaxOffsetBase="10dp"
    app:parallaxOffsetIncrement="5dp">
```
*The defaul values are 10dp for the base and 5dp for the increment*

## Vertical and horizontal effect scale

Sometimes you might want to reduce or increase the effect for vertical or horizontal movement.

```xml
<com.schibsted.spain.parallaxlayerlayout.ParallaxLayerLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:parallaxScaleVertical="0.25"
        >
```
In this example the views will move vertically a 25% with respect to what they move horizontally.

*Note: The parallax scale doesn't scale the views, but rather the parallax movement*

## Custom Z index

By default the parallax effect is applied in the views Z index. This means that the last view in the layout will be the one closer to the user, and so it will move less; and the first view will be the furthest and will move more.

If you need a different parallax order you can change this behavior by overriding the views index with the attribute **layout_parallaxZIndex**:

```xml
<com.schibsted.spain.parallaxlayerlayout.ParallaxLayerLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <View
        android:id="@+id/layer_2"
        android:layout_width="180dp"
        android:layout_height="180dp"
        android:background="@color/colorPrimaryDark"
        app:layout_parallaxZIndex="1" />

    <View
        android:id="@+id/layer_3"
        android:layout_width="120dp"
        android:layout_height="120dp"
        android:background="@color/colorPrimary"
        app:layout_parallaxZIndex="2" />

    <View
        android:id="@+id/layer_1"
        android:layout_width="80dp"
        android:layout_height="80dp"
        android:background="@color/colorAccent"
        app:layout_parallaxZIndex="0" />

</com.schibsted.spain.parallaxlayerlayout.ParallaxLayerLayout>
```

# Contribute
For bugs, requests, questions and discussions please use [Github Issues](https://github.com/SchibstedSpain/parallax-layer-layout/issues).	

# Attributions
This library was developed by [Rafa Vázquez](https://github.com/Sloy/) with the idea of moving images from [Alex Bailón](https://github.com/abailon) for the [Infojobs' Android application](https://play.google.com/store/apps/details?id=net.infojobs.mobile.android).

# License

```
Copyright 2016 Schibsted Classified Media Spain S.L.


Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
