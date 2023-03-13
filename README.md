# Introduction

This project is created as part of the Toppan Fullstack Developer Assessment. Original problem statement can be found at https://github.com/RyanToppan/CoDev-Assignment-FullStack-Spring

Before going into the local development setup, there are several assumptions made when building the solution. Since no questions are allowed to be asked to clarify these assumptions, the next section will list some of the important assumptions and elaborate on why they are made.

## Assumptions

Backend Assumptions:

1. Mapping of the People database country_code to the API call country_code follows the ISO-3611 country code standard. The people database stores country_code as big_int, but the country_code supplied through the API is in a string in the form of XX (e.g. SG, ID, TH, etc). It is not stated what kind of mapping we should follow, which might cause problems when the app is tested with Toppan's database. For this project, it is assumed that the mapping follows the ISO standard and the csv mapping is taken from https://github.com/lukes/ISO-3166-Countries-with-Regional-Codes/blob/master/slim-2/slim-2.csv.

2. Database will be loaded through a `data.sql` file and not through an API. Since there is no instruction to explicitly create a controller to insert the test database, it is assumed that the database will be loaded through `data.sql`. Hence, the controller is not configured to handle bulk inserts of data.

3. There is no secondary sorting we must follow for `/getTop3ReadBooks` results.

Frontend Assumptions:

1. Upon startup, the page shown will be one with 'No data found' and only after clicking the 'Get country' will it populate the data.
2. Upon clicking the 'Get country' button it will fetch a random country code and send a request to `/getTop3ReadBook` with the random country code as a parameter. This because in the problem statement README, the instruction mentions to call to `/getTop3ReadBook?country_code=SG` which makes it ambiguous whether should the button always call with country code `SG` or should it use the randomly fetched country_code.

# Setting Up

### Pre-Requisite

The setup instruction assumes the machine already has the following software/packages installed (the value in brackets are version that I run it with):

- ReactJS (18.2.0)
- npm (8.14.0)
- Node (16.16.0)
- Java (17)
- Docker (20.10.21)
- Git (2.28.0)
- IntelliJ

### Section 1. Clone GitHub Repository

To clone the github repository go to the directory you want the project to be cloned and run `git clone`

```
cd my_directory
git clone https://github.com/danipurwadi/toppan-fullstack-spring.git
```

### Section 2: Setting up BackEnd in IntelliJ

It is recommended to use IntelliJ as it simplifies a lot of the setup. It is recommended to open the `backend` folder of this repository as opening it at the root caused some issues for me.

Step 1. Open the backend folder on IntelliJ using `File > Open`

Step 2. Run the `pom.xml` file. You can do this by clicking the green play button at the top of the file or `right click on pom.xml > Maven > Reload project`

Step 3. Setup database using docker.

Assuming you have docker installed, you can go to the `docker-compose.yml` file and run the file by clicking the green play button on IntelliJ or right click and select run file.

If you do not use intellij you use the terminal go to the backend folder `cd backend` and run `docker compose up -d`

To check this is done correctly, run `docker compose ps` and it should show you something like below:

```
NAME                COMMAND                  SERVICE             STATUS              PORTS
backend-db-1        "docker-entrypoint.s…"   db                  running             0.0.0.0:5332->5432/tcp
```

Step 4. Run backend
The last thing to do is to go to the `BackendApplication.java` file in `src/main/java/com/dani/BackendApplication.java` and run the file. In IntelliJ you can do so my pressing the green play button or right click and select run.

Once that is done the termianl should print out be something like below:

```
2023-03-13T16:22:02.417+08:00  INFO 19840 --- [           main] com.dani.BackendApplication              : Started BackendApplication in 12.453 seconds (process running for 13.064)
```

Step 5. Setting up custom data in DB (Optional);

By default the database will be populated with data in `data.sql`. If you would like to insert a different set of data, you can go ahead and rewrite the sql commands inside `data.sql`

### Section 3: Setting up the FrontEnd

Step 1. Install necessary packages

Go to the `frontend/` directory and run `npm install`. It might show some warning of vulnerabilities, as I am not updated with the latest packages, but it can still run despite the warnings.

Step 2. Run the application

Run `npm start` in the terminal and it should automatically open up your browser and load the page `http://localhost:3000/`

It should boot up a page and you're done!

# Last Note

If you encounter any difficulty in setting up, feel free to contact me using my email/phone number in the resume. Thank you!
