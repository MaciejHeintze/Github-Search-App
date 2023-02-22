
# GitHub Repository Search Information Android App

## Table of contents
* [Overview](#Overview)
* [Architecture](#Architecture)
* [Screenshots](#Screenshots)
* [Getting Started](#Getting-Started)
* [Limitations](#Limitations)
* [Future Improvements](#Future-Improvements)

## Overview

This Android application allows the user to input a GitHub repository name in the following format: <owner>/<repository>, for example, bright/shouldko. When the repository name is provided, the app fetches and displays the following information about it using the GitHub API:

* Repository ID
* List of commits in the repository
* Each commit is described by message, SHA value, and author's name
* List is sorted by date, with the latest commits at the top
The app caches the previously used repositories data, and the user can access the history of the last used repositories. Using the history, the user can open the repository information again in the same way as if they typed the repository name manually again.

Even when the app is offline, the user can still see the previously fetched repository information.

Additionally, the user can send selected commits' data (message, SHA value, author's name) using a 3rd party application installed on the phone (e.g., an email client or Facebook Messenger) in the following way:

* The commits on the list can be selected and unselected
* The user selects at least one commit on the list
* The user taps a "send" button
* The user is asked by the system to choose an application they want to use for sending a message
* A new message in the selected app already contains passed commits information so that the user does not have to copy-paste it manually.

## Architecture
The application is built using the following technologies:

* Kotlin
* Compose
* Koin
* Coroutines
* Flow
* Retrofit
* Room local database
* Compose Navigation
* ViewModel


The application architecture follows the following design pattern:

* Clean Architecture
* MVVM
* Use Cases

This design pattern ensures maintainability, testability, and scalability. Despite the app and it's features are small I have implemented and designed the architecture as it would be for bigger application. This application is open for extension of any features related as well.

## Screenshots

![Search screen](screenshots/search.jpg | height=400px)

![Search result](screenshots/searchResult.jpg | height=400px)

![Error popup](screenshots/errorPopup.jpg | height=400px)

![Saved repositories](screenshots/savedRepos.jpg | height=400px)

![Send commits](screenshots/send.jpg | height=400px)


## Getting Started
To get started with the app, follow these steps:

Download the APK file from:

[Download APK](https://drive.google.com/file/d/1tYbuqiI86zFJ2OKG0Yn0JTJQi1TSwQib/view?usp=sharing)

* Install the APK file on your Android device
* Launch the app and start by entering a GitHub repository name and repository owner in the required format.
* Initially "Search" button is inactive - after providing input for both ower and repository input fields it is activated to search API
* Bottom navigation provides navigation to two screens: Search and History
* In History screen user can see all of the repositories searched in the past (even without internet connection).
* When user presses on History screen list item it is redirected to Search screen and all of the information reagrding repository and commits are visible for user (also without internet connection)
* In Search screen user can select commit in order to send it's information using 3rd party application installed on the phone (like for example Messenger)
* After selecting one or more checkboxes Send button is visible at the bottom of commits list
* Pressing Send button opens dialog with 3rd party applications which can be used to send commit data

## Limitations

* The app only supports GitHub repositories and does not support repositories hosted on other platforms.
* The app only supports sending data to 3rd party applications installed on the device, and it does not support sending data to web services or APIs directly.

## Future Improvements

* Internet connection observer which is notifying user regarding phone internet connection (Snackbar with notification)
* UI/UX improvements for a more intuitive and user-friendly experience
* Add autocomplete option to input fields to be able to prompt user with possible search query results
* Implement unit tests


