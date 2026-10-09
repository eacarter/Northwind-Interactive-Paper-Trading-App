package com.northwindinteractive.northwindinteractivepapertrader.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.northwindinteractive.northwindinteractivepapertrader.data.alpaca.AlpacaApiServiceImpl
import com.northwindinteractive.northwindinteractivepapertrader.nav.Screen
import com.northwindinteractive.northwindinteractivepapertrader.presentation.AlpacaViewModel
//import com.northwindinteractive.northwindinteractivepapertrader.presentation.AlpacaViewModel
import com.northwindinteractive.northwindinteractivepapertrader.presentation.AuthViewModel
import com.northwindinteractive.northwindinteractivepapertrader.presentation.LoginUiState
import com.patrykandpatrick.vico.compose.cartesian.data.CartesianLayerDrawingModelInterpolator
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberLineCartesianLayer
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import northwindinteractivepapertrader.shared.generated.resources.Res
import northwindinteractivepapertrader.shared.generated.resources.ic_bell
import northwindinteractivepapertrader.shared.generated.resources.ic_logout
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel


    @Composable
//    @Preview
    fun LoginScreen(viewModel: AuthViewModel = koinViewModel(), navController: NavController){

        val state by viewModel.uiState.collectAsState()

        var email by remember { mutableStateOf("") }
        var pass by remember { mutableStateOf("")}
        var error by remember { mutableStateOf(false) }

        Column(modifier = Modifier.fillMaxSize().background(Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                "Northwind Interactive Paper Trading",
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(24.dp))
            OutlinedTextField(
                onValueChange = {email = it},
                value = email,
                label = { Text("Email") },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )
            Spacer(modifier = Modifier.height(24.dp))
            OutlinedTextField(
                onValueChange = {pass = it},
                value = pass,
                label = { Text("Password") },
                maxLines = 1,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
            Row {
                TextButton(
                    modifier = Modifier.padding(16.dp),
                    content = {
                        Text(
                            text = "Login",
                            fontSize = 24.sp
                        )
                    },
                    onClick = {
                        viewModel.signIn(email, pass)
                    }
                )
                TextButton(
                    modifier = Modifier.padding(16.dp),
                    content = {
                        Text(
                            text = "SignUp",
                            fontSize = 24.sp
                        )
                    },
                    onClick = {
                        viewModel.signUp(email, pass)
                    }
                )
            }
            Spacer(Modifier.height(8.dp))
            if (error) {
                Text(
                    "Login Error, Try Again Later",
                    color = Color.Red,
                    textAlign = TextAlign.Center,
                )
            }

            when (state) {
                is LoginUiState.Success -> {
                    navController.navigate(Screen.Portfolio.route)
                }

                is LoginUiState.Loading -> {
                    Box(
                        Modifier.fillMaxSize().background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
                is LoginUiState.Idle -> {
                }
                is LoginUiState.Error -> {
                    error = true
                }
            }
        }
    }

    @Composable
    fun PortfolioScreen(viewModel: AuthViewModel = koinViewModel(), alpaca: AlpacaViewModel = koinViewModel(), navController: NavController){

        val account by alpaca.account.collectAsState()

        LaunchedEffect(Unit) {
            alpaca.loadAccount()
        }

        Column(modifier = Modifier.background(color = Color.White).padding(vertical = 48.dp)) {
            Row (
                Modifier.fillMaxWidth().padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                IconButton(
                    onClick = {
                        viewModel.sighOut()
                        navController.navigate(Screen.Login.route)
                    }
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_logout),
                        contentDescription = "Sign out Button"
                    )
                }
                Text(
                    "Paper Trading",
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center
                )
                IconButton(
                    onClick = {  }
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_bell),
                        contentDescription = "Notification Bell Icon",
                        tint = Color.Black,
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    "Account Value"
                )
                Text(
                    "${account?.portfolioValue}",
                    fontSize = 28.sp
                )
                Text(
                    "+20,000",
                    fontSize = 12.sp
                )
                rememberLineCartesianLayer(
                    drawingModelInterpolator = CartesianLayerDrawingModelInterpolator.line(sweep = true),
                )
            }
            Card (modifier= Modifier.fillMaxWidth().padding(12.dp)){
                Row (modifier= Modifier.fillMaxWidth().padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly){
                    Column {
                        Text("Buying Power")
                        Text("${account?.buyingPower}")
                    }
                    Spacer(modifier = Modifier.width(24.dp))
                    Column {
                        Text("Cash")
                        Text("${account?.cash}")
                    }

                }
            }
            Card (modifier= Modifier.fillMaxWidth().padding(12.dp)){
                Row (modifier= Modifier.fillMaxWidth().padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly){
                    Column {
                        Text("Buying Power")
                        Text("$100,000")
                    }
                    Column {
                        Text("Cash")
                        Text("$100,000")
                    }
                    Column {
                        Text("Cash")
                        Text("$100,000")
                    }
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    "Positions"
                )
                TextButton(
                    content = {Text("See All")},
                    onClick = {}
                )
            }
            LazyColumn {

            }
        }
    }

@Composable
fun AlpacaTestScreen() {

    LaunchedEffect(Unit) {
        val client = createAlpacaHttpClient()

        try {
            val service = AlpacaApiServiceImpl(
                client = client,
                apiKey = "PKEH4BZAYEJLFXCMWSSAHKZJZP",
                secretKey = "AQtt82EFnVdXL5SYhptgbFpYvqY2ezmsj9gsPKib1dZ5"
            )

            val account = service.getAccount()

            println("ALPACA ACCOUNT: $account")
            println("PORTFOLIO VALUE: ${account.portfolioValue}")

        } catch (e: Exception) {
            println("ALPACA ERROR: ${e.message}")
        } finally {
            client.close()
        }
    }
}

fun createAlpacaHttpClient(): HttpClient {
    return HttpClient {
        expectSuccess = true

        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                }
            )
        }
    }
}
