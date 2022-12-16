# POO-TV

```
Author: 
    {
        name: "Ionuț Găină",
        series: "324CD"
    }
```

## About

The project consists in implementing a platform for viewing movies and series. Where user can make
some actions, which are changing pages, purchasing, watching, liking and rating a movie. \
This project is having a database where is registered users and they can authenticate with an
username and password.


<img src="https://ocw.cs.pub.ro/courses/lib/exe/fetch.php?hash=2597cd&media=https%3A%2F%2Fi.imgur.com%2FS1ROjQr.gif" width="400" style="display: block; margin: 0 auto;" />

## Skel Structure

The project is divided in three packages for easier access to different parts in my app.

- Database - input classes, from where I take data about users, movies and actions
- Utilities - contains constants, filters, sorts and output
- Website - implementation for the website, contains all pages, logic for handling the page change
  and actions on page.

## Implementation

### Website Data

I implemented some **singleton** instances for saving current data which need to know live:

- **Current database** - contains the current database, where I fetch and edit data for my website.
- **Current page** - contains information about my page which consist name of the page and list of
  the movies which user can see on this page.
- **Current user** - contains the user information, to know about the logged user.

***

### Handlers

For the user to change pages or to interact with current page, I implemented handlers to choose the
action that the user wants. \
These handlers can throws output for debugging to know if the action was successfully or not.

- **ChangePageHandler** - is handler for the changing pages between them
- **OnPageHandler** - is handler for users action on the page
- **ErrorHandler** - is handler for successfully action output and failed one

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

## What can I do better for part two

For next part I want to change the pages from **Singleton pattern** to **Command Pattern**, which
will be useful to me when I want to make undo/redo for pages, because in a real web application you
can do it and is useful for users.

## Feedback

It was some inconsistencies between requirements and output, where needing to follow the output and
to take care about cases which were not specified. \
But overall I learned a lot from this part of the project and I really enjoyed thinking about
implementing what I use everyday.

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
