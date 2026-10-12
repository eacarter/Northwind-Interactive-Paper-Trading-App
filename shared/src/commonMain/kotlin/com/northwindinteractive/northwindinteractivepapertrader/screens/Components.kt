package com.northwindinteractive.northwindinteractivepapertrader.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.northwindinteractive.northwindinteractivepapertrader.data.alpaca.models.PortfolioHistoryDto
import com.northwindinteractive.northwindinteractivepapertrader.presentation.AlpacaViewModel
import com.patrykandpatrick.vico.compose.cartesian.CartesianChartHost
import com.patrykandpatrick.vico.compose.cartesian.axis.HorizontalAxis
import com.patrykandpatrick.vico.compose.cartesian.axis.VerticalAxis
import com.patrykandpatrick.vico.compose.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.compose.cartesian.data.CartesianLayerDrawingModelInterpolator
import com.patrykandpatrick.vico.compose.cartesian.data.lineModel
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberLineCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.rememberCartesianChart

@Composable
fun PortfolioChart(
    history: PortfolioHistoryDto?
) {
    val modelProducer = remember {
        CartesianChartModelProducer()
    }

    LaunchedEffect(history) {

        val points = history?.timestamp
            ?.indices
            ?.mapNotNull { index ->
                val equity = history.equity.getOrNull(index)

                if (equity != null) {
                    index.toDouble() to equity
                } else {
                    null
                }
            }
            .orEmpty()

        if (points.isNotEmpty()) {
            modelProducer.runTransaction {
                lineModel {
                    series(
                        x = points.map { it.first },
                        y = points.map { it.second }
                    )
                }
            }
        }
    }

    if (history != null &&
        history.equity.any { it != null }
    ) {
        CartesianChartHost(
            chart = rememberCartesianChart(
                rememberLineCartesianLayer(
                    drawingModelInterpolator =
                        CartesianLayerDrawingModelInterpolator.line(
                            sweep = true
                        )
                ),
                startAxis = VerticalAxis.rememberStart(),
                bottomAxis = HorizontalAxis.rememberBottom()
            ),
            modelProducer = modelProducer,
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
        )
    } else {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp),
            contentAlignment = Alignment.Center
        ) {
            Text("No portfolio history available")
        }
    }
}

@Composable
fun TimeRangeSelector(alpaca : AlpacaViewModel){
    val periods = listOf("1D", "1W", "1M", "3M", "1A")

    var selectedPeriod by remember {
        mutableStateOf("1M")
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        periods.forEach { period ->
            TextButton(
                onClick = {
                    selectedPeriod = period

                    alpaca.loadPortfolioHistory(
                        period = period,
                        timeframe = when (period) {
                            "1D" -> "5Min"
                            "1W" -> "1H"
                            else -> "1D"
                        }
                    )
                }
            ) {
                Text(period)
            }
        }
    }
}