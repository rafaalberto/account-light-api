# Coding Challenge: Providing a RESTful API Java for Banking Account Management.

The reason for this app is providing a solution without Heavy frameworks <br/>
The App works by saving, listing, deleting Banking Accounts and transferring money among them. <br/>
The main technologies are: <br/>

- Java 11
- Undertow (Embedded Server)
- REST concepts
- H2 Database
- Unit Tests
- REST Assured (API Unit Test)

# API Services

# Create Account

POST: http://localhost:8080/accounts

Request:
{
	"name": "Rafael"
}

Response:
{
    "id": 1,
    "name": "Rafael",
    "balance": 0.00
}

# Update Account

PUT: http://localhost:8080/accounts/1

Request:
{
	"name": "John"
}

Response:
{
    "id": 1,
    "name": "John",
    "balance": 0.00
}

# Delete Account

DELETE: http://localhost:8080/accounts/1

Response: 204 - No Content

# Find Account By Id

GET: http://localhost:8080/accounts/1

Response:
{
    "id": 1,
    "name": "Rafael",
    "balance": 0.00
}

# Find All Accounts

GET: http://localhost:8080/accounts

Response:
[
    {
        "id": 1,
        "name": "Rafael",
        "balance": 1000.00
    },
    {
        "id": 2,
        "name": "Mary",
        "balance": 2000.30
    },
    {
        "id": 3,
        "name": "Pedro",
        "balance": 800.00
    }
]

# Banking Transaction - Deposit

POST: http://localhost:8080/transactions

Request: 
{
	"accountSenderId": 1,
	"accountReceiverId": 1,
	"amount": 5000,
	"type": "DEPOSIT"
}

Response:
{
    "success": true,
    "description": "Deposit executed successfully"
}

# Banking Transaction - Withdraw

POST: http://localhost:8080/transactions

Request: 
{
	"accountSenderId": 1,
	"accountReceiverId": 1,
	"amount": 100,
	"type": "WITHDRAW"
}

Response:
{
    "success": true,
    "description": "Withdraw executed successfully"
}

# Banking Transaction - Transfer

POST: http://localhost:8080/transactions

Request: 
{
	"accountSenderId": 1,
	"accountReceiverId": 2,
	"amount": 500,
	"type": "TRANSFER"
}

Response:
{
    "success": true,
    "description": "Transfer executed successfully"
}