package com.kursatkumsuz.animated_connection_alerter.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun MessageBar(
    message: String?,
    icon: Int,
    backgroundColor: Color,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = backgroundColor)
            .padding(WindowInsets.statusBars.asPaddingValues())
            .padding(5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(Modifier.width(5.dp))
        Icon(
            painter = painterResource(icon),
            contentDescription = "Message Bar Icon",
            tint = Color.White
        )
        Spacer(Modifier.width(5.dp))
        Text(text = message ?: "", color = Color.White, fontSize = 15.sp)
        Spacer(Modifier.width(5.dp))
    }
}