# android_ao
Android AssetOwl application

### Usage
First time when you checkout this project, I think you would like to run all of the instrumentation tests on your testing devices. Please follow the steps below:
```bash
# go to your project directory first
# connect your testing devices or emulators to your computer.
./gradlew clean build connectedCheck
```

### Clean Architecture
This application follows the principles of Clean Architecture. To aid in clean boundaries, we also use reactive java RxJava.

##### The application is divided into the following horizontal layers.
* UI
* Domain
* Data


###### Key architecture abstractions
* Usecase (Interactors)
* Presenter
* Component
* ApplicationComponent
* ActivityComponent
* Module
* ApplicationModule
* ActivityModule

###### Using Data Contracts
Data contracts define the contract between:
*data and domain layers
*domain and presenter layers at the model view mapper.

Data contracts allow us to determine what the application needs for display purposes and also how to massage request/responses to satisfy domain and presentation requirements.
They are released in their own jars to decouple dependencies between layers. As a result we're also able to track breaking changes via data contract versions.

###### Taking from Screaming Architecture
The package naming should *scream* what it is first and the convention of assembly second.

##### Assembly:
* The pemise is that components will be pulled into a project using gradle and AAR.
* Using dependency injection in the skeleton app, we can tie components together in Components and Modules.
* We will handcraft navigation using Navigator.class
* Component libraries are customisable by composition at the Module level


##### Suggestions for implementing features.

1. Define your Component and modules.
2. Wire your module to provide the usecase (i.e. GetAccountUseCase)
3. Create your usecase injecting a repository interface
4. Define Observables exposed by you repositoy interface (i.e. Observable<Account> getAccount()).
5. Create your implementation of your repository interface, possibly injecting data mappers

**Note**
*The data layer is an android library @aar*
*The data layer agrees to a data-contract, which is a dependency of the domain layer*


Using:
```
 versionCode = <build>
 versionName = <major>.<minor>.<patch>.<build>
```

 * major - version when you make incompatible changes
 * minor - version when you add functionality in a backwards-compatible manner
 * patch - version when you make backwards-compatible bug fixes.
 * build - version every time a build is made

