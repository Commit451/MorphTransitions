# MorphTransitions

Morph transitions from FAB to dialog, and Button to dialog

Inspired by [Plaid](https://github.com/nickbutcher/plaid)

[![Build Status](https://travis-ci.org/Commit451/MorphTransitions.svg?branch=master)](https://travis-ci.org/Commit451/MorphTransitions)
[![](https://jitpack.io/v/Commit451/MorphTransitions.svg)](https://jitpack.io/#Commit451/MorphTransitions)

# Usage
See the sample app. Typical usage:
```java
MorphTransform.addExtras(intent, color, dialogCornerRadius);
ActivityOptions options =
        ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, button,
                getString(R.string.morph_transition));
startActivity(intent, options.toBundle());
```
in the first activity, then:
```java
MorphTransform.setup(this, container, color, dialogCornerRadius));
```
in the next activity. Be sure that you override `onBackPressed()` and call `ActivityCompat.finishAfterTransition(DialogActivity.this);`

In addition, be sure that your activities themes have the attribute:
```xml
<item name="android:windowContentTransitions">true</item>
```

License
--------

    Copyright 2015 Google, Inc
    Copyright 2016 Commit 451

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
