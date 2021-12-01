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

**Privacy Policy**

Dmytro Turskyi built the Відчуття.Тиші & Zigmund Afraid app as a Free app. This SERVICE is provided by Dmytro Turskyi at no cost and is intended for use as is.

This page is used to inform visitors regarding my policies with the collection, use, and disclosure of Personal Information if anyone decided to use my Service.

If you choose to use my Service, then you agree to the collection and use of information in relation to this policy. The Personal Information that I collect is used for providing and improving the Service. I will not use or share your information with anyone except as described in this Privacy Policy.

The terms used in this Privacy Policy have the same meanings as in our Terms and Conditions, which are accessible at Відчуття.Тиші & Zigmund Afraid unless otherwise defined in this Privacy Policy.

**Information Collection and Use**

For a better experience, while using our Service, I may require you to provide us with certain personally identifiable information. The information that I request will be retained on your device and is not collected by me in any way.

The app does use third-party services that may collect information used to identify you.

Link to the privacy policy of third-party service providers used by the app

*   [Google Play Services](https://www.google.com/policies/privacy/)
*   [Google Analytics for Firebase](https://firebase.google.com/policies/analytics)
*   [Firebase Crashlytics](https://firebase.google.com/support/privacy/)

**Log Data**

I want to inform you that whenever you use my Service, in a case of an error in the app I collect data and information (through third-party products) on your phone called Log Data. This Log Data may include information such as your device Internet Protocol (“IP”) address, device name, operating system version, the configuration of the app when utilizing my Service, the time and date of your use of the Service, and other statistics.

**Cookies**

Cookies are files with a small amount of data that are commonly used as anonymous unique identifiers. These are sent to your browser from the websites that you visit and are stored on your device's internal memory.

This Service does not use these “cookies” explicitly. However, the app may use third-party code and libraries that use “cookies” to collect information and improve their services. You have the option to either accept or refuse these cookies and know when a cookie is being sent to your device. If you choose to refuse our cookies, you may not be able to use some portions of this Service.

**Service Providers**

I may employ third-party companies and individuals due to the following reasons:

*   To facilitate our Service;
*   To provide the Service on our behalf;
*   To perform Service-related services; or
*   To assist us in analyzing how our Service is used.

I want to inform users of this Service that these third parties have access to their Personal Information. The reason is to perform the tasks assigned to them on our behalf. However, they are obligated not to disclose or use the information for any other purpose.

**Security**

I value your trust in providing us your Personal Information, thus we are striving to use commercially acceptable means of protecting it. But remember that no method of transmission over the internet, or method of electronic storage is 100% secure and reliable, and I cannot guarantee its absolute security.

**Links to Other Sites**

This Service may contain links to other sites. If you click on a third-party link, you will be directed to that site. Note that these external sites are not operated by me. Therefore, I strongly advise you to review the Privacy Policy of these websites. I have no control over and assume no responsibility for the content, privacy policies, or practices of any third-party sites or services.

**Children’s Privacy**

These Services do not address anyone under the age of 13. I do not knowingly collect personally identifiable information from children under 13 years of age. In the case I discover that a child under 13 has provided me with personal information, I immediately delete this from our servers. If you are a parent or guardian and you are aware that your child has provided us with personal information, please contact me so that I will be able to do the necessary actions.

**Changes to This Privacy Policy**

I may update our Privacy Policy from time to time. Thus, you are advised to review this page periodically for any changes. I will notify you of any changes by posting the new Privacy Policy on this page.

This policy is effective as of 2021-12-01

**Contact Us**

If you have any questions or suggestions about my Privacy Policy, do not hesitate to contact me at dmitriy.turskiy@gmail.com.
