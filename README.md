<div align="center">
<h1>
 <b style="font-size:24px;line-height:24px;vertical-align:middle;">Levi9 Hackathon Challenge</b>
</h1>

![GitHub Repo stars](https://img.shields.io/github/stars/momcilovicluka/5-days-in-cloud-task?style=for-the-badge&color=0000ff) ![GitHub last commit](https://img.shields.io/github/last-commit/momcilovicluka/5-days-in-cloud-task?style=for-the-badge&color=0000ff) ![GitHub repo size](https://img.shields.io/github/repo-size/momcilovicluka/5-days-in-cloud-task?style=for-the-badge&color=0000ff)

This project is a Java-based application for managing matches between teams, keeping track of player stats, and updating team information. Below is the documentation detailing the setup, build, and usage instructions, along with the technologies used.
</div>

# 🚀 Setup Guide
## 🛠 Prerequisites
To successfully build and run the project, you will need the following tools and dependencies:
- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/downloads/): Version 23 is used, but should be able to run on earlier by changing java version in pom.xml ☕
- [Maven](https://maven.apache.org/download.cgi) 🏗
- All project dependencies are in pom.xml so they will be downloaded automatically ⬇

## ⚡️ Getting Started

Follow these steps to set up and run the Spring Boot application locally.

### 1. Clone the Repository 📂
- 1.1 With git:
```bash
git clone https://github.com/momcilovicluka/5-days-in-cloud-task.git
```
or
- 1.2 With GitHub cli:
```bash
gh repo clone momcilovicluka/5-days-in-cloud-task
```

### 2. Navigate to the Project Directory 📁
```bash
cd 5-days-in-cloud-task
```

### 3. Install Dependencies 🚀
Use Maven to compile and package the application:
```bash
mvn clean install
```

### 4. Run the application! 💨
```bash
mvn spring-boot:run
```
You can also run it as a normal jar after compilation if you're really keen:
```bash
java -jar target/5-days-in-cloud-task-0.0.1-SNAPSHOT.jar
```

### 5. Access the application ▶
The app is served on `http://localhost:8080`

## **🛠 Used Technologies**

| Technology          | Description                                                                                                                                   |
|---------------------|-----------------------------------------------------------------------------------------------------------------------------------------------|
| [**☕ Java**](https://www.java.com/en/)                         | Core programming language used to build the application.                                         |
| [**🌱 Spring Boot**](https://spring.io/)                        | Framework for creating REST APIs, managing application lifecycle, and simplifying configurations.|
| [**📦 Maven**](https://maven.apache.org/)                       | Dependency management and build automation tool.                                                 |
| [**🌶 Lombok**](https://projectlombok.org/)                     | Reduces boilerplate code with annotations for getters, setters, and more.                         |
| [**💾 H2 Database**](https://www.h2database.com/html/main.html) | Lightweight, in-memory database for development and testing.                                     |
