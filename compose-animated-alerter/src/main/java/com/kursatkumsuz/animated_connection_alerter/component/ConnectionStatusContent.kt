package com.kursatkumsuz.animated_connection_alerter.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.constraintlayout.compose.ConstraintLayout
import com.kursatkumsuz.animated_connection_alerter.R
import com.kursatkumsuz.animated_connection_alerter.util.animation.animateMessageBarColor
import com.kursatkumsuz.animated_connection_alerter.util.animation.noInternetCardSlideInAnimation
import com.kursatkumsuz.animated_connection_alerter.util.animation.noInternetCardSlideOutAnimation
import com.kursatkumsuz.animated_connection_alerter.util.color.CardBackgroundColor
import com.kursatkumsuz.animated_connection_alerter.util.color.CardContentColor
import com.kursatkumsuz.animated_connection_alerter.util.color.MessageBarErrorColor
import com.kursatkumsuz.animated_connection_alerter.util.color.MessageBarSuccessColor
import kotlinx.coroutines.delay

@Composable
fun ConnectionStatusContent(
    modifier: Modifier = Modifier,
    isVisible: Boolean = false,
    messageBarMessageNoConnection: String = "No Connection!",
    messageBarMessageBackOnline: String = "Back Online",
    messageBarIconSuccess: Int = R.drawable.ic_success,
    messageBarIconError: Int = R.drawable.ic_warning,
    messageBarBackgroundColorSuccess: Color = MessageBarSuccessColor,
    messageBarBackgroundColorError: Color = MessageBarErrorColor,
    cardBackgroundColor: Color = CardBackgroundColor,
    cardContentColor: Color = CardContentColor,
    cardNoInternetIcon: Int = R.drawable.ic_no_internet,
    messageBarDelay: Long = 3000L,
    cardDelay: Long = 1000L,
    noInternetCardTitle: String = "No Internet Connection!",
    noInternetCardMessage: String = "Please check your connection or watch downloaded movies",
    noInternetCardIconContentDescription: String = "No Internet Icon",
    content: @Composable () -> Unit = {},
    navigationButton: @Composable () -> Unit = {},
) {
    var cardVisible by remember { mutableStateOf(false) }
    var messageBarVisible by remember { mutableStateOf(false) }

    val backgroundColor by animateMessageBarColor(
        isVisible,
        messageBarBackgroundColorSuccess,
        messageBarBackgroundColorError
    )

    val barIcon = if (isVisible) messageBarIconError else messageBarIconSuccess
    val messageBarMessage = if (isVisible) messageBarMessageNoConnection else messageBarMessageBackOnline

    LaunchedEffect(key1 = isVisible) {
        if (isVisible) {
            delay(messageBarDelay)
            messageBarVisible = true
            delay(cardDelay)
            cardVisible = true
        } else {
            delay(messageBarDelay)
            cardVisible = false
            delay(cardDelay)
            messageBarVisible = false
        }
    }

    ConstraintLayout(modifier = modifier) {
        val (messageBar, content, noInternetCard) = createRefs()

        AnimatedVisibility(
            visible = messageBarVisible,
            modifier = Modifier.constrainAs(messageBar) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ) {
            MessageBar(
                message = messageBarMessage,
                icon = barIcon,
                backgroundColor = backgroundColor
            )
        }

        Box(
            modifier = Modifier.constrainAs(content) {
                top.linkTo(messageBar.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ) {
            content()
        }

        AnimatedVisibility(
            modifier = Modifier
                .constrainAs(noInternetCard) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            visible = cardVisible,
            enter = noInternetCardSlideInAnimation(),
            exit = noInternetCardSlideOutAnimation(),
        ) {
            NoInternetConnectionCard(
                titleText = noInternetCardTitle,
                messageText = noInternetCardMessage,
                contentDescription = noInternetCardIconContentDescription,
                cardBackgroundColor = cardBackgroundColor,
                cardContentColor = cardContentColor,
                noInternetIcon = cardNoInternetIcon,
                navigationButton = navigationButton
            )
        }
    }
}