# sense.of.silence & Zigmund Afraid [![Codemagic build status](https://api.codemagic.io/apps/60d07948ab5163f7f1fb5066/release-workflow/status_badge.svg)](https://codemagic.io/apps/60d07948ab5163f7f1fb5066/release-workflow/latest_build)

Online Music Player for particular Music Band "відчуття.тиші"  (where I used to play on piano), with
an opportunity to listen to almost all their songs online.

## PROJECT SPECIFICATION

• App store:
[Google Play](https://play.google.com/store/apps/details?id=com.music.android.sensilence);

• Testing invite link: https://appdistribution.firebase.dev/i/0fab69b4a3a132f6;

• Operating system: [Android](https://www.android.com/);

• Programming language: [Java](https://www.oracle.com/java/);

• Interface: [XML](https://developer.android.com/guide/topics/ui/declaring-layout);

• SDK: [Android](https://developer.android.com/studio/intro);

• CI/CD: [GitHub Actions](https://docs.github.com/en/actions) is used to deliver new Android
Package (APK) to [Firebase App Distribution](https://firebase.google.com/docs/app-distribution)
after every push to the **dev** branch,
[Codemagic](https://codemagic.io/start/) is used to deliver new release app bundle to **Google
Play** after every push to **master** branch;

• Architectural pattern: [MVVM](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel)
wrapped with
[Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html);

• Architecture Components:
[LiveData](https://developer.android.com/topic/libraries/architecture/livedata),
[ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel);

• Database: [Room](https://developer.android.com/training/data-storage/room);

• Dependency injection:
[Hilt](https://developer.android.com/training/dependency-injection/hilt-android);

• Asynchronous programming: [ReactiveX](http://reactivex.io/) with
[RxJava3](https://github.com/ReactiveX/RxJava);

• Cloud services: [Firebase Cloud Messaging](https://firebase.google.com/docs/cloud-messaging);

• UI components: [ViewPager2](https://developer.android.com/jetpack/androidx/releases/viewpager2),
[View Binding](https://developer.android.com/topic/libraries/view-binding)

• Layout: the app contains 5 lists of relevant songs, user can navigate between lists using a
central screen and a View pager, each list item contains information about a song and a picture,
screens include cover pictures of the albums on the background;

• Functionality: app uses a custom adapter to populate the layout with views based on instances of
the custom class, all images are stored as drawables, the code runs without errors;

• Code Readability: code is easily readable such that a fellow programmer can understand the purpose
of the app, all variables, methods, and resource IDs are descriptively named such that another
developer reading the code can easily understand their function, no unnecessary blank lines, no
unused variables or methods, no commented out code.

• Screenshots:

<img src="/screenshots/bonus-2021-11-01.png?raw=true" width="200" ><img src="/screenshots/home-2021-11-01.png?raw=true" width="200" >
<img src="/screenshots/za-2021-11-01.png?raw=true" width="300" ><img src="/screenshots/zombi-2021-11-01.png?raw=true" width="200" >
