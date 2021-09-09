# TemperWorks Android app in Kotlin with MVVM 

This is the assignment Project in creating an Android Application using open api from temperworks and MVVM

## Installation
Clone this repository and import into **Android Studio**
```bash
https://github.com/jasonwaku/TemperWorksApp.git
```
## Application aspects

1. [Code] Usage of Kotlin
2. [Architecture] Service layer implementation
3. [Architecture] MVVM with Paging extention to use coroutines
4. [Architecture] API error handling
5. [Architecture] Dagger Hilt used as Dependency Injection
6. [UI] Login and Signup buttons will show at skeleton empty fragments
7. [UI] Filter button is a dummy
8. [UI] Pull to refresh enabled
9. [Code] Coroutines with Paging extention
10. [Testing] testing ViewModels
11. [Testing] testing of RxJava

## API endpoint example 

API Endpoint: https://temper.works/api/v3/shifts?filter[date]=2021-07-19


## Configuration
### Keystores:
Create `app/keystore.gradle` with the following info:
```gradle
ext.key_alias='...'
ext.key_password='...'
ext.store_password='...'
```
And place both keystores under `app/keystores/` directory:
- `playstore.keystore`
- `stage.keystore`


## Build variants
Use the Android Studio *Build Variants* button to choose between **production** and **staging** flavors combined with debug and release build types


## Generating signed APK
From Android Studio:
1. ***Build*** menu
2. ***Generate Signed APK...***
3. Fill in the keystore information *(you only need to do this once manually and then let Android Studio remember it)*
