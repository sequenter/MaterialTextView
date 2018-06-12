# MaterialTextView
<p>
  <a href="https://jitpack.io/#onemandan/MaterialTextView" rel="nofollow"><img alt="API" src="https://img.shields.io/badge/API-15%2B-brightgreen.svg" style="max-width:100%;"></a>
  <a href="https://jitpack.io/#onemandan/MaterialTextView" rel="nofollow"><img alt="Release" src="https://jitpack.io/v/onemandan/MaterialTextView.svg" style="max-width:100%;"></a>
  <a href="https://raw.githubusercontent.com/onemandan/MaterialTextView/master/LICENSE" rel="nofollow"><img alt="GitHub license" src="https://img.shields.io/badge/license-MIT-blue.svg" style="max-width:100%;"></a>
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

## Attributes & Methods

#### Text
| Attribute | Method Counterparts | Description | Default |
| --------- | ------------------- | ----------- | ------- |
| app:mtv_labelText | ```void setLabelText(CharSequence text)``` </br> ```CharSequence getLabelText()``` | The text for the label at the top of the view | |
| app:mtv_contentText | ```void setContentText(CharSequence text)``` </br> ```CharSequence getContentText()``` | The text for the content of the view | |
| app:mtv_helperText | ```void setHelperText(CharSequence text)``` </br> ```CharSequence getHelperText()``` | The text for the helper at the bottom of the view | |

#### Colour
| Attribute | Method Counterparts | Description | Default |
| --------- | ------------------- | ----------- | ------- |
| app:mtv_labelTextColour | | The text colour for the label at the top of the view | ?android:textColorSecondary |
| app:mtv_contenxtTextColour | | The text colour for the content of the view | ?android:textColorPrimary |
| app:mtv_helperTextColour | | The text colour for the helper at the bottom of the view | ?colorAccent |

#### Miscellaneous 
| Attribute | Method Counterparts | Description | Default |
| --------- | ------------------- | ----------- | ------- |
| app:mtv_keepLabelSpacing | | In the event that the label text is empty, whether or not the spacing should be kept by setting the respective TextView to *GONE* or *VISIBLE* | true |
| app:mtv_keepHelperSpacing | | In the event that the helper text is empty, whether or not the spacing should be kept by setting the respective TextView to *GONE* or *VISIBLE* | false |

#### Listeners
You can implement a click listener to the MaterialTextView:
```
materialTextView.setOnClickListener(new View.OnClickListener() {
  @Override
  public void onClick(View v) { ... }
});
```

In the event a click listener is added, the following attributes are set to the View:
```
setForeground(...[?selectableItemBackground]...);
setClickable(true);
setFocusable(true);
```

When removing the click listener, the attributes of the View are removed and the foreground restored to the previous state.  To Remove the click listener:
```
materialTextView.setOnClickListener(null);
```
