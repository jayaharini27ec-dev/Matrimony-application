# Matrimony Connect

A simple Spring Boot matrimonial profile registration & browsing app, built with the
same stack and structure as the Course Registration System (CRS) sample: Spring Boot
+ Spring Data JPA + MySQL, plain HTML/CSS/JS on the frontend (no template engine).

## Features
- Sign up with a full matrimony profile (name, gender, age, religion, caste, marital
  status, education, occupation, city/state, contact number, about).
- Sign in / logout.
- Dashboard that lists other members' profiles and lets you filter by gender,
  religion, and city (via a small JSON API, fetched with plain JavaScript).

## Tech stack
- Java 21
- Spring Boot 4.1.1 (Web MVC + Data JPA)
- MySQL
- No frontend framework — static HTML/CSS/JS in `src/main/resources/static`

## 1. Create the database
```sql
CREATE DATABASE matrimony;
```

## 2. Configure credentials
Edit `src/main/resources/application.properties` and set your own MySQL
username/password (defaults are `root` / `root`):
```
spring.datasource.url=jdbc:mysql://localhost:3306/matrimony
spring.datasource.username=root
spring.datasource.password=root
```
`spring.jpa.hibernate.ddl-auto=update` is already set, so the `profile` table is
created automatically on first run — you don't need to create it by hand.

## 3. Run locally
```bash
./mvnw spring-boot:run
```
The app starts on **http://localhost:8084** (change `server.port` in
`application.properties` if you need a different port).

## 4. Build a deployable jar
```bash
./mvnw clean package
java -jar target/matrimony-0.0.1-SNAPSHOT.jar
```
Copy `target/matrimony-0.0.1-SNAPSHOT.jar` to your server (or a platform like
Render/Railway/EC2), make sure a MySQL database is reachable from it, set the
`spring.datasource.*` values (as env vars or by editing `application.properties`
before building), and run the jar the same way.

## Notes / limitations (same style as the CRS sample this was based on)
- Authentication is intentionally minimal, matching the original CRS project: there's
  no session/token-based security, so `dashboard.html` receives the logged-in
  username as a URL query parameter (`?user=...`) rather than a secure session. This
  is fine for a class project/demo but **should not be used as-is in production** —
  add Spring Security if you need real authentication.
- Passwords are stored in plain text in the database, again matching the original
  sample's simplicity. For anything real-world, hash passwords (e.g. with
  `BCryptPasswordEncoder`) before saving.
- There's no photo upload; add a `photoUrl` field or a file-upload endpoint if you
  want profile pictures.
