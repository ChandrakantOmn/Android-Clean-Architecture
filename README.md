# Android-Clean-Architecture
This is a sample github app that how to architect an android application using clean architecture.

## Requirements
- minSdkVersion: 21
- targetSdkVersion: 33

## Architecture
This app follows the `Clean Architecture` and `MVVM design patterns`. 

### Why used Clean Architecture ?
Clean Architecture is a software design principle that aims to make systems more maintainable, testable, and scalable by organizing code into separate layers, each with a specific responsibility.

### Why used MVVM ?
MVVM stands for Model-View-ViewModel, which is an architectural pattern used in software engineering. It is designed to separate the user interface (view) from the data and business logic (model), and introduce a new component called the ViewModel.

## Languages
- Kotlin
- Kotlin Flow
- Coruotines

## Libraries

- Network:
  - okhttp3-logging-interceptor: `4.8.0`
  - retrofit2: `2.9.0`
  - retrofit2-kotlinx-serialization-converter: `0.8.0`
- Dependency Injection:
  - koin-android: `3.3.0`
- Serialization:
  - kotlinx-serialization-json: `1.3.3`
- ViewModel:
  - lifecycle-viewmodel-ktx: `2.6.1`
- LiveData:
  - lifecycle-livedata-ktx: `2.6.1`
- Image Loader:
  - coil: `2.2.2`
- viewPager2: `1.0.0`
- Preferences DataStore: `1.0.0`

# License
```
MIT License

Copyright (c) 2023 Anthony

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
