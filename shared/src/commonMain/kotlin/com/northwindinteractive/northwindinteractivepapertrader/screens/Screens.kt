package com.northwindinteractive.northwindinteractivepapertrader.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ScrollField
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.northwindinteractive.northwindinteractivepapertrader.nav.Screen
import com.northwindinteractive.northwindinteractivepapertrader.presentation.AlpacaViewModel
//import com.northwindinteractive.northwindinteractivepapertrader.presentation.AlpacaViewModel
import com.northwindinteractive.northwindinteractivepapertrader.presentation.AuthViewModel
import com.northwindinteractive.northwindinteractivepapertrader.presentation.LoginUiState
import com.patrykandpatrick.vico.compose.cartesian.data.CartesianLayerDrawingModelInterpolator
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberLineCartesianLayer
import northwindinteractivepapertrader.shared.generated.resources.Res
import northwindinteractivepapertrader.shared.generated.resources.compose_multiplatform
import northwindinteractivepapertrader.shared.generated.resources.ic_activity
import northwindinteractivepapertrader.shared.generated.resources.ic_ai_spark
import northwindinteractivepapertrader.shared.generated.resources.ic_back
import northwindinteractivepapertrader.shared.generated.resources.ic_bell
import northwindinteractivepapertrader.shared.generated.resources.ic_chevron_right
import northwindinteractivepapertrader.shared.generated.resources.ic_logout
import northwindinteractivepapertrader.shared.generated.resources.ic_microphone
import northwindinteractivepapertrader.shared.generated.resources.ic_search
import northwindinteractivepapertrader.shared.generated.resources.ic_star
import northwindinteractivepapertrader.shared.generated.resources.northwind_mark
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel


    @Composable
    fun LoginScreen(viewModel: AuthViewModel = koinViewModel(), navController: NavController){

        val state by viewModel.uiState.collectAsState()

        var email by remember { mutableStateOf("")}
        var pass by remember { mutableStateOf("")}
        var error by remember { mutableStateOf(false) }

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
                        onValueChange = { email = it },
                        value = email,
                        label = { Text("Email") },
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    OutlinedTextField(
                        onValueChange = { pass = it },
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
                                navController.navigate(Screen.SignUp.route)
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
                }
            }
            is LoginUiState.Error -> {
                error = true
            }
        }
    }

    @Composable
    fun SignUpScreen(viewModel: AuthViewModel= koinViewModel(), navController: NavController){

        var email by remember { mutableStateOf("")}
        var pass by remember { mutableStateOf("")}
        var apikey by remember { mutableStateOf("Optional")}
        var secretkey by remember { mutableStateOf("Optional")}

        Column(modifier = Modifier.fillMaxSize().background(Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            IconButton(
                onClick = { navController.currentBackStack},
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_back),
                    contentDescription = "Notification Bell Icon",
                    tint = Color.Black,
                    modifier = Modifier.padding(12.dp)
                )
            }

            Text(
                "Northwind Interactive Paper Trading",
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(24.dp))
            OutlinedTextField(
                onValueChange = { email = it },
                value = email,
                label = { Text("Email") },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )
            Spacer(modifier = Modifier.height(24.dp))
            OutlinedTextField(
                onValueChange = { pass = it },
                value = pass,
                label = { Text("Password") },
                maxLines = 1,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
//            Spacer(modifier = Modifier.height(24.dp))
//            OutlinedTextField(
//                onValueChange = { apikey = it },
//                value = apikey,
//                label = { Text("Alpaca Api Key") },
//                maxLines = 1,
//                visualTransformation = PasswordVisualTransformation(),
//                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
//            )
//            Spacer(modifier = Modifier.height(24.dp))
//            OutlinedTextField(
//                onValueChange = { secretkey = it },
//                value = secretkey,
//                label = { Text("Alpaca Secret Key") },
//                maxLines = 1,
//                visualTransformation = PasswordVisualTransformation(),
//                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
//            )
            Row {
                TextButton(
                    modifier = Modifier.padding(16.dp),
                    content = {
                        Text(
                            text = "Sign Up",
                            fontSize = 24.sp
                        )
                    },
                    onClick = {
                        viewModel.signUp(email, pass, apikey = apikey, secretkey= secretkey)
                        //TODO() this i changing to just they have to get there keys and store them locally
                    }
                )
            }
        }
    }

    @Composable
    fun PortfolioScreen(viewModel: AuthViewModel = koinViewModel(), alpaca: AlpacaViewModel = koinViewModel(), navController: NavController){

        val account by alpaca.account.collectAsState()
        val positions by alpaca.positions.collectAsState()
        val portHistory by alpaca.portHistory.collectAsState()
        val loading by alpaca.isLoading.collectAsState()

        val scrollState = rememberScrollState()

        if(loading){
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
        else{
        Column(modifier = Modifier.background(color = Color.White).padding(top = 48.dp, start= 12.dp, end= 12.dp ).fillMaxSize().verticalScroll(scrollState)) {
            Row(
                Modifier.fillMaxWidth().padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
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
                    onClick = { }
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
                    "$${account?.portfolioValue}",
                    fontSize = 28.sp
                )
                Text(
                    "$${portHistory?.profitLoss?.last()} (${portHistory?.profitLossPercent?.last()}%)",
                    fontSize = 12.sp,
//                    color = if (portHistory?.profitLoss?.last()!!.toFloat() < 0 && portHistory?.profitLoss?.last() != null) Color.Red else Color.Green
                )
                PortfolioChart(portHistory)
                TimeRangeSelector(alpaca)
            }
            Card(modifier = Modifier.fillMaxWidth().padding(12.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Column {
                        Text("Buying Power")
                        Text("$${account?.buyingPower}")
                    }
                    Spacer(modifier = Modifier.width(24.dp))
                    Column {
                        Text("Cash")
                        Text("$${account?.cash}")
                    }

                }
            }
            Card(modifier = Modifier.fillMaxWidth().padding(12.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_ai_spark),
                        contentDescription = "Notification Bell Icon",
                        tint = Color.Black,
                        modifier = Modifier.padding(12.dp)
                    )
                    Column {
                        Text(
                            "AI Portfolio Summary",
                            style = TextStyle(
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Text(
                            "Your portfolio is up 1.2% today, Tech exposure increased by 12% this week",
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    IconButton(
                        onClick = { }
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_chevron_right),
                            contentDescription = "Notification Bell Icon",
                            tint = Color.Black,
                            modifier = Modifier.padding(12.dp)
                        )
                    }
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Positions"
                )
                TextButton(
                    content = { Text("See All") },
                    onClick = {}
                )
            }

            if (positions.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth().weight(1f)
                ) {
                    items(
                        positions,
                        key = { it.symbol }
                    ) { item ->
                        Card(
                            modifier = Modifier.fillMaxWidth().padding(12.dp).clickable(
                                onClick = {
                                    navController.navigate(Screen.ResearchDetail.route)
                                }
                            )
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth().padding(18.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(verticalArrangement = Arrangement.SpaceEvenly) {
                                    Text(item.symbol)
                                }
                                Column(verticalArrangement = Arrangement.SpaceEvenly) {
                                    Text(item.avgEntryPrice)
                                    Text(
                                        item.pnlnc,
                                        color = if (item.pnlnc.toFloat() < 0) Color.Red else Color.Green
                                    )
                                }
                            }
                        }
                    }
                }
            }
            else{
                Column (modifier = Modifier.fillMaxWidth().padding(12.dp)){
                    Text("Head over to the research tab and start trading")
                }
            }
        }

        }
    }
@Composable
fun ResearchScreen(alpaca: AlpacaViewModel = koinViewModel(), navController: NavController){

    val watchList by alpaca.watchList.collectAsState()

    val selection : List<Unit> = listOf(WatchListComponent(list= watchList))

    var i by remember {mutableStateOf(0)}
    val scrollState = rememberScrollState()
    var search by remember { mutableStateOf("")}

    Column(
        modifier = Modifier.fillMaxSize().padding(top = 48.dp, start = 12.dp, end = 12.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon(
                    painter = painterResource(Res.drawable.ic_search),
                    contentDescription = "Search Icon",
                    tint = Color.White,
                )
            },
            trailingIcon = {
                FloatingActionButton(
                    modifier = Modifier.size(24.dp),
                    containerColor = Color.Blue,
                    shape = CircleShape,
                    onClick = {/**TODO: text to speech**/ },
                    content = {
                        Icon(
                            modifier = Modifier.size(18.dp),
                            painter = painterResource(Res.drawable.ic_microphone),
                            contentDescription = "Search Icon",
                            tint = Color.White,
                        )
                    }
                )
            },
            onValueChange = { search = it },
            value = search,
            label = { Text("") },
            placeholder = { Text(
                "Search symbols, companies, or ask AI",
                overflow = TextOverflow.Ellipsis
            ) },
            maxLines = 1,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        //TODO: since no popular, or etf endpoint exist, im turning this into scroable watchlist row
        Row(
            modifier = Modifier.fillMaxWidth().padding(6.dp).horizontalScroll(scrollState),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            Button(
                onClick = {
                    i = 0
                }
            ){
                Text(
                    "Watchlist",
                    fontSize = 12.sp
                )
            }
            Button(
                onClick = {}
            ){
                Text("AI Scanner",
                    fontSize = 12.sp)
            }
            Button(
                onClick = {}
            ){
                Text("Popular",
                    fontSize = 12.sp)
            }
            Button(
                onClick = {}
            ){
                Text("ETF",
                    fontSize = 12.sp)
            }
        }
        selection[i]
    }
}

@Composable
@Preview
fun ResearchDetail(/*onBackClick: () -> Unit*/){
    Column(modifier = Modifier.background(Color.White).fillMaxSize().padding(top= 48.dp, start = 12.dp, end= 12.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            IconButton(
                onClick = { }
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_back),
                    contentDescription = "Notification Bell Icon",
                    tint = Color.Black,
                    modifier = Modifier.padding(12.dp)
                )
            }
            Row {
                IconButton(
                    onClick = { }
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_bell),
                        contentDescription = "Notification Bell Icon",
                        tint = Color.Black,
                        modifier = Modifier.padding(12.dp)
                    )
                }
                IconButton(
                    onClick = { }
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_star),
                        contentDescription = "Notification Bell Icon",
                        tint = Color.Black,
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }
        }
        Text("NVDA")
        Text("Price")
        Text("Change")
        //TODO: Graph local
        Card( modifier = Modifier.fillMaxWidth().padding (12.dp)) {
            Text("Your Position")
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                Column (){
                    Text("Shares")
                    Text("10")
                }
                Column (){
                    Text("Avg Cost")
                    Text("$123.43")
                }
                Column (){
                    Text("Market Value")
                    Text("$1234.20")
                }
            }
        }

        Card( modifier = Modifier.fillMaxWidth().padding (12.dp)) {
            Text("Unrealized P and L Goes in this card")
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                Column (){
                    Text("Shares")
                    Text("10")
                }
                Column (){
                    Text("Avg Cost")
                    Text("$123.43")
                }
                Column (){
                    Text("Market Value")
                    Text("$1234.20")
                }
            }
        }

        Row(modifier = Modifier.fillMaxWidth()){
            Button(
                modifier = Modifier.fillMaxWidth(.5f),
                onClick = {}
            ){
                Text("BUY")
            }
            Button(
                modifier = Modifier.fillMaxWidth(.5f),
                onClick = {}
            ){
                Text("Sell")
            }
        }

    }
}
