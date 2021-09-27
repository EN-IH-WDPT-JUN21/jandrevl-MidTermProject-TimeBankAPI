Under Construction...



![](src/main/resources/images/TimeBankLogo.JPG)

###GET: /admin

###GET: /admin/users/{id}

###POST: /admin/users/accountholders
- "name": String,
- "username": String,
- "password": String,
- "dateOfBirth": LocalDate (yyyy-MM-dd),
- "primaryAddressId": Long,
- "mailingAddressId": Long (optional)

###GET: /admin/accounts/accountholders/primaryowners/{primaryOwner}

###GET: /admin/accounts/accountholders/{id}

###POST: /admin/accounts/checkingaccounts
- "primaryOwnerId": Long,
- "secondaryOwnerId": Long (optional),
- "secretKey": String

###POST: /admin/accounts/savings
- "primaryOwnerId": Long,
- "secondaryOwnerId": Long (optional),
- "secretKey": String,
- "minimumBalance": BigDecimal (optional. x >= 100. If present, savingInterestRate must be defined),
- "savingInterestRate": BigDecimal (optional. 0 < x < 0.5. If present, minimumBalance must be defined)

###POST: /admin/accounts/creditcardaccounts
- "primaryOwnerId": Long,
- "secondaryOwnerId": Long (optional),
- "secretKey": String,
- "creditCardInterestRate": BigDecimal (optional. x >= 0.1. If present, creditLimit must be defined),
- "creditLimit": BigDecimal (optional. 0 < x < 100000. If present, creditCardInterestRate must be defined)

###GET: /admin/accounts/balance/{accountNumber}

###PATCH: /admin/accounts/balances/{accountNumber}
- "balance": BigDecimal

###POST: /admin/addresses
- "streetAndNumber": String,
- "zipCode": Long (max 10 digits),
- "city": String,
- "country": String

###GET: /admin/addresses/{id}

###POST: /admin/thirdparties

###GET: /accountholders

###GET: /accountholders/accounts

###GET: /accountholders/accounts/balance/{accountNumber}

###PATCH: /accountholders/transfers

###PATCH: /thirdparty/transactions



