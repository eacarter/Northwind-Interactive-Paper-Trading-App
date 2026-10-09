# Northwind Interactive Paper trading App #

A Paper Trading app that allows users to learn and get comfortable trading stocks with fake money.
It uses Alpaca for the trading API and allows for the user to choose the AI model of there choice
for addtional information on their portfolio, researching different ticker symbols as well as guide
and automated trading. The Stack used is provided below:


- Kotlin Multiplatform
- Compose Multiplatform
- Firebase Auth
- Cloud Firestore
- Alpaca
- Koin
- Ktor

To use it just clone the project and create an account at https://app.alpaca.markets/account/login 
and generate your api and secret key and place them in the init map in the KoinInitializer.kt file.