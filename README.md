# YesNoButton [![](https://jitpack.io/v/MattJAshworth/YesNo.svg)](https://jitpack.io/#MattJAshworth/YesNo)
A simple customizable yes/no style button for Android

# Screenshots
![Demo](/screenshots/yesnolib.gif)

# Download
## build.gradle (Groovy)
Add to your project level `build.gradle`
```
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
```
Add a dependency to your module `build.gradle`:
```
dependencies {
    implementation 'com.github.MattJAshworth:YesNo:1.1'
}
```

## build.gradle.kts (Kotlin DSL)

Add to `settings.gradle.kts`
```Kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://www.jitpack.io" ) }

    }
}
```

Add a dependency to your `build.gradle.kts`:
```Kotlin
dependencies {
    implementation("com.github.MattJAshworth:YesNo:1.1")
}
```
# Implementation
Add the `xyz.mattjashworth.yesno.YesNoButton.YesNoButton` to your layout XML file.

Below are all the YesNoButton's xml attributes. You cannot currently set these programmatically.
```XML
<xyz.mattjashworth.yesno.YesNoButton.YesNoButton
    android:id="@+id/yesNoDemo"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_margin="10dp"
    app:CornerRadius="50"
    app:yesButtonText="Accept"
    app:noButtonText="Decline"
    app:NoTextColor="@color/default_text"
    app:YesTextColor="@color/default_text"
    app:UnselectedColor="@color/default_unselected"
    app:YesButtonColor="@color/default_yes"
    app:NoButtonColor="@color/default_no"
    app:layout_constraintEnd_toEndOf="parent"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintTop_toTopOf="parent" />
```

Attach a click listener just like you would any normal button. The listener returns enum type 'YesNoResult'
```Kotlin
val yesNoButtons = findViewById<YesNoButton>(R.id.yesNoDemo)
        yesNoButtons.setOnYesNoClickListener(object : YesNoButton.OnClickListener {
    override fun onClick(model: YesNoResult) {
        //Show Snackbar with result (Yes, No, None)
        Snackbar.make(rootView, model.name, Snackbar.LENGTH_LONG).show()
    }

})
```        

# License
```
MIT License

Copyright (c) [2024] [Matt J Ashworth]

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```