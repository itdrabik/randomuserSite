# Nordea Recruitment Task – randomuserSite

This project was developed as part of a technical recruitment challenge.  
The objective was to implement both **API and GUI automated tests** for the [https://randomuser.me](https://randomuser.me) service using **Java, TestNG, Selenium, and REST Assured**.

The entire task was completed within approximately **5 hours**, including implementation, parameterization, Maven configuration, and output formatting.

---

## ✅ Task Summary

- Use **TestNG** to define a test suite (`FAR.xml`)
- Configure the project using **Maven**, with support for IntelliJ execution
- Enable **parameterization** through Maven profiles (`gender`)
- Implement:
  - One **API test** for retrieving user data from the API
  - One **GUI test** for verifying UI content and exporting it as a CSV file

---

## 📋 Detailed Requirements

### GUI Test:
- Navigate to `https://randomuser.me`
- Use **XPath** to locate and validate **six user values** under the `#user_value` element
- Extract those values and save them as a **CSV row**
- Generate a **unique CSV file** on each run

### API Test:
- Accept a `gender` parameter (default: `female`)
- Use it in a GET request to retrieve **10 random users**
- Deserialize the response into a `List<User>` object
- Log user names and login credentials

---

## 🛠️ Technologies Used

- Java 17
- TestNG
- Selenium WebDriver
- REST Assured
- Jackson (JSON serialization)
- Maven (build and profile management)

---

## 📁 Project Structure

```
src/
├── test/
│   ├── java/
│   │   └── com/
│   │       └── randomuser/
│   │           └── tests/
│   │               ├── api/
│   │               │   ├── ApiTest.java          # API test logic
│   │               │   └── User.java             # POJO for API response
│   │               └── gui/
│   │                   └── GuiTest.java          # GUI test logic
│   └── resources/
│       └── FAR.xml                               # TestNG suite file
├── pom.xml                                       # Project configuration
├── .gitignore
├── test_results_*.csv                            # Output CSV files per run
└── target/                                       # Maven build output
```

---

## ▶️ How to Run

### From IntelliJ:
1. Open `ApiTest` or `GuiTest`
2. Run directly using TestNG runner

### From Maven (default gender):
```bash
mvn test
```

### From Maven with profile (e.g. male):
```bash
mvn test -P male
```

---

## 📤 Output Example (CSV)

Each GUI test run generates a new, uniquely named CSV file with user values:

```csv
Isabella Cruz,isabella.cruz@example.com,2/4/1951,6029 Oak Ridge Ln,(546) 432-4317,red123
```

---

## 🧠 Notes

- Parameter `gender` is configurable via Maven profile and injected at runtime
- Tests are structured clearly and independently (API and GUI)
- XPath is used exclusively in the UI test to meet the requirements
- Output is written to a CSV file in the root directory, with timestamp-based filenames
- All logic is modular and clean, designed for maintainability

---

## 👤 Author

**Kamil Drabik**  
📧 it.drabik@gmail.com  
🔗 [LinkedIn](https://linkedin.com/in/kamil-drabik-2757a6150)  
🔗 [GitHub Portfolio](https://github.com/itdrabik)

---

> This project demonstrates the ability to deliver fast, reliable test automation under time constraints — with proper structure, parameterization, and real-world tools.
