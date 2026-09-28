# BloodBank – Blood Bank Inventory and Donor Eligibility Tracker

Spring Boot + MySQL project based on the entities and architecture in the supplied design.

## Entities used
1. Donor
2. BloodGroup
3. Donation
4. BloodUnit
5. IssueRecord
6. IssueUnitMap

## Architecture
- Client: Web Client / Postman / Swagger / Admin Console
- Presentation/API: REST Controllers
- Business Logic: Services
- Persistence: Spring Data JPA Repositories
- Database: MySQL

## Business rules implemented
- Donor eligibility requires a minimum 90-day gap since the last donation.
- Blood unit expiry must not be before today.
- Only AVAILABLE, non-expired units can be issued.
- Blood group must match the requested group when issuing a unit.
- Issued units are marked ISSUED and recorded in ISSUE_UNIT_MAP.

## IntelliJ setup
1. Extract the ZIP.
2. Open the extracted folder in IntelliJ IDEA.
3. Make sure Java 17 is selected.
4. Wait for Maven dependencies to finish.
5. Create a MySQL database named `bloodbank` (the application can also create it automatically).
6. Open `src/main/resources/application.properties`.
7. Change:
   `spring.datasource.password=YOUR_MYSQL_PASSWORD`
   to your MySQL password.
8. Run `BloodBankApplication.java`.
9. Server runs at `http://localhost:8080`.

## Example Postman requests

### Create donor
POST `http://localhost:8080/api/donors`
```json
{
  "name": "Arun Kumar",
  "bloodGroup": "O_POSITIVE",
  "lastDonationDate": null
}
```

### Check donor eligibility
GET `http://localhost:8080/api/donors/{donorId}/eligibility`

### Create donation
POST `http://localhost:8080/api/donations`
```json
{
  "donorId": "PASTE_DONOR_UUID",
  "collectionDate": "2026-09-28"
}
```

### Add blood unit
POST `http://localhost:8080/api/blood-units`
```json
{
  "donationId": "PASTE_DONATION_UUID",
  "bloodGroup": "O_POSITIVE",
  "expiryDate": "2026-10-28"
}
```

### View available units
GET `http://localhost:8080/api/blood-units/available/O_POSITIVE`

### Issue blood
POST `http://localhost:8080/api/issues`
```json
{
  "unitId": "PASTE_UNIT_UUID",
  "bloodGroup": "O_POSITIVE",
  "recipientDetails": "Patient-001",
  "requestDate": "2026-09-28"
}
```

## Note
The image's UML/ER design uses `IssueUnitMap` as the mapping table between `IssueRecord` and `BloodUnit`. This project keeps that structure instead of replacing it with unrelated entities.
