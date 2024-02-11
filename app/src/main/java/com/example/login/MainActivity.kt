package com.example.login

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.login.ui.theme.LoginTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    LoginLayout()
                }
            }
        }
    }
}

@Composable
fun LoginLayout() {
    var credentials by remember { mutableStateOf(Credentials()) }
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .padding(horizontal = 49.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 7.dp, bottom = 5.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.annajiah_logo_color),
                contentDescription = null,
                modifier = Modifier
                    .size(149.dp)
            )
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 35.dp)
        ) {
            Text(
                text = stringResource(R.string.no_hp),
                modifier = Modifier
                    .padding(bottom = 1.dp)
                    .align(alignment = Alignment.Start)
            )
            NoHPField(
                value = credentials.hp,
                onValueChanged = {data -> credentials = credentials.copy(hp = data)},
                modifier = Modifier
                    .padding(14.dp)
                    .fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(14.dp))
            Text(
                text = stringResource(R.string.pass),
                modifier = Modifier
                    .padding(bottom = 1.dp)
                    .align(alignment = Alignment.Start)
            )
            PassField(
                value = credentials.pw,
                onValueChanged = { data -> credentials = credentials.copy(pw = data)},
                submit = {
                    if (!checkCredentials(credentials, context)) credentials = Credentials()
                },
                modifier = Modifier
                    .padding(14.dp)
                    .fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(14.dp))
            Box {
                Button(
                    onClick = {
                        if (!checkCredentials(credentials, context)) credentials = Credentials()
                    },
                    shape = RoundedCornerShape(7.dp),
                    modifier = Modifier
                        .padding(14.dp)
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Green
                    )
                ) {
                    Text(stringResource(R.string.masuk))
                }
            }
            Spacer(modifier = Modifier.height(25.dp))
            Text(
                text = stringResource(R.string.kontak_cs),
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(bottom = 21.dp)
                    .align(alignment = Alignment.CenterHorizontally)
            )
            Text(
                text = stringResource(R.string.ikhwan),
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                color = Color.Blue,
                modifier = Modifier
                    .padding(bottom = 21.dp)
                    .align(alignment = Alignment.CenterHorizontally)
                    .clickable(
                        onClick = {
                            Toast
                                .makeText(
                                    context,
                                    R.string.menghubungi_cs_ikhwan,
                                    Toast.LENGTH_SHORT
                                )
                                .show()
                        }
                    )
            )
            Text(
                text = stringResource(R.string.akhwat),
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                color = Color.Blue,
                modifier = Modifier
                    .padding(bottom = 21.dp)
                    .align(alignment = Alignment.CenterHorizontally)
                    .clickable(
                        onClick = {
                            Toast
                                .makeText(
                                    context,
                                    R.string.menghubungi_cs_akhwat,
                                    Toast.LENGTH_SHORT
                                )
                                .show()
                        }
                    )
            )
        }
        Box(
            modifier = Modifier
                .align(Alignment.End)
                .padding(top = 75.dp, bottom = 7.dp)
        ) {
            Button(
                onClick = {
                    Toast.makeText(
                        context,
                        R.string.menuju_halaman_faq, Toast.LENGTH_SHORT
                    )
                        .show()
                },
                shape = RoundedCornerShape(7.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 27.dp)
                    .border(
                        BorderStroke(width = 1.dp, color = Color.Black),
                        shape = RoundedCornerShape(49)
                    ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                )
            ) {
                Row {
                    Text(
                        stringResource(R.string.lihat_faq),
                        color = Color.Blue
                    )
                    Spacer(modifier = Modifier.width(7.dp))
                    Icon(
                        Icons.Outlined.Info,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

fun checkCredentials(cred: Credentials, context: Context): Boolean {
    if (cred.isNotEmpty()) {
        Toast.makeText(context,
            context.getString(R.string.menuju_halaman_beranda),
            Toast.LENGTH_SHORT).show()
        return true
    } else if (cred.hp.isEmpty() && cred.pw.isEmpty()){
        Toast.makeText(context,
            context.getString(R.string.nohp_dan_password_harus_diisi),
            Toast.LENGTH_SHORT).show()
        return false
    } else if (cred.hp.isEmpty() && cred.pw.isNotEmpty()){
        Toast.makeText(context,
            context.getString(R.string.nohp_harus_diisi),
            Toast.LENGTH_SHORT).show()
        return false
    } else {
        Toast.makeText(context,
            context.getString(R.string.password_harus_diisi),
            Toast.LENGTH_SHORT).show()
        return false
    }
}

data class Credentials(
    var hp: String = "",
    var pw: String = "",
    var remember: Boolean = false
) {
    fun isNotEmpty(): Boolean {
        return hp.isNotEmpty() && pw.isNotEmpty()
    }
}

@Composable
fun NoHPField(
    value: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = stringResource(R.string.masukan_nohp),
    placeholder: String = stringResource(R.string.hp)
) {
    val focusManager = LocalFocusManager.current
    val leadingIcon = @Composable {
        Icon(
            Icons.Default.Person,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.primary
        )
    }
    TextField(
        value = value,
        onValueChange = onValueChanged,
        modifier = modifier
            .border(
                BorderStroke(width = 1.dp, Color.Gray ),
                shape = RoundedCornerShape(21)
            ),
        leadingIcon = leadingIcon,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions(
            onNext = { focusManager.moveFocus(FocusDirection.Down) }
        ),
        placeholder = { Text(placeholder) },
        label = { Text(label) },
        visualTransformation = VisualTransformation.None
    )
}

@Composable
fun PassField(
    value: String,
    onValueChanged: (String) -> Unit,
    submit: () -> Unit,
    modifier: Modifier = Modifier,
    label: String = stringResource(R.string.pw),
    placeholder: String = stringResource(R.string.pass)
) {
    var isPasswordVisible by remember { mutableStateOf(false) }
    val leadingIcon = @Composable {
        Icon(
            Icons.Default.Lock,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.primary
        )
    }
    val trailingIcon = @Composable {
        IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
            Icon(
                if (isPasswordVisible) Icons.Default.FavoriteBorder else Icons.Default.Favorite,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
    TextField(
        value = value,
        singleLine = true,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        modifier = modifier
            .border(
                BorderStroke(width = 1.dp, Color.Gray ),
                shape = RoundedCornerShape(21)
            ),
        onValueChange = onValueChanged,
        label = { Text(label) },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password
        ),
        keyboardActions = KeyboardActions(
            onDone = { submit() }
        ),
        placeholder = { Text(placeholder) },
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()
    )
}

@Preview(showBackground = true)
@Composable
fun TipTimeLayoutPreview() {
    LoginTheme {
        LoginLayout()
    }
}
