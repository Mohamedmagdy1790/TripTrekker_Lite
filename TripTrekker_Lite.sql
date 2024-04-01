CREATE TABLE usertype (
   id SERIAL PRIMARY KEY,
   type VARCHAR(50) NOT NULL
);

CREATE TABLE roles (
  id SERIAL PRIMARY KEY, 
  role VARCHAR(50)
);

CREATE TABLE users (
   id SERIAL PRIMARY KEY,
   name VARCHAR(255) NOT NULL,
   email VARCHAR(50) UNIQUE NOT NULL,
   password VARCHAR(30) NOT NULL,
   usertype_id INTEGER,
   FOREIGN KEY (usertype_id) REFERENCES usertype(id)
);
CREATE TABLE userrole (
   user_id INTEGER,
   role_id INTEGER,
   PRIMARY KEY(user_id, role_id),
   FOREIGN KEY (user_id) REFERENCES users(id),
   FOREIGN KEY (role_id) REFERENCES roles(id)
);
CREATE TABLE address (
   id SERIAL PRIMARY KEY,
   line1 VARCHAR(255),
   line2 VARCHAR(255),
   city VARCHAR(100),
   state VARCHAR(100),
   zip VARCHAR(20),
   country VARCHAR(100)
);

CREATE TABLE customer (
   id SERIAL PRIMARY KEY,
   name VARCHAR(255) NOT NULL,
   address_id INTEGER,
   phone VARCHAR(20),
   user_id INTEGER,
   gender VARCHAR(10),
   FOREIGN KEY (address_id) REFERENCES address(id),
   FOREIGN KEY (user_id) REFERENCES users(id) -- Assuming there's a users table with an id column
);
CREATE TABLE booking_status (
   id SERIAL PRIMARY KEY,
   status VARCHAR(50)
);
CREATE TABLE payment_status (
   id SERIAL PRIMARY KEY,
   status VARCHAR(50)
);
CREATE TABLE payment_integration_type (
   id SERIAL PRIMARY KEY,
   type VARCHAR(50)
);
CREATE TABLE prefered_payment_settings (
   customer_id INTEGER,
   payment_integration_id INTEGER,
   FOREIGN KEY (customer_id) REFERENCES customer(id),
   FOREIGN KEY (payment_integration_id) REFERENCES payment_integration_type(id)
);

CREATE TABLE payment_configuration (
   id SERIAL PRIMARY KEY,
   payment_integration_id INTEGER,
   FOREIGN KEY (payment_integration_id) REFERENCES payment_integration_type(id)
);
CREATE TABLE notification_type (
   id SERIAL PRIMARY KEY,
   type VARCHAR(50)
);

CREATE TABLE notification_status (
   id SERIAL PRIMARY KEY,
   status VARCHAR(50)
);
CREATE TABLE notification (
   id SERIAL PRIMARY KEY,
   customer_id INTEGER,
   notification_type_id INTEGER,
   notification_status_id INTEGER,
   title VARCHAR(255),
   content TEXT,
   date DATE,
   FOREIGN KEY (customer_id) REFERENCES customer(id),
   FOREIGN KEY (notification_type_id) REFERENCES notification_type(id),
   FOREIGN KEY (notification_status_id) REFERENCES notification_status(id)
);
CREATE TABLE transaction (
   id SERIAL PRIMARY KEY,
   customer_id INTEGER,
   payment_type_id INTEGER,
   payment_status_id INTEGER,
   transaction_date DATE,
   total_amount DECIMAL(10, 2),
   FOREIGN KEY (customer_id) REFERENCES customer(id),
   FOREIGN KEY (payment_type_id) REFERENCES payment_integration_type(id),
   FOREIGN KEY (payment_status_id) REFERENCES payment_status(id)
);

CREATE TABLE transaction_details (
   id SERIAL PRIMARY KEY,
   transaction_id INTEGER,
   details TEXT,
   FOREIGN KEY (transaction_id) REFERENCES transaction(id)
);
CREATE TABLE flight_booking (
   id SERIAL PRIMARY KEY,
   customer_id INTEGER,
   booking_status_id INTEGER,
   flight_id INTEGER,
   booking_date DATE,
   total_cost DECIMAL(10, 2),
   transaction_id INTEGER,
   FOREIGN KEY (customer_id) REFERENCES customer(id),
   FOREIGN KEY (booking_status_id) REFERENCES booking_status(id),
   FOREIGN KEY (transaction_id) REFERENCES transaction(id)
);

CREATE TABLE hotel_booking (
   id SERIAL PRIMARY KEY,
   customer_id INTEGER,
   booking_status_id INTEGER,
   hotel_id INTEGER,
   booking_date DATE,
   total_cost DECIMAL(10, 2),
   transaction_id INTEGER,
   check_in_date DATE,
   check_out_date DATE,
   FOREIGN KEY (customer_id) REFERENCES customer(id),
   FOREIGN KEY (booking_status_id) REFERENCES booking_status(id),
   FOREIGN KEY (transaction_id) REFERENCES transaction(id)
);