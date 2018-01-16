# Eartrainer
This project is being created as a programming project in Informatics class with Ms Vazquez in January 2018.

[Download](#download) and [install](#installation).

[Time Schedule](#time-schedule)

[Project Goals](#project-goals)

[Code of Conduct](#code-of-conduct)

## TODOS
- [ ] Initialise Project
	- [x] Gradle Command
	- [x] Git init
	- [x] Copy this readme
	- [x] Add .gitignore
	- [x] Add .gitattributes
	- [x] Add already defined deps
	- [ ] Create predefined directory structure
	- [ ] Write first classes
	- [ ] Write first tests
	- [x] Push to github
- [ ] Create the Project Dossier for MSI
	- [ ] Add a Project Description 1/4 page
	- [ ] Definition of the Problem
	- [ ] Analysis of the Requirements
	- [ ] Specifications
	- [ ] Draft (UML)
	- [ ] Copy Implementation
	- [ ] Results and Tests
- [ ] Finish the Project
	- [ ] Write a working interface
	- [ ] Write a working application
	- [ ] Add Salt and Pepper!!!
- [ ] Create the Project Dossier for MSII
	- [ ] Write the TODO for this part.
- [ ] Have a Beer where we celebrate the finished Project

## The Idea
A game based on the game of [set](https://en.wikipedia.org/wiki/Set_(game) "Wikipedia").

However, we do not want to create a clone of this game but rather convert it into something new.
Our idea is to use this notion of a set and apply it to music. 
Instead of having a 3x3 grid where one has to find a set, we will use a simpler version of the game.
In this version two 'cards' already lie on the table. By the rules of set, the third card is clearly determined.

In our version of the game the visual features of colour, number, symbol and shading are replaced by auditive features.
These auditive features are:

 - Which interval is it (out of a predetermined list of three)?
 - Is the lower or the higher note played first or are they played simultaneously?
 - In which range is the interval (high, middle, low)?
 - By which instrument is the interval played (out of a predetermined list of three)?
 
[//]: # (@TODO: Define high, middle and low range.)

[//]: # (@TODO: Refactor this into a project description part)


## Setup
### Git and Github
Git is 'the' version management tool out there. It makes version management a breeze. Github makes distributed working easy.
To get an overview over this tool checkout:
 - [My Bash and Git Cheatsheet](https://github.com/stymphalide/git_bash_cheatsheet/blob/master/cheatsheet.md "git and bash cheatsheet")
 - [A really good git tutorial](http://gitimmersion.com/ "gitimmersion") (I really recommend checking this out)
 - [An introduction to Github's markdown](https://guides.github.com/features/mastering-markdown/ "mastering-markdown") (This file is written in this markdown.)
 - [A Markdown Cheatsheet](https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet "Markdown Cheatsheet")
 - [A Markdown Editor](https://jbt.github.io/markdown-editor/ "markdown editor")

Note: Since Windows and Linux handles line endings differently there has to be some set up in a .gitattributes file. (Also because of gradle)
[Dealing with line endings](https://help.github.com/articles/dealing-with-line-endings/)

### Gradle
A Build tool for our application. Takes the task of compiling the code and running tests away from us. Can also be used to create a `.jar` file. It also sets up a basic project, and most importantly can be used as a plugin in eclipse. This way we can make sure, that the setup of our application 'fits' for the IDE but can still be developed outside.
 - [Gradle Build Tool](https://gradle.org/ "gradle build tool") with tutorials on how to use it
 - [Eclipse Plugin Installation guide](http://www.vogella.com/tutorials/EclipseGradle/article.html "gradle plugin for eclipe") Remark: Make sure to install `gradle 4.4.1` or the corresponding version. I don't know if there can be conflicts if we use different versions (though I doubt that).
 - [Github source code](https://github.com/gradle/gradle "gradle")
 - [Add dependencies](https://stackoverflow.com/questions/20700053/how-to-add-local-jar-file-dependency-to-build-gradle-file "gradle dependency stackoverflow")
 - [The Maven Architecture]((https://maven.apache.org/guides/introduction/introduction-to-the-standard-directory-layout.html))


### Testing
 - [JUnit on Github](https://github.com/junit-team/junit4 "source code") 
 - [Getting started with junit](https://github.com/junit-team/junit4/wiki/Getting-started "getting started with junit")
 - [junit dcoumentation](http://junit.org/junit4/javadoc/latest/ "junit documentation") (Don't get eye cancer!)
 - [Why Testing?](https://www.quora.com/Why-is-testing-code-important "why testing is important") (Note we are probably only going to do 	the first category of tests that he describes.)
 - [TDD](https://www.agilealliance.org/glossary/tdd/ "tdd") would be nice if we could actually implement some parts of the test-driven paradigm. (Note that under time pressure this is not going to work.)

### Front End
After the 'coding part' and the testing part we should decide on which libraries to use to accomplish out task. One thing that needs to be covered is the how to make the Graphical User Interface (Something that I consider highly overrated!!!). We are going to use Java's go-to tool, namely javafx.
Why the fuck does anyone write such shit???
 - [JavaFx API](https://docs.oracle.com/javase/8/javafx/api/toc.htm "JavaFX API") (Don't get eye cancer here!!!)
 - [JavaFX Tutorial](http://code.makery.ch/library/javafx-8-tutorial/ "JavaFX Tutorial")
 - [Use JavaFX's WebView](https://docs.oracle.com/javafx/2/webview/jfxpub-webview.htm "JavaFX WebView") Could be used to just use html5 and css3 as well as js? to render the view. (This approach could be considered cheating)
 - [JavaFX Guide](https://javabeginners.de/Frameworks/JavaFX/FXML.php)

### Further Libraries
Since we are going to produce sound in one way or another it is necessary to check out some midi/sound libraries for Java.

Java ships with some integrated libraries to accomplish these tasks:
 - `javax.sound.sampled`
 - `javax.sound.midi`

A higher level library could be jFugue:
 - [jFugueGithub](https://github.com/dmkoelle/jfugue "jfugue/github")
 - [jFugue](http://www.jfugue.org/ "jFugue") A high level midi parser, that take care of most of the dirty details, works out of the box.(?)
 - [Some basic examples](http://www.jfugue.org/examples.html "jfugue examples")
 - [jFugue API](http://www.jfugue.org/doc/index.html "jFugue API")


## Time Schedule
List of important dates.

- [ ] 19.01.2018: Project Goals + Additional Goals defined
- [ ] 14.02.2018: [Milestone I](http://www.vazquez-informatik.ch/files/AKSA_ProgProjLE1_MilestoneI.pdf "milestone1")
- [ ] 21.03.2018: [Milestone II](http://www.vazquez-informatik.ch/files/AKSA_ProgProjLE1_MilestoneII.pdf "milestone2")

[//]: # (@TODO: Make it exacter -> intermediate steps)


## Project Structure
The Project has the basic gradle structure.
The source files i.e. the part that we write is in the ./src/ directory.


### Tree
Make a tree of the project structure.

[//]: # (@TODO: tree . > tree_test)


## Installation
### Prerequisites
You need to have gradle installed:
[Gradle Build Tool](https://gradle.org/ "gradle build tool")

### Download

Download an exectuable .jar archive [here](somerepository "exectuable")

[//]: # (@TODO: Create a repository where an executable .jar file will be placed. I have done some research that .exe file is not likely to work. byAB)

Or clone this repository

	git clone https://github.com/stymphalide/eartrainer.git

	cd eartrainer
	gradle build
	gradle run

## Project Goals

 - Desktop-App: Eartrainer
 - Inspired by the game "Set", whereby the visual features are replaced by auditive ones.
 - There is a menu screen where the level can be chosen.
 - There is an in-game screen where the magic happens. 
 - There is an after-game screen where the results are shown.

### Additional Goals

 - Multiple levels
 - Add (compose) background music in the menu and after-game-screen.

### Required Libraries
 
 - jFugue
 - javaFX
 - some random stuff
 - javax.sound.sampled

## Task Sharing
Task | Person
-----|-------
Level Design | Both
Dependency Management | Angelo
Tests | Both
Interface | Angelo
JavaFX | Angelo
Gradle Build | Angelo
jFugue | Tobias
Music Theory | Tobias
Music Composition | Tobias

## Code of Conduct
Based on the requirements in the Milestone I file.

- Main-Class is commented as follows:


	```
	// Date: 12.01.2018
	// Project Name: Eartrainer
	// Names: Angelo Birrer G4L and Tobias Seefeld G4L
	// Main Sources: TBD 
	// Code Sharing: TBD
	```

[//]: # (@TODO: Evaluate main sources. Code sharing follows from task sharing.)

- Every Class has a `/*classdoc*/` comment where the usage of the class is described.
- After every import there is a `// What is this for` comment
- Important variables (Probably instance variables of the class) are explained
- Important Functions (Those that are used by the main function?) are explained
- Larger code fragments such as (Nestings, loops constructors etc.)
- Copy-pasted stackoverflow code has to be commented.
- Larger code bits are stated on top of the class (I hope this will not happen too often.)
- The code should be slim! (Refactoring is a crucial part of TDD!)

[//]: # (Was für en Gugus!)


## Sources
Everything we stumble upon doing research comes into this part.
### JavaFX
- [JavaFX Guide](https://javabeginners.de/Frameworks/JavaFX/index.php)
- [JavaFX Wikipedia](https://de.wikipedia.org/wiki/JavaFX)
- [JavaFx API](https://docs.oracle.com/javase/8/javafx/api/toc.htm "JavaFX API")
- [FXML Sources](https://docs.oracle.com/javafx/2/fxml_get_started/jfxpub-fxml_get_started.htm)
- [100 javafx tutorials](http://javafxjq.blogspot.ch)

### JFugue
- [Some basic examples](http://www.jfugue.org/examples.html "jfugue examples")
- [Compose music like a pro rapid music programming with java jfugue](https://www.techrepublic.com/article/compose-music-like-a-pro-rapid-music-programming-with-java-jfugue/)

## License
To be determined

[//]: # (@TODO: License that important??)
