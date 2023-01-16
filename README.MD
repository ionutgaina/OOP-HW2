# POO-TV

```
Author: 
    {
        name: "Ionuț Găină",
        series: "324CD"
    }
```

## About

### Part 1
The project consists in implementing a platform for viewing movies and series. Where user can make
some actions, which are changing pages, purchasing, watching, liking and rating a movie. \
This project is having a database where is registered users and they can authenticate with an
username and password.

### Part 2
For the next part implemented new features, which are:
- adding a new movie from the database
- deleting a movie from the database
- back to the previous page
- subscribe to a genre
- for premium users can get a recommendation for a movie based on the genre they subscribed to
- 
<img src="https://ocw.cs.pub.ro/courses/lib/exe/fetch.php?hash=2597cd&media=https%3A%2F%2Fi.imgur.com%2FS1ROjQr.gif" width="400" style="display: block; margin: 0 auto;" />

## Skel Structure

The project is divided in three packages for easier access to different parts in my app.

- Database - input classes, from where I take data about users, movies and actions
- Utilities - contains constants, filters, sorts and output
- Website - implementation for the website, contains all pages, logic for handling the page change
  and actions on page.

## Implementation

### Website Data

#### Part 1
I implemented some **singleton** instances for saving current data which need to know live:

- **Current database** - contains the current database, where I fetch and edit data for my website.
- **Current page** - contains information about my page which consist name of the page and list of
  the movies which user can see on this page.
- **Current user** - contains the user information, to know about the logged user.

#### Part 2

Added new data classes for the new features:
- **Notification** - contains information about the notification, which is sent to the user when
  is added, deleted a new movie in the database or when is received a recommendation.
- **Recomandation** - contains information about the recommendation, which is sent to the user
  when is subscribed to a genre and is premium user.

***
### Handlers

For the user to change pages or to interact with current page, I implemented handlers to choose the
action that the user wants. \
These handlers can throws output for debugging to know if the action was successfully or not.

#### Part 1
- **ChangePageHandler** - is handler for the changing pages between them
- **OnPageHandler** - is handler for users action on the page
- **ErrorHandler** - is handler for successfully action output and failed one

#### Part 2
- **Page List Handler** - handles the page change and the back action for the user, with a list of
  movies
- **Database Action Handler** - handles the actions for the database, like adding, deleting and a movie.

***

### Pages

Made for each page a Singleton instance (the ***pages*** package), to use their methods where I
need, because I don't need to create multiple instances of this page, I used this pattern to use
effectively the memory. \
In these Singleton instances is implemented all functionality for changing to page from page and
verification if is correctly changed also actions on it.

***

### Actions

For these, I can say more about actions from ***movies*** page, where I sorted and filtered movies
to correct display for users. \
The filters and sorts was implemented with **Strategy Pattern**, which splits multiple algorithms.

- Filters - Implemented with an interface and for filtering used **streams**, because it helped me a
  lot to keep my code clean and not make many mistakes in what I do.
- Sorts - Implemented with arraylist method ```.sort```, using lambda functions changed the way to
  sort according to the requirements. Used **Factory Pattern** to create dynamically sorts for what
  i need.

 <br>

  Recommendation was implemented with streams and lambda functions, to get the movies which are
    from the genre which user subscribed to, using filters and sorts to get the best movie for the
    user.
<br> <br>
  Notification was implemented with **Builder Pattern**, which is used to create objects with
    different parameters, in my case I used it to create a notification with different types.

***

## What can I do better

- I can use more **Design Patterns** to make my code more clean and easy to understand

- I can use more **Java 8** features, like streams and lambda functions, to make my code more
  clean and easy to understand

## Feedback

The project was a valuable learning experience. 
Utilizing streams and design patterns in the creation of the website was a great way 
to improve the efficiency and organization of the code. 
The project provided a good introduction to the concepts and gave me a better understanding 
of how to apply them in real-world projects. Overall, the homework was well-designed and provided 
a solid foundation for further learning in the field. Good job.

<br></br>
<br></br>
<center> 
<h2 style="margin: 0 auto;">Thank you for the work you have done</h2>
</center>
<img src="https://gifdb.com/images/high/gojill-cat-thank-you-9eiqz5rqpliyluox.webp" 
        height="200"
        width="200"
        alt="Picture" 
        style="display: block; margin: 0 auto;" />
