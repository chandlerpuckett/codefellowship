# CodeFellowship App

## Summary
Ahoy! Join thee codeFellow*SHIP*

## Operation
- start Spring from `CodeFellowshipApplication`
- visit `localhost:8080`
- from the home page visit `sign up` to sign up as a new user


## Feature Tasks
- [X] Splash page at `(/)` with links to "sign up" page
- [X] ApplicationUser with username, password, firstName, lastName,
dateOfBirth, bio
- [X] Create a user on "sign up" page
    - [X] password encoder
- [ ] page which allows viewing about a single user at route `/users/{id}`
    - [ ] include default profile picture w/ basic information
- [ ] ability to login to app
- [X] homepage, login, and registration routes are accessible to non-logged in users
- [ ] re-usable templates (at minimum 1 thymeleaf fragment used on multiple pages)
- [X] non-whitelabel error handling page that shows error code
- [ ] ensure that user registration also logs users into the app automatically
- [ ] add `Post` entity
    - [ ] `Post` has a `body` and a `createdAt` timestamp
    - [ ] logged in user should be able to create a `Post` and the post should belong to the 
            user that created it
- [ ] users posts should be visible on their profile page