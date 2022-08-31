# ArtSee

ArtSee is a little Android app to play around with modern architecture and tools. The app is powered by the [Art Institute of Chicago API](https://api.artic.edu/docs/), a robust and well-documented public API.  

## Architecture

The app is separated into the following architecture layers: 
```
project
└───data 
└───domain
└───presentation
└───ui
```
Currently, these layers are separated into packages. A future improvement would be to modularize these packages to  enforce a separation of concerns.

Our domain layer contains our business models and repository interfaces, while the presentation layer (consistent with practical MVVM architecture) contains our business logic and UI state. 

Our data layer contains repository implementations including both a remote data source (the Art Institute of Chicago API) and a storage data source (Proto DataStore). The data layer also contains state mappers to convert our data source DTOs to domain models. 

Our UI layer contains our composable functions and themes. 

## Tools

A non-exhaustive list of highlighted tools:
- Dagger Hilt for dependency injection
- Retrofit 2 for remote API handling
- Proto DataStore for saving artwork to local storage
- Kotlin Serialization!
- Paging 3 with Compose
- Kotlin Coroutines for doing asynchronous work
- Kotlin Flow to make the app reactive


## UI

The app uses Jetpack Compose! Worth checking out: the composable function `AnimatedShimmer`, which gives us a nice shimmer effect while the art detail loads.
