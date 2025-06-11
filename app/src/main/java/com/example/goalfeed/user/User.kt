package com.example.goalfeed.user

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.example.goalfeed.R

@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
@Composable
fun User() {
    val viewModel = hiltViewModel<UserViewModel>()
    val userData = viewModel.userData.collectAsStateWithLifecycle()

    if (userData.value == null) {
        GoogleLoginButton(
            modifier = Modifier,
            onClick = viewModel::launchCredentialManager
        )
    } else {
        Column {
            AsyncImage(
                model = userData.value?.photoUrl,
                contentDescription = "",
                modifier = Modifier.size(40.dp),
            )
            Text(
                userData.value?.displayName ?: ""
            )
            Text(
                userData.value?.email ?: ""
            )

            Button(onClick = { viewModel.signOut() }) {
                Text("Sign out")
            }
        }
    }
}

@Composable
private fun GoogleLoginButton(
    onClick: () -> Unit,
    modifier: Modifier
) {
    GoogleButtonUI(
        modifier = modifier,
        onClick = onClick,
    )
}

@Composable
private fun GoogleButtonUI(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, Color.LightGray),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp)
    ) {
        Image(
            modifier = Modifier.size(24.dp),
            painter = painterResource(R.drawable.dead),
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text("Continue with Google")
    }
}