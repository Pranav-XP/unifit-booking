# UniFit - Fitness Facility Booking System

UniFit is a fitness facility booking system built with Spring Boot, MySQL, and SendGrid API.

![Booking Page](https://media.licdn.com/dms/image/D4D2DAQEyqD1XITz1ew/profile-treasury-image-shrink_800_800/0/1698304018143?e=1698912000&v=beta&t=7WMYwjVKZPPLr5KUWK-IbLLSLDl1dxDUwYBWRnXiiLE)
<br>
![Admin Dashboard](https://media.licdn.com/dms/image/D4D2DAQGyukzCs2sAwQ/profile-treasury-image-shrink_800_800/0/1698303964301?e=1698912000&v=beta&t=7v0y2ta1ztgsrUTfmXiI88ibfyKboC_F4HBavBHeulA)
<br>
![Facility Selection](https://media.licdn.com/dms/image/D4D2DAQH-A9jVFJIWFw/profile-treasury-image-shrink_800_800/0/1698304197646?e=1698912000&v=beta&t=XXjdfpOQVYWBvTLfDlH0ANDvoo23wZn_yaBwXHVm2hI)




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

## Package by Feature
- Promotes Best Practices
- High cohesion
- Low coupling
- High modularity
- Encapsulation (Minimize Scope)
- Allows some classes to set their access modifier package-private instead of public.
- Modularity
- Since each package is limited to classes related to a particular feature, it is easy to break code down into a microservice later on.
- Maintainability
- Reduces the need to navigate between packages since all classes needed for a feature are in the same package.
-  Testability
- Since classes are not public, they can only be accessed by tests in the same package. This allows you to write more focused tests.
- Domain Driven Design
- Promotes Domain Driven Design

## Package by Layer
- Promotes Best Practice (❌)
- Low cohesion
- High coupling
- Low modularity
- Encapsulation (❌)
- Most classes must be public
- Modularity (❌)
- Since each layer is limited to classes related to a particular layer, it is difficult to break code down into a microservice later on.
- Maintainability (❌)
- Since classes are scattered across packages, it is difficult to find the class you are looking for.
- Testability (❌)
- Since classes are public, they can be accessed by tests in any package. This allows you to write less focused tests.
- Domain Driven Design (❌)
- Promotes Database Driven Design

## Contributing

This project was done as a requirement for CS241 University of the South Pacific. No contributions are required.

## License

This project is licensed under the [MIT License](LICENSE).
