# UniFit - Fitness Facility Booking System

UniFit is a fitness facility booking system built with Spring Boot, MySQL, and SendGrid API.

## Features

- **User Authentication:** Secure user registration and login.
- **Facility Booking:** Browse and book fitness facilities based on availability.
- **Booking Management:** View and manage your booking details.
- **Admin Dashboard:** Admins can manage facilities, bookings, and user accounts.

## Technologies Used

- **Spring Boot:** Backend framework for building Java-based applications.
- **MySQL:** Relational database for storing application data.
- **SendGrid API:** Used for sending email notifications.

## Getting Started

To run UniFit locally, follow these steps:

1. Clone the repository:
   ```bash
   git clone https://github.com/Pranav-XP/unifit-booking.git
   ```

2. Configure your MySQL database and update application.properties.

3. Set up your SendGrid API key for email notifications.

4. Build and run the application:
   ```bash
   ./mvnw spring-boot:run
   ```

5. Access the application at `http://localhost:8080`.

## Usage

- Visit the homepage to browse available facilities.
- Register or log in to make a booking.
- Admins can access the admin dashboard to manage facilities and bookings.
- Admin credentials is given in SetUpLoader config. PLEASE DO NOT USE THIS IN PRODUCTION!!

## Feature Ideas
- Add payment integration
- Create more fleshed out user notification system
- Tidy up maintenance scheduling module or make it its own module

## Contributing

This project was done as a requirement for CS241 University of the South Pacific. No contributions are required.

## License

This project is licensed under the [MIT License](LICENSE).

```

Feel free to customize it based on the specifics of your UniFit project. You might want to include additional sections like "Screenshots," "Known Issues," or "Future Enhancements" depending on the project's status and your plans for it.