# MaterialTextView
<p>
  <a href="https://jitpack.io/#onemandan/MaterialTextView" rel="nofollow"><img alt="API" src="https://img.shields.io/badge/API-15%2B-brightgreen.svg" style="max-width:100%;"></a>
  <a href="https://jitpack.io/#onemandan/MaterialTextView" rel="nofollow"><img alt="Release" src="https://jitpack.io/v/onemandan/MaterialTextView.svg" style="max-width:100%;"></a>
</p>

Material Design Text View that is displayed in a translucent box.  Based on the [Material Design guidelines](https://material.io/design/components/text-fields.html#usage).

<img src="https://github.com/onemandan/MaterialTextView/blob/master/MaterialTextView.png" height="431px"/>

## Why Does This Exist?
[Great Solutions](https://github.com/HITGIF/TextFieldBoxes) already exist for Material Design EditText fields, but I wanted a solution for static data, or data that only gets changed via an action (such as a click, or date selection).  The layout only consists of a few Views, but having a lot of them can result in a very inflated layout file and as such I'd much prefer adding a single View.

## Installation
To get MaterialTextView into your project, add the repository to your build.gradle.

#### Gradle
1. Add the JitPack repository to your projects build.gradle:
```
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```

2. Add the dependency to your modules build.gradle :
```
dependencies {
  implementation 'com.github.onemandan:MaterialTextView:0.0.1'
}
```

#### Maven
1. Add the JitPack repository to your build file:
```
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>
```

2. Add the dependency:
```
<dependency>
  <groupId>com.github.onemandan</groupId>
  artifactId>MaterialTextView</artifactId>
  <version>0.0.1</version>
</dependency>
```
