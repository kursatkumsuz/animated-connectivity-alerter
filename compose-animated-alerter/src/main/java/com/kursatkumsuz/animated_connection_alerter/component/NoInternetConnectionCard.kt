package com.kursatkumsuz.animated_connection_alerter.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kursatkumsuz.animated_connection_alerter.R
import com.kursatkumsuz.animated_connection_alerter.util.color.CardBackgroundColor
import com.kursatkumsuz.animated_connection_alerter.util.color.CardContentColor

@Composable
internal fun NoInternetConnectionCard(
    titleText: String = "No Internet Connection!",
    messageText: String = "Please check your connection or watch downloaded movies",
    contentDescription: String = "No Internet Icon",
    cardBackgroundColor: Color = CardBackgroundColor,
    cardContentColor: Color = CardContentColor,
    noInternetIcon: Int = R.drawable.ic_no_internet,
    navigationButton: @Composable () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .padding(WindowInsets.navigationBars.asPaddingValues())
            .padding(horizontal = 20.dp),
        colors = CardColors(
            containerColor = cardBackgroundColor,
            contentColor = cardContentColor,
            disabledContentColor = Color.Transparent,
            disabledContainerColor = Color.Transparent
        )
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 20.dp, horizontal = 30.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = noInternetIcon),
                    contentDescription = contentDescription
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = titleText,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(messageText)

            Spacer(modifier = Modifier.height(10.dp))

            navigationButton()
        }
    }
}