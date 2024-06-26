package com.example.stravel.screen

import android.view.Gravity
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.stravel.R
import com.example.stravel.ui.theme.CardColor
import com.example.stravel.viewmodel.UserViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

@Composable
fun LoginScreen(mainController: NavHostController, auth: FirebaseAuth, userViewModel: UserViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LoginContent(mainController, auth, userViewModel)
    }
}

@Composable
fun LoginContent(
    mainController: NavHostController,
    auth: FirebaseAuth,
    userViewModel: UserViewModel
) {
    var emailValue by remember{ mutableStateOf("") }
    var passwordValue by remember{ mutableStateOf("") }
    val customShape = RoundedCornerShape(4.dp)
    val context  = LocalContext.current
    val emailFocusRequester = FocusRequester()
    val passwordFocusRequester = FocusRequester()
    var isHidePassword by remember {
        mutableStateOf(true)
    }
    var showDialog by remember {
        mutableStateOf(false)
    }
    val database = Firebase.database.getReference("Accounts")
    val keyboardController = LocalSoftwareKeyboardController.current

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box (
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.login_image),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .align(Alignment.BottomCenter)
            )
        }
        Column (
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "ĐĂNG NHẬP",
                fontSize = 36.sp,
                modifier = Modifier.padding(top = 48.dp, bottom = 24.dp)
            )

            Spacer(modifier = Modifier.padding(8.dp))

            OutlinedTextField(
                value = emailValue,
                onValueChange = { emailValue = it },
                placeholder = {
                    Row {
                        Text("Nhập Email")
                    }
                },
                label = { Text(text = "Email") },
                leadingIcon = { Icon(Icons.Rounded.Email, contentDescription = null, tint = CardColor) },
                shape = RoundedCornerShape(16.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        passwordFocusRequester.requestFocus()
                    }
                ),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = CardColor,
                    unfocusedBorderColor = CardColor
                ),
                modifier = Modifier
                    .background(color = Color.Transparent)
                    .fillMaxWidth(0.7f)
                    .focusRequester(emailFocusRequester)
            )

            Spacer(modifier = Modifier.padding(8.dp))

            OutlinedTextField(
                value = passwordValue,
                onValueChange = { passwordValue = it },
                placeholder = {
                    Row {
                        Text("Nhập mật khẩu")
                    }
                },
                label = { Text(text = "Password") },
                leadingIcon = { Icon(Icons.Rounded.Lock, contentDescription = null, tint = CardColor) },
                visualTransformation = if (isHidePassword) {
                    PasswordVisualTransformation()
                } else {
                    VisualTransformation.None
                },
                trailingIcon = {
                    if (passwordValue != "") {
                        if (isHidePassword) {
                            IconButton(
                                onClick = {
                                    isHidePassword = !isHidePassword
                                }
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.hide_password_icon),
                                    contentDescription = null,
                                    tint = CardColor,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        } else {
                            IconButton(
                                onClick = {
                                    isHidePassword = !isHidePassword
                                }
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.show_password_icon),
                                    contentDescription = null,
                                    tint = CardColor,
                                    modifier = Modifier.size(24.dp)

                                )
                            }
                        }
                    }
                },
                shape = RoundedCornerShape(16.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = CardColor,
                    unfocusedBorderColor = CardColor
                ),
                modifier = Modifier
                    .background(color = Color.Transparent)
                    .fillMaxWidth(0.7f)
                    .focusRequester(passwordFocusRequester)
            )

            Spacer(modifier = Modifier.padding(8.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth(0.7f)
            ) {
                Text(
                    text = "Quay lại",
                    color = Color.Blue,
                    modifier = Modifier
                        .clickable {
                            mainController.navigateUp()
                        }
                )
                Text(
                    text = "Quên mật khẩu",
                    color = Color.Blue,
                    modifier = Modifier
                        .clickable {
                            showDialog = true
                        }
                )
            }

            Spacer(modifier = Modifier.padding(8.dp))

            Button(
                onClick = {
                    auth.signInWithEmailAndPassword(emailValue, passwordValue).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val toast = Toast.makeText(context,
                                "Đăng nhập thành công!",
                                Toast.LENGTH_SHORT)
                            toast.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, 0, 0)
                            toast.show()
                            val user = userViewModel.userAccounts.find { it.email == emailValue }
                            if (user!!.id == "") {
                                user.id = Firebase.auth.currentUser!!.uid
                                database.child(user.userName).child("id").setValue(user.id)
                            }
                            mainController.navigateUp()
                        } else {
                            val toast = Toast.makeText(context,
                                "Đăng nhập không thành công!",
                                Toast.LENGTH_SHORT)
                            toast.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, 0, 0)
                            toast.show()
                            emailValue = ""
                            passwordValue = ""
                        }
                    }
                    keyboardController?.hide()
                },
                enabled = emailValue != "" && passwordValue != "",
                shape = customShape,
                modifier = Modifier.fillMaxWidth(0.7f)
            ) {
                Text("Login")
            }

            Spacer(modifier = Modifier.padding(8.dp))

            Text(
                text = "Chưa có tài khoản? Đăng ký ngay",
                color = Color.Blue,
                modifier = Modifier.clickable {
                    mainController.navigate("register")
                }
            )

        }
    }

    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = {
                    Text(
                        text = "Quên mật khẩu",
                    )
                },
                text = {
                    LazyColumn (
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        item(1) {
                            OutlinedTextField (
                                value = emailValue,
                                onValueChange = { emailValue = it },
                                placeholder = {
                                    Row {
                                        Text("Nhập Email")
                                    }
                                },
                                label = { Text(text = "Email") },
                                leadingIcon = { Icon(Icons.Rounded.Email, contentDescription = null, tint = CardColor) },
                                shape = RoundedCornerShape(16.dp),
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Email,
                                    imeAction = ImeAction.Next
                                ),
                                keyboardActions = KeyboardActions(
                                    onNext = {
                                        passwordFocusRequester.requestFocus()
                                    }
                                ),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = CardColor,
                                    unfocusedBorderColor = CardColor
                                ),
                                modifier = Modifier
                                    .background(color = Color.Transparent)
                                    .fillMaxWidth()
                                    .focusRequester(emailFocusRequester)
                            )
                        }
                    }
                },
                confirmButton = {
                    Button(
                        onClick = {
                            if (emailValue != "") {
                                val toast = Toast.makeText(context,
                                    "Đã gửi mật khẩu về Email của bạn!",
                                    Toast.LENGTH_SHORT)
                                toast.setGravity(Gravity.TOP , 0, 0)
                                toast.show()
                                showDialog = false
                            } else {
                                val toast = Toast.makeText(context,
                                    "Vui lòng nhập Email",
                                    Toast.LENGTH_SHORT)
                                toast.setGravity(Gravity.TOP, 0, 0)
                                toast.show()
                            }

                        },
                        content = {
                            Text(text = "OK")
                        }
                    )
                },
                dismissButton = {
                    Button(
                        onClick = {
                            showDialog = false
                        },
                        content = {
                            Text(text = "Hủy")
                        }
                    )
                },
                modifier = Modifier
            )
        }
    }


}