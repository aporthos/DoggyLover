# DoggyLover

### This app can be used to see dogs, save favorites, and see details in offline mode.

![functionality](https://github.com/user-attachments/assets/9234434f-6909-4344-8906-083afa2acf1a)

<img src="https://github.com/user-attachments/assets/16a015e9-623e-4ae1-923b-94c88e50cfd7" align="right" width="320"/>

## Tech stack & Open-source libraries
- Minimum SDK level 24.
- [Kotlin](https://kotlinlang.org/) based, utilizing [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous operations.
- Jetpack Libraries:
  - Jetpack Compose: Android’s modern toolkit for declarative UI development.
  - Lifecycle: Observes Android lifecycles and manages UI states upon lifecycle changes.
  - StateFlow - StateFlow is a state-holder observable flow that emits the current and new state updates to its collectors.
  - ViewModel: Manages UI-related data and is lifecycle-aware, ensuring data survival through configuration changes.
  - Navigation: Facilitates screen navigation, complemented by [Hilt Navigation Compose](https://developer.android.com/jetpack/compose/libraries#hilt) for dependency injection.
  - Room: Constructs a database with an SQLite abstraction layer for seamless database access.
  - Datastore: Jetpack DataStore is a data storage solution that allows you to store key-value pairs or typed objects with protocol buffers.
  - [Hilt](https://dagger.dev/hilt/): Facilitates dependency injection.
- Architecture:
  - MVVM Architecture (View - ViewModel - Model): Facilitates separation of concerns and promotes maintainability.
  - Repository Pattern: Acts as a mediator between different data sources and the application's business logic.
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit): Constructs REST APIs and facilitates paging network data retrieval.
- [Turbine](https://github.com/cashapp/turbine): A small testing library for kotlinx.coroutines Flow.
- [MockK](https://github.com/mockk/mockk): Allows mock objects in both your Android unit tests and instrumented tests.
- [Lottie](https://github.com/airbnb/lottie-android): Lottie is a mobile library for Android and iOS that parses Adobe After Effects animations exported as json with Bodymovin and renders them natively on mobile!.
- [Coil](https://github.com/cashapp/turbine): An image loading library for Android and Compose Multiplatform.
- [Moshi](https://github.com/square/moshi): Modern JSON library for Android, Java and Kotlin. It makes it easy to parse JSON into Java and Kotlin classes

# Architecture

The **DoggyLover** app follows the
[official architecture guidance](https://developer.android.com/topic/architecture) 
and is described in detail in the
[architecture learning journey](docs/ArchitectureLearningJourney.md).

![architecture_doggy](https://github.com/user-attachments/assets/572d5f9f-b429-4cbf-b756-189a6fed2887)



## Package Structure
![structure](https://github.com/user-attachments/assets/c73a4ca4-5417-4bee-9acf-9df16170a347)




