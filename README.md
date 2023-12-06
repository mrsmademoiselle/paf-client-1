# PAF - Client 1

First client for the application for my university module "Patterns and frameworks".

This game was created as a group project of 3 people in winter 2021.

<details>
    <summary>Pt. 1: Functional requirements</summary>
    <br>

**Overview:**

The focus of the module is on understanding and practically applying proven design patterns and frameworks commonly used in professional software projects, especially within the Java environment. As an examination task, a project needs to be constructed, consisting of at least client and server components. The project can be worked on by a team of 2-4 students.
<br><br>

**Functional Requirements:**

Implement a simple multiplayer game for a minimum of 2 players. As a project idea, the implementation of a well-known arcade classic is suggested, such as Tetris, Tank, Snake, Pac-Man, Dig Dug, or Blobby Volley. Alternatively, a board game or quiz game can be implemented. The game rules can be adjusted individually. Regarding the desired functionality of the application, there is room for creative ideas, to be coordinated with the respective module supervisor. While Java is not a particularly common language for game implementation, this uncommon choice allows for creativity in the design. Different solutions can be discussed with enthusiasm. Additionally, the humor typically associated with game development can enhance motivation. 

Regardless of the chosen project idea, the following requirements should be considered:

- A user should be able to register, log in, and log out.
- Each user's history of played games should be recorded and presented through simple evaluations (won and lost games, average score, etc.).
- Images should be requested via the API and displayed in the client at least at one meaningful point (e.g., user profile pictures).


***Server Component (Programming Language Java):***
Registered users and their game history should be centrally stored in a relational database. An object-relational mapping framework should be used for this purpose. An API should be provided for data exchange with the client, facilitating the exchange of data in JSON or XML format. The API should support JSON Web Tokens (JWT) for user authentication.

***Client Component:*** The client should include a graphical UI and fulfill the above functional requirements. In teams with ≥ 3 students, two clients using different frameworks should be created, sharing a server-side API. If only one client is implemented (i.e., the team consists of a maximum of 2 students), JavaFX should be used by default for this client—or alternatively, the Android Java API framework. If an additional client is created, any other framework, including the free choice of the programming language, can be chosen after consultation with the supervisor (e.g., desktop application with JavaFX, mobile application with Android, web application with Angular, etc.).

</details>

<details>
    <summary>Pt. 2: Other requirements</summary>
    
***Learning Outcome "Software Project in Java":*** Even if not all functional and non-functional requirements of the project task have been fully implemented, this learning outcome can be partially achieved once a functional, sufficiently complex application has been constructed, and organizational conditions (e.g., mandatory use of Git, Maven, JIRA, etc.) have been adhered to. The assessment also considers internal code quality with criteria such as code readability, adherence to coding conventions, error handling, and moderate internal documentation (cf. [Mar09]).

***Learning Outcome "Application of Design Patterns":*** Students consciously applied design patterns and could present and justify their advantageous use in the presentation.

***Learning Outcome "Selection of Architectural Patterns and Frameworks":*** Students could explain the architecture of the application they constructed and justify the architectural decisions.

***Learning Outcome "Distributed Communication":*** In their project, students implemented synchronous and asynchronous communication through suitable protocols to exchange data between client(s) and server. They can explain and justify their chosen approach.

***Learning Outcome "Parallel Processing":*** In their project, students consciously and meaningfully parallelized certain processing steps, for example, to avoid blocking the UI, and can explain the chosen approach.
    </details>
