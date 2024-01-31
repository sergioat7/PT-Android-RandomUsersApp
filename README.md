# PT-Android-RandomUsersApp

RandomUsersApp is a simple Master-Detail Android app for a technical code test. It fetchs users from Random User Generator API (https://randomuser.me/) and list them in a screen, with the possibility to see details of every item.

## Project Structure

This app is written in Kotlin and implements an MVVM architecture, with:
- View: show data to the user
- ViewModel: fetch data and pass it to the view
- Repository and Service: load data from backend

I have also used a Mapper to map data object from backend to custom objects that are going to be displayed to the user. I have only mapped needed data for details screen.

## Technologies Used
- **ViewModel and LiveData**
- **Hilt**
- **Retrofit**
- **Moshi**
- **Picasso**
- **Jetpack navigation**
- **Mockito**
- **MockitoKotlin**
- **Espresso**

## Other considerations

- For unit testing, I have preferred to use mocks instead of fakes because it was faster, but I think the best approach is to use fakes whenever possible, because they provide you more flexibility.

- I haven't extracted dimensions to a resource file because of lack of time.

- As I have not seen in Random User Generator API any method to get a single user from its id, I have decided to pass data between fragments and to share view model, because that way detail fragment could get user from list.
