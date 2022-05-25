# Android challenge

## The task

We would like you to build an app to search for flights.

You are provided with a codebase containing a bit of code so that you can start working on the task faster. It
includes some parts of the UI, basic network layer implementation with DTOs & DI setup. Feel free to modify any file
you find in the repo – this is only there to help you out.

Please develop an Android app with the following requirements:

---

### Search screen

* User can search for flights with the following criteria:
    * Origin & destination station
    * Departure date
    * Number of adults, teen & children
* Destination can only be selected after picking origin
* All fields need to be filled to make a search
* Number of adults must be greater than 1

### Station search screen

* User can select a station from a list
* Stations are grouped by country (country name to be shown before each group)
* User can use search field to filter the airports list
* Screen works with 2 configurations
    * Origin selection – all stations are available for selection
    * Destination selection – only stations to which we can travel from origin selection are available for selection 

### Results screen

* A list of results for the specified search criteria is shown  
* Show only the crucial information about the trip
* Make sure to handle network errors and empty results

___

**URLs:**

* for `AirportService` and `RoutesService`: <https://services-api.ryanair.com/>
* for `FlightService`: <https://www.ryanair.com/api/>

*Tip: For test purposes try DUB (Dublin) – STN (London Stansted) route – it has flights every day.*  

---

### What we expect from you

* Architecture for both domain and UI layer. Please avoid using framework libraries if possible
* Use some reactive library. Rx or Flow are preferred. Do not use Live Data
* Squeeze all the best from Kotlin!
* UI part is not crucial, we would more appreciate better code than great design in this task.
* Creativity - shine in areas you are best
* Understanding of your solution

### Questions for you

Please submit the answers to the following questions in the README.md file
* How long did you spend on the task?
* Rate from 0-10, how would you assess the complexity of the task? _0 - very trivial_, _10 - extremely complex_
* If you had more time (or if you were doing it in production) what would you have changed/improved?
* Which parts of the code would you like to highlight?
* In your opinion, which Google library was the best addition to the Android Dev world in the last years?

> If you have the need to comment the provided task, we would love to hear that from you 🙂  
> ...  
> ...

### Deadline time frame

You have __4 days__ to solve test, but it would be great if you will finish quicker. Keep in mind that we do not expect perfect solution from you.

---

#### Please fork this repository. After finishing a task, create pull request to the parent repository ``main`` branch.

---

In case of any questions feel free to drop us an email at _AndroidDevelopers@ryanair.com_ any time.

<img src="https://4.bp.blogspot.com/-NnAkV5vpYuw/XNMYF4RtLvI/AAAAAAAAI70/kdgLm3cnTO4FB4rUC0v9smscN3zHJPlLgCLcBGAs/s1600/Jetpack_logo%2B%25282%2529.png" height=150 width=150>

Fly high!

___

### COPYRIGHTS

Copyright licensed by &copy; Ryanair Labs