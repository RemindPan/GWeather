## GWeather

* A weather demo app.
* Apply clean architecture + MVVM as project architecture

### Architecture

feature -> data -> domain
feature -> domain
feature -> common


### Statement

* This is a demo weather app depend on location service
* Home page will show weather of current time and following days
* Detail page will show following hours weather(exclude time before current hour)
* Domain package should be a pure kotlin/java package which will not depend on any Android os api and just define the declear for server data and repositories interfaces.
* Data package will response convert domain entities to ui display ectities and implement for domain repositoies interfaces. Also will has network releate to get api datas.
* Features package will response for UI display. Adopt MVVM.
* Common package will only used by features packages, define base classes, constants, common widgets and so on.

### Dependency lib
* retrofit
* okhttp
* gson
