# sense.of.silence & Zigmund Afraid
Music Player for particular Music Band "відчуття.тиші"  (where I used to play on piano),
 with an opportunity to listen to all their songs online.

 Showcase for using java, view-model, clean-architecture, hilt and RxJava3.

 [Project on Google Play](https://play.google.com/store/apps/details?id=com.music.android.sensilence)

## PROJECT SPECIFICATION

• CI/CD: [GitHub Actions](https://docs.github.com/en/actions) is used to deliver new APK to [Firebase App Distribution](https://firebase.google.com/docs/app-distribution) after every push to the dev branch,
[Codemagic](https://codemagic.io/start/) is used to deliver new release app bundle to **Google Play** after every push to master branch;

• Programming language: **Java**;

• Structural design patterns: [MVVM](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel) wrapped with [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html);

• Architecture Components: [LiveData](https://developer.android.com/topic/libraries/architecture/livedata),
[ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel);

• Database: [Room](https://developer.android.com/training/data-storage/room);

• Dependency injection: [Hilt](https://developer.android.com/training/dependency-injection/hilt-android);

• Asynchronous programming: [ReactiveX](http://reactivex.io/) with [RxJava3](https://github.com/ReactiveX/RxJava);

• UI components: [ViewPager2](https://developer.android.com/jetpack/androidx/releases/viewpager2),
[View Binding](https://developer.android.com/topic/libraries/view-binding)

• Layout: the app contains 5 lists of relevant songs, user can navigate between lists using a central screen and a View pager,
 each list item contains information about a song and a picture, screens include cover pictures of the albums on the background,

• Functionality: app uses a custom adapter to populate the layout with views based on instances of the custom class,
 all images are stored as drawables, the code runs without errors.

• Code Readability: code is easily readable such that a fellow programmer can understand the purpose of the app,
all variables, methods, and resource IDs are descriptively named such that another developer reading the code can easily understand their function,
no unnecessary blank lines, no unused variables or methods, no commented out code.
