# Sortify

## Overview

Sortify allows users to convert long URLs into shorter, more manageable links. It provides features such as tracking the
number of clicks on the shortened URL, viewing the last click date, and checking the expiration status of the URL. This
application aims to simplify the sharing of links and enhance the user's ability to monitor link performance.

<img src="/images/home.png" width="400">
<img src="/images/shorten.png" width="400">
<img src="/images/track.png" width="400">

## Features

- Shorten long URLs to easily shareable links.
- Track the number of clicks on each shortened URL.
- View the last time the link was clicked.
- Check the expiration status and activity of the shortened URL.
- User-friendly interface built with Thymeleaf and Bootstrap.

## Technologies Used

- **Spring Boot**: For backend development and URL processing.
- **MongoDB**: For storing URL data.
- **Thymeleaf**: For rendering dynamic HTML pages.
- **Bootstrap**: For responsive design and user interface components.
- **JavaScript**: For handling dynamic form submission and frontend logic.

## Getting Started

To set up and run this application locally, follow these steps:

### Prerequisites

- Java Development Kit (JDK) 23
- Gradle
- MongoDB

### Running MongoDB with Docker Compose

If you want to run MongoDB using Docker, you can use the provided `docker-compose.yml` file. Make sure you have Docker
and Docker Compose installed.

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/Gemois/shortify.git
   cd shortify
   ```

2. **Run MongoDB**:
   ```bash
   docker-compose up -d
   ```

### Build and Run the Application

1. **Build the Application**:
   ```bash
   ./gradlew build
   ```

2. **Run the Application**:
   ```bash
   ./gradlew bootRun
   ```

3. Open your web browser and go to `http://localhost:8080` to access the application.

## Usage

- Input a long URL into the provided field and click on the "Shorten URL" button.
- After shortening, you can track your URL by clicking on the "Track Clicks" button.
- Share the shortened URL as needed, and monitor its performance through the application.